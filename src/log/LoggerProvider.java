package log;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.annotation.Nonnull;

public class LoggerProvider {
	
	private static @Nonnull ILogger logger;
	
	public static void initialize(Socket socket) throws UnknownHostException, IOException {
		FileLogger fileLogger = FileLogger.getInstance();
		NetworkLogger networkLogger = new NetworkLogger(socket);
		logger = new CompositeLogger(Arrays.asList(fileLogger,networkLogger));
	}
	
	public static ILogger get() {
		return logger;
	}
	
	public static void close() throws Exception {
		logger.close();
	}
		
}
