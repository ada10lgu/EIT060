package certTester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class TestServer extends Thread {

	int port;
	File keystore;
	File truststore;
	char[] password;

	public void run() {
		try {
			System.out.println("Server info:");
			System.out.println("key:\t" + keystore);
			System.out.println("trust:\t" + truststore);
			
			ServerSocketFactory ssf = getServerSocketFactory();
			ServerSocket ss = ssf.createServerSocket(port);
			((SSLServerSocket) ss).setNeedClientAuth(true); // enables client
															// authentication

			SSLSocket socket = (SSLSocket) ss.accept();
			// SSLSession session = socket.getSession();
			// X509Certificate cert = (X509Certificate) session
			// .getPeerCertificateChain()[0];
			// String subject = cert.getSubjectDN().getName();
			// String issuer = cert.getIssuerDN().getName();
			// String serial = cert.getSerialNumber().toString();

			PrintWriter out = null;
			BufferedReader in = null;
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String clientMsg = null;
			while ((clientMsg = in.readLine()) != null) {
				String rev = new StringBuilder(clientMsg).reverse().toString();
				System.out.println("received '" + clientMsg + "' from client");
				System.out.print("sending '" + rev + "' to client...");
				out.println(rev);
				out.flush();
				System.out.println("done\n");
			}
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Client died: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private ServerSocketFactory getServerSocketFactory() {
		SSLServerSocketFactory ssf = null;
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance("SunX509");
			KeyStore ks = KeyStore.getInstance("JKS");
			KeyStore ts = KeyStore.getInstance("JKS");

			ks.load(new FileInputStream(keystore), password);
			ts.load(new FileInputStream(truststore), password);
			kmf.init(ks, password);
			tmf.init(ts);
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			ssf = ctx.getServerSocketFactory();
			return ssf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TestServer(File keystore, File truststore, char[] password, int port) throws Exception {
		this.port = port;
		this.keystore = keystore;
		this.truststore = truststore;
		this.password = password;
		start();
	}
}
