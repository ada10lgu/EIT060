package medicalstuff.general.connection;

import java.net.Socket;

import javax.net.ssl.SSLSocket;

public interface ConnectionHandler {
	public void addConnection(Socket s);
	public void addSecureConnection(SSLSocket s);
}
