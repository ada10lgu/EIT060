package medicalstuff.client;

import medicalstuff.client.gui.ClientGUI;
import medicalstuff.client.model.ClientModel;

public class MedicalClient {
	public static void main(String[] args) {
		String certFolder = "certs/";

		ClientModel model = new ClientModel("localhost", 12345, certFolder);

		new ClientGUI(model);
	}
}