import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class PassHashData {
	
	public byte[][] passHashList;
	public String hashAlgorithm = "MD5";
	
	public PassHashData(String filename, String algo){
		int n =0;
		
	    try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
	       
	        String line = br.readLine();
	        n=1;
	        List<String> lineList = new LinkedList<String>();

	        while (line != null) {
	            
	            line = br.readLine();	            
	            n++;
	            lineList.add(line);
	            
	        }
	        
	        int length = lineList.get(0).getBytes().length;
	        this.passHashList = new byte[n][length];
	        
	        for(int i =0; i<n;i++){
	        	this.passHashList[i] = lineList.get(i).getBytes();	    	
	        }
	        
	        Arrays.sort(this.passHashList);
	        
	    } catch (FileNotFoundException e) {
			// TODO Ask the file again
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
	    this.hashAlgorithm = algo;
	        
		
	}
	
	public PassHashData(String filename){
		this(filename, "MD5");
	}
	
	public PassHashData(String hashAlgorithm, byte[][] passHashList){
		this.hashAlgorithm = hashAlgorithm;
		this.passHashList = passHashList;
	}
	
	public void setHashAlgorithm (String algo){
		this.hashAlgorithm = algo;
	}

}
