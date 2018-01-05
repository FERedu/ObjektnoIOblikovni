package main;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.SwingUtilities;

import ui.LogListenApplication;

public class LogListenMain {

	private LogListenMain() {}
	
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(60000);
		
		SwingUtilities.invokeLater(()->{
			LogListenApplication application = new LogListenApplication(socket);
			application.setVisible(true);
		});
		
		
	}
	
	
	
}
