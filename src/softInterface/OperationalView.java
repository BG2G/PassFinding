package softInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OperationalView extends Window implements ActionListener {

	private JPanel general;
	private Panel pan1;
	private Panel pan2;
	private Panel pan3;
	private Panel pan4;
	private JTable tablt;
	private JTable tabgt;
	private JTable tabm;

	public OperationalView(String tag, String ip, String pw){

		// -- Bloc 1 --

		Component ele1[] = new Component[3];
		JLabel text1 = new JLabel("You are connected as : "+tag);
		JLabel text2 = new JLabel("I.P. : "+ip);
		JLabel text3 = new JLabel("Password : "+pw);
		ele1[0] = text1;
		ele1[1] = text2;
		ele1[2] = text3;
		pan1 = new Panel(ele1);
		pan1.setLayout(new BoxLayout(pan1,BoxLayout.Y_AXIS));
		//		pan1.setBackground(Color.ORANGE);
		pan1.setAlignmentX(Component.LEFT_ALIGNMENT);
		pan1.setBorder(BorderFactory.createLineBorder(Color.black));


		// -- Bloc 2 --

		// Select code menu
		Component ele2[] = new Component[4];
		JComboBox sel1 = new JComboBox();
		sel1.addItem("Code_1");
		sel1.addItem("Code_2");
		sel1.setMaximumSize(new Dimension(100,20));
		sel1.setPreferredSize(sel1.getPreferredSize());

		// Texts
		JLabel text4 = new JLabel("File Name : ****");
		JLabel text5 = new JLabel("Location : ****");

		// Table
		String colo[] = {"Password","Time"};
		String data[][]= new String[3][2];
		JTable tabu1 = new JTable(data, colo);
		JScrollPane scrollPane1 = new JScrollPane(tabu1);
		tabu1.setFillsViewportHeight(true);
		tabu1.setPreferredScrollableViewportSize(tabu1.getPreferredSize());

		ele2[2] = sel1;
		ele2[0] = text4;
		ele2[1] = text5;
		ele2[3] = scrollPane1;
		pan2 = new Panel(ele2);
		pan2.setLayout(new BoxLayout(pan2,BoxLayout.Y_AXIS));
		pan2.setAlignmentX(Component.LEFT_ALIGNMENT);
		//pan2.setBackground(Color.ORANGE);
		pan2.setAlignmentX(Component.LEFT_ALIGNMENT);
		pan2.setBorder(BorderFactory.createLineBorder(Color.black));


		// -- Bloc 3 --

		// Local Tasks Panel
		JLabel tit1 = new JLabel("Local Task");
		// Table
		String cololt[] = {"Task #","Status","Time"};
		String datalt[][]= new String[3][3];
		JTable tabu2 = new JTable(datalt, cololt);
		tablt = tabu2;
		JScrollPane scrollPane2 = new JScrollPane(tablt);
		tablt.setFillsViewportHeight(true);
		tablt.setPreferredScrollableViewportSize(tablt.getPreferredSize());

		// Action Bar

		JLabel text6 = new JLabel("Sort By : ");
		JComboBox sel2 = new JComboBox();
		sel2.addItem("Task #");
		sel2.addItem("Status");
		sel2.addItem("Time");
		sel2.setSelectedIndex(0);
		sel2.setPreferredSize(sel2.getPreferredSize());
		JButton act1 = new JButton("Actions");
		act1.addActionListener(this);
		act1.setActionCommand("actlt");

		Component[] actio = {text6,sel2,act1};
		Panel actionBar1 = new Panel(actio);
		actionBar1.setLayout(new FlowLayout());

		Component[] cplt = {tit1,scrollPane2,actionBar1};
		Panel ltt = new Panel(cplt);
		ltt.setLayout(new BoxLayout(ltt,BoxLayout.Y_AXIS));




		// Global Tasks Panel
		JLabel tit2 = new JLabel("Global Task");
		// Table
		String cologt[] = {"Task #","Status","Time"};
		String datagt[][]= new String[3][3];
		JTable tabu3 = new JTable(datagt, cologt);
		tabgt = tabu3;
		JScrollPane scrollPane3 = new JScrollPane(tabgt);
		tabgt.setFillsViewportHeight(true);
		tabgt.setPreferredScrollableViewportSize(tabgt.getPreferredSize());



		// Action Bar

		JLabel text7 = new JLabel("Sort By : ");
		JComboBox sel3 = new JComboBox();
		sel3.addItem("Task #");
		sel3.addItem("Status");
		sel3.addItem("Time");
		sel3.setSelectedIndex(0);
		sel3.setPreferredSize(sel3.getPreferredSize());
		JButton act2 = new JButton("Actions");
		act2.addActionListener(this);
		act2.setActionCommand("actgt");

		Component[] actio2 = {text7,sel3,act2};
		Panel actionBar2 = new Panel(actio2);
		actionBar2.setLayout(new FlowLayout());

		Component[] cpgt = {tit2,scrollPane3,actionBar2};
		Panel gtt = new Panel(cpgt);
		gtt.setLayout(new BoxLayout(gtt,BoxLayout.Y_AXIS));



		// Pan3
		if(tag.equals("HEAD")){
			Component[] ele3 = {ltt,gtt};
			pan3 = new Panel(ele3);
		}
		else if (tag.equals("SLAVE")){
			Component[] ele3 = {ltt};
			pan3 = new Panel(ele3);
		}
		pan3.setLayout(new FlowLayout());
		//pan3.setBackground(Color.ORANGE);
		pan3.setAlignmentX(Component.LEFT_ALIGNMENT);
		pan3.setBorder(BorderFactory.createLineBorder(Color.black));


		// -- Bloc 4 --
		JLabel tit3 = new JLabel("Machines");

		// Table
		String colom[] = {"Machine #","I.P.","Status","Time"};
		String datam[][]= new String[3][4];
		JTable tabu4 = new JTable(datam, colom);
		tabm = tabu4;
		JScrollPane scrollPane4 = new JScrollPane(tabm);
		tabm.setFillsViewportHeight(true);
		tabm.setPreferredScrollableViewportSize(tabm.getPreferredSize());

		// Action Bar

		JLabel text8 = new JLabel("Sort By : ");
		JComboBox sel4 = new JComboBox();
		sel4.addItem("Machine #");
		sel4.addItem("I.P.");
		sel4.addItem("Status");
		sel4.addItem("Time");
		sel4.setSelectedIndex(0);
		sel4.setPreferredSize(sel4.getPreferredSize());
		JButton act3 = new JButton("Actions");
		act3.addActionListener(this);
		act3.setActionCommand("actm");

		Component[] actio3 = {text8,sel4,act3};
		Panel actionBar3 = new Panel(actio3);
		actionBar3.setLayout(new FlowLayout());

		Component[] ele4 = {tit3,scrollPane4,actionBar3};
		pan4 = new Panel(ele4);
		pan4.setLayout(new BoxLayout(pan4,BoxLayout.Y_AXIS));
		pan4.setAlignmentX(Component.LEFT_ALIGNMENT);
		//pan4.setBackground(Color.ORANGE);
		pan4.setAlignmentX(Component.LEFT_ALIGNMENT);
		pan4.setBorder(BorderFactory.createLineBorder(Color.black));


		// -- General Panel --

		JPanel general = new JPanel();

		general.add(pan1);
		general.add(Box.createRigidArea(new Dimension(0,10)));
		general.add(pan2);
		general.add(Box.createRigidArea(new Dimension(0,10)));
		general.add(pan3);
		general.add(Box.createRigidArea(new Dimension(0,10)));
		general.add(pan4);
		general.add(Box.createRigidArea(new Dimension(0,10)));


		general.setLayout(new BoxLayout(general,BoxLayout.Y_AXIS));
		general.setAlignmentX(Component.LEFT_ALIGNMENT);


		// -- General Container --

		Container cont = this.getContentPane();
		cont.setLayout(new BorderLayout());
		cont.add(general);
		revalidate();
		pack();


	}
	
	public JTable getTablt() {
		return tablt;
	}

	public void setTablt(JTable tablt) {
		this.tablt = tablt;
	}

	public JTable getTabgt() {
		return tabgt;
	}

	public void setTabgt(JTable tabgt) {
		this.tabgt = tabgt;
	}

	public JTable getTabm() {
		return tabm;
	}

	public void setTabm(JTable tabm) {
		this.tabm = tabm;
	}

	public void actionPerformed(ActionEvent ev){
		
		if (ev.getActionCommand().equals("actlt")){
			
			Actions act = new Actions("Local Tasks",this);
			
			
		}
		else if (ev.getActionCommand().equals("actgt")){
			
			Actions act = new Actions("Global Tasks", this);
						
		}
		else if(ev.getActionCommand().equals("actm")){
			
			Actions act = new Actions("Machines", this);
			
		}
		
	}



}
