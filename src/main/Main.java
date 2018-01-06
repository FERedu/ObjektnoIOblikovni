package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import log.LoggerProvider;
import ui.ApplicationFrame;

public class Main {

	
	private Main() {}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		//Load properties
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File("properties/app.properties")));
		String server = properties.getProperty("log.server");
		int port = Integer.parseInt(properties.getProperty("log.port"));

		//Initialize log
		Socket socket = new Socket(server, port);
		LoggerProvider.initialize(socket);

		//Start application UI
		SwingUtilities.invokeLater(()->{
		ApplicationFrame frame = new ApplicationFrame();
		frame.addCloseHook(socket);
		frame.addCloseHook(()->LoggerProvider.close());
		frame.setVisible(true);
	});
	}
}
