package mainPackage;
import java.util.Arrays;


public class PassTask {
	
	private final int MAX_PASS_LENGTH_BY_THREAD_UNICASE = 5;
	private final int MAX_PASS_LENGTH_BY_THREAD_MULTICASE = 4;
	
	private int id;
	
	private int passLength;
	private boolean useLowerCase;
	private boolean useUpperCase;
	private boolean useNumbers;
	private boolean useSpecialCharacters;
	private char[] specialCharsUsed= null;
	private String prefix = "";
	private String startingPrefix = "";
	private String endingPrefix ="";
	private char[] availableChars;
	private String hashAlgorithm = "MD5";
	private boolean done = false;
	

	
	
	public PassTask(int length, boolean lowercase, boolean uppercase, boolean numbers, boolean specialChars, char[] specialCharsUsed, String hashAlgorithm, String startingPrefix, String endingPrefix, int ID){
		this.passLength = length;
		this.useLowerCase = lowercase;
		this.useUpperCase = uppercase;
		this.useNumbers = numbers;
		this.useSpecialCharacters = specialChars;
		this.specialCharsUsed = specialCharsUsed;
		this.hashAlgorithm = hashAlgorithm;
		this.endingPrefix = endingPrefix;
		this.startingPrefix = startingPrefix;
		this.id =ID;
		
		initiateAvailableCharacters(lowercase, uppercase, numbers, specialChars, specialCharsUsed);
		
		int n;
		if(this.useLowerCase&&this.useUpperCase){
			n = this.passLength - MAX_PASS_LENGTH_BY_THREAD_MULTICASE;
		}else{
			n = this.passLength - MAX_PASS_LENGTH_BY_THREAD_UNICASE;
		}
		char first = this.getAvailableCharacters()[0];
		n = n - startingPrefix.length();
		prefix = new String(startingPrefix);
		
		if(n>0){
			for(int i =0; i< n; i++){
				this.prefix = this.prefix+first;
			}
			
		}
		
	}
	public PassTask(int length, boolean lowercase, boolean uppercase, boolean numbers, boolean specialChars, char[] specialCharsUsed, String hashAlgorithm, String startingPrefix, String endingPrefix){
		
		this(length, lowercase, uppercase, numbers, specialChars, specialCharsUsed, hashAlgorithm, startingPrefix, endingPrefix, Control.getControl().getNextId());
	}
	
	public PassTask(int length, boolean lowercase, boolean uppercase, boolean numbers, boolean specialChars, char[] specialCharsUsed, String hashAlgorithm){
		this(length, lowercase, uppercase, numbers, specialChars, specialCharsUsed, hashAlgorithm, "", "");
	}
	
	public PassTask(int length, boolean lowercase, boolean uppercase, boolean numbers){
				
		this(length,lowercase,uppercase,numbers, false, null, "MD5");
		
		
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

	public int getPassLength(){
		return passLength;
	}
	
	public boolean usingLowercase(){
		return useLowerCase;
	}
	public boolean usingUppercase(){
		return useUpperCase;
	}
	public boolean usingNumbers(){
		return useNumbers;
	}
	public boolean usingSpecialChars(){
		return useSpecialCharacters;
	}
	public String getStartingPrefix(){
		return startingPrefix;
	}
	public int getId(){
		return this.id;
	}
	public void setDone(boolean b){
		this.done = b;
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
		char[] endingPrefixArray = this.endingPrefix.toCharArray();
		
		boolean done = true;
		for(int i = prefixArray.length-1; i>=endingPrefix.length();i--){

			if(prefixArray[i]!=availableChars[n-1]){
				done = false;
				int index = Arrays.binarySearch(availableChars, prefixArray[i]);
				
				prefixArray[i]=availableChars[index+1];
				this.prefix = new String(prefixArray);
				return;
			}
		
		}
		for(int i = 0; i<endingPrefix.length(); i++){
			if(prefixArray[i] != endingPrefixArray[i]){
				//Not done
				done =false;
				// updating the first part of the prefix
				nextFirstPartPrefix(prefixArray, endingPrefix.length(), availableChars);
				
				this.prefix = new String(prefixArray);
				return;
			}
		}
		if(done){
			this.done =true;
		}
	}
	
	public int getPrefixSize(){
		return endingPrefix.length();
	}
	
	public String getEndingPrefix(){
		return endingPrefix;
	}
	
	public char[] getSpecialChars(){
		return specialCharsUsed;
	}
	public String getAlgorithm(){
		return hashAlgorithm;
	}
	private void nextFirstPartPrefix(char[] current, int n, char[] options){
		for(int i = n-1; i>=0; i--){
			if(current[i] != options[options.length -1]){
				int index = Arrays.binarySearch(options, current[i]);				
				current[i]=options[index+1];
				return;
			}
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
	
	public String getname(){
		String name = "";
		
		if(this.useLowerCase){
			name = name + "L";
		}
		if(this.useUpperCase){
			name = name + "U";
		}
		if(this.useNumbers){
			name = name + "N";
		}
		if(this.useSpecialCharacters){
			name = name + "S";
		}
		name = name + passLength;
		return name;
	}

}
