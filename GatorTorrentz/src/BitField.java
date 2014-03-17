public class BitField 
{
	int messageLength;
	int messageType;
	int[] bitFieldArray;
	public static byte[] arrayToByte(int[] bitArray) {
		// TODO add a method in Filecheck to return the array
		bitArray = Filecheck.getChunkArray();
		int numParts = bitArray.length;
		int i = 0;
		String bitStr = "";
		while (i < numParts) {
			bitStr += bitArray[i];
			i++;
		}
		byte[] byteChunk = bitStr.getBytes();
		System.out.println(new String(byteChunk));
		return byteChunk;
	}
	public static void sendByteBitField() {
		int[] bitArray = new int[10];
		byte[] bitFieldByte = arrayToByte(bitArray);
		FileShare.sendByte(bitFieldByte);
	}
}
