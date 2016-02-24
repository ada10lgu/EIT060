package medicalstuff.general.medicalpackets;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.factory.PacketFactory;
import medicalstuff.general.medicalpackets.packets.CreateJournalPacket;
import medicalstuff.general.medicalpackets.packets.GetJournalPacket;
import medicalstuff.general.medicalpackets.packets.GetPatientsPacket;
import medicalstuff.general.medicalpackets.packets.JournalListPacket;
import medicalstuff.general.medicalpackets.packets.LoginPacket;
import medicalstuff.general.medicalpackets.packets.UserPacket;

public class MedicalFactory implements PacketFactory {

	public static final byte LOGIN_PACKET = 11;
	public static final byte USER_PACKET = 12;
	public static final byte JOURNAL_LIST_PACKET = 13;
	public static final byte GET_PATIENTS_PACKET = 14;
	public static final byte CREATE_JOURNAL_PACKET = 15;
	public static final byte GET_JOURNAL_PACKET = 16;

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
			p = new UserPacket(data, packets);
			break;
		case JOURNAL_LIST_PACKET:
			p = new JournalListPacket(data, packets, model);
			break;
		case GET_PATIENTS_PACKET:
			p = new GetPatientsPacket(data, packets, model);
			break;
		case CREATE_JOURNAL_PACKET:
			p = new CreateJournalPacket(data, packets, model);
			break;
		case GET_JOURNAL_PACKET:
			p = new GetJournalPacket(data, packets, model);
			break;
		}
		return p;
	}
}
