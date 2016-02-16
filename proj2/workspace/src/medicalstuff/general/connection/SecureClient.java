package medicalstuff.general.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SecureClient extends Client {

	public SecureClient(String addr, int port, File keystore, File truststore, char[] password)
			throws UnknownHostException, IOException, UnrecoverableKeyException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException {
		super(createSocket(addr, port, keystore, truststore, password));
	}

	private static SSLSocket createSocket(String addr, int port, File keystore, File truststore, char[] password)
			throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, KeyManagementException {
		SSLSocketFactory factory = null;
		KeyStore ks = KeyStore.getInstance("JKS");
		KeyStore ts = KeyStore.getInstance("JKS");
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		SSLContext ctx = SSLContext.getInstance("TLS");
		ks.load(new FileInputStream(keystore), password);
		ts.load(new FileInputStream(truststore), password);
		kmf.init(ks, password);
		tmf.init(ts);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		factory = ctx.getSocketFactory();
		SSLSocket socket = (SSLSocket) factory.createSocket(addr, port);

		socket.startHandshake();
		return socket;
	}

}
