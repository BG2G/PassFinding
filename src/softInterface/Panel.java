package softInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	private JComponent ele[];
	
	
	public Panel(JComponent ele[]){
		this.ele=ele;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for (int i = 0; i<ele.length; i++){
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
			
//			panel.setBackground(Color.ORANGE);
			ele[i].setMaximumSize(ele[i].getPreferredSize());
			panel.add(ele[i]);

			panel.setAlignmentY(Component.TOP_ALIGNMENT);
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(Box.createHorizontalGlue());
			this.add(panel);
			this.add(Box.createRigidArea(new Dimension(1,1)));
		}	
		
		this.add(Box.createVerticalGlue());
	}


	public JComponent[] getEle() {
		return ele;
	}


	public void setEle(JComponent[] ele) {
		this.ele = ele;
	}
	
	
	
}
