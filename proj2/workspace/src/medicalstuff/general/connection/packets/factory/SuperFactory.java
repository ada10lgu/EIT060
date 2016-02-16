package medicalstuff.general.connection.packets.factory;

import java.util.ArrayList;

import medicalstuff.general.connection.data.ByteArrayReader;
import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.SuperPacket;
import medicalstuff.general.connection.packets.data.BooleanPacket;
import medicalstuff.general.connection.packets.data.BytePacket;
import medicalstuff.general.connection.packets.data.IntPacket;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.NullPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;

public class SuperFactory implements PacketFactory {
	
	public static final byte SUPER_PACKET = 1;

	public static final byte RESPONSE_PACKET = 2;
	public static final byte LOGIN_PACKET = 3;
	public static final byte CHAT_PACKET = 4;
	public static final byte NULL_PACKET = 5;

	public static final byte STRING_PACKET = 6;
	public static final byte INT_PACKET = 7;
	public static final byte BYTE_PACKET = 8;
	public static final byte BOOL_PACKET = 9;
	
	private ArrayList<PacketFactory> factories = new ArrayList<>();

	public void addFactory(PacketFactory pf) {
		factories.add(pf);
	}

	public Packet createPacket(byte[] data) {
		return createPacket(new ByteArrayReader(data));
	}

	private Packet createPacket(ByteArrayReader bar) {
		byte type = bar.next();
		byte dataSize = bar.next();
		byte[] data = new byte[dataSize];
		bar.fillArray(data);

		byte packetSize = bar.next();
		Packet[] packets = new Packet[packetSize];
		for (int i = 0; i < packetSize; i++) {
			packets[i] = createPacket(bar);
		}

		Packet p = createPacket(type,data,packets);
		if (p == null)
			throw new IllegalArgumentException("Unparsable type ("+type+")"+bar.toString());
		return p;
	}
	
	@Override
	public Packet createPacket(byte type, byte[] data, Packet[] packets) {
		Packet p = null;
		switch (type) {
		case SUPER_PACKET:
			p = new SuperPacket(data, packets);
			break;
		case RESPONSE_PACKET:
			p = new ResponsePacket(data, packets);
			break;
		case STRING_PACKET:
			p = new StringPacket(data, packets);
			break;
		case INT_PACKET:
			p = new IntPacket(data, packets);
			break;
		case BYTE_PACKET:
			p = new BytePacket(data, packets);
			break;
		case NULL_PACKET:
			p = new NullPacket(data, packets);
			break;
		case BOOL_PACKET:
			p = new BooleanPacket(data,packets);
			break;
		default:
			for (PacketFactory pf : factories) {
				p = pf.createPacket(type, data, packets);
				if (p != null)
					break;
			}
		}
		return p;
	}

	


}
