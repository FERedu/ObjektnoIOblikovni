package log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Nonnull;

import log.impl.CompositeLogger;

public class LoggerProvider {
	
	private static @Nonnull ILogger logger;
	
	public static void initialize(Properties properties) throws Exception {
		
		List<ILogger> loggers = new ArrayList<>();
		
		String loggerPackage = properties.getProperty("log.package");
		String[] loggerImpls = properties.getProperty("log.impl").split(";");
		
		//Reading implementations
		for(String loggerImpl:loggerImpls) {
			Class<?> clazz = LoggerProvider.class.getClassLoader().loadClass(loggerPackage+"."+loggerImpl);
			Method method = clazz.getMethod("getInstance", Properties.class);
			@SuppressWarnings("resource")
			ILogger loggerInstance = (ILogger) method.invoke(null, properties);
			loggers.add(loggerInstance);
		}
		
		logger = new CompositeLogger(loggers);
		
		//Decorating
		String loggerDecorationPackage = properties.getProperty("log.decoration.package");
		String[] loggerDecorationImpls = properties.getProperty("log.decoration.impl").split(";");
		for(String loggerDecorationImpl:loggerDecorationImpls) {
			Class<?> clazz = LoggerProvider.class.getClassLoader().loadClass(loggerDecorationPackage+"."+loggerDecorationImpl);
			Constructor<?> constructor = clazz.getConstructor(ILogger.class,Properties.class);
			logger = (ILogger) constructor.newInstance(logger,properties);
		}
	}
	
	public static ILogger get() {
		return logger;
	}
	
	public static void close() throws Exception {
		logger.close();
	}
		
}
