package medicalstuff.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.X509Certificate;

public class ServerConnection extends Thread {
	private SSLSocket socket;

	public ServerConnection(SSLSocket socket) {
		this.socket = socket;
		start();
	}

	@Override
	public void run() {
		System.out.println("Handeling connection for socket:");
		System.out.println(socket);

		SSLSession session = socket.getSession();
		X509Certificate cert;
		try {
			cert = (X509Certificate) session.getPeerCertificateChain()[0];
			String subject = cert.getSubjectDN().getName();
			System.out.println("client connected");
			System.out.println("client name (cert subject DN field): " + subject);

			PrintWriter out = null;
			BufferedReader in = null;
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String clientMsg = null;
			while ((clientMsg = in.readLine()) != null) {
				String rev = new StringBuilder(clientMsg).reverse().toString();
				rev = "" + clientMsg.length();
				System.out.println("received '" + clientMsg + "' from client");
				System.out.print("sending '" + rev + "' to client...");
				out.println(rev);
				out.flush();
				System.out.println("done\n");
			}
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
