package medicalstuff.client.model;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import medicalstuff.general.connection.SecureClient;
import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.ArrayPacket;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalFactory;
import medicalstuff.general.medicalpackets.chat.ChatPacket;
import medicalstuff.general.medicalpackets.packets.CreateJournalPacket;
import medicalstuff.general.medicalpackets.packets.GetPatientsPacket;
import medicalstuff.general.medicalpackets.packets.JournalListPacket;
import medicalstuff.general.medicalpackets.packets.JournalListPacket;
import medicalstuff.general.medicalpackets.packets.LoginPacket;
import medicalstuff.general.medicalpackets.packets.UserPacket;

public class ClientModel extends Observable {

	private String addr;
	private int port;
	private String certFolder;

	private String name;

	private SecureClient connection;

	public ClientModel(String addr, int port, String certFolder) {
		this.addr = addr;
		this.port = port;
		this.certFolder = certFolder;
	}

	public boolean login(String username, char[] password) {
		try {
			connection = new SecureClient(addr, port, new File(certFolder + username + "_key"),
					new File(certFolder + username + "_trust"), password);
			connection.addFactory(new MedicalFactory(null));
			byte id = connection.send(new LoginPacket());
			OperatorPacket op = connection.waitForReply(id);
			if (op.isNull())
				return false;
			ResponsePacket rp = (ResponsePacket) op;
			UserPacket up = (UserPacket) rp.getPacket();
			name = up.getName();

			setChanged();
			notifyObservers();
			if (name.isEmpty())
				return false;
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

	public String getName() {
		return name;
	}

	public HashMap<String, String> getServerInfo() {
		return connection.getServerInfo();
	}

	public ArrayList<String[]> getPatients() {
		ArrayList<String[]> patients = new ArrayList<String[]>();

		byte id = connection.send(new GetPatientsPacket());

		OperatorPacket op = connection.waitForReply(id);
		ResponsePacket rp = (ResponsePacket) op;
		ArrayPacket ap = (ArrayPacket) rp.getPacket();

		for (Packet p : ap) {
			ArrayPacket apInner = (ArrayPacket) p;
			String[] patient = new String[2];
			patient[0] = ((StringPacket) apInner.get(0)).toString();
			patient[1] = ((StringPacket) apInner.get(1)).toString();
			patients.add(patient);
		}
		return patients;
	}

	public void createJournal(String patient) {
		CreateJournalPacket cjp = new CreateJournalPacket(patient);
		byte id = connection.send(cjp);
		connection.waitForReply(id);
	}

	public ArrayList<JournalInfo> getJournals() {
		ArrayList<JournalInfo> journals = new ArrayList<JournalInfo>();
		byte id = connection.send(new JournalListPacket());

		OperatorPacket op = connection.waitForReply(id);
		ResponsePacket rp = (ResponsePacket) op;
		ArrayPacket ap = (ArrayPacket) rp.getPacket();

		for (Packet p : ap) {
			ArrayPacket apInner = (ArrayPacket) p;
			String userName = ((StringPacket) apInner.get(0)).toString();
			int userId = ((IntPacket) apInner.get(1)).toInt();
			JournalInfo ji = new JournalInfo(userName, userId);
			journals.add(ji);
		}
		return journals;
	}
}
