package main;

import javax.swing.SwingUtilities;

import log.FileLogger;
import ui.ApplicationFrame;

public class WebCamMain {

	
	private WebCamMain() {}
	
	
	public static void main(String[] args) throws Exception {

		FileLogger.createFileLogger();
		FileLogger logger = FileLogger.getFileLogger();
		logger.log("Starting");
		//Start application UI
		SwingUtilities.invokeLater(()->{
			ApplicationFrame frame = new ApplicationFrame();
			frame.setVisible(true);
			frame.addCloseHook(logger);
		});
	}
}
