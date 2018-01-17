package logger;

import java.io.File;
import java.io.PrintStream;

import javax.annotation.Nonnull;

public class SystemLogger extends ALogger {
	

	public SystemLogger() throws Exception {
		super(System.out);
	}	


}
