package medicalstuff.general.connection.packets.operands;

import medicalstuff.general.connection.packets.Packet;


public abstract class OperatorPacket extends Packet {

	public abstract OperatorPacket perform();

}
