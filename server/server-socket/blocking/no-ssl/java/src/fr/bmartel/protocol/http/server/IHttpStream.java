package fr.bmartel.protocol.http.server;

import fr.bmartel.protocol.http.inter.IHttpFrame;
import fr.bmartel.protocol.http.inter.IHttpResponseFrame;

/**
 * Interface for writing http data frame
 * 
 * @author Bertrand Martel
 *
 */
public interface IHttpStream {

	/**
	 * Write http response frame
	 * 
	 * @param httpFrame
	 *            response frame
	 * @return 0 if OK -1 if error
	 */
	public int writeHttpResponseFrame(IHttpResponseFrame httpFrame);

	/**
	 * Write http request frame
	 * 
	 * @param httpFrame
	 *            request frame
	 * @return 0 if OK -1 if error
	 */
	public int writeHttpRequestFrame(IHttpFrame httpFrame);

}
