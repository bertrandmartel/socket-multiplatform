# Multi-platform Socket connections


This project will features socket connection implementation on multiple platforms and frameworks :

<table>
    <tr>
        <td colspan="6"></td>
        <td colspan="2"><b>Java</b></td>
        <td colspan="2"><b>JS browser</b></td>
        <td colspan="2"><b>C++ QT4</b></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="4">TCP Server socket</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2" rowspan="8"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="4">Server websocket</td>
        <td colspan="2" rowspan="2">blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2" rowspan="2">non-blocking</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">TCP Client socket</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2" rowspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="4" rowspan="2">Client websocket</td>
        <td colspan="2">no ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">ssl</td>
        <td colspan="2"></td>
        <td colspan="2"><img src="./OK.png"/></td>
        <td colspan="2"></td>
    </tr>
</table>

List of external projects :

* Websocket C++ QT4 :  http://akinaru.github.io/websocket-non-blocking-cpp/

* Websocket JAVA    :  http://akinaru.github.io/websocket-java/

<hr/>

<b>How to launch Java HTTP Server socket</b>

```
HttpServer server = new HttpServer(PORT);

server.start();

```

<b>How to monitor my clients connected to server ?</b>

Just add a Listener to server object. You have 1 callback that enables you to :

* To get current http frame object received (decoded http frame)

* To get http decoding frame status (in case of parsing error, reading error or socket error)

* To write Http request or response back to current client

```
server.addServerEventListener(new IHttpServerEventListener() {

            @Override
            public void onHttpFrameReceived(IHttpFrame httpFrame,HttpStates receptionStates, IHttpStream httpStream) {

                //check if http frame is OK
                if (receptionStates == HttpStates.HTTP_FRAME_OK) {

                    //you can check here http frame type (response or request frame)
                    if (httpFrame.isHttpRequestFrame()) {

                        //we want to send a message to client for http GET request on page with uri /index
                        if (httpFrame.getMethod().equals("GET") && httpFrame.getUri().equals("/index")) {

                            HashMap<String, String> headers = new HashMap<String, String>();

                            String defaultPage = "Hello from custom Java HTTP Server\r\nThis page has been seen "
                                    + PAGE_VIEW_COUNT + " times before.";

                            // return default html page for this HTTP Server
                            httpStream.writeHttpResponseFrame(new HttpResponseFrame(
                                            StatusCodeList.OK, new HttpVersion(1, 1), headers, defaultPage.getBytes()));
                            PAGE_VIEW_COUNT++;
                        }
                    }
                }
            }
        });
```
Featured exemple display a message in client's browser when uri /index is called. When refreshing page counter will be incremented on displayed page.

``IHttpFrame`` has following useful methods :
* boolean isHttpRequestFrame() 
* boolean isHttpResponseFrame()
* String getMethod()
* String getUri()
* HashMap<String, String> getHeaders()
* int getStatusCode()
* String getReasonPhrase()
* HttpVersion getHttpVersion()

``HttpStates`` enum can have following states : 
* MALFORMED_HTTP_FRAME
* HTTP_FRAME_OK
* HTTP_READING_ERROR
* HTTP_WRONG_VERSION
* HTTP_STATE_NONE
* SOCKET_ERROR
* HTTP_BODY_PARSE_ERROR

``IHttpStream`` enables you to answered to http client with : 
* writeHttpResponseFrame(IHttpResponseFrame httpFrame)
* writeHttpRequestFrame(IHttpFrame httpFrame)

``IHttpResponseFrame`` and ``IHttpFrame`` are interface issued from http-endec-java project in http://akinaru.github.io/http-endec-java/

<hr/>

<b>How to launch a SSL secured HTTP server ?</b>

```
HttpServer server = new HttpServer(PORT);

server.setSsl(true); // set SSL to true (default is false)

```

Then you set your kestore, trustore, type of these certificates, filepath and passwords : 

```
server.setSSLParams(String KEYSTORE_DEFAULT_TYPE,
            String KEYSTORE_FILE_PATH,
            String TRUSTORE_FILE_PATH,
            String SSL_PROTOCOL,
            String KEYSTORE_PASSWORD,
            String TRUSTORE_PASSWORD);
```

Here is the description of all of these parameters : 

* KEYSTORE_DEFAULT_TYPE : type of certificates used as keystore, it usually contains public and private certificates, common format are PKCS12 and JKS
* TRUSTORE_DEFAULT_TYPE : type of certificates used as trustore, it should contain list of CA cert your server will trust
* KEYSTORE_FILE_PATH : file path to keystore cert file
* TRUSTORE_FILE_PATH: file path to trustore cert file
* SSL_PROTOCOL : ssl protocol used 
* KEYSTORE_PASSWORD : keystore file password
* TRUSTORE_PASSWORD : trustore file password

Eventually add event listener as described above and start HTTP server : 

```
server.start();
```

<hr/>

<b>Keystore : public and private server certificates</b>

* To convert cert and key certs to p12 : 

``openssl pkcs12 -export -out server.p12 -inkey server.key -in server.crt``

Thus, you will have : ``String KEYSTORE_DEFAULT_TYPE = "PKCS12"``

* To convert your p12 (containing public and private cert) to jks : 

You have to know your alias (name matching your cert entry), if you dont have it retrieve it with : ``keytool -v -list -storetype pkcs12 -keystore server.p12``

``keytool -importkeystore -srckeystore server.p12 -srcstoretype PKCS12 -deststoretype JKS -destkeystore server.jks``

Thus, you will have : ``String KEYSTORE_DEFAULT_TYPE = "JKS"``

<b>Trustore : should contain only CA certificates</b>

convert ca cert to jks : 

```keytool -import -alias ca -file ca.crt -keystore cacerts.jks -storepass 123456```

Thus, you will have : ``String TRUSTORE_DEFAULT_TYPE = "JKS"``

<hr/>


<b>TroubleShooting</b>

<i>Bad certificate | Unknown CA errors</i>

This could mean you didn't import your not-trusted-CA certificate into your browser.

<i>The remote host closed the connection</i>

Usually happen when your browser closed the connection before the end of SSL handshake. If you already added your CA to your browser dont worry.
Both Chrome and Firefox need to MANUALLY add the certificate (in a popup) so putting it in parameter menu don't change anything.

Just load your URL with "https" : https://127.0.0.1:8443 . Browser will prompt you to accept the certificates and it will probably solve your connection error.

<i>CKR_DOMAIN_PARAMS_INVALID error using openjdk</i>

With openjdk-6-jdk and openjdk-7-jdk, I encountered java.security bug described in https://bugs.launchpad.net/ubuntu/+source/openjdk-7/+bug/1006776 triggering a ``CKR_DOMAIN_PARAMS_INVALID`` exception error. Solution was to edit java.security parameters in /etc/java-7-openjdk/security/java.security 

I replaced that : 
```
security.provider.9=sun.security.smartcardio.SunPCSC
security.provider.10=sun.security.pkcs11.SunPKCS11 ${java.home}/lib/security/nss.cfg
```

with that : 
```
#security.provider.9=sun.security.smartcardio.SunPCSC
security.provider.9=sun.security.ec.SunEC
#security.provider.10=sun.security.pkcs11.SunPKCS11 ${java.home}/lib/security/nss.cfg
```

<b>Browser tested</b>

This has been tested on following browser : 
* Chrome
* Chromium
* Firefox

<hr/>

<b>Debugging SSL connection error</b>

I recommmend using openssl command line tool to debug ssl connection : 

``openssl s_client -connect 127.0.0.1:8443``

You can also use vm argument debugging java ssl implementation : ``-Djavax.net.debug=ssl``

<hr/>

<b>Server-Client key/cert generation</b>

Certs are in libwesocket-test/certs folder, you will find server,client and ca cert build with easy-rsa :

https://github.com/OpenVPN/easy-rsa

With last release of easy-rsa, you can build your own key with the following : 

* ``./build-ca`` : generate a new CA for you
* ``./build-server-full myServer`` : will build for you public cert and private cert signed with CA for server
* ``./build-client-full myClient`` : will build for you public cert and private cert signed with CA for client

<b>How to close my HTTP server ?</b>

``server.closeServer();``

<hr/>

<b>COMMAND LINE SYNTAX</b> 

The following will open http server on port 4343 (default port value for my exemple)

``java -cp ../libs/http-endec-1.0.jar:libsocket-1.0.jar fr.bmartel.network.LaunchServer``

You can change port number by specifying yours

``java -cp ../libs/http-endec-1.0.jar:libsocket-1.0.jar fr.bmartel.network.LaunchServer 4343``

This exemple is launched from /release folder

<hr/>

<b>Exemple with Browser HTTP client</b>

* Launch the HTTP server on port 8443
* On your browser go to url http://127.0.0.1:8443/index

![client side](https://raw.github.com/akinaru/socket-multiplatform/master/server/server-socket/non-blocking/no-ssl/java/readme_images/clientSide.png)

<hr/>

* Project is JRE 1.7 compliant
* You can build it with ant => build.xml
* Development on Eclipse 
