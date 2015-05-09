package mainPackage;

public class Result {
	
	private String password;
	private String hash;
	private int seconds;
	
	public Result(String password, String algo){
		this.password = password;
		hash = stringHash(password,algo);
		seconds= (System.currentTimeMillis()-Control.getControl().getInitialTimer())/1000;
	}

	private String stringHash(String password, String algo){
		String hash = "";
		Hash hasher = new Hash(algo);
		byte[] byteHash = hasher.hashing(password);
		for(int i = 0; i< byteHash.length; i++){
			hash =hash+ (char) byteHash[i];
		}
		return hash;
	}
	public String getPassword(){
		return password;
	}
	public String getHash(){
		return hash;
	}
	public long getSeconds(){
		return seconds;
	}
	
	
	public static void matchFound(String matchingPassword, byte[]hash){
		//TODO link to GUI
		System.out.print("Password found : ");
		System.out.print(matchingPassword);
		System.out.print(", hash : ");
		Auxiliary.printByteArray(hash);
	}
	
	public static void taskStarted(PassTask task){
		System.out.println(task.toString() + " started");
	}
	
	public static void taskFinished(PassTask task){
		System.out.println(task.toString() + " finished");
	}
	
	public static void taskOnGoing(PassTask task){
		System.out.println(task.toString() + "; all threads started");
	}
}
