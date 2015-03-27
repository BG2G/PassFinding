
public class PassTasks {
	
	private int passLength;
	private boolean useLowerCase;
	private boolean useUpperCase;
	private boolean useNumbers;
	private boolean useSpecialCharacters;
	private char[] specialCharsUsed;
	
	public PassTasks(int length, boolean lowercase, boolean uppercase, boolean numbers, boolean specialChars, char[] specialCharsUsed){
		this.passLength = length;
		this.useLowerCase = lowercase;
		this. useUpperCase = uppercase;
		this.useNumbers = numbers;
		this.useSpecialCharacters = specialChars;
		this.specialCharsUsed = specialCharsUsed;
	}
	
	public PassTasks(int length, boolean lowercase, boolean uppercase, boolean numbers){
		this.passLength = length;
		this.useLowerCase = lowercase;
		this. useUpperCase = uppercase;
		this.useNumbers = numbers;
		this.useSpecialCharacters = false;
		this.specialCharsUsed = null;
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
	
	public int getPassLegnth(){
		return passLength;
		
	}

}
