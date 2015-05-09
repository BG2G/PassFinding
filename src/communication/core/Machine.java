package communication.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Machine {


	private InetAddress ip;
	private int port = 30318;
	private int activeStrength =1;
	private Socket socket = null;
	private long key;
	private OutputStream os;
	private InputStream is;
	private int onGoingTasks = 0;
	private String name="";
	
	public Machine(InetAddress IP, long communicationKey, String name){
		this.ip = IP;
		this.key = communicationKey;
		this.name = name;
	}
	
	public Machine(InetAddress IP, long communicationKey){
		this.ip = IP;
		this.key = communicationKey;
	}
	
	public Machine(byte[]  addr, long communicationKey) throws UnknownHostException{
		this.ip = InetAddress.getByAddress(addr);
		this.key = communicationKey;
	}
	
	public Machine(Socket socket, long communicationKey){
		key = communicationKey;
		this.socket = socket;
	}
	
	public void connect() throws IOException{
		socket = new Socket(ip,port);
		os = socket.getOutputStream();
		is =socket.getInputStream();
	}
	
	public void setStrength(int strength){
		activeStrength = strength;
	}
	
	public int getStrenght(){
		return this.activeStrength;
	}
	
	public long getKey(){
		return key;
	}
	public void setName(String name) {
		this.name =name;	
	}
	public String getName(){
		return name;
	}
	public boolean isEnabled(){
		return !(socket==null);
	}
	
	public void send(byte[] payload) throws IOException{

		//TO DO :  Encryption
		os.write(payload);
		os.flush();
	}
	
	public void closeConnection() throws IOException{
		is.close();
		os.close();
		socket.close();
	}
	
	public void receiveMessage() throws IOException{
		// TO DO  : Decryption
		int type = is.read();
		int length = is.read()*256 +is.read();
		byte[] message = new byte[length-2];
		int n = is.read(message);
		
		switch(type){
		case StatusMessage.STATUS:
			StatusMessage.translate(message, this,n);
			break;
		case DataMessage.DATA:
			DataMessage.translate(message, this,n);
			break;
		case TaskMessage.TASK:
			TaskMessage.translate(message, this,n);
			break;
		}
		
	}
	
	public void incrementOngoingTasks(){
		onGoingTasks++;
	}
	public void decrementOngoingTasks(){
		onGoingTasks--;
	}
	public int getOngoingTasks(){
		return onGoingTasks;
	}
	
	
}
