package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.GET_JOURNAL_PACKET;

import java.util.ArrayList;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.ArrayPacket;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.NullPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalModel;
import medicalstuff.server.model.data.journal.Journal;
import medicalstuff.server.model.data.journal.JournalEntry;

public class GetJournalPacket extends OperatorPacket {
	private MedicalModel model;
	private int journalId;
	
	public GetJournalPacket(int journalId) {
		this.journalId = journalId;
	}
	
	
	public GetJournalPacket(byte[] data, Packet[] packets, MedicalModel model) {
		this.model = model;
	}
	
	@Override
	public OperatorPacket perform() {
		Journal journal = model.getJournal(journalId);
		if (journal == null) {
			return new NullPacket();
		}
		
		ArrayPacket ap = new ArrayPacket();
		
		ap.addPacket(new IntPacket(journal.getId()));
		ap.addPacket(new StringPacket(model.getUserName(journal.getPatient())));
		ap.addPacket(new StringPacket(model.getUserName(journal.getDoctor())));
		ap.addPacket(new StringPacket(model.getUserName(journal.getNurse())));
		ap.addPacket(new StringPacket(journal.getDate()));
		
		ArrayPacket entryPacket = new ArrayPacket();
		ArrayList<JournalEntry> entries = model.getJournalEntries(journal.getId());
		for(JournalEntry je : entries) {
			ArrayPacket temp = new ArrayPacket();
			temp.addPacket(new StringPacket(model.getUserName(je.getUser())));
			temp.addPacket(new StringPacket(je.getTimeStamp()));
			temp.addPacket(new StringPacket(je.getData()));
			
			entryPacket.addPacket(temp);
		}
		ap.addPacket(entryPacket);
		
		ResponsePacket rp = new ResponsePacket(ap);
		return rp;
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[0];
	}

	@Override
	protected byte getType() {
		return GET_JOURNAL_PACKET;
	}
	
}
