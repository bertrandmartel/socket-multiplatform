/**
    clientsocket.cpp

    http client object featuring one physical http connection

    @author Bertrand Martel
    @version 1.0
*/
#include "clientSocket.h"

#include <QTcpSocket>
#include <iostream>

using namespace std;

/**
 * @brief ClientSocketObj::ClientSocketObj
 *      Build one client
 */
ClientSocket::ClientSocket()
{
    clientSocket=0;
}

/**
 * @brief ClientSocket::~ClientSocket
 *      clean pointers
 */
ClientSocket::~ClientSocket()
{
    //client socket should NOT be deleted here => connection MUST be deleted in HttpServer class
}

/**
 *  close websoclet client object
 *
 * @return
 * 		0 if success -1 if error
 */
int ClientSocket::close()
{
    clientSocket->close();

    return 0;
}

/**
 * Send a message to HTTP client
 *
 * @param string
 * 		HTTP Message to be sent to client
 * @return
 *		0 if success -1 if error
 */
int ClientSocket::sendHttpMessage(std::string message)
{
    clientSocket->write(message.data());
    clientSocket->flush();

    return 0;
}

/**
 * @brief setSocketClient
 *      set socket client socket
 * @param clientSocket
 */
void ClientSocket::setSocketClient(QTcpSocket * clientSocket)
{
    this->clientSocket=clientSocket;
}
