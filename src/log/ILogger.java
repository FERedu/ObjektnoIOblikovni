package log;

import javax.annotation.Nonnull;

public interface ILogger extends AutoCloseable{
	
	public void log(@Nonnull String message);

	public void log(@Nonnull String message, @Nonnull MessageType type);

}