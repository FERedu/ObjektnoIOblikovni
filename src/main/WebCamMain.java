package main;

import javax.swing.SwingUtilities;

import ui.ApplicationFrame;

public class WebCamMain {

	
	private WebCamMain() {}
	
	
	public static void main(String[] args) throws Exception {

		//Start application UI
		SwingUtilities.invokeLater(()->{
		ApplicationFrame frame = new ApplicationFrame();
		frame.setVisible(true);
	});
	}
}
