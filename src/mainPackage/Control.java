package mainPackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import communication.core.Machine;


public class Control {

	private static Control instance = null;
	private PassHashData data;
	private volatile List<PassTask> tasks = new ArrayList<PassTask>();
	private volatile List<PassTask> localTasks = new ArrayList<PassTask>();
	private volatile int taskID = 0;
	private boolean searchOngoing = false;
	private volatile List<Machine> machines = new ArrayList<Machine>(); 
	
	
	
	private Control(){	
	}
	
	public static Control getControl(){
		if(instance !=null){
			return instance;
		}else {
			Control control = new Control();
			control.setInstance(control);
			return control;
		}
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
	
	public void addMachine(Machine machine){
		machines.add(machine);
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
