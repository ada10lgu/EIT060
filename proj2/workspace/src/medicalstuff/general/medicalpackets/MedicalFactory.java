package medicalstuff.general.medicalpackets;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.factory.PacketFactory;
import medicalstuff.general.medicalpackets.packets.GetPatientsPacket;
import medicalstuff.general.medicalpackets.packets.JournalListPacket;
import medicalstuff.general.medicalpackets.packets.LoginPacket;
import medicalstuff.general.medicalpackets.packets.UserPacket;

public class MedicalFactory implements PacketFactory {

	public static final byte LOGIN_PACKET = 11;
	public static final byte USER_PACKET = 12;
	public static final byte JOURNAL_LIST_PACKET = 13;
	public static final byte GET_PATIENT_PACKET = 14;
	

	private MedicalModel model;

	public MedicalFactory(MedicalModel model) {
		this.model = model;
	}

	@Override
	public Packet createPacket(byte type, byte[] data, Packet[] packets) {
		Packet p = null;
		switch (type) {
		case LOGIN_PACKET:
			p = new LoginPacket(data, packets, model);
			break;
		case USER_PACKET:
			p = new UserPacket(data,packets);
			break;
		case JOURNAL_LIST_PACKET:
			p = new JournalListPacket(data,packets,model);
			break;
		case GET_PATIENT_PACKET:
			p = new GetPatientsPacket(data,packets,model);
			break;
		}
		return p;
	}

}
