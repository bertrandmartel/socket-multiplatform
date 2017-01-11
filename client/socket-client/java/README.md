# Java client socket Secure/Insecure


<b> Build/Connect a Java client socket</b>

Build an instance of client socket for later connection to server on HOSTNAME:PORT

``ClientSocket clientSocket = new ClientSocket(HOSTNAME, PORT);```

Add a client event listener to be notified when incoming http arrives from the server

```
clientSocket.addClientSocketEventListener(new IHttpClientListener() {

            @Override
            public void onIncomingHttpFrame(HttpFrame frame,
                    HttpStates httpStates, IClientSocket clientSocket) {

                //here test if http frame decoding is fine
                if (httpStates == HttpStates.HTTP_FRAME_OK && frame.isHttpResponseFrame()) {

                    //manage your response from the server or what you want

                }
            }
        });
```

You can write request to server with ``write(byte[] data)`` method :

```clientSocket.write("GET /say_hello HTTP/1.1\r\n\r\n".getBytes("UTF-8"));```

You can use http-endec library to build http frame request or response to be sent to the server : http://bertrandmartel.github.io/http-endec-java/

The ``write(byte[] data)`` method will automatically connect to the server and send your http frame data direclty. A reading thread will be created on the fly and response from the server will be received there and dispatch to your client event listener, you have declared above.

Depending on your server implementation, client connection will be closed immediatly after http frame processing from the server. Reading thread will die shortly when no data remains to be read from the server socket.

<hr/>

<b> Build/Connect a Java SSL client socket</b>

This is the same as above but we add some SSL flavor to this : 

Build an instance of client socket for later connection to server on HOSTNAME:PORT

```
ClientSocket clientSocket = new ClientSocket(HOSTNAME, PORT);

//set SSL encryption
clientSocket.setSsl(true);

```

Then you set your keystore, trustore, type of these certificates, filepath and passwords : 

```
clientSocket.setSSLParams(KEYSTORE_DEFAULT_TYPE, TRUSTORE_DEFAULT_TYPE,
        KEYSTORE_FILE_PATH, TRUSTORE_FILE_PATH, SSL_PROTOCOL,
        KEYSTORE_PASSWORD, TRUSTORE_PASSWORD);

```

Here is the description of all of these parameters : 

* KEYSTORE_DEFAULT_TYPE : type of certificates used as keystore, it usually contains public and private certificates, common format are PKCS12 and JKS
* TRUSTORE_DEFAULT_TYPE : type of certificates used as trustore, it should contain list of CA cert your client will trust
* KEYSTORE_FILE_PATH : file path to keystore cert file
* TRUSTORE_FILE_PATH: file path to trustore cert file
* SSL_PROTOCOL : ssl protocol used 
* KEYSTORE_PASSWORD : keystore file password
* TRUSTORE_PASSWORD : trustore file password

Later, when you will write http frames to your client socket, all your data will be encrypted according to your SSL settings

<hr/>


<b>Interact with external host</b>

To use with external host such as http://www.google.com or in a more secured way https://www.google.com this is exaxctly the same as before :


* Insecure connection

Usually port 80 is used, so to retrieve http://www.google.com page :

```
ClientSocket clientSocket = new ClientSocket("www.google.fr", 80);

clientSocket.addClientSocketEventListener(new IHttpClientListener() {

	@Override
	public void onIncomingHttpFrame(HttpFrame frame,
			HttpStates httpStates, IClientSocket clientSocket) {

		printHttpFrameDecodedResult(frame, httpStates);

	}
});

HashMap<String, String> headers  = new HashMap<String, String>();
headers.put("Host", "www.google.fr");

HttpFrame frame = new HttpFrame("GET", new HttpVersion(1, 1),
		headers, "http://www.google.fr/", new ListOfBytes(""));

clientSocket.write(frame.toString().getBytes());
```

In ``onIncomingHttpFrame`` , you will find response of your request. Here it will print all http frame information in ``printHttpFrameDecodedResult``
You can find more information in [java main class](src/fr/bmartel/network/ExternalHostTest.java)

* Secured connection

Usually port 443 is used, so to retrieve https://www.google.com page :

```
clientSocket = new ClientSocket("www.google.fr", 443);

// set SSL encryption
clientSocket.setSsl(true);

// set ssl parameters

clientSocket.setSSLParams(
				"JKS",
				"JKS",
				"",
				"/home/socket-multiplatform/client/socket-client/java/certs/trust.keystore",
				"TLS", "", "123456");

clientSocket.addClientSocketEventListener(new IHttpClientListener() {

	@Override
	public void onIncomingHttpFrame(HttpFrame frame,
			HttpStates httpStates, IClientSocket clientSocket) {

		printHttpFrameDecodedResult(frame, httpStates);

	}
});

headers = new HashMap<String, String>();
headers.put("Host", "www.google.fr");
 frame = new HttpFrame("GET", new HttpVersion(1, 1), headers,
		"https://www.google.fr/", new ListOfBytes(""));

clientSocket.write(frame.toString().getBytes());
```

Here you noticed that my Keystore is null and my trustore contains one keystore file.

This file ``trust.keystore`` is located in ``certs`` directory.

The following will explain how to generate it from root CA of www.google.fr page : 

* Go to https://www.google.fr and download root CA cert (export file) :

![udp communication](https://raw.github.com/bertrandmartel/socket-multiplatform/master/cert_google.png)

* Create a keystore which will store your CA : 

``keytool -v -genkey -v -keystore trust.keystore -alias yourTrustoreName -keyalg RSA``

We use this keystore as "trustore", it is designed to store all certificates that we "trust" so basically, put all your CA cert you want to trust in there.

* Convert you CA cert you have exported from browser to DER format : 

``openssl x509 -outform der -in GeoTrustGlobalCA.crt -out GeoTrustGlobalCA.der``

* Import your DER CA cert to trustore you have just created :

``keytool -import -alias google -keystore trust.keystore -file GeoTrustGlobalCA.der``


Thats it you have your trustore file ready. So you will have something like this for your cert parameter : 

```
clientSocket.setSSLParams(
	"JKS",
	"JKS",
	"",
	"/home/socket-multiplatform/client/socket-client/java/certs/trust.keystore",
	"TLS", "", "123456"
);
```
Replace "123456" with your trustore password and that's it

<b>Command line for external host testing</b>

```
cd ./client/socket-client/java/release

java -cp clientsocket-1.0.jar:../libs/http-endec-1.0.jar:../libs/libsocket-1.0.jar fr.bmartel.network.ExternalHostTest

```

<b>Command line for local server<-> client testing</b>

```
cd ./client/socket-client/java/release

java -cp clientsocket-1.0.jar:../libs/http-endec-1.0.jar:../libs/libsocket-1.0.jar fr.bmartel.network.LaunchClient

```

<b>Java HTTP client : Exemple with Java socket server <-> Java socket client</b>

This exemple is located in client/socket-client/no-ssl/java or client/socket-client/java

Launch your LaunchClient java exec, it will :
* open a server on 127.0.0.1:8443 (ssl or not)
* build a client socket to be connecte to latter server later
* ask you a set of command which will match HTTP request to be sent to this server. Each command is mapped in the server and a response is sent back to the client.

![client side](https://raw.github.com/bertrandmartel/socket-multiplatform/master/client_to_server_java.png)

<hr/>
