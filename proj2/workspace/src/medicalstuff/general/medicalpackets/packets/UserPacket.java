package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.USER_PACKET;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.StringPacket;

public class UserPacket extends Packet {
	private StringPacket user;

	public UserPacket(String user) {
		this.user = new StringPacket(user);
	}

	public UserPacket(byte[] data, Packet[] packets) {
		user = (StringPacket) packets[0];
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] { user };
	}
	
	public String getName() {
		return user.toString();
	}
	
	@Override
	protected byte getType() {
		return USER_PACKET;
	}

	@Override
	public String toString() {
		return "USER (" + user.toString() + ")";
	}

}
