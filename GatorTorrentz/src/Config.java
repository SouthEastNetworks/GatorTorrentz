import java.io.BufferedReader;
import java.io.FileReader;


public class Config {
	
	public void readCommon() throws Exception
	{
		int NumberOfPreferredNeighbors,UnchokingInterval;
		
		String line;
		String fname = "src/configFiles/Common.cfg";
        FileReader fileReader = 
                new FileReader(fname);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	String[] splits = line.split(" ");
            	if(splits[0].equals("NumberOfPreferredNeighbors"))
            	{
            		NumberOfPreferredNeighbors=Integer.parseInt(splits[0]);
            	}
            	else if(splits[0].equals("UnchokingInterval"))
            	{
            		UnchokingInterval=Integer.parseInt(splits[0]);
            	}
            }	
            bufferedReader.close();	
	}

}
