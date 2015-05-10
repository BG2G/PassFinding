package mainPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

import communication.core.Listening;
import communication.core.Machine;
import communication.core.TaskMessage;



public class Main {
	
	private final static int MAX_TASKS_BY_CLIENT = 15;
	private static int concurrentThreads = 4;

	public static void main(String[] args) {
		
		

		Test.testHashfileGeneration();
		
		try {
			Test.test1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void startMaster(){
		
		
		PassHashData data = new PassHashData("hashData.dat");
		Control.getControl().setHashData(data);
		
		// Listen for incoming communication
		Listening listener = new Listening();
		listener.listen();
			
		
	}
	
	public static void startSlave(){
		try {
			ServerSocket masterListener = new ServerSocket(Machine.port);
			Socket socket = masterListener.accept();
			
			Control ctrl = Control.getControl();
			Machine master = new Machine(socket,ctrl.getKey());
			ctrl.setMaster(master);
			
			Listening listener = new Listening();
			listener.listen();
			
			masterListener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void startLocalSearch(int concurrentThreads){
		Control ctrl = Control.getControl();
		if(!ctrl.isSearchOngoing()&&ctrl.nextLocalTaskExists()){
			Runnable r = new Runnable(){
				public void run(){
					try {
						runSearch(concurrentThreads);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			new Thread(r).start();
		}
	}
		
	public static void runSearch(int concurrentThreads) throws InterruptedException{
		
	
		Semaphore semaphore = new Semaphore(concurrentThreads);
		Control ctrl = Control.getControl();	
		ctrl.setSearchOngoing(true);
		
		while(ctrl.nextLocalTaskExists()){
			PassTask task = ctrl.getNextLocalTask();
		
			while(!task.isDone()){
				
				semaphore.acquire();
				PassTaskThread newThread = task.nextThread();
				newThread.setSemaphore(semaphore);
				newThread.setPassHashData(ctrl.getHashData());
				newThread.setDaemon(true);
				newThread.start();
				
			}
			Result.taskOnGoing(task);
		}
		
		ctrl.setSearchOngoing(false);
	}
	
	public void scheduleTasks() throws IOException{
		Control ctrl = Control.getControl();
		List<PassTask> unscheduledTasks =  ctrl.getUnscheduledTasks();
		Iterator<PassTask> itTasks = unscheduledTasks.iterator();
		List<Machine> machines = ctrl.getMachines();
		Iterator<Machine> itMachines;
		boolean b = true;
		while(b){
			b = false;
			itMachines = machines.iterator();
			//schedule tasks to the master
			int m = MAX_TASKS_BY_CLIENT - ctrl.countLocalTasks();
			if (m>0){
				if(m>1){
					b = true;
				}
				if(itTasks.hasNext()){
					ctrl.addLocalTasks(itTasks.next());
					itTasks.remove();
				}
			}
			// schedule tasks to the connected  machines
			while(itMachines.hasNext()){
				Machine machine = itMachines.next();
				int n = MAX_TASKS_BY_CLIENT - machine.getOngoingTasks();
				if(n>0){
					// we can give another tasks to this machine
					if(n>1){
						b =true;
						// we can give an other distribution
					}
					if(itTasks.hasNext()){
						TaskMessage.sendNewTask(itTasks.next(), machine);
						itTasks.remove();
					}
				}
			}
						
		}
		startLocalSearch(concurrentThreads);
	}

}
