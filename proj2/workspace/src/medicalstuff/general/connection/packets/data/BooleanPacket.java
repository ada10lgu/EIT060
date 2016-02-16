package medicalstuff.general.connection.packets.data;

import medicalstuff.general.connection.packets.Packet;

public class BooleanPacket extends DataPacket {

	private boolean b;
	
	public BooleanPacket(boolean b) {
		this.b = b;
	}
	
	public BooleanPacket(byte[] data, Packet[] packets) {
		b = data[0] == 1;
	}

	@Override
	protected byte[] getLoad() {
		if (b) {
			return new byte[] {1};
		}
		return new byte[] {0};
	}

	@Override
	protected byte getType() {
		return BOOL_PACKET;
	}

}
