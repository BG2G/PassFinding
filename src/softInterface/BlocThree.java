package softInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class BlocThree {


	private JLabel tit1;
	private Table tablt;
	private JLabel tit2;
	private Table tabgt;
	private Panel ltt;
	private Panel gtt;
	private Panel panel;
	private JCheckBox check3;
	private JCheckBox check4;
	private JTextField enter3;
	private JTextField enter4;
	private JTextField enter5;
	private JCheckBox check1;
	private JCheckBox check2;

	public BlocThree(String tag){
		// Local Tasks Panel

		tit1 = new JLabel("Local Task");
		// Table
		tablt = new TableLT(3,3);
		// Panel
		JComponent[] cplt = {tit1,tablt.getScroll()};
		ltt = new Panel(cplt);
		ltt.setLayout(new BoxLayout(ltt,BoxLayout.Y_AXIS));
		
		// Global Tasks Panel
		
		tit2 = new JLabel("Global Task");
		// Table
		tabgt = new TableGT(3,3);
		// Actions 
		//ADD
		JLabel adt = new JLabel("Add :");
		check3 = new JCheckBox("Upper Case");
		check4 = new JCheckBox("Lower Case");
		check1 = new JCheckBox("Numbers");
		check2 = new JCheckBox("Special Characters");
		enter3 = new JTextField("Special Characters");
		enter4 = new JTextField("Length");
		JButton add = new JButton("ADD");
		add.setActionCommand("addtask");
		JComponent[] addele = {adt, check3, check4, check1, check2, enter3,enter4,add};
		Panel addPanel = new Panel(addele);
		addPanel.setLayout(new BoxLayout(addPanel,BoxLayout.Y_AXIS));
		//DELETE
		JLabel det = new JLabel("Delete :");
		enter5 = new JTextField("Task #");
		enter5.setMaximumSize(enter5.getPreferredSize());
		JButton del = new JButton("DELETE");
		del.setActionCommand("deltask");
		JComponent[] delele = {det,enter5,del};
		Panel delPanel = new Panel(delele);
		delPanel.setLayout(new BoxLayout(delPanel,BoxLayout.Y_AXIS));
		
		addPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		delPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		JComponent[] actele = {addPanel,delPanel};
		Panel actionPanel = new Panel(actele);
		actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.X_AXIS));
		
				
		
		
		// Panel
		JComponent[] cpgt = {tit2,tabgt.getScroll(),actionPanel};
		gtt = new Panel(cpgt);
		gtt.setLayout(new BoxLayout(gtt,BoxLayout.Y_AXIS));
		
		
		// Panel
		if(tag.equals("MASTER")){
			JComponent[] ele3 = {ltt,gtt};
			panel = new Panel(ele3);
		}
		else if (tag.equals("SLAVE")){
			JComponent[] ele3 = {ltt};
			panel = new Panel(ele3);
		}
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}

	public JLabel getTit1() {
		return tit1;
	}

	public void setTit1(JLabel tit1) {
		this.tit1 = tit1;
	}

	public Table getTablt() {
		return tablt;
	}

	public void setTablt(Table tablt) {
		this.tablt = tablt;
	}

	public JLabel getTit2() {
		return tit2;
	}

	public void setTit2(JLabel tit2) {
		this.tit2 = tit2;
	}

	public Table getTabgt() {
		return tabgt;
	}

	public void setTabgt(Table tabgt) {
		this.tabgt = tabgt;
	}

	public Panel getLtt() {
		return ltt;
	}

	public void setLtt(Panel ltt) {
		this.ltt = ltt;
	}

	public Panel getGtt() {
		return gtt;
	}

	public void setGtt(Panel gtt) {
		this.gtt = gtt;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
	public void addLineGT(String[] elements){
		tabgt.addLine(elements);
				
	}
	
	public void addLineLT(String[] elements){
		tablt.addLine(elements);
				
	}
	

}
