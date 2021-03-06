package medicalstuff.server.model.data.log;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import medicalstuff.general.csv.CSV;

public class Logger {

	private CSV csv;
	
	public Logger(String folder) throws IOException {
		csv = new CSV(new File(folder +"logfile"));

	}
	public synchronized void log(String user, String patient, String action,String ip){
		ArrayList<String> data = new ArrayList<>();
		data.add(getNewID());
		data.add(user);
		data.add(patient);
		data.add(action);
		data.add(ip);
		data.add(getDateTime());
		csv.getData().add(data);
		try {
			csv.save();
		} catch (IOException e) {
			System.err.println("Could not log entry");
		}
	}
	
	private String getNewID() {
		int id = -1;
		int size = csv.getData().size();
		if (size != 0) {
			id = Integer.parseInt(csv.getData().get(size-1).get(0));
		}
		id++;
		return "" + id;
	}
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
		
	}
}
