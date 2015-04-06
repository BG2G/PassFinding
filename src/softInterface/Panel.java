package softInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	private Component ele[];
	
	
	public Panel(Component ele[]){
		this.ele=ele;
		for (int i = 0; i<ele.length; i++){
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			panel.setBackground(Color.ORANGE);
			panel.add(ele[i]);
			this.add(panel);
			this.add(Box.createRigidArea(new Dimension(2,2)));
		}	
	}


	public Component[] getEle() {
		return ele;
	}


	public void setEle(Component[] ele) {
		this.ele = ele;
	}
	
	
	
}
