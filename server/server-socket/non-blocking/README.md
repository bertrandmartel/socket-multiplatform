# QT4 C++ Non blocking HTTP Server socket

<b>QT C++ Non Blocking HTTP server socket</b>

Instantiation and start : 

```
HttpServer server;

server.listen(QHostAddress(IP),PORT);

```

You can add socket event listener that notify you of incominh http request/response 

```
ClientSocketHandler *clientHandler = new ClientSocketHandler();

server.addClientEventListener(clientHandler);
```

``ClientSocketHandler`` inherit from interface ``IClientEventListener`` which contains following methods :
* ``void onHttpRequestReceived(IHttpClient &client,Ihttpframe* consumer);`` : notify when http request is received
* ``void onHttpResponseReceived(IHttpClient &client,Ihttpframe* consumer);`` : notify when http response is received

You can send http response or http request back to the client with ``IHttpClient`` got from previous callback.

 ``IHttpClient`` has a ``sendHttpMessage(std::string message)`` for sending http message back to client

<hr/>

<b>QT C++ non-blocking HTTP server : Exemple with Browser HTTP client</b>

This exemple is located in server/server-socket/non-blocking/no-ssl/cpp or server/server-socket/non-blocking/ssl/cpp

* Launch the HTTP server on port 8443
* On your browser go to url http://127.0.0.1:8443/index

![client side](https://raw.github.com/akinaru/socket-multiplatform/master/clientSideHttpCpp.png)

<hr/>

* valgrind memcheck

```

cd ./httpserver-test/release/

valgrind --tool=memcheck --leak-check=full  --suppressions=../../memcheck.suppress  ./httpserver-test

```