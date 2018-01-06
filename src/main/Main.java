package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import log.LoggerProvider;
import ui.ApplicationFrame;

public class Main {

	
	private Main() {}
	
	
	public static void main(String[] args) throws Exception {

		//Load properties
		Properties properties = new Properties();
		try(FileInputStream fileInputStream = new FileInputStream(new File("properties/app.properties"))){
			properties.load(fileInputStream);
		}

		//Initialize log
		LoggerProvider.initialize(properties);

		//Start application UI
		SwingUtilities.invokeLater(()->{
		ApplicationFrame frame = new ApplicationFrame();
		frame.addCloseHook(()->LoggerProvider.close());
		frame.setVisible(true);
	});
	}
}
