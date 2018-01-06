package log.decoration;

import java.util.Properties;

import log.ILogger;
import log.MessageType;

public class MessageTypeFilteringLogger extends AbstractDecoratorLogger{

	private static final String LOG_DEC_MESSAGE_TYPE = "log.dec.message.type";
	private final MessageType type;
	
	public MessageTypeFilteringLogger(ILogger logger, Properties properties) {
		super(logger);
		
		String filterType = properties.getProperty(LOG_DEC_MESSAGE_TYPE);
		this.type = MessageType.valueOf(filterType);
	}

	@Override
	public void log(String message) {
		if(type.equals(MessageType.Plain))
			super.log(message);
	}
	
	@Override
	public void log(String message, MessageType type) {
		if(this.type.equals(type))
			super.log(message, type);
	}
}
