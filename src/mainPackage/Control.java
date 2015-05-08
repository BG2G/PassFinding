package mainPackage;
import java.util.ArrayList;
import java.util.List;


public class Control {

	private static Control instance = null;
	private PassHashData data;
	private List<PassTask> tasks = new ArrayList<PassTask>();
	
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
	public PassHashData getHashData(){
		return data;
	}
	
}
