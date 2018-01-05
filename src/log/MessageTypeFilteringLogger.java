package log;

public class MessageTypeFilteringLogger extends AbstractDecoratorLogger{

	private final MessageType type;
	
	public MessageTypeFilteringLogger(ILogger logger, MessageType type) {
		super(logger);
		this.type = type;
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
