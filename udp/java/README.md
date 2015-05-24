# Java UDP Server/Client socket


<b>Java UDP Server/Client</b>

UDP Server and client are located in udp folder. One exemple features interactions between UDP server and UDP client in JAVA.

The following will start an UDP socket (running as server) on port defined by PORT : 

```
UdpServer udpServer = new UdpServer(PORT);

//add a server event listener
udpServer.addServerEventListener(new IUdpServerEventListener() {

    @Override
    public void onDataReceived(byte[] data, IUdpServer server,
            InetAddress clientAddress, int clientPort) {
        
        String dataStr = new String(data);

        System.out
                .println("[UDP SERVER] new data receveived in server : "
                        + dataStr);
    }
});

// UDP server is runnable : just run it into a thread ( naming the thread is recommended for debuggging )
Thread serverThread = new Thread(udpServer,"UDP_SERVER");

// start udp server
serverThread.start();

```

UDP client socket will broadcast data to this server : 

```
UdpClient udpClient = new UdpClient(InetAddress.getByName(SERVER_BROADCAST_IP), PORT);

udpClient.addClientEventListener(new IudpEventListener() {

    @Override
    public void onDataReceived(byte[] data) {
        System.out
                .println("[UDP CLIENT] new data receveived in client : "
                        + new String(data));
    }
});
```

To send a string message use : ``udpClient.sendMessage(String message);`` method : 

```
udpClient.sendMessage("HELLO");
```

Mecanism of event listener is exactly the same as explained before for client socket in Java or for Java websocket lib (http://akinaru.github.io/websocket-java/).

``onDataReceived(byte[] data)`` for UDP client callback will enable you to get incoming data response from UDP server.

``onDataReceived(byte[] data, IUdpServer server,InetAddress clientAddress, int clientPort) for UDP server callback will enable you to get incoming data from the client and to redispatch any response you want to this client thanks to ``IUdpServer`` object that permits you to write a ``byte[]`` data like this :

``
server.write("Hello from websocket server".getBytes(),clientAddress, clientPort);
``

With address and port, UDP server will send your data back to client.

For now, I didn't put in place a timer mecanisme on client side, to close client receiving thread. 
TODO : timeout for client UDP socket waiting for response

<hr/>