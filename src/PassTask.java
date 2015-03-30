import java.util.Arrays;


public class PassTask {
	
	private final int MAX_PASS_LENGTH_BY_THREAD_UNICASE = 5;
	private final int MAX_PASS_LENGTH_BY_THREAD_MULTICASE = 4;
	
	private int passLength;
	private boolean useLowerCase;
	private boolean useUpperCase;
	private boolean useNumbers;
	private boolean useSpecialCharacters;
	private char[] specialCharsUsed;
	private String prefix = "";
	private char[] availableChars;
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
		
		initiateAvailableCharacters(lowercase, uppercase, numbers, specialChars, specialCharsUsed);
		
		int n;
		if(this.useLowerCase&&this.useUpperCase){
			n = this.passLength - MAX_PASS_LENGTH_BY_THREAD_MULTICASE;
		}else{
			n = this.passLength - MAX_PASS_LENGTH_BY_THREAD_UNICASE;
		}
		char first = this.getAvailableCharacters()[0];
		
		
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
	

	public void initiateAvailableCharacters(boolean lowercase, boolean uppercase, boolean numbers, boolean specialChars, char[] specialCharsUsed){
		
		int n = 0;
		if(lowercase){
			n = n+ 26;
		}
		if(uppercase){
			n = n + 26;
		}
		if(numbers){
			n = n + 10;
		}
		if(specialChars){
			n = n + specialCharsUsed.length;
		}
		
		this.availableChars = new char[n];
		int j =0;

		if(lowercase){
			int currentChar = 97;
			for(int i =0; i<26; i++){
				this.availableChars[j]= (Character.toChars(currentChar+i))[0];
				j++;
			}
			
		}
		if(uppercase){
			int currentChar = 65;
			for(int i =0; i<26; i++){
				this.availableChars[j]= (Character.toChars(currentChar+i))[0];
				j++;
			}
		}
		if(numbers){
			int currentChar = 48;
			for(int i =0; i<10; i++){
				this.availableChars[j]= (Character.toChars(currentChar+i))[0];
				j++;
			}
		}
		if(specialChars){
			for(int i =0; i<specialCharsUsed.length; i++){
				this.availableChars[j] = specialCharsUsed[i];
				j++;
			}
		}
		
		Arrays.sort(this.availableChars);
	}
	
	public char[] getAvailableCharacters(){
		return this.availableChars;
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
		for(int i = prefixArray.length-1; i>=0;i--){
			if(prefixArray[i]!=availableChars[n-1]){
				done = false;
				int index = Arrays.binarySearch(availableChars, prefixArray[i]);
				
				prefixArray[i]=availableChars[index+1];
				this.prefix = new String(prefixArray);
				return;
			}
		}
		if(done){
			this.done =true;
		}
	}
	
	public String toString(){
		String description = "PassFinding Task : "+this.passLength+" characters";
		
		if(this.useLowerCase){
			description = description + ", lowercase";
		}
		if(this.useUpperCase){
			description = description + ", uppercase";
		}
		if(this.useNumbers){
			description = description +", numbers";
		}
		if(this.useSpecialCharacters){
			description = description +", "+ this.specialCharsUsed.length + " special characters";
		}
		
		return description;
	}

}
