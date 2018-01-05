package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.SwingUtilities;

import log.LoggerProvider;
import ui.ApplicationFrame;

public class Main {

	
	private Main() {}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 50000);
		LoggerProvider.initialize(socket);
		
		SwingUtilities.invokeLater(()->{
		ApplicationFrame frame = new ApplicationFrame();
		frame.addCloseHook(socket);
		frame.addCloseHook(()->LoggerProvider.close());
		frame.setVisible(true);
	});
	}
}
