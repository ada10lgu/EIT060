package medicalstuff.general.medicalpackets.packets;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.medicalpackets.MedicalModel;
import static medicalstuff.general.medicalpackets.MedicalFactory.GET_JOURNAL_PACKET;

public class GetJournalPacket extends OperatorPacket {
	private MedicalModel model;
	public GetJournalPacket(byte[] data, Packet[] packets, MedicalModel model) {
		this.model = model;
	}
	
	@Override
	public OperatorPacket perform() {
		
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
