package medicalstuff.general.medicalpackets;

import static medicalstuff.general.medicalpackets.MedicalFactory.LOGIN_PACKET;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;

public class LoginPacket extends OperatorPacket {

	private MedicalModel model;

	public LoginPacket() {

	}

	public LoginPacket(byte[] data, Packet[] packets, MedicalModel model) {
		this.model = model;
	}

	@Override
	public OperatorPacket perform() {
		String id = model.login();
		return new ResponsePacket(new StringPacket(id));
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
		return LOGIN_PACKET;
	}

}
