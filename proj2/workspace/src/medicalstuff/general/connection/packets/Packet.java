package medicalstuff.general.connection.packets;

public abstract class Packet {
	
	protected abstract byte[] getLoad();

	protected abstract Packet[] getPackages();

	protected abstract byte getType();

	public boolean isNull() {
		return false;
	}
	
	public final byte[] getData() {
		int size = 3;
		byte[] load = getLoad();
		size += load.length;
		for (Packet p : getPackages())
			size += p.getData().length;

		byte[] data = new byte[size];
		int i = 0;
		data[i++] = getType();
		data[i++] = (byte) load.length;
		for (byte b : load)
			data[i++] = b;
		data[i++] = (byte) getPackages().length;
		for (Packet p : getPackages()) {
			byte[] temp = p.getData();
			for (byte b : temp)
				data[i++] = b;
		}

		return data;
	}
}
