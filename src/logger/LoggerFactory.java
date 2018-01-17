package logger;

import java.util.Properties;

public class LoggerFactory {

	private static ALogger logger = null;

	public static void createLogger(Properties properties) throws Exception {
		
		String paket = properties.getProperty("log.package");
		String implName = properties.getProperty("log.instance");
		
		Class<?> clazz = LoggerFactory.class.getClassLoader().
				loadClass(paket+"."+implName);
		logger = (ALogger) clazz.newInstance();
	}   
  
	
	public static ALogger getLogger() {  
		return logger;
	}
}
