
public class Interested {
	static int messageLength = 0;
	static int messageType = 2;
	public static void shouldSendInterested() {
		int[] piecesPresent = Filecheck.getChunkArray();
		int[] neighborPieces = FileShare.rcvBitArray;
		for (int i = 0; i < piecesPresent.length; i++) 
		{
			if (piecesPresent[i] == 0 && neighborPieces[i] == 1) {
				sendInterestedMessage();
				break;
			}
		}
	}
	public static void sendInterestedMessage() {
		String bitStr = ""+messageLength+messageType;
		byte[] byteChunk = bitStr.getBytes();
		System.out.println("Interested Message "+new String(byteChunk));
	}
}
