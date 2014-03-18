

public class Eencoding {

	public static void main(String[] args) {
		String header = "ACK";
		String message = "ACK Hi Akshay";
		String decode="";
		char[] buffer = header.toCharArray();
		byte[] buff2 = message.getBytes();
		byte[] b = new byte[buffer.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) buffer[i]; 
		}
		char c;
		for (byte bp : b)
		{
			c = (char)bp;
			System.out.println("Byte is " + bp);
			System.out.println("Char is "+ c);
			decode +=c;
		}
		System.out.println("String is "+ decode);
		
		String file_string = "";
	    for(int i = 0; i < 3; i++)
	    {
	        file_string += (char)buff2[i];
	    }
	    
	    System.out.println("Header is:"+ file_string);
	    file_string = "";
	    for(int i = 3; i < buff2.length; i++)
	    {
	        file_string += (char)buff2[i];
	    }
	    System.out.println("new message is:"+ file_string);
	}
}
