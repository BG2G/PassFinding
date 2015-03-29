import java.util.Arrays;
import java.util.concurrent.Semaphore;


public class PassTaskThread extends Thread{
	
	private String prefix;
	private int passLength;
	private char[] availableChars;
	private Hash hashFunction;
	private PassHashData passHashData;
	private Semaphore semaphore;
	
	
	public PassTaskThread(String prefix, int length, char[] availableChars, String hashAlgorithm){
		super();
		this.prefix =prefix;
		this.passLength = length;
		this.availableChars = availableChars;
		
		this.hashFunction = new Hash(hashAlgorithm);
		
		
	}
	
	private void nestedLoops (String password, int level){
		if(level == 0){
			//password generated, hashing and testing
			byte[] passHash = this.hashFunction.hashing(password);
			int index = Arrays.binarySearch(this.passHashData.passHashList, passHash);
			if(index>=0){
				//Found
				Result.matchFound(password, passHash);
			}
		}else{
			for(int i = 0; i<this.availableChars.length;i++){
				
				nestedLoops(password+availableChars[i], level -1);
			}
			
		}
		
	}
	
	public void setPassHashData(PassHashData phd){
		this.passHashData = phd;
	}
	
	public void setSemaphore(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	
	public void run(){
	
		int n = this.passLength - prefix.length();
		String pass = new String(prefix);
		if(n>0){			
			nestedLoops(pass, n);		
		}
		this.semaphore.release();
				
	}

}
