package logger;

import java.util.List;

public class CompositeLogger implements ILogger{

	private List<ILogger> logesrs;

	public CompositeLogger(List<ILogger> logesrs) {
		if(logesrs.size()==0)
			throw new IllegalArgumentException("Loggers have to have at least one logger!");
		this.logesrs = logesrs;
	}
	
	@Override
	public void close() throws Exception {
		for(ILogger logger:logesrs)
			logger.close();
	}

	@Override
	public void log(String message) {
		for(ILogger logger:logesrs)
			logger.log(message);		
	}

	@Override
	public void log(String message, MessageType messageType) {
		for(ILogger logger:logesrs)
			logger.log(message,messageType);				
	}

}
