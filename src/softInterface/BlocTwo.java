package softInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class BlocTwo {

	private JComboBox box;
	private JLabel text4;
	private JLabel text5;
	private Table tab;
	private Panel panel;
	

	public BlocTwo(){

		// Select code menu		
		box = new JComboBox();
		box.addItem("Code_1");
		box.addItem("Code_2");
		box.setMaximumSize(new Dimension(100,20));
		box.setPreferredSize(box.getPreferredSize());

		// Texts
		text4 = new JLabel("File Name : ****");
		text5 = new JLabel("Location : ****");

		// Table
		tab = new TableCode(3,3);
		
				
		// Panel
		Component ele2[] = new Component[4];
		ele2[2] = box;
		ele2[0] = text4;
		ele2[1] = text5;
		ele2[3] = tab.getScroll();
		panel = new Panel(ele2);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	}

	

	public JComboBox getBox() {
		return box;
	}

	public void setBox(JComboBox box) {
		this.box = box;
	}

	public JLabel getText4() {
		return text4;
	}

	public void setText4(JLabel text4) {
		this.text4 = text4;
	}

	public JLabel getText5() {
		return text5;
	}

	public void setText5(JLabel text5) {
		this.text5 = text5;
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
