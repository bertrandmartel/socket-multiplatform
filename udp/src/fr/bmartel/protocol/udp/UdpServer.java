/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Bertrand Martel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.bmartel.protocol.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * UDP server used to wait for incoming message on a specific UDP port
 * 
 * @author Bertrand Martel
 *
 */
public class UdpServer implements Runnable, IUdpServer {

	/**
	 * buffer size for expecting message
	 */
	private int bufferSize = 4096;

	/**
	 * list of server event listener to notify those whose submitted them
	 */
	private ArrayList<IUdpServerEventListener> udpServerEventListener = new ArrayList<IUdpServerEventListener>();

	/**
	 * listen to udp message while activate is true
	 */
	private volatile boolean activate = true;

	/**
	 * udp server port
	 */
	private int serverPort = 0;

	private DatagramSocket socket = null;

	/**
	 * Build UDP server
	 *
	 * @param serverPort
	 *            udp server port
	 */
	public UdpServer(int serverPort) {
		this.serverPort = serverPort;
	}

	@Override
	public void run() {

		byte[] message = new byte[bufferSize];

		DatagramPacket datagramePacket = new DatagramPacket(message,
				message.length);

		socket = null;

		try {
			System.out.println("Started UDP server ...");

			socket = new DatagramSocket(serverPort);

			while (activate) {

				// block until one message is received
				socket.receive(datagramePacket);

				byte[] data = Arrays.copyOfRange(message, 0,
						datagramePacket.getLength());

				if (data != null) {
					for (int i = 0; i < udpServerEventListener.size(); i++) {
						udpServerEventListener.get(i).onDataReceived(data,
								UdpServer.this, datagramePacket.getAddress(),
								datagramePacket.getPort());
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("UDP server is closing...");
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}

	/**
	 * add server event listener to event list
	 * 
	 * @param listener
	 *            listener to be added to the list
	 */
	public void addServerEventListener(IUdpServerEventListener listener) {
		udpServerEventListener.add(listener);
	}

	/**
	 * stop UDP server
	 */
	@Override
	public void close() {
		activate = false;
		if (socket != null) {
			socket.close();
		}
	}

	@Override
	public void setBuffersize(int newSize) {
		bufferSize = newSize;
	}

	@Override
	public int write(byte[] data, InetAddress clientAddress, int clientPort) {
		DatagramPacket packet;

		// create packet to be sent to server
		try {
			packet = new DatagramPacket(data, data.length, clientAddress,
					clientPort);
			// broadcast data
			socket.setBroadcast(true);
			socket.send(packet);
			return 0;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
