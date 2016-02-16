package medicalstuff.general.connection.packets.factory;

import medicalstuff.general.connection.packets.Packet;

public interface PacketFactory {

	public Packet createPacket(byte type, byte[] data, Packet[] packets);
	
}
