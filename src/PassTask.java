import java.util.Arrays;


public class PassTask {
	
	private final int MAX_PASS_LENGTH_BY_THREAD = 5;
	
	private int passLength;
	private boolean useLowerCase;
	private boolean useUpperCase;
	private boolean useNumbers;
	private boolean useSpecialCharacters;
	private char[] specialCharsUsed;
	private String prefix = "";
	private String hashAlgorithm = "MD5";
	private boolean done = false;
	
	public PassTask(int length, boolean lowercase, boolean uppercase, boolean numbers, boolean specialChars, char[] specialCharsUsed, String hashAlgorithm){
		this.passLength = length;
		this.useLowerCase = lowercase;
		this. useUpperCase = uppercase;
		this.useNumbers = numbers;
		this.useSpecialCharacters = specialChars;
		this.specialCharsUsed = specialCharsUsed;
		this.hashAlgorithm = hashAlgorithm;
		
		int n =this.passLength - MAX_PASS_LENGTH_BY_THREAD;
		char first = this.getFirstChar(lowercase,uppercase, numbers);
		
		if(n>0){
			for(int i =0; i< n; i++){
				this.prefix = this.prefix+first;
			}
			
		}
		
	}
	
	public PassTask(int length, boolean lowercase, boolean uppercase, boolean numbers){
				
		this(length,lowercase,uppercase,numbers, false, null, "MD5");
		
		
	}
	
	


	private char getFirstChar(boolean lowercase, boolean uppercase, boolean numbers){
		if(lowercase){
			return 'a';
		}else if(uppercase){
			return 'A';
		}else if(numbers){
			return '0';
		}else{
			return 'a';
		}
		
	}
	

	public char[] getAvailableCharacters(){
		
		int n = 0;
		if(this.useLowerCase){
			n = n+ 26;
		}
		if(this.useUpperCase){
			n = n + 26;
		}
		if(this.useNumbers){
			n = n + 10;
		}
		if(this.useSpecialCharacters){
			n = n + this.specialCharsUsed.length;
		}
		
		char[] availableChars = new char[n];
		int j =0;

		if(this.useLowerCase){
			int currentChar = 97;
			for(int i =0; i<26; i++){
				availableChars[j]= (Character.toChars(currentChar))[0];
				j++;
			}
			
		}
		if(this.useUpperCase){
			int currentChar = 65;
			for(int i =0; i<26; i++){
				availableChars[j]= (Character.toChars(currentChar))[0];
				j++;
			}
		}
		if(this.useNumbers){
			int currentChar = 48;
			for(int i =0; i<10; i++){
				availableChars[j]= (Character.toChars(currentChar))[0];
				j++;
			}
		}
		if(this.useSpecialCharacters){
			for(int i =0; i<this.specialCharsUsed.length; i++){
				availableChars[j] = specialCharsUsed[i];
				j++;
			}
		}
		
		return availableChars;
	}
	

	
	public PassTaskThread nextThread(){
		
		String currentPrefix = new String(this.prefix);
		this.updatePrefix();
		
		return new PassTaskThread(currentPrefix, this.passLength, this.getAvailableCharacters(), this.hashAlgorithm);
		
	}
	
	public boolean isDone(){
		
		return this.done;
	}
	
	private void updatePrefix(){
		
		char[] availableChars = this.getAvailableCharacters();
		int n = availableChars.length;
		char[] prefixArray = this.prefix.toCharArray();
		boolean done = true;
		for(int i = prefixArray.length; i>0;i--){
			if(prefixArray[i]!=availableChars[n]){
				done = false;
				int index = Arrays.binarySearch(availableChars, prefixArray[i]);
				prefixArray[i]=availableChars[index+1];
				return;
			}
		}
		if(done){
			this.done =true;
		}
	}

}
