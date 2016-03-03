package medicalstuff.server;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;

import medicalstuff.server.model.ServerModel;

public class MedicalServer {
	public static void main(String[] args) {

		boolean verbose = false;

		HashMap<String, String> settings = new HashMap<>();
		settings.put("certFolder", "certs/");
		settings.put("password", "password");
		settings.put("port", "12345");
		settings.put("dataFolder","data/");

		for (int i = 0; i < args.length; i++) {
			String s = args[i];
			if (!s.startsWith("-")) {
				System.out.println("Unknown command \"" + s + "\"");
				System.exit(0);
			}
			String command = s.substring(1);
			if (command.equalsIgnoreCase("verbose")) {
				verbose = true;
				continue;
			}
			if (i == args.length - 1) {
				System.out.println("Missing data to command " + command);
				System.exit(0);
			}
			String old = settings.put(command, args[++i]);
			if (old == null) {
				System.out.println("Unknown command \"" + s + "\"");
				System.exit(0);
			}
		}

		if (verbose) {
			System.out.println("The follow settings are beeing used:");
			System.out.println(settings);
			System.out.println();
		}
		char[] password = settings.get("password").toCharArray();

		int port = -1;
		try {
			port = Integer.parseInt(settings.get("port"));
		} catch (NumberFormatException e) {
			System.out.println("Can't parse port");
			System.exit(0);
		}

		String certFolder = settings.get("certFolder");
		String dataFolder = settings.get("dataFolder");
		File keystore = new File(certFolder + "server_key");
		File truststore = new File(certFolder + "server_trust");

		try {
			new ServerModel(port, keystore, truststore, password, dataFolder, verbose);
		} catch (KeyManagementException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException
				| CertificateException | IOException e) {
			System.out.println("\nCould not start server!");
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
