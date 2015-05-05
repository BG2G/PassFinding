package softInterface;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class BlocOne {

	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private Panel panel;
	
	public BlocOne(String tag, String ip, String pw){
		
		
		text1 = new JLabel("You are connected as : "+tag);
		text2 = new JLabel("I.P. : "+ip);
		text3 = new JLabel("Password : "+pw);
		
		// Panel
		Component ele1[] = new Component[3];
		ele1[0] = text1;
		ele1[1] = text2;
		ele1[2] = text3;
		panel = new Panel(ele1);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	}

	public JLabel getText1() {
		return text1;
	}

	public void setText1(JLabel text1) {
		this.text1 = text1;
	}

	public JLabel getText2() {
		return text2;
	}

	public void setText2(JLabel text2) {
		this.text2 = text2;
	}

	public JLabel getText3() {
		return text3;
	}

	public void setText3(JLabel text3) {
		this.text3 = text3;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
}
