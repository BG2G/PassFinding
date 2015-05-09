package softInterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BlocFour {

	private JLabel tit3;
	private Table tab;
	private Panel panel;
	private JTextField enter1;
	private JTextField enter2;
	private JTextField enter3;
	private JTextField enter4;

	public BlocFour(){

		tit3 = new JLabel("Machines");
		// Table
		tab = new TableMachine(6,4);

		
		// Actions
		//ADD
		JLabel adt = new JLabel("Add :");
		enter1 = new JTextField("Machine Name");

		enter2 = new JTextField("Internet Protocol");
		

		enter3 = new JTextField("Password");

		JButton add = new JButton("ADD");
		add.setActionCommand("addmachine");
		JComponent[] addele = {adt, enter1, enter2, enter3,add};
		Panel addPanel = new Panel(addele);
		addPanel.setLayout(new BoxLayout(addPanel,BoxLayout.Y_AXIS));
		//DELETE
		JLabel det = new JLabel("Delete :");
		enter4 = new JTextField("Machine Name");
		enter4.setMaximumSize(enter4.getPreferredSize());
		JButton del = new JButton("DELETE");
		del.setActionCommand("delmachine");
		JComponent[] delele = {det,enter4,del};
		Panel delPanel = new Panel(delele);
		delPanel.setLayout(new BoxLayout(delPanel,BoxLayout.Y_AXIS));
		
		addPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		delPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		JComponent[] actele = {addPanel,delPanel};
		Panel actionPanel = new Panel(actele);
		actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.X_AXIS));

		// Panel
		JComponent[] ele4 = {tit3,tab.getScroll(),actionPanel};
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



	public void addLine(String[] elements){
		tab.addLine(elements);

	}




}
