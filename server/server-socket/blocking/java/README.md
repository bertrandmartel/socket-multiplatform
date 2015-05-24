# Jave HTTP Server socket Secured/Insecured


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

Then you set your keystore, trustore, type of these certificates, filepath and passwords : 

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

<b>How to close my HTTP server ?</b>

``server.closeServer();``

<hr/>

<b>Java HTTP server : Exemple with Browser HTTP client</b>

This exemple is located in server/server-socket/blocking/no-ssl/java or server/server-socket/blocking/ssl/java

* Launch the HTTP server on port 8443
* On your browser go to url http://127.0.0.1:8443/index

![client side](https://raw.github.com/akinaru/socket-multiplatform/master/clientSide.png)

<b>COMMAND LINE SYNTAX</b> 

The following will open http server on port 4343 (default port value for my exemple)

``java -cp ../libs/http-endec-1.0.jar:libsocket-1.0.jar fr.bmartel.network.LaunchServer``

You can change port number by specifying yours

``java -cp ../libs/http-endec-1.0.jar:libsocket-1.0.jar fr.bmartel.network.LaunchServer 4343``

This exemple is launched from /release folder
