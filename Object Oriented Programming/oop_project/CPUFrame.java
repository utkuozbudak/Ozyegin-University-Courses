 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CPUFrame extends JFrame {
	JPanel panel_title;
	
	
	JLabel label_title;
	JLabel label_price;
	
	
	public CPUFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750,500);
		setLayout(new GridLayout(5,1,1,1));
		
		Color color = new Color(128,0,0);
		
		panel_title = new JPanel();
		add(panel_title);
		panel_title.setBackground(Color.GRAY);
		
		label_title = new JLabel("CPU");
		panel_title.add(label_title);
		
		
		
	
		
		
		
		setVisible(false);
		
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
