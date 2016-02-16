package medicalstuff.server;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLSocket;

import medicalstuff.general.connection.ConnectionHandler;
import medicalstuff.general.connection.SecureServer;

public class SStart {
	public static void main(String[] args) {

		char[] password = "password".toCharArray();

		// new ServerConnectionHandler(password,12345);

		try {
			new SecureServer(12345, new Conn(), new File("serverkeystore"), new File("servertruststore"), password)
					.start();
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class Conn implements ConnectionHandler {

		@Override
		public void addConnection(Socket s) {
			System.out.println("Connection: " + s);
		}

		@Override
		public void addSecureConnection(SSLSocket s) {
			System.out.println("Secure Connection: " + s);
		}

	}
}
