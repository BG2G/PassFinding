package communication.core;

import java.io.IOException;
import java.util.Iterator;

import mainPackage.Control;

//Listening to the machines/master
public class Listening extends Thread{

	public void  listen(){
		
		this.setDaemon(true);
		this.start();
		
	}
	
	public void run(){
		
		while(true){
			Control ctrl = Control.getControl();
			Iterator<Machine> itMachines = ctrl.getMachines().iterator();
			
			while(itMachines.hasNext()){
				Machine machine = itMachines.next();
				try {
					if(machine.isDataAvailable()){
						machine.receiveMessage();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			Machine master = ctrl.getMaster();
			if(master!= null){
				try {
					if(master.isDataAvailable()){
						master.receiveMessage();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
