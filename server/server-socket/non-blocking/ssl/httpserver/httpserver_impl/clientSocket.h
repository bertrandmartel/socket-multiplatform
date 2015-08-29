/**
    clientsocket.h

    http client object featuring one physical http connection

    @author Bertrand Martel
    @version 1.0
*/
#ifndef CLIENTSOCKET_H
#define CLIENTSOCKET_H

#include <QByteArray>
#include <QTcpSocket>
#include <QList>
#include "httpserver_inter/IHttpClient.h"

class ClientSocket : public IHttpClient
{

public:

    /**
     * @brief ClientSocket::ClientSocket
     *      Build one client
     */
    ClientSocket();


    ~ClientSocket();

    /**
     * @brief getPeerAddress
     *      Retrieve client ip address
     * @return
     */
    std::string getPeerAddress();

    /**
     *  close websoclet client object
     *
     * @return
     * 		0 if success -1 if error
     */
    int close();

    /**
     * Send a message to HTTP client
     *
     * @param string
     * 		Message to be sent to client
     * @return
     *		0 if success -1 if error
     */
    int sendHttpMessage(std::string message);

    /**
     * @brief setSocketClient
     *      Define client socket for this object
     * @param clientSocket
     *      client socket
     */
    void setSocketClient(QTcpSocket * clientSocket);

private:

    /**
     * @brief clientSocket
     *      client socket
     */
    QTcpSocket* clientSocket;
};

#endif // CLIENTSOCKET_H
