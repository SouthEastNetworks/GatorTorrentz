import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket welcomeSocket = new ServerSocket(6792);
			System.out.println("Server Running...");
			while (true) {
				// System.out.println("Server part A...");
				Socket connectionSocket = welcomeSocket.accept();
				InputStream inFromRemote = connectionSocket.getInputStream();
				int bytesRead;
				byte[] rx = new byte[10000];
				bytesRead = inFromRemote.read(rx);
				System.out.println("Total Bytes read> " + bytesRead);
				String pktType = "";
				for (int i = 0; i < 3; i++) {
					pktType += (char) rx[i];
				}
				System.out.println("Received Packet type:" + pktType);
				byte[] rxFile = new byte[bytesRead];
				for (int i = 3; i < bytesRead; i++) {
					rxFile[i - 3] = rx[i];
				}
				FileOutputStream fos;
				String fname = "rxFile";
				File ofile = new File(fname);
				fos = new FileOutputStream(ofile, true);
				fos.write(rxFile);
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
