package medicalstuff.general.connection.packets.data;

import medicalstuff.general.connection.packets.Packet;

public class BytePacket extends DataPacket {

	byte b;
	
	public BytePacket(Byte b) {
		this.b = b;
	}
	
	public BytePacket(byte[] data, Packet[] packets) {
		b = data[0];
	}

	@Override
	protected byte[] getLoad() {
		return new byte[] {b};
	}

	@Override
	protected byte getType() {
		return BYTE_PACKET;
	}
	
	@Override
	public String toString() {
		return b + "b";
	}
	
	public byte getByte() {
		return b;
	}

}
