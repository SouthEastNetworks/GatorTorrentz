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


	public static void main(String[] args) throws Exception {

		//pavan : 192.168.0.20
		Server fs = new Server(serverPort);
		(new Thread(fs)).start();
		//fs.run();

		Thread.sleep(2000);
		
		sendHello();
		BitField.sendByteBitField();
		
	}

	public static void rxPacket(byte[] rxPkt) {
		String pktType = "";
		for (int i = 0; i < 5; i++) {
			pktType += (char) rxPkt[i];
		}
		System.out.println("Received Packet type:" + pktType);
		if (pktType.equals("HELLO")) {
			String t = "";
			for (int i = 37; i < 41; i++) {
				t += (char) rxPkt[i];
			}
			n1Id = Integer.parseInt(t);
			handShake1 = true;
			System.out.println("Shook hands with "+n1Id);
		}
		else {
			String len = ""+rxPkt[0]+rxPkt[1];
			String type = ""+rxPkt[2];
			String bitField = "";
			for (int i = 3;i < rxPkt.length;i++) {
				bitField += (char) rxPkt[i];
			}
			System.out.println("Received bitfield");
			System.out.println("Length: "+len+" Type: "+type+" Message: "+bitField);
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
