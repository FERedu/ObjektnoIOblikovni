package logger;

import java.util.Properties;

public class LoggerFactory {

	private static ILogger logger = null;

	public static void createLogger(Properties properties) throws Exception {

		String paket = properties.getProperty("log.package");
		String implName = properties.getProperty("log.instance");

		Class<?> clazz = LoggerFactory.class.getClassLoader().loadClass(paket + "." + implName);
		ILogger logger = (ILogger) clazz.newInstance();

		LoggerFactory.logger = new MessageFilterDecorator(logger, MessageType.Plain);
	}

	public static ILogger getLogger() {
		return logger;
	}
}
