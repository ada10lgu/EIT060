package medicalstuff.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

public class ClientConnection {
	public ClientConnection(String user, char[] password,String host, int port) throws IOException {
		String truststore = user+"_trust";
		String keystore = user+"_key";
		SSLSocketFactory factory = null;
		  try {
	            KeyStore ks = KeyStore.getInstance("JKS");
	            KeyStore ts = KeyStore.getInstance("JKS");
	            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	            SSLContext ctx = SSLContext.getInstance("TLS");
	            ks.load(new FileInputStream(keystore), password);  // keystore password (storepass)
				ts.load(new FileInputStream(truststore), password); // truststore password (storepass);
				kmf.init(ks, password); // user password (keypass)
				tmf.init(ts); // keystore can be used as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	            factory = ctx.getSocketFactory();
	            SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
	            System.out.println("\nsocket before handshake:\n" + socket + "\n");
         
		  socket.startHandshake();

          SSLSession session = socket.getSession();
          X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
          String subject = cert.getSubjectDN().getName();
          System.out.println("certificate name (subject DN field) on certificate received from server:\n" + subject + "\n");
          System.out.println("socket after handshake:\n" + socket + "\n");
          System.out.println("secure connection established\n\n");
          
          BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); // tastatur
          
          
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          
          
          String msg;
			for (;;) {
              System.out.print(">");
              msg = read.readLine();
              if (msg.equalsIgnoreCase("quit")) {
				    break;
				}
              System.out.print("sending '" + msg + "' to server...");
              out.println(msg);
              out.flush();
              System.out.println("done");

              System.out.println("received '" + in.readLine() + "' from server\n");
          }
          in.close();
			out.close();
			read.close();
			socket.close();
            
		  } catch (Exception e) {
              throw new IOException(e.getMessage());
          }
		
	}
}
