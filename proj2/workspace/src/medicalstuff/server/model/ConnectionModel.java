package medicalstuff.server.model;

import javax.net.ssl.SSLSocket;

import medicalstuff.general.connection.SecureClient;

public class ConnectionModel {
	private SecureClient connection;
	private ServerModel superModel;
	
	
	public ConnectionModel(SSLSocket s, ServerModel superModel) {
		connection = new SecureClient(s);
		this.superModel = superModel;
	}
}
