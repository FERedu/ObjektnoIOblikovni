package log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.annotation.Nonnull;

public class FileLogger extends AbstractLogger {

	private static final @Nonnull String LOG_FILE_NAME = "log/logfile.log";

	private static FileLogger logger;

	public static @Nonnull FileLogger getInstance() throws FileNotFoundException {
		if (logger == null)
			logger = new FileLogger();
		return logger;
	}

	// ========================================================================================

	private FileLogger() throws FileNotFoundException {
		super(new PrintStream(new File(LOG_FILE_NAME)));
	}

	@Override
	public void close() throws Exception {
		super.close();
	}

}
