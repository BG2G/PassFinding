package communication.core;
import java.io.IOException;

import mainPackage.*;

public class TaskMessage {
	
	public final static int  TASK = 3;
	private final static int MD5 = 1;
	private final static int SHA1 = 2;
	private final static int ADDTASK = 5;
	private final static int RESULT  = 9;

	public static void translate(byte[] message, Machine machine, int length) {
		
		if(message[0] == ADDTASK){
			int n;
			boolean lowercase =false;
			boolean uppercase = false;
			boolean numbers = false;
			boolean specialChars = false;
			int prefixLength;
			String algo = null;
			String startingPrefix ="";
			String endingPrefix = "";
			
			if(message[1] == MD5){
				algo = new String ("MD5");
			}else if (message[0] == SHA1){
				algo = new String("SHA1");
			}
			n = message[2];
			
			int temp = message[3];
			if(temp%2 == 1){
				lowercase = true;
			}
			temp = temp/2;
			if(temp%2 == 1){
				uppercase = true;
			}
			temp = temp/2;
			if(temp%2 == 1){
				numbers = true;
			}
			temp = temp/2;
			if(temp%2 == 1){
				specialChars = true;
			}
			
			prefixLength = message[4];
			for(int i = 0; i< prefixLength; i++){
				startingPrefix = startingPrefix + (char) message[i+5];
				endingPrefix = endingPrefix + (char) message[i+prefixLength+5];
			}
			
			PassTask newTask;
			
			if(specialChars){
				char[] specialCharsUsed = new char[length - 5 - 2*prefixLength];
				for (int i = 0; i< specialCharsUsed.length; i++){
					specialCharsUsed[i] = (char) message[i + 5 + 2*prefixLength];
				}
				
				newTask = new PassTask(n, lowercase, uppercase, numbers, specialChars, specialCharsUsed, algo, startingPrefix, endingPrefix);
			}else{
				newTask = new PassTask(n, lowercase, uppercase, numbers, specialChars, null, algo, startingPrefix, endingPrefix);
			}
			
			Control.getControl().addTask(newTask);
		}
		if(message[0] == RESULT){
			//TO DO
		}
	}
	
	public static void sendNewTask(PassTask task, Machine machine) throws IOException{
		int n = 5+2*task.getPrefixSize();
		if(task.getSpecialChars()!=  null){
			n = n + task.getSpecialChars().length;
		}
		byte[] message = new byte[n];
		
		message[0] = ADDTASK;
		if(task.getAlgorithm() == "MD5"){
			message[1] = MD5;
		}else if(task.getAlgorithm() == "SHA1"){
			message[1] = SHA1;
		}
		message[2] = (byte) task.getPassLength();
		int temp = 0;
		if(task.usingLowercase()){
			temp = temp +1;
		}
		if(task.usingUppercase()){
			temp = temp +2;
		}
		if(task.usingNumbers()){
			temp = temp +4;
		}
		if(task.usingSpecialChars()){
			temp = temp +8;
		}
		message[3] = (byte) temp;
		message[4] = (byte) task.getPrefixSize();
		char[] startingPrefixArray = task.getStartingPrefix().toCharArray();
		char[] endingPrefixArray = task.getEndingPrefix().toCharArray();
		
		for(int i = 0; i< task.getPrefixSize(); i++){
			message[5+i]= (byte) startingPrefixArray[i];
			message[5+i+task.getPrefixSize()] = (byte) endingPrefixArray[i];
		}
	
		for(int i = 0; i<task.getSpecialChars().length;i++){
			message[5+2*task.getPrefixSize()+i] = (byte) task.getSpecialChars()[i];  
		}
		
		if(!machine.isEnabled()){
			machine.connect();
		}
		machine.send(message);
	}
}
