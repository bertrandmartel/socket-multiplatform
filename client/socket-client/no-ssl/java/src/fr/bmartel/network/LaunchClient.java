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
package fr.bmartel.network;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

import fr.bmartel.protocol.http.ClientSocket;
import fr.bmartel.protocol.http.HttpFrame;
import fr.bmartel.protocol.http.HttpResponseFrame;
import fr.bmartel.protocol.http.HttpVersion;
import fr.bmartel.protocol.http.IClientSocket;
import fr.bmartel.protocol.http.IHttpClientListener;
import fr.bmartel.protocol.http.constants.StatusCodeList;
import fr.bmartel.protocol.http.inter.IHttpFrame;
import fr.bmartel.protocol.http.listeners.IHttpServerEventListener;
import fr.bmartel.protocol.http.server.HttpServer;
import fr.bmartel.protocol.http.server.IHttpStream;
import fr.bmartel.protocol.http.states.HttpStates;

/**
 * Launch java HTTP client socket
 * 
 * @author Bertrand Martel
 *
 */
public class LaunchClient {

	/**
	 * server hostname
	 */
	private final static String HOSTNAME = "127.0.0.1";

	/**
	 * server port
	 */
	private final static int PORT = 8443;

	/**
	 * http request command list to be sent to server and client will be
	 * awaiting response from these
	 */
	private final static HashMap<String, String> HTTP_REQUEST_LIST = new HashMap<String, String>();

	/**
	 * http server used to connect our http client to it
	 */
	private static HttpServer serverTest = null;

	static {
		HTTP_REQUEST_LIST.put("HELLO", "GET /say_hello HTTP/1.1\r\n\r\n");
		HTTP_REQUEST_LIST.put("GOODBYE", "GET /say_goodbye HTTP/1.1\r\n\r\n");
		HTTP_REQUEST_LIST.put("T_T", "GET /cry HTTP/1.1\r\n\r\n");
	}

	private final static String KEYSTORE_DEFAULT_TYPE = "PKCS12";
	private final static String TRUSTORE_DEFAULT_TYPE = "JKS";
	private final static String KEYSTORE_FILE_PATH = "/home/abathur/Bureau/open_source/socket-multiplatform/client/socket-client/ssl/java/certs/server/server.p12";
	private final static String TRUSTORE_FILE_PATH = "/home/abathur/Bureau/open_source/socket-multiplatform/client/socket-client/ssl/java/certs/ca.jks";
	private final static String SSL_PROTOCOL = "TLS";
	private final static String KEYSTORE_PASSWORD = "123456";
	private final static String TRUSTORE_PASSWORD = "123456";

	/**
	 * launch a testing server and build a client socket we can connect to and
	 * write some request to the server
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// start testing server
		startTestServer(PORT);

		// new instance of client socket
		ClientSocket clientSocket = new ClientSocket(HOSTNAME, PORT);

		// set SSL encryption
		//clientSocket.setSsl(true);

		// set ssl parameters
		/*
		clientSocket.setSSLParams(KEYSTORE_DEFAULT_TYPE, TRUSTORE_DEFAULT_TYPE,
				KEYSTORE_FILE_PATH, TRUSTORE_FILE_PATH, SSL_PROTOCOL,
				KEYSTORE_PASSWORD, TRUSTORE_PASSWORD);
		*/
		
		// add a client event listener to be notified for incoming http frames
		clientSocket.addClientSocketEventListener(new IHttpClientListener() {

			@Override
			public void onIncomingHttpFrame(HttpFrame frame,
					HttpStates httpStates, IClientSocket clientSocket) {
				if (httpStates == HttpStates.HTTP_FRAME_OK
						&& frame.isHttpResponseFrame()) {

					// this is data coming from the server
					System.out.print("server > ");
					try {
						System.out.println(new String(frame.getBody()
								.getBytes(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// you can choose here which command to send to the server
		Scanner scan = new Scanner(System.in);

		System.out.println("------------------------------------------------");
		System.out.println("Started HTTP chat with server " + HOSTNAME + ":"
				+ PORT);
		System.out.println("------------------------------------------------");
		System.out.println("List of chat command :");
		System.out.println("HELLO");
		System.out.println("GOODBYE");
		System.out.println("T_T");
		System.out.println("EXIT");
		System.out.println("------------------------------------------------");

		String command = "";

		while (!command.equals("EXIT")) {

			command = scan.nextLine();

			try {
				switch (command) {
				case "HELLO":
					clientSocket.write(HTTP_REQUEST_LIST.get("HELLO").getBytes(
							"UTF-8"));
					break;
				case "GOODBYE":
					clientSocket.write(HTTP_REQUEST_LIST.get("GOODBYE")
							.getBytes("UTF-8"));
					break;
				case "T_T":
					clientSocket.write(HTTP_REQUEST_LIST.get("T_T").getBytes(
							"UTF-8"));
					break;
				case "EXIT":
					break;
				default:
					System.out.println("Unknown command");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Exiting HTTP chat ...");

		// socket will be closed and reading thread will die if it exists
		clientSocket.closeSocketJoinRead();

		// clean event listener list
		clientSocket.cleanEventListeners();

		// close server
		if (serverTest != null)
			serverTest.closeServer();

	}

	/**
	 * This will start a server based on Server socket
	 * 
	 * For more details about its launch / implementation see
	 * https://github.com/akinaru/socket-multiplatform
	 * 
	 * @param port
	 *            server port
	 */
	public static void startTestServer(int port) {

		// start a new http server to test our http client
		serverTest = new HttpServer(port);

		//serverTest.setSsl(true);

		// set ssl parameters
		/*
		serverTest.setSSLParams(KEYSTORE_DEFAULT_TYPE, TRUSTORE_DEFAULT_TYPE,
				KEYSTORE_FILE_PATH, TRUSTORE_FILE_PATH, SSL_PROTOCOL,
				KEYSTORE_PASSWORD, TRUSTORE_PASSWORD);
		*/
		
		// get frames coming from client socket
		// you can put any message you want to notify client here
		serverTest.addServerEventListener(new IHttpServerEventListener() {

			@Override
			public void onHttpFrameReceived(IHttpFrame frame,
					HttpStates states, IHttpStream stream) {
				if (frame.isHttpRequestFrame()
						&& frame.getMethod().equals("GET")
						&& frame.getUri().equals("/say_hello")) {
					stream.writeHttpFrame(new HttpResponseFrame(
							StatusCodeList.OK, new HttpVersion(1, 1),
							new HashMap<String, String>(),
							"Hello client, I'm the server".getBytes())
							.toString().getBytes());
				} else if (frame.isHttpRequestFrame()
						&& frame.getMethod().equals("GET")
						&& frame.getUri().equals("/say_goodbye")) {
					stream.writeHttpFrame(new HttpResponseFrame(
							StatusCodeList.OK, new HttpVersion(1, 1),
							new HashMap<String, String>(),
							"Goodbye client, See you".getBytes()).toString()
							.getBytes());
				} else if (frame.isHttpRequestFrame()
						&& frame.getMethod().equals("GET")
						&& frame.getUri().equals("/cry")) {
					stream.writeHttpFrame(new HttpResponseFrame(
							StatusCodeList.OK, new HttpVersion(1, 1),
							new HashMap<String, String>(),
							"Don't cry ! We'll connect again later !"
									.getBytes()).toString().getBytes());
				} else {
					stream.writeHttpFrame(new HttpResponseFrame(
							StatusCodeList.OK, new HttpVersion(1, 1),
							new HashMap<String, String>(),
							"I dont undestand what you say".getBytes())
							.toString().getBytes());
				}
			}
		});

		// start server in another thread not to block socket client
		// interactions
		Thread serverThread = new Thread(new Runnable() {

			@Override
			public void run() {
				serverTest.start();
			}
		});
		serverThread.start();
	}
}
