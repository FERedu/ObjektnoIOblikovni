package log.impl;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.annotation.Nonnull;

import log.AbstractLogger;

public class NetworkLogger extends AbstractLogger{

		
	private static final @Nonnull String LOG_SERVER = "log.server";
	private static final @Nonnull String LOG_PORT = "log.port";

	@SuppressWarnings("resource")
	public static NetworkLogger getInstance(Properties properties) throws Exception {
		String server = properties.getProperty(LOG_SERVER);
		int port = Integer.parseInt(properties.getProperty(LOG_PORT));
		Socket socket = new Socket(server, port);
		return new NetworkLogger(socket);
	}
	
	private Socket socket;

	private NetworkLogger(@Nonnull Socket socket) throws UnknownHostException, IOException {
		super(new PrintStream(socket.getOutputStream()));
		this.socket = socket;
	}

	@Override
	public void close() throws Exception {
		super.close();
		socket.close();
	}

}
