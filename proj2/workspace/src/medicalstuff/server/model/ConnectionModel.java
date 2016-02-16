package medicalstuff.server.model;

import javax.net.ssl.SSLSocket;

import medicalstuff.general.connection.SecureClient;
import medicalstuff.general.medicalpackets.ChatFactory;
import medicalstuff.general.medicalpackets.ChatModel;

public class ConnectionModel implements ChatModel {
	private SecureClient connection;
	private ServerModel superModel;
	
	
	public ConnectionModel(SSLSocket s, ServerModel superModel) {
		connection = new SecureClient(s);
		connection.addFactory(new ChatFactory(this));
		this.superModel = superModel;
	}


	@Override
	public String chat(String message) {
		StringBuilder sb = new StringBuilder(message).reverse();
		return sb.toString();
	}
}
