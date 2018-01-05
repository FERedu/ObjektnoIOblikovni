package log;

import java.util.List;

import javax.annotation.Nonnull;

public class CompositeLogger implements ILogger{

	private final @Nonnull List<ILogger> loggers;

	public CompositeLogger(List<ILogger> loggers) {
		this.loggers = loggers;
	}
	
	@Override
	public void log(String message) {
		for(ILogger logger:loggers) {
			logger.log(message);
		}
		//loggers.forEach(l->l.log(message));
	}

	@Override
	public void log(String message, MessageType type) {
		for(ILogger logger:loggers) {
			logger.log(message,type);
		}
		//loggers.forEach(l->l.log(message, type));
	}

	@Override
	public void close() throws Exception {
		for(ILogger logger:loggers) {
			logger.close();
		}
	}
}
