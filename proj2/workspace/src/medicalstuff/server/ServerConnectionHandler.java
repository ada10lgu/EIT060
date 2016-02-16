package medicalstuff.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class ServerConnectionHandler extends Thread {

	private ServerSocket ss;

	public ServerConnectionHandler(char[] password, int port) {

		String type = "TLS";
		try {
			ServerSocketFactory ssf = getServerSocketFactory(type);
			ss = ssf.createServerSocket(port);
			((SSLServerSocket) ss).setNeedClientAuth(true); // enables client
															// authentication
			start();
		} catch (IOException e) {
			System.out.println("Unable to start Server: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private ServerSocketFactory getServerSocketFactory(String type) {
		if (type.equals("TLS")) {
			SSLServerSocketFactory ssf = null;
			try { // set up key manager to perform server authentication
				SSLContext ctx = SSLContext.getInstance("TLS");
				KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
				TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
				KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
				char[] password = "password".toCharArray();

				ks.load(new FileInputStream("serverkeystore"), password); // keystore
																			// password
																			// (storepass)
				ts.load(new FileInputStream("servertruststore"), password); // truststore
																			// password
																			// (storepass)
				kmf.init(ks, password); // certificate password (keypass)
				tmf.init(ts); // possible to use keystore as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
				ssf = ctx.getServerSocketFactory();
				return ssf;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return ServerSocketFactory.getDefault();
		}
		return null;
	}

	@Override
	public void run() {
		System.out.println("Server started on port " + ss.getLocalPort());
		while (true) {
			System.out.println("Waiting for connection");
			try {
				SSLSocket socket = (SSLSocket) ss.accept();
				new ServerConnection(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
