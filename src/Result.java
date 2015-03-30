
public class Result {

	
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
