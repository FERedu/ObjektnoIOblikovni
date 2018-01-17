package logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LoggerFactory {

	private static ILogger logger = null;

	public static void createLogger(Properties properties) throws Exception {

		String paket = properties.getProperty("log.package");
		String implNames = properties.getProperty("log.instance");

		List<ILogger> loggers = new ArrayList<>();
		
		for(String name:implNames.split(";")) {
			Class<?> clazz = LoggerFactory.class.getClassLoader().loadClass(paket + "." + name);
			ILogger logger1 = (ILogger) clazz.newInstance();
			loggers.add(logger1);
		}
		
		ILogger logger = new CompositeLogger(loggers);
		
		LoggerFactory.logger = new MessageFilterDecorator(logger, MessageType.Plain);
	}

	public static ILogger getLogger() {
		return logger;
	}
}
