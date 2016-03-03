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
import medicalstuff.general.connection.packets.data.BooleanPacket;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalFactory;
import medicalstuff.general.medicalpackets.packets.AddJournalEntryPacket;
import medicalstuff.general.medicalpackets.packets.CreateJournalPacket;
import medicalstuff.general.medicalpackets.packets.GetJournalPacket;
import medicalstuff.general.medicalpackets.packets.GetNursesPacket;
import medicalstuff.general.medicalpackets.packets.GetPatientsPacket;
import medicalstuff.general.medicalpackets.packets.JournalListPacket;
import medicalstuff.general.medicalpackets.packets.LoginPacket;
import medicalstuff.general.medicalpackets.packets.UserPacket;
import medicalstuff.server.model.data.journal.JournalEntry;

public class ClientModel extends Observable {

	private String addr;
	private int port;
	private String certFolder;
	private SecureClient connection;

	private String name;
	private String serial;
	private int group;
	
	private int activeJournal = -1;


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
			serial = up.getSerial();
			group = up.getGroup();

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

	public String getName() {
		return name;
	}

	public String getSerial() {
		return serial;
	}

	public int getGroup() {
		return group;
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

	public boolean createJournal(String patient,String nurse) {
		CreateJournalPacket cjp = new CreateJournalPacket(patient,nurse);
		byte id = connection.send(cjp);
		ResponsePacket rp = (ResponsePacket) connection.waitForReply(id);
		BooleanPacket bp = (BooleanPacket) rp.getPacket();
		return bp.toBoolean();
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

	public Journal getJournal() {
		if (activeJournal == -1)
			return null;
		byte id = connection.send(new GetJournalPacket(activeJournal));
		OperatorPacket op = connection.waitForReply(id);
		ResponsePacket rp = (ResponsePacket) op;
		ArrayPacket ap = (ArrayPacket) rp.getPacket();
		int journalID  = ((IntPacket) ap.get(0)).toInt();
		String patient = ((StringPacket) ap.get(1)).toString();
		String pnr = ((StringPacket) ap.get(2)).toString();
		String doctor = ((StringPacket) ap.get(3)).toString();
		String nurse = ((StringPacket) ap.get(4)).toString();
		String created = ((StringPacket) ap.get(5)).toString();
		Journal j = new Journal(journalID,patient, pnr, doctor, nurse,created);
		return j;
	}
	

	public ArrayList<String[]> getNurses() {
		ArrayList<String[]> patients = new ArrayList<String[]>();

		byte id = connection.send(new GetNursesPacket());

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

	public void setActiveJournal(int activeJournal) {
		this.activeJournal = activeJournal;
	}
	
	public boolean addJournalEntry(int journalId, String data) {
		byte id = connection.send(new AddJournalEntryPacket(journalId, data));
		
		OperatorPacket op = connection.waitForReply(id);
		ResponsePacket rp = (ResponsePacket) op;
		BooleanPacket bp = (BooleanPacket) rp.getPacket();
		
		return bp.toBoolean();
	}
}
