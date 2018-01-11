package example2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Example2Main {
	
	public static void main(String[] args)  {

		try(PrintStream output = new PrintStream(
				new FileOutputStream(
						new File("log/log.txt")))) {
			
			
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			
			
			output.println(dateFormat.format(date)+": "+"Bok");
		} catch (FileNotFoundException e) {
			System.out.println("Problem");
		}
	}

}
