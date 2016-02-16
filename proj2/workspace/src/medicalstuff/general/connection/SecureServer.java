package medicalstuff.general.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class SecureServer extends Server {

	public SecureServer(int port, ConnectionHandler ch, File keystore, File truststore, char[] password)
			throws IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException {
		super(getSecureServer(port, keystore, truststore, password), ch);
	}

	private static ServerSocket getSecureServer(int port, File keystore, File truststore, char[] password)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException,
			IOException, KeyManagementException, UnrecoverableKeyException {
		SSLServerSocketFactory ssf = null;
		SSLContext ctx = SSLContext.getInstance("TLS");
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		KeyStore ks = KeyStore.getInstance("JKS");
		KeyStore ts = KeyStore.getInstance("JKS");

		ks.load(new FileInputStream("serverkeystore"), password);
		ts.load(new FileInputStream("servertruststore"), password);
		kmf.init(ks, password);
		tmf.init(ts);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		ssf = ctx.getServerSocketFactory();
		ServerSocket ss = ssf.createServerSocket(port);
		((SSLServerSocket) ss).setNeedClientAuth(true);
		return ss;
	}
	
	@Override
	public void run() {
		System.out.println("Secure server started");
		SSLServerSocket ss = (SSLServerSocket) this.ss;
		while (!isInterrupted()) {
			try {
				SSLSocket s = (SSLSocket) ss.accept();
				ch.addSecureConnection(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}