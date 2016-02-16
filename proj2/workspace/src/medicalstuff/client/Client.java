package medicalstuff.client;

import medicalstuff.client.gui.ClientGUI;
import medicalstuff.client.model.ClientModel;

public class Client {
	public static void main(String[] args) {
		ClientModel model = new ClientModel("localhost", 12345);
		
		new ClientGUI(model);
	
	}
}
