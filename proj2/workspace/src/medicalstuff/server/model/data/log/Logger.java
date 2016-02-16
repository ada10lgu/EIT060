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
	
	public Logger() throws IOException{
		csv = new CSV(new File("data/logfire"));

	}
	public void log(String user, String patient, String action) throws IOException{
		ArrayList<String> data = new ArrayList<>();
		data.add(user);
		data.add(patient);
		data.add(action);
		data.add(getDateTime());
		csv.getData().add(data);
		csv.save();
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
		
	}
}
