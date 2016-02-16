package medicalstuff.general.medicalpackets.chat;

import medicalstuff.general.connection.packets.Packet;
import medicalstuff.general.connection.packets.data.StringPacket;
import medicalstuff.general.connection.packets.operands.OperatorPacket;
import medicalstuff.general.connection.packets.operands.ResponsePacket;

public class ChatPacket  extends OperatorPacket{

	private ChatModel model;
	private StringPacket sp;
	
	
	public ChatPacket(String message) {
		sp = new StringPacket(message);
	}
	
	public ChatPacket(byte[] data, Packet[] packets, ChatModel model) {
		this.model = model;
		sp = (StringPacket) packets[0];
	}

	@Override
	public OperatorPacket perform() {
		String response = model.chat(sp.toString());
		return new ResponsePacket(new StringPacket(response));
	}

	@Override
	protected byte[] getLoad() {
		return new byte[0];
	}

	@Override
	protected Packet[] getPackages() {
		return new Packet[] {sp};
	}

	@Override
	protected byte getType() {
		return ChatFactory.CHAT_PACKET;
	}
	
	@Override
	public String toString() {
		
		return "Chat: " +sp;
	}

}
