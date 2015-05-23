/**
    IClientEventListener.h

    Define all client socket event that can happen and that will pop event in class that will inherit this one

    @author Bertrand Martel
    @version 1.0
*/
#ifndef ICLIENTEVENTLISTENER_H
#define ICLIENTEVENTLISTENER_H

#include "IHttpClient.h"
#include "string"
#include "protocol/inter/http/IhttpFrame.h"

/**
 * @brief The IClientEventListener class
 *      Client Socket event listener template class
 */
class IClientEventListener
{

public :

    /**
     * called when an http request has been received from client
     *
     * @param client
     * 		client object
     * @param message
     * 		message delivered
     */
    virtual void onHttpRequestReceived(IHttpClient &client,Ihttpframe* consumer)= 0;

    /**
     * called when an http response has been received from client
     *
     * @param client
     * 		client object
     * @param message
     * 		message delivered
     */
    virtual void onHttpResponseReceived(IHttpClient &client,Ihttpframe* consumer)= 0;

};


#endif // ICLIENTEVENTLISTENER_H
