package medicalstuff.general.medicalpackets.chat;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.factory.PacketFactory;

public class ChatFactory implements PacketFactory {

	public static final byte CHAT_PACKET = 121;

	private ChatModel model;

	public ChatFactory(ChatModel model) {
		this.model = model;
	}

	@Override
	public Packet createPacket(byte type, byte[] data, Packet[] packets) {
		Packet p = null;
		switch (type) {
		case CHAT_PACKET:
			p = new ChatPacket(data, packets, model);
			break;
		}
		return p;
	}

}
