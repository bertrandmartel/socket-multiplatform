package fr.bmartel.protocol.udp;

import java.net.InetAddress;

/**
 * Generic interface for UDP server
 * 
 * @author Bertrand Martel
 *
 */
public interface IUdpServer {

	/**
	 * close udp server
	 */
	public void close();

	/**
	 * add server event listener to event list
	 * 
	 * @param listener
	 *            listener to be added to the list
	 */
	public void addServerEventListener(IUdpServerEventListener listener);

	/**
	 * set udp server buffer size
	 * 
	 * @param newSize
	 */
	public void setBuffersize(int newSize);

	/**
	 * write data to UDP socket
	 * 
	 * @param data
	 */
	public int write(byte[] data, InetAddress clientAddress, int clientPort);
}
