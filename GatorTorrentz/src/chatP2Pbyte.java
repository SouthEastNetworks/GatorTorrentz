

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class chatP2Pbyte {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// String remoteIP = args[0];
		// int remotePort = Integer.parseInt(args[1]);
		System.out.println("Hi...");
		chatServerbyte cs = new chatServerbyte();
		Thread t = new Thread(cs);
		t.setName("Service");
		t.start();
		System.out.println("Hello...");
		String remoteIP = "192.168.0.19";
		int remotePort = 6792;
		String sentence;
		Thread.sleep(2000);

		System.out.println("Client Running...");
		while (true) {
			Socket clientSocket = new Socket(remoteIP, remotePort);
			BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));
			sentence = inFromUser.readLine();
			byte[] buff = sentence.getBytes();
			System.out.println("you /> " + sentence + "\n");
			String fname = "src/file/dummyfile";
			File ifile = new File(fname);
			FileInputStream fis;
			fis = new FileInputStream(ifile);
			int readLength = (int) ifile.length();
			byte[] byteChunk = new byte[readLength];
			int read = fis.read(byteChunk, 0, readLength);
			assert (read == byteChunk.length);
			OutputStream outToServer = clientSocket.getOutputStream();
			byte[] outPkt = concatBytes(buff, byteChunk);
			outToServer.write(outPkt);
		}
	}

	static byte[] concatBytes(byte a[], byte b[]) {
		byte[] result = new byte[((int) a.length) + ((int) b.length)];
		int i=0;
		while(i<((int)a.length))
		{
			result[i]=a[i];
			i++;
		}
		int j=0;
		while(j<((int)b.length))
		{
			result[i]=b[j];
			i++;
			j++;
		}
		return result;
	}
}

class chatServerbyte implements Runnable {
	String clientSentence;

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
					rxFile[i-3] = rx[i];
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
