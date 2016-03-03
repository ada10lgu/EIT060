package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.REMOVE_JOURNAL_PACKET;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.operands.NullPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.medicalpackets.MedicalModel;


public class RemoveJournalPacket extends OperatorPacket {

	private MedicalModel model;
	private IntPacket journal;
	
	public RemoveJournalPacket(int journal) {
		this.journal = new IntPacket(journal);
	}
	
	public RemoveJournalPacket(byte[] data, Packet[] packets, MedicalModel model) {
		journal = (IntPacket) packets[0];
		this.model = model;
	}

	@Override
	public OperatorPacket perform() {
		model.remove(journal.toInt());
		return new NullPacket();
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] {journal};
	}

	@Override
	protected byte getType() {
		return REMOVE_JOURNAL_PACKET;
	}

}
