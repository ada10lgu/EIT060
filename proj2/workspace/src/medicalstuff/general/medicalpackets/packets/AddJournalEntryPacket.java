package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.ADD_JOURNAL_ENTRY_PACKET;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.BooleanPacket;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalModel;

public class AddJournalEntryPacket extends OperatorPacket {
	private MedicalModel model;
	private IntPacket journalId;
	private StringPacket entry;

	public AddJournalEntryPacket(int journalId, String entry) {
		this.journalId = new IntPacket(journalId);
		this.entry = new StringPacket(entry);
	}

	public AddJournalEntryPacket(byte[] data, Packet[] packets, MedicalModel model) {
		this.journalId = (IntPacket) packets[0];
		this.entry = (StringPacket) packets[1];
		this.model = model;
	}

	@Override
	public OperatorPacket perform() {
		boolean b = model.addJournalEntry(journalId.toInt(), entry.toString());
		return new ResponsePacket(new BooleanPacket(b));
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] {journalId, entry};
	}

	@Override
	protected byte getType() {
		return ADD_JOURNAL_ENTRY_PACKET;
	}

}
