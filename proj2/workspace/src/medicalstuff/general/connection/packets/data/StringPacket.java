package medicalstuff.general.connection.packets.data;

import medicalstuff.general.connection.packets.Packet;
import static medicalstuff.general.connection.packets.factory.SuperFactory.*;

public class StringPacket extends DataPacket {

	private String s;
	
	public StringPacket(String s) {
		this.s = s;
	}
	
	public StringPacket(byte[] data, Packet[] packets) {
		s = new String(data);
	}

	@Override
	protected byte[] getLoad() {
		return s.getBytes();
	}

	@Override
	protected byte getType() {
		return STRING_PACKET;
	}
	
	@Override
	public String toString() {
		return s;
	}
	
}
