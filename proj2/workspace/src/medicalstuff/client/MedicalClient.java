package medicalstuff.client;

import java.util.HashMap;

import medicalstuff.client.gui.ClientGUI;
import medicalstuff.client.model.ClientModel;

public class MedicalClient {
	public static void main(String[] args) {

		boolean verbose = false;

		HashMap<String, String> settings = new HashMap<>();
		settings.put("certFolder", "certs/");
		settings.put("ip", "localhost");
		settings.put("port", "12345");

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
		}

		int port = -1;
		try {
			port = Integer.parseInt(settings.get("port"));
		} catch (NumberFormatException e) {
			System.out.println("Can't parse port");
			System.exit(0);
		}

		ClientModel model = new ClientModel(settings.get("ip"), port, settings.get("certFolder"));

		new ClientGUI(model);
	}
}