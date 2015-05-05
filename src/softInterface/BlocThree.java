package softInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;


public class BlocThree {


	private JLabel tit1;
	private Table tablt;
	private JLabel tit2;
	private Table tabgt;
	private Panel ltt;
	private Panel gtt;
	private Panel panel;

	public BlocThree(String tag){
		// Local Tasks Panel

		tit1 = new JLabel("Local Task");
		// Table
		tablt = new TableLT(3,3);
		// Panel
		Component[] cplt = {tit1,tablt.getScroll()};
		ltt = new Panel(cplt);
		ltt.setLayout(new BoxLayout(ltt,BoxLayout.Y_AXIS));
		
		// Global Tasks Panel
		
		tit2 = new JLabel("Global Task");
		// Table
		tabgt = new TableGT(3,3);
		// Panel
		Component[] cpgt = {tit2,tabgt.getScroll()};
		gtt = new Panel(cpgt);
		gtt.setLayout(new BoxLayout(gtt,BoxLayout.Y_AXIS));
		
		
		// Panel
		if(tag.equals("MASTER")){
			Component[] ele3 = {ltt,gtt};
			panel = new Panel(ele3);
		}
		else if (tag.equals("SLAVE")){
			Component[] ele3 = {ltt};
			panel = new Panel(ele3);
		}
		panel.setLayout(new FlowLayout());
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

}
