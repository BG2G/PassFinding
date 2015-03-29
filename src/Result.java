
public class Result {

	
	public static void matchFound(String matchingPassword, byte[]hash){
		//TODO link to GUI
		System.out.print("Password found : ");
		System.out.print(matchingPassword);
		System.out.print(", hash : ");
		System.out.println(hash);
	}
	
}
