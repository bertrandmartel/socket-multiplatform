/**
    IWebsocketClient.h

    Define generic template for http client socket

    @author Bertrand Martel
    @version 1.0
*/
#ifndef IHTTPCLIENT_H
#define IHTTPCLIENT_H
#include "string"

class IHttpClient
{

public :
    /**
     * Send http message to client
     *
     * @param string
     * 		Message to be sent to client
     * @return
     *		0 if success -1 if error
     */
    virtual int sendHttpMessage(std::string message)= 0;
};

#endif // IHTTPCLIENT_H
