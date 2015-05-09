package mainPackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import communication.core.Machine;


public class Control {

	private static Control instance = null;
	private long initialTimer;
	private PassHashData data;
	private volatile List<PassTask> tasks = new ArrayList<PassTask>();
	private volatile List<PassTask> localTasks = new ArrayList<PassTask>();
	private volatile int taskID = 0;
	private boolean searchOngoing = false;
	private volatile List<Machine> machines = new ArrayList<Machine>(); 
	private Machine master = null;
	private volatile List<Result> results = new ArrayList<Result>();
	private volatile List<PassTaskThread> threads = new ArrayList<PassTaskThread>();
	
	
	
	private Control(){	
	}
	
	public static Control getControl(){
		if(instance !=null){
			return instance;
		}else {
			Control control = new Control();
			control.setInstance(control);
			control.setInitialTimer(System.currentTimeMillis());
			return control;
		}
	}
	
	private void setInitialTimer(long currentTimeMillis) {
		initialTimer = currentTimeMillis;
		
	}
	public long getInitialTimer(){
		return initialTimer;
	}

	private void setInstance(Control control){
		Control.instance = control;
	}
	
	public void addTask(PassTask task){
		tasks.add(task);
	}
	public void setHashData(PassHashData data){
		this.data = data;
	}
	public  List<PassTask> getTasks(){
		return tasks;
	}
	public PassTask getNextLocalTask(){
		PassTask task = localTasks.get(0);
		localTasks.remove(0);
		return task;
	}
	public boolean nextLocalTaskExists(){
		return !localTasks.isEmpty();
	}
	public PassHashData getHashData(){
		return data;
	}
	public int getNextId(){
		this.taskID++;
		return this.taskID;
	}
	public boolean isSearchOngoing(){
		return searchOngoing;
	}
	public void setSearchOngoing(boolean b){
		searchOngoing = b;
	}
	public List<Machine> getMachines(){
		return machines;
	}
	public Machine getMaster(){
		return master;
	}
	public void setMachine(Machine master){
		this.master = master;
	}
	
	public void addMachine(Machine machine){
		machines.add(machine);
	}
	
	public void addThread(PassTaskThread thread){
		threads.add(thread);
	}
	public void removeThread(PassTaskThread thread){
		threads.remove(thread);
	}
	public List<PassTaskThread> getThreads(){
		return threads;
	}
	public void addResult(Result r){
		results.add(r);
	}
	public List<Result> getResult(){
		return results;
	}
	public PassTask findTaskbyId(int id){
		Iterator<PassTask> iterator =  tasks.iterator();
		while(iterator.hasNext()){
			PassTask task = iterator.next();
			if(task.getId()==id){
				return task;
			}
		}
		return null;
	}
}
