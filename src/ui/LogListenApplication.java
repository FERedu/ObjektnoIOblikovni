package ui;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LogListenApplication extends JFrame{

	private final @Nonnull ServerSocket serverSocket;
	private final @Nonnull List<Socket> sockets = new ArrayList<>();

	private final @Nonnull DefaultListModel<String> listModel;
	
	//TODO: you would never really collect threads like this. There should be a mechanism to remove un-active sockets and threads. (when you need to redo you login because you have been disconnected) 
	private final @Nonnull List<Thread> threads = new ArrayList<>();
	
	public LogListenApplication(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		
		//General frame configuration
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(300,300,500,500);
		
		//Initialize UI
		listModel = new DefaultListModel<>();
		JList<String> messageList = new JList<>(listModel);
		
		setLayout(new BorderLayout());
		add(messageList,BorderLayout.CENTER);
		
		//Listening for incoming connections
		Thread connectionListeningThread = new Thread(()->{
			while(true){
				try {
					//Blocks until a connection is made or exception thrown
					Socket connectionSocket = serverSocket.accept();
					if(connectionSocket!=null) {
						sockets.add(connectionSocket);
						BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
						
						Thread inputReadingThread = new Thread(()->{
							try {
								while(true) {
										if(connectionSocket!=null && connectionSocket.isConnected()) {
											String line = reader.readLine();
											if(line!=null && !line.isEmpty()) {
												//TODO: change to proper listener implementation
												listModel.addElement(connectionSocket.getInetAddress() + "->" + line);
												repaint();
											}
										}
										else {
											Thread.sleep(500);
										}
								}
							} catch (IOException e) {
							} catch (InterruptedException e) {
								throw new RuntimeException(e);
							} 
						});
						inputReadingThread.setDaemon(true);
						inputReadingThread.start();
						threads.add(inputReadingThread);
					}
				} catch (IOException e) {
				}
			}
		});
		connectionListeningThread.setDaemon(true);
		connectionListeningThread.start();
		threads.add(connectionListeningThread);
		
		
		//window closing listener
		addWindowListener(new WindowClosingAdapter());
	}
	
	
	private final class WindowClosingAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				for(Thread thread:threads) {
					thread.interrupt();
				}
				for(Socket socket:sockets)
					socket.close();
				serverSocket.close();
			} catch (IOException e1) {
			}
			super.windowClosing(e);
		}
	}

}
