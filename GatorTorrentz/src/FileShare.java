import java.io.OutputStream;
import java.net.Socket;

public class FileShare {
	static int peerID = 1234;

	static int prefNeighbours[] = new int[2];
	static int allNeighbours[] = new int[10];
	static boolean handShake1 = false;
	static int n1Id = 0;
	static int[] n1List = new int[10];


	public static void main(String[] args) throws Exception {
		int serverPort = 6792;
		String remoteIp = "localhost";
		int remotePort = 6799;
		
		Server fs = new Server(serverPort);
		fs.run();

		Thread.sleep(2000);
		
		sendHello();
		
	}

	public static void rxPacket(byte[] rxPkt) {
		String pktType = "";
		for (int i = 0; i < 5; i++) {
			pktType += (char) rxPkt[i];
		}
		System.out.println("Received Packet type:" + pktType);
		if (pktType.equals("HELLO")) {
			String t = "";
			for (int i = 32; i < 37; i++) {
				t += (char) rxPkt[i];
			}
			n1Id = Integer.parseInt(t);
			handShake1 = true;
			System.out.println("Shook hands with "+n1Id);
			
		}
	}

	public static void sendByte(byte[] pkt)throws Exception
	{
		String ip="";
		int port=6792;
		@SuppressWarnings("resource")
		Socket clientSocket = new Socket(ip, port);
		OutputStream outToServer = clientSocket.getOutputStream();
		outToServer.write(pkt);
		
	}
	public static void sendHello()throws Exception
	{
		String sentence = "HELLO";
		for(int i=0;i<32;i++)
		{
			sentence +="0";
		}
		sentence += peerID;
		byte[] buff = sentence.getBytes();
		sendByte(buff);
	}
	
	public static void askFordb()
	{
		
	}
}
