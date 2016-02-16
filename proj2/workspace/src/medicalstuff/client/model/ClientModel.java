package medicalstuff.client.model;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Observable;

import medicalstuff.general.connection.SecureClient;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.ChatPacket;

public class ClientModel extends Observable {

	private String addr;
	private int port;

	private SecureClient connection;

	public ClientModel(String addr, int port) {
		this.addr = addr;
		this.port = port;
	}

	public boolean login(String username, char[] password) {
		try {
			connection = new SecureClient(addr, port, new File(username + "_key"), new File(username + "_key"),
					password);
			setChanged();
			notifyObservers();
			return true;
		} catch (UnrecoverableKeyException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException
				| CertificateException | IOException e) {
			return false;
			}
	}
	
	public boolean isConnected() {
		if (connection == null)
			return false;
		return connection.isAlive();
	}
	
	public void logout() throws IOException {
		connection.close();
		setChanged();
		notifyObservers();
	}
	
	public String sendMessage(String message) {
		ChatPacket cp = new ChatPacket(message);
		byte id = connection.send(cp);
		ResponsePacket rp = (ResponsePacket) connection.waitForReply(id);
		StringPacket sp = (StringPacket) rp.getPacket();
		return sp.toString();
	}
}
