package softInterface;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Actions extends Window implements ActionListener {



	public Actions(String tag,OperationalView ope){

		JLabel titl = new JLabel(tag);

		
		// Action Bar
		JButton ren = new JButton("Rename");
		JButton add = new JButton("Add");
		JButton del = new JButton("Delete");
		JButton retour = new JButton("Back");
		retour.addActionListener(this);

		Component[] cp = {ren, add,del,retour};
		Panel panint = new Panel(cp);
		panint.setLayout(new FlowLayout());

		JScrollPane scroll = new JScrollPane();;

		if(tag.equals("Local Tasks")){		

			scroll = new JScrollPane(ope.getTablt());
		}
		else if(tag.equals("Global Tasks")){

			scroll = new JScrollPane(ope.getTabgt());
		}
		else if(tag.equals("Machines")){

			scroll = new JScrollPane(ope.getTabm());
		}


		Component[] cp2 = {titl,scroll,panint};
		Panel act = new Panel(cp2);
		act.setLayout(new BoxLayout(act,BoxLayout.Y_AXIS));

		getContentPane().add(act);
		revalidate();
		pack();

	}
	
	public void actionPerformed(ActionEvent ev){
		if (ev.getActionCommand().equals("Back")){
			this.dispose();
		}
	}

}
