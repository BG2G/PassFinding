package softInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
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

	private BlocOne nbOne;
	private BlocTwo nbTwo;
	private BlocThree nbThree;
	private BlocFour nbFour;
	private JPanel general;


	public OperationalView(String tag, String ip, String pw){

		// -- Blocs --
		nbOne = new BlocOne(tag,ip,pw);
		nbTwo = new BlocTwo();
		nbThree = new BlocThree(tag);
		nbFour = new BlocFour();


		// -- General Panel --

		general = new JPanel();

		general.add(nbOne.getPanel());
		general.add(Box.createRigidArea(new Dimension(0,10)));
		general.add(nbTwo.getPanel());
		general.add(Box.createRigidArea(new Dimension(0,10)));
		general.add(nbThree.getPanel());
		general.add(Box.createRigidArea(new Dimension(0,10)));
		general.add(nbFour.getPanel());


		general.setLayout(new BoxLayout(general,BoxLayout.Y_AXIS));
		general.setAlignmentX(Component.LEFT_ALIGNMENT);


		// -- General Container --

		Container cont = this.getContentPane();
		cont.setLayout(new BorderLayout());
		cont.add(general);
		revalidate();
		pack();
		
		
		// Listener
		nbFour.getButt().setActionCommand("test");
		nbFour.getButt().addActionListener(this);


	}


	public BlocOne getNbOne() {
		return nbOne;
	}


	public void setNbOne(BlocOne nbOne) {
		this.nbOne = nbOne;
	}


	public BlocTwo getNbTwo() {
		return nbTwo;
	}


	public void setNbTwo(BlocTwo nbTwo) {
		this.nbTwo = nbTwo;
	}


	public BlocThree getNbThree() {
		return nbThree;
	}


	public void setNbThree(BlocThree nbThree) {
		this.nbThree = nbThree;
	}


	public BlocFour getNbFour() {
		return nbFour;
	}


	public void setNbFour(BlocFour nbFour) {
		this.nbFour = nbFour;
	}


	public JPanel getGeneral() {
		return general;
	}


	public void setGeneral(JPanel general) {
		this.general = general;
	}

	public void actionPerformed(ActionEvent ev){
		
		if (ev.getActionCommand().equals("test")){
			System.out.println("test");
			String[] elements = {"Machine Name","I.P.", "Status", "Time"};
			nbFour.addLine(elements);
			revalidate();
		}
		
	}






}
