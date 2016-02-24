package medicalstuff.general.medicalpackets.packets;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.medicalpackets.MedicalModel;

public class AddJournalEntryPacket extends OperatorPacket {

	
	public AddJournalEntryPacket(byte[] data, Packet[] packets,
			MedicalModel model) {
	}

	@Override
	public OperatorPacket perform() {
		return null;
	}

	@Override
	protected byte[] getLoad() {
		return null;
	}

	@Override
	protected Packet[] getPackages() {
		return null;
	}

	@Override
	protected byte getType() {
		return 0;
	}

}
