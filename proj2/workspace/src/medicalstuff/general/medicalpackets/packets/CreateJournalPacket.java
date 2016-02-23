package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.CREATE_JOURNAL_PACKET;
import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.NullPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.medicalpackets.MedicalModel;


public class CreateJournalPacket extends OperatorPacket {

	private MedicalModel model;
	private StringPacket patient;

	public CreateJournalPacket(String patient) {
		this.patient = new StringPacket(patient);
	}

	public CreateJournalPacket(byte[] data, Packet[] packets, MedicalModel model) {
		patient = (StringPacket) packets[0];
		this.model = model;
	}


	@Override
	public OperatorPacket perform() {
		model.createJournal(patient.toString());
		return new NullPacket();
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] {patient};
	}

	@Override
	protected byte getType() {
		return CREATE_JOURNAL_PACKET;
	}

}
