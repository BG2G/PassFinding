package communication.core;
import java.io.IOException;

import mainPackage.*;

public class TaskMessage {
	
	public final static int  TASK = 3;
	private final static int MD5 = 1;
	private final static int SHA1 = 2;
	private final static int ADDTASK = 5;
	private final static int RESULT  = 9;
	private final static int TASK_STARTED = 10;
	private static final byte NOT_FOUND = 7;
	private static final byte RESULT_FOUND = 8;

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
			int id;
			
			id = message[1]*256 + message[2];
			
			
			if(message[3] == MD5){
				algo = new String ("MD5");
			}else if (message[3] == SHA1){
				algo = new String("SHA1");
			}
			n = message[4];
			
			int temp = message[5];
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
			
			prefixLength = message[6];
			for(int i = 0; i< prefixLength; i++){
				startingPrefix = startingPrefix + (char) message[i+7];
				endingPrefix = endingPrefix + (char) message[i+prefixLength+7];
			}
			
			PassTask newTask;
			
			if(specialChars){
				char[] specialCharsUsed = new char[length - 7 - 2*prefixLength];
				for (int i = 0; i< specialCharsUsed.length; i++){
					specialCharsUsed[i] = (char) message[i + 7 + 2*prefixLength];
				}
				
				newTask = new PassTask(n, lowercase, uppercase, numbers, specialChars, specialCharsUsed, algo, startingPrefix, endingPrefix, id);
			}else{
				newTask = new PassTask(n, lowercase, uppercase, numbers, specialChars, null, algo, startingPrefix, endingPrefix,id);
			}
			
			Control.getControl().addTask(newTask);
		}else if(message[0] == RESULT){
			int id;
			id = message[1]*256 + message[2];
			if(message[3] == NOT_FOUND){
				PassTask task =Control.getControl().findTaskbyId(id);
				task.setDone(true);
				task.setStatus(PassTask.COMPLETED);
				//TO DO : update GUI
			}else if(message[3] == RESULT_FOUND){
				String result ="";
				for(int i = 4; i<length;i++){
					result = result+ (char) message[i];
				}
				//TO DO : update GUI
				
			}
		}else if(message[0] == TASK_STARTED){
			int id = message[1]*256 +message[2];
			Control ctrl = Control.getControl();
			ctrl.findTaskbyId(id).setStatus(PassTask.STARTED);
		}
	}
	
	public static void sendNewTask(PassTask task, Machine machine) throws IOException{
		int n = 10+2*task.getPrefixSize();
		if(task.getSpecialChars()!=  null){
			n = n + task.getSpecialChars().length;
		}
		byte[] message = new byte[n];
		
		message[0] = TASK;
		message[1] = (byte) (n/256);
		message[2] = (byte) (n%256);
		
		message[3] = ADDTASK;
		int id = task.getId();
		message[4] = (byte) (id/256);
		message[5] = (byte) (id%256);
		if(task.getAlgorithm() == "MD5"){
			message[6] = MD5;
		}else if(task.getAlgorithm() == "SHA1"){
			message[6] = SHA1;
		}
		message[7] = (byte) task.getPassLength();
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
		message[8] = (byte) temp;
		message[9] = (byte) task.getPrefixSize();
		char[] startingPrefixArray = task.getStartingPrefix().toCharArray();
		char[] endingPrefixArray = task.getEndingPrefix().toCharArray();
		
		for(int i = 0; i< task.getPrefixSize(); i++){
			message[10+i]= (byte) startingPrefixArray[i];
			message[10+i+task.getPrefixSize()] = (byte) endingPrefixArray[i];
		}
	
		for(int i = 0; i<task.getSpecialChars().length;i++){
			message[10+2*task.getPrefixSize()+i] = (byte) task.getSpecialChars()[i];  
		}
		
		if(!machine.isEnabled()){
			machine.connect();
		}
		machine.send(message);
	}
	
	public void sendResult(boolean found, String result, PassTask task, Machine master) throws IOException{
		int n = 7;
		if(found){
			n = n + result.length();
		}
		byte[] message = new byte[n];
		message[0] = TASK;
		message[1] = 0;
		message[2] =(byte) n;
		message[3] = RESULT;
		message[4] = (byte) (task.getId()/256);
		message[5] = (byte) (task.getId()%256);
		if(found){
			message[6] = RESULT_FOUND;
			for(int i =7; i<7+result.length(); i++){
				message[i] = (byte) result.charAt(i);
			}
		}else{
			message[6] = NOT_FOUND;
		}
		if(!master.isEnabled()){
			master.connect();
		}
		master.send(message);
		
	}
	
	public void sendTaskStarted(PassTask task, Machine master) throws IOException{
		byte[] message =  new byte[6];
		message[0] = TASK;
		message[1] = 0;
		message[2] = 6;
		message[3] = TASK_STARTED;
		message[4] = (byte) (task.getId()/256);
		message[5] = (byte) (task.getId()%256);
		
		if(!master.isEnabled()){
			master.connect();
		}
		master.send(message);
	}
}
