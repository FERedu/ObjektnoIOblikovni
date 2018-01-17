package logger;

public interface ILogger extends AutoCloseable {

	void log(String message);

	void log(String message, MessageType messageType);

}