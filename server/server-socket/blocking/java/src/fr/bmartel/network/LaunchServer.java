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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import fr.bmartel.protocol.http.HttpResponseFrame;
import fr.bmartel.protocol.http.HttpVersion;
import fr.bmartel.protocol.http.constants.StatusCodeList;
import fr.bmartel.protocol.http.inter.IHttpFrame;
import fr.bmartel.protocol.http.listeners.IHttpServerEventListener;
import fr.bmartel.protocol.http.server.HttpServer;
import fr.bmartel.protocol.http.server.IHttpStream;
import fr.bmartel.protocol.http.states.HttpStates;
import fr.bmartel.protocol.http.utils.StringUtils;

/**
 * Http Server launcher
 * 
 * @author Bertrand Martel
 *
 */
public class LaunchServer {

	private static int PORT = 8443;

	private static int PAGE_VIEW_COUNT = 0;

	private final static String KEYSTORE_DEFAULT_TYPE = "PKCS12";
	private final static String TRUSTORE_DEFAULT_TYPE = "JKS";
	private final static String KEYSTORE_FILE_PATH = "~/socket-multiplatform/server/server-socket/blocking/ssl/java/certs/server/server.p12";
	private final static String TRUSTORE_FILE_PATH = "~/socket-multiplatform/server/server-socket/blocking/ssl/java/certs/ca.jks";
	private final static String SSL_PROTOCOL = "TLS";
	private final static String KEYSTORE_PASSWORD = "123456";
	private final static String TRUSTORE_PASSWORD = "123456";

	public static void main(String[] args) {

		if (args.length > 0) {
			// see if arg[0] is int if not choose port 8443
			if (StringUtils.isInteger(args[0])) {
				PORT = Integer.parseInt(args[0]);
			}
		}

		// initiate HTTP server
		HttpServer server = new HttpServer(PORT);

		// set ssl encryption
		server.setSsl(true);

		// set ssl parameters
		server.setSSLParams(KEYSTORE_DEFAULT_TYPE, TRUSTORE_DEFAULT_TYPE,
				KEYSTORE_FILE_PATH, TRUSTORE_FILE_PATH, SSL_PROTOCOL,
				KEYSTORE_PASSWORD, TRUSTORE_PASSWORD);

		server.addServerEventListener(new IHttpServerEventListener() {

			@Override
			public void onHttpFrameReceived(IHttpFrame httpFrame,
					HttpStates receptionStates, IHttpStream httpStream) {
				System.out.println("Received http frame");

				if (receptionStates == HttpStates.HTTP_FRAME_OK) {
					if (httpFrame.isHttpRequestFrame()) {
						if (httpFrame.getMethod().equals("GET")
								&& httpFrame.getUri().equals("/index")) {

							HashMap<String, String> headers = new HashMap<String, String>();

							String defaultPage = "Hello from custom Java HTTP Server\r\nThis page has been seen "
									+ PAGE_VIEW_COUNT + " times before.";

							// return default html page for this HTTP Server
							httpStream.writeHttpFrame(new HttpResponseFrame(
									StatusCodeList.OK, new HttpVersion(1, 1),
									headers, defaultPage.getBytes()).toString()
									.getBytes());
							PAGE_VIEW_COUNT++;
						}
					}
				}
			}

		});

		server.start(); // start HTTP server => this method will block

	}

	/**
	 * Read all bytes from file
	 * 
	 * @param path
	 *            file path
	 */
	static byte[] readFile(String path) {
		Path path2 = Paths.get(path);

		byte[] data = new byte[] {};

		try {
			data = Files.readAllBytes(path2);
		} catch (IOException e) {
			System.err.println("Error file path is incorrect");
		}

		return data;
	}
}
