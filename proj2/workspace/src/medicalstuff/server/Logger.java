package medicalstuff.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private PrintStream outstream;

	public Logger(String file){
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
			outstream = new PrintStream(fileOutputStream);
		} catch (FileNotFoundException e) {
			System.out.println("No output file exists");
			e.printStackTrace();
		}

	}
	public void log(String user, String patient, String action){
		outstream.println (getDateTime() + " " + user + " " + action + " for patient " + patient);
		outstream.flush();
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
		
	}
}
