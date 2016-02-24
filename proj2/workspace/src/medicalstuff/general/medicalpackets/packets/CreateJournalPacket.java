package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.CREATE_JOURNAL_PACKET;
import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.BooleanPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalModel;


public class CreateJournalPacket extends OperatorPacket {

	private MedicalModel model;
	private StringPacket patient;
	private StringPacket doctor;
	private StringPacket nurse;

	public CreateJournalPacket(String patient, String doctor, String nurse) {
		this.patient = new StringPacket(patient);
		this.doctor = new StringPacket(doctor);
		this.nurse = new StringPacket(nurse);
	}

	public CreateJournalPacket(byte[] data, Packet[] packets, MedicalModel model) {
		patient = (StringPacket) packets[0];
		doctor = (StringPacket) packets[1];
		nurse = (StringPacket) packets[2];
		this.model = model;
	}


	@Override
	public OperatorPacket perform() {
		boolean b = model.createJournal(patient.toString(), doctor.toString(), nurse.toString());
		return new ResponsePacket(new BooleanPacket(b));
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] {patient, doctor, nurse};
	}

	@Override
	protected byte getType() {
		return CREATE_JOURNAL_PACKET;
	}

}
