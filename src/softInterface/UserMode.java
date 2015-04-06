package softInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UserMode extends Window implements ActionListener {
	
	private JLabel tit;
	private JLabel cho;
	private JButton head;
	private JButton slave;
	
	
	public UserMode(){
		
		// Access to container
		Container pac = this.getContentPane();
		pac.setLayout(new BorderLayout());
		
		
		// Text init. + add
		JLabel text1 = new JLabel("PassFinding");
		this.tit = text1;
		
				
		JLabel text2 = new JLabel("Choose your mode");
		this.cho = text2;
		
		
		// Buttons init. + add
		JButton but1 = new JButton("Head");
		this.head = but1;
		head.setActionCommand("blop");
		head.addActionListener(this);
		JButton but2 = new JButton("Slave");
		this.slave = but2;
		slave.setActionCommand("plop");
		slave.addActionListener(this);
	
		Component[] cp1 = {head,slave};
		Panel pan2 = new Panel(cp1);
		pan2.setLayout(new FlowLayout());
		
		Component[] cp2 = {tit,cho,pan2};
		Panel princ = new Panel(cp2);
		princ.setLayout(new BoxLayout(princ, BoxLayout.Y_AXIS));
		
		pac.add(princ);
		revalidate();
		pack();
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent ev){
		if (ev.getActionCommand().equals("blop")){
			String ip = "blabla";
			System.out.println("head");
			try 
			{ 
			InetAddress thisIp = InetAddress.getLocalHost(); 
			ip = thisIp.getHostAddress(); 
			} 
			catch(UnknownHostException e) 
			{ 
			e.printStackTrace(); 
			}
			this.setVisible(false);
			OperationalView op = new OperationalView("HEAD", ip, "My Password");
		}
		else if (ev.getActionCommand().equals("plop")){
			String ip = "blabla";
			System.out.println("slave");
			try 
			{ 
			InetAddress thisIp = InetAddress.getLocalHost(); 
			ip = thisIp.getHostAddress(); 
			} 
			catch(UnknownHostException e) 
			{ 
			e.printStackTrace(); 
			}
			this.setVisible(false);
			OperationalView op = new OperationalView("SLAVE", ip, "My Password");
		}
	}
	
	
	

}
