
public class PassTaskThread extends Thread{
	
	private String prefix;
	private int passLength;
	private int[] index;
	private char[] availableChars;
	private Hash hashFunction;
	
	
	public PassTaskThread(String prefix, int length, char[] availableChars){
		super();
		this.prefix =prefix;
		this.passLength = length;
		this.availableChars = availableChars;
		
		this.hashFunction = new Hash();
		
		int n = length - prefix.length();
		
		if(n>0){
			this.index = new int[n];
		}
	}
	
	private void nestedLoops (String password, int level){
		if(level == 0){
			//password generated, hashing and testing
			byte[] passHash = this.hashFunction.hashing(password);
			//TODO Comparing to the list of hash
		}else{
			for(int i = 0; i<this.availableChars.length;i++){
				
				nestedLoops(password+availableChars[i], level -1);
			}
			
		}
		
	}
	
	
	public void run(){
		
		int n = this.passLength - prefix.length();
		String pass = new String(prefix);
		if(n>0){
			
			nestedLoops(pass, n);
				
			
		}
	}

}
