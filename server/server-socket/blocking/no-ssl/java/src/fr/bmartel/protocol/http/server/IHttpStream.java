package fr.bmartel.protocol.http.server;

/**
 * Interface for writing http data frame
 * 
 * @author Bertrand Martel
 *
 */
public interface IHttpStream {

	/**
	 * Write http request frame
	 * 
	 * @param httpFrame
	 *            request frame
	 * @return 0 if OK -1 if error
	 */
	public int writeHttpFrame(byte[] data);

}
