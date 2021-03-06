package log.decoration;

import javax.annotation.Nonnull;

import log.ILogger;
import log.MessageType;

public abstract class AbstractDecoratorLogger implements ILogger{


	private @Nonnull ILogger logger;

	public AbstractDecoratorLogger(@Nonnull ILogger logger) {
		this.logger = logger;
	}
	
	@Override
	public void log(String message) {
		logger.log(message);
	}

	@Override
	public void log(String message, MessageType type) {
		logger.log(message, type);
	}

	@Override
	public void close() throws Exception {
		logger.close();
	}

}
