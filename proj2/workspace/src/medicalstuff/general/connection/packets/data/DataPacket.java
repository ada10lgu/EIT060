package medicalstuff.general.connection.packets.data;

import medicalstuff.general.connection.packets.Packet;

public abstract class DataPacket extends Packet {

	@Override
	protected Packet[] getPackages() {
		return new Packet[0];
	}
}
