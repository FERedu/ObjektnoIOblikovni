package log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nonnull;

public abstract class AbstractLogger implements ILogger{

	private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	private final @Nonnull PrintStream output;

	public AbstractLogger(@Nonnull PrintStream output) {
		this.output = output;
	}

	@Override
	public void log( @Nonnull String message) {
		log(message, MessageType.Plain);
	}

	@Override
	public void log(@Nonnull String message,@Nonnull MessageType type) {
		String formatedDate = dateTimeFormatter.format(new Date());
		output.println(type+"("+formatedDate+"):"+message);
	}

	@Override
	public void close() throws Exception {
		output.close();
	}
}
