package communication.core;

import java.io.IOException;

public class DataMessage {
	
	public final static int DATA = 2;
	private final static int HASHFILE_MD5 = 0;
	private final static int HASHFILE_SHA1 = 1;

	public static void translate(byte[] message, Machine machine, int length) {
		switch(message[0]){
			case HASHFILE_MD5 :
				byte[][] hashdata = new byte[(length-1)/16][16];
				for(int i = 0; i<hashdata.length; i++){
					mainPackage.Auxiliary.transferBytes(message, hashdata[i], 1+16*i,0, 16);
				}
				mainPackage.PassHashData data = new mainPackage.PassHashData("MD5", hashdata);
				mainPackage.Control.getControl().setHashData(data);
				break;
			
			case HASHFILE_SHA1 :
				byte[][] hashdata1 = new byte[(length-1)/20][20];
				for(int i = 0; i<hashdata1.length; i++){
					mainPackage.Auxiliary.transferBytes(message, hashdata1[i], 1+20*i,0, 20);
				}
				mainPackage.PassHashData data1 = new mainPackage.PassHashData("SHA1", hashdata1);
				mainPackage.Control.getControl().setHashData(data1);
				break;
			}
	}
	
	public static void sendDataMessage(mainPackage.PassHashData data, Machine machine) throws IOException{
		byte[][] passHashList = data.passHashList;
		String algo =   data.getAlgorithm();
		byte[] message = null;
		if(algo == "MD5"){
			message = new byte[passHashList.length*16+4];
			message[3] = HASHFILE_MD5;
			for(int i=0;i<passHashList.length; i++){
				mainPackage.Auxiliary.transferBytes(passHashList[i], message, 0, 4+i*16, 16);
			}
		}else if(algo =="SHA1"){
			message = new byte[passHashList.length*20+4];
			message[3] = HASHFILE_SHA1;
			for(int i=0;i<passHashList.length; i++){
				mainPackage.Auxiliary.transferBytes(passHashList[i], message, 0, 4+i*20, 20);
			}
		}
		
		
		message[0] = DATA;
		int n = message.length;
		message[1] = (byte) (n/256);
		message[2] = (byte) (n%256);
		if(!machine.isEnabled()){
			machine.connect();
		}
		machine.send(message);
	}
}
