package softInterface;

public class Update {

	public static final int STARTING = 1; 
	public static final int DONE = 2; 

	
	public static void updateLocalTask(String prefix, int length, int differentChars, int code){
		String name ="";
		name = name+prefix + differentChars +"-" + length;
		String status = "";
		
		
		//update le tableau
		
		if (code ==STARTING){
			status = "In Progress";									
		}else if (code == DONE){
			status = "Done";
		}
		
		
		
	}
	
	public static void updateGlobalTask(String name, int code){

		String status = "";
		
		
		//update le tableau
		
		if (code ==STARTING){
			status = "In Progress";									
		}else if (code == DONE){
			status = "Done";
		}
		
		
		
	}
}
