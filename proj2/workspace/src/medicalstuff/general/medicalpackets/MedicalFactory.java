package medicalstuff.general.medicalpackets;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.factory.PacketFactory;

public class MedicalFactory implements PacketFactory {

	public static final byte LOGIN_PACKET = 10;

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

		}
		return p;
	}

}
