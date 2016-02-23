package medicalstuff.general.medicalpackets.packets;

import static medicalstuff.general.medicalpackets.MedicalFactory.JOURNAL_LIST_PACKET;

import java.util.ArrayList;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.ArrayPacket;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;
import medicalstuff.general.medicalpackets.MedicalModel;
import medicalstuff.server.model.data.journal.JournalSnippet;

public class JournalListPacket extends OperatorPacket {

	private MedicalModel model;

	public JournalListPacket() {
	}

	public JournalListPacket(byte[] data, Packet[] packets, MedicalModel model) {
		this.model = model;
	}

	@Override
	public OperatorPacket perform() {
		ArrayList<JournalSnippet> js = model.getJournals();

		ArrayPacket list = new ArrayPacket();
		for (JournalSnippet snip : js) {
			ArrayPacket temp = new ArrayPacket();
			temp.addPacket(new StringPacket(snip.getUser()));
			temp.addPacket(new IntPacket(snip.getID()));
			list.addPacket(temp);
		}

		return new ResponsePacket(list);
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
		return JOURNAL_LIST_PACKET;
	}

}
