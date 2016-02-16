package medicalstuff.general.connection.packets.operands;

import medicalstuff.general.connection.packets.Packet;
import static medicalstuff.general.connection.packets.factory.SuperFactory.*;
public class NullPacket  extends OperatorPacket{

	public NullPacket() {
		
	}
	
	public NullPacket(byte[] data, Packet[] packets) {
	}

	@Override
	public OperatorPacket perform() {
		return new NullPacket();
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
		return NULL_PACKET;
	}

	@Override
	public String toString() {
		return "NULL";
	}
	
}
