package mainPackage;

import java.util.concurrent.Semaphore;

public class Master {



	public static void startMaster(){
		
	
		PassHashData data = new PassHashData("hashData.dat");
		Control.getControl().setHashData(data);
		
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
}
