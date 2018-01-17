package logger;

import java.io.PrintStream;

import javax.annotation.Nonnull;

public class MessageFilterDecorator implements ILogger{

	private final @Nonnull ILogger output;
	private MessageType type;

	public MessageFilterDecorator(ILogger output, MessageType type) throws Exception {
		this.output = output;
		this.type = type;
	}

	@Override
	public void log(String message) {
		if(!type.equals(MessageType.Plain))
			output.log(message);
	}

	@Override
	public void log(String message, MessageType messageType) {
		if(!type.equals(messageType))
			output.log(message, messageType);
	}


	@Override
	public void close() throws Exception {
		output.close();
	}

}
