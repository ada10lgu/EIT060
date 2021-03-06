package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.GET_NURSES_PACKET;

import java.util.ArrayList;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.ArrayPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalModel;


public class GetNursesPacket extends OperatorPacket {

	private MedicalModel model;
	
	public GetNursesPacket() {

	}

	public GetNursesPacket(byte[] data, Packet[] packets, MedicalModel model) {
		this.model = model;
	}

	@Override
	public OperatorPacket perform() {
		ArrayList<String[]> patients = model.getNurses();
		ArrayPacket ap = new ArrayPacket();
		for (String[] patient : patients) {
			ArrayPacket temp = new ArrayPacket();
			temp.addPacket(new StringPacket(patient[0]));
			temp.addPacket(new StringPacket(patient[1]));
			ap.addPacket(temp);
		}
		ResponsePacket rp = new ResponsePacket(ap);
		return rp;
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
		return GET_NURSES_PACKET;
	}

}
