package medicalstuff.general.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import medicalstuff.general.connection.data.ConnectionQueue;

class Connection {

	private Socket socket;
	private ConnectionQueue inbox;
	private ConnectionQueue outbox;

	public Connection(String addr, int port) throws UnknownHostException,
			IOException {
		this(new Socket(addr, port));
	}

	public Connection(Socket socket) {
		this.socket = socket;
		inbox = new ConnectionQueue();
		outbox = new ConnectionQueue();
		new Reciever().start();
		new Sender().start();
	}

	public synchronized void send(byte[] b) {
		outbox.offer(b);
		notifyAll();
	}

	private synchronized byte[] getData() {
		while (outbox.isEmpty())
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return outbox.poll();
	}

	public synchronized byte[] recieve() {
		while (inbox.isEmpty())
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return inbox.poll();
	}

	private synchronized void newData(byte[] b) {
		inbox.offer(b);
		notifyAll();
	}

	private class Reciever extends Thread {

		@Override
		public void run() {
			try {
				InputStream is = socket.getInputStream();
				while (true) {
					byte[] l = new byte[4];
					for (int i = 0; i < 4; i++)
						l[i] = readByte(is);
					int length = byteToInt(l);
					byte[] data = new byte[length];
					for (int i = 0; i < data.length; i++)
						data[i] = readByte(is);
					newData(data);
				}
			} catch (IOException e) {
				if (!socket.isClosed() || !e.getMessage().startsWith("EOF")
						|| !e.getMessage().startsWith("Socket closed")) {
					System.err.println("Error in connection! (recieveing)");
					System.err.println(e.getMessage());
					System.err.println(socket.isConnected());
					System.err.println("------");
				}
				try {
					close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		private byte readByte(InputStream is) throws IOException {
			int input = is.read();
			if (input == -1)
				throw new IOException("EOF");
			return (byte) input;
		}
	}

	private class Sender extends Thread {

		@Override
		public void run() {
			try {
				OutputStream os = socket.getOutputStream();
				while (true) {
					byte[] data = getData();
					byte[] size = intToByte(data.length);

					os.write(size);
					os.write(data);
					os.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Error in connection! (Sending)");
				try {
					close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	public void close() throws IOException {
		socket.close();
	}

	public boolean isAlive() {
		return !socket.isClosed();
	}

	private byte[] intToByte(int i) {
		return ByteBuffer.allocate(4).putInt(i).array();
	}

	private int byteToInt(byte[] b) {
		ByteBuffer bb = ByteBuffer.wrap(b);
		return bb.getInt();
	}
}
