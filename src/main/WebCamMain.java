package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.SwingUtilities;

import logger.ALogger;
import logger.FileLogger;
import logger.LoggerFactory;
import logger.SystemLogger;
import ui.ApplicationFrame;

public class WebCamMain {

	
	private WebCamMain() {}
	
	
	public static void main(String[] args) throws Exception {

		Properties properties = new Properties();
		properties.load(new FileInputStream(new File("properties/app.properties")));
		
		
		LoggerFactory.createLogger(properties);
		ALogger logger = LoggerFactory.getLogger();
		logger.log("Starting");
		//Start application UI
		SwingUtilities.invokeLater(()->{
			ApplicationFrame frame = new ApplicationFrame();
			frame.setVisible(true);
			frame.addCloseHook(logger);
		});
	}
}
