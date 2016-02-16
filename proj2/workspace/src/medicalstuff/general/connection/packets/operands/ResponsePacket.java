package medicalstuff.general.connection.packets.operands;

import medicalstuff.general.connection.packets.Packet;

public class ResponsePacket extends OperatorPacket {

	private Packet p;

	public ResponsePacket(Packet p) {
		this.p = p;
	}

	public ResponsePacket(byte[] data, Packet[] packets) {
		p = packets[0];
	}

	@Override
	public OperatorPacket perform() {
		return new NullPacket();
	}

	public Packet getPacket() {
		return p;
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] { p };
	}

	@Override
	protected byte getType() {
		return RESPONSE_PACKET;
	}
	
	@Override
	public String toString() {
		return "Response: " + p;
	}

}
