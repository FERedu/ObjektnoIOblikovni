package logger;

import java.io.PrintStream;

import javax.annotation.Nonnull;

public abstract class ALogger implements ILogger {
	
	
	private final @Nonnull PrintStream output;

	public ALogger(PrintStream output) throws Exception {
		this.output = output;
	}	

	@Override
	public void log(String message) {
		log(message, MessageType.Plain);
	}


	@Override
	public void log(String message, MessageType messageType) {
		output.println(messageType + ": " + message);
	}

	@Override
	public void close() throws Exception {
		output.close();
	}

}
