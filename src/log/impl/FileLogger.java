package log.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Properties;

import javax.annotation.Nonnull;

import log.AbstractLogger;

public class FileLogger extends AbstractLogger {

	private static final @Nonnull String LOG_FILE_NAME = "log.file.name";
	private static final @Nonnull String LOG_FILE_FOLDER = "log.file.folder";
	
	private static FileLogger logger;

	public static @Nonnull FileLogger getInstance(Properties properties) throws FileNotFoundException {
		if (logger == null)
			logger = new FileLogger(properties);
		return logger;
	}

	// ========================================================================================

	@SuppressWarnings("resource")
	private FileLogger(Properties properties) throws FileNotFoundException {
		super(new PrintStream(new File(properties.getProperty(LOG_FILE_FOLDER),properties.getProperty(LOG_FILE_NAME))));
	}

	@Override
	public void close() throws Exception {
		super.close();
	}

}
