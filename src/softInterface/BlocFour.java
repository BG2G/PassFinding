package softInterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class BlocFour {
	
	private JLabel tit3;
	private Table tab;
	private Panel panel;
	
	public BlocFour(){
		
		tit3 = new JLabel("Machines");
		// Table
		tab = new TableMachine(6,4);
		// Panel
		Component[] ele4 = {tit3,tab.getScroll()};
		panel = new Panel(ele4);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}

	public JLabel getTit3() {
		return tit3;
	}

	public void setTit3(JLabel tit3) {
		this.tit3 = tit3;
	}

	public Table getTab() {
		return tab;
	}

	public void setTab(Table tab) {
		this.tab = tab;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
	

}
