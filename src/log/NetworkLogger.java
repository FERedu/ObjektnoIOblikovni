package log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.annotation.Nonnull;

public class NetworkLogger extends AbstractLogger{

		
	public NetworkLogger(@Nonnull Socket socket) throws UnknownHostException, IOException {
		super(new PrintStream(socket.getOutputStream()));
	}

	@Override
	public void close() throws Exception {
		super.close();
	}

}
