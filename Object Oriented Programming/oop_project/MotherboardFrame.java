 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;



public class MotherboardFrame extends JFrame {
	
	
	JPanel panel_title;
	JLabel label_title;
	
	
	public MotherboardFrame() {

		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750,500);
		setLayout(new GridLayout(7,1,1,1));
		
		Color color = new Color(128,0,0);
		
		panel_title = new JPanel();
		add(panel_title);
		
		panel_title.setBackground(Color.GRAY);
		
		label_title = new JLabel("Motherboard");
		panel_title.add(label_title);
		
		setVisible(false);
		
		
		}
}






	

	

