package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

import ui.LogListenApplication;

public class LogListenMain {

	private LogListenMain() {}
	
	public static void main(String[] args) throws IOException {
		
		// Load properties
		Properties properties = new Properties();
		try(FileInputStream fileInputStream = new FileInputStream(new File("properties/app.properties"))){
			properties.load(fileInputStream);
		}
		int port = Integer.parseInt(properties.getProperty("log.port"));

		ServerSocket socket = new ServerSocket(port);
		
			LogListenApplication application = new LogListenApplication(socket);
			application.setVisible(true);
		}
		
		
	}
