package certTester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

public class TestClient {


	public static boolean testClient(File keystore, File truststore,
			String host, int port, char[] password, boolean verbose) throws Exception {
		System.out.println("Client info:");
		System.out.println("key:\t" + keystore);
		System.out.println("trust:\t" + truststore);
		
			SSLSocketFactory factory = null;
				KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
				KeyManagerFactory kmf = KeyManagerFactory
						.getInstance("SunX509");
				TrustManagerFactory tmf = TrustManagerFactory
						.getInstance("SunX509");
				SSLContext ctx = SSLContext.getInstance("TLS");
				ks.load(new FileInputStream(keystore), password);
				ts.load(new FileInputStream(truststore), password);
				kmf.init(ks, password);
				tmf.init(ts);
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
				factory = ctx.getSocketFactory();
			SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
			System.out.println("\nsocket before handshake:\n" + socket + "\n");

			socket.startHandshake();

			SSLSession session = socket.getSession();
			X509Certificate cert = (X509Certificate) session
					.getPeerCertificateChain()[0];
			String subject = cert.getSubjectDN().getName();
			String issuer = cert.getIssuerDN().getName();
			String serial = cert.getSerialNumber().toString();
			System.out
					.printf("certificate name (subject DN field) on certificate received from server:%n%s%n%s%n%s%n",
							subject, issuer, serial);
			System.out.println("socket after handshake:\n" + socket + "\n");
			System.out.println("secure connection established\n\n");

			BufferedReader read = new BufferedReader(new InputStreamReader(
					System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			in.close();
			out.close();
			read.close();
			socket.close();

			return true;

	}
}
