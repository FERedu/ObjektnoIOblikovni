package logger;

import java.io.File;
import java.io.PrintStream;

import javax.annotation.Nonnull;

public class FileLogger extends ALogger {
	
	public FileLogger() throws Exception {
		super(new PrintStream(new File("log/log.txt")));
	}	
}
