package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.USER_PACKET;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;

public class UserPacket extends Packet {
	private StringPacket user;
	private StringPacket serial;
	private IntPacket group;

	public UserPacket(String user, String serial, int group) {
		this.user = new StringPacket(user);
		this.serial = new StringPacket(serial);
		this.group = new IntPacket(group);
	}

	public UserPacket(byte[] data, Packet[] packets) {
		user = (StringPacket) packets[0];
		serial = (StringPacket) packets[1];
		group = (IntPacket) packets[2];
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] { user, serial, group };
	}

	public String getName() {
		return user.toString();
	}

	public String getSerial() {
		return serial.toString();
	}

	public int getGroup() {
		return group.toInt();
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
