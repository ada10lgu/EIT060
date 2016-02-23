package medicalstuff.general.connection.packets.data;

import static medicalstuff.general.connection.packets.factory.SuperFactory.ARRAY_PACKET;

import java.util.ArrayList;
import java.util.Iterator;

import medicalstuff.general.connection.packets.Packet;

public class ArrayPacket extends DataPacket implements Iterable<Packet>{

	private ArrayList<Packet> packets;
	
	public ArrayPacket() {
		packets = new ArrayList<>();
	}
	
	public ArrayPacket(byte[] data, Packet[] packets) {
		this.packets = new ArrayList<>();
		for (Packet p : packets)
			this.packets.add(p);
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}
	
	@Override
	protected Packet[] getPackages() {
		Packet[] ps = new Packet[packets.size()];
		for (int i = 0; i < ps.length; i++)
			ps[i] = packets.get(i);
		return ps;
	}

	@Override
	protected byte getType() {
		return ARRAY_PACKET;
	}
	
	@Override
	public String toString() {
		return packets.toString();
	}
	
	public void addPacket(Packet p) {
		packets.add(p);
	}

	@Override
	public Iterator<Packet> iterator() {
		return packets.iterator();
	}

}
