package log;

import java.io.PrintStream;

import javax.annotation.Nonnull;

public abstract class AbstractLogger implements ILogger{

	
	private final @Nonnull PrintStream output;

	public AbstractLogger(@Nonnull PrintStream output) {
		this.output = output;
	}

	@Override
	public void log( @Nonnull String message) {
		output.println(message);
	}

	@Override
	public void log(@Nonnull String message,@Nonnull MessageType type) {
		output.println(type+"\t"+message);
	}

	@Override
	public void close() throws Exception {
		output.close();
	}
}
