import java.io.OutputStream;
import java.net.Socket;

public class FileShare {
	static int peerID = 1234;

	static int prefNeighbours[] = new int[2];
	static int allNeighbours[] = new int[10];
	static boolean handShake1 = false;
	static int n1Id = 0;
	static int[] n1List = new int[10];
	
	static int serverPort = 6792;
	static String remoteIp = "192.168.0.10";
	static int remotePort = 6792;

	static int[] rcvBitArray;

	public static void main(String[] args) throws Exception {

		//pavan : 192.168.0.20
		Server fs = new Server(serverPort);
		(new Thread(fs)).start();
		//fs.run();

		Thread.sleep(2000);
		
		sendHello();
		BitField.sendByteBitField();
		Interested.shouldSendInterested();
	}

	public static void rxPacket(byte[] rxPkt) {
		String pktType = "";
		for (int i = 0; i < 5; i++) {
			pktType += (char) rxPkt[i];
		}
		if (pktType.equals("HELLO")) {
			System.out.println("Received Packet type:" + pktType);
			String t = "";
			for (int i = 37; i < 41; i++) {
				t += (char) rxPkt[i];
			}
			n1Id = Integer.parseInt(t);
			handShake1 = true;
			System.out.println("Shook hands with "+n1Id);
		}
		else {
			String len = ""+(char)rxPkt[0]+(char)rxPkt[1];
			String type = ""+(char)rxPkt[2];
			if (type.equals('5')) {
				String bitField = "";
				rcvBitArray = new int[10];
				int j = 0;
				for (int i = 3;i < rxPkt.length;i++) {
					bitField += (char) rxPkt[i];
					rcvBitArray[j] = (int) rxPkt[i];
					j++;
				}
				System.out.println("Received bitfield");
				System.out.println("Length: "+len+" Type: "+type+" Message: "+bitField);	
			}
			else if (type.equals('2')) {
				System.out.println("Received Interested Message");
			}
		}
	}

	public static void sendByte(byte[] pkt)throws Exception
	{
		String ip = remoteIp;
		int port = remotePort;
		@SuppressWarnings("resource")
		Socket clientSocket = new Socket(ip, port);
		OutputStream outToServer = clientSocket.getOutputStream();
		outToServer.write(pkt);
		
	}
	public static void sendHello()throws Exception
	{
		System.out.println("Came to hello method");
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
