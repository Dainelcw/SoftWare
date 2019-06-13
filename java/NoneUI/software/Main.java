package software;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame implements ActionListener{
	JButton A1, A2, A3, A4, A5, B1, B2;
	int x_location, y_location;
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		setTitle("B16031114");
		setSize(600,400);
		x_location = getCenterLocation(1);
		y_location = getCenterLocation(2);
		setLocation(x_location, y_location);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,4));
		
		A1 = new JButton("A1");
		A1.addActionListener(this);
		add(A1);
		
		A2 = new JButton("A2");
		A2.addActionListener(this);
		add(A2);
		
		A3 = new JButton("A3");
		A3.addActionListener(this);
		add(A3);
		
		A4 = new JButton("A4");
		A4.addActionListener(this);
		add(A4);
		
		A5 = new JButton("A5");
		A5.addActionListener(this);
		add(A5);
		
		B1 = new JButton("B1");
		B1.addActionListener(this);
		add(B1);
		
		B2 = new JButton("B2");
		B2.addActionListener(this);
		add(B2);
		
		setVisible(true);
	}

	public int getCenterLocation(int type) {
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		if(type == 1) {
			return (int) (width - this.getWidth()) / 2;
		}else {
			return (int) (height - this.getHeight()) / 2;
		}
	} 
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == A1) {
			A1_Frame a1 = new A1_Frame();
			a1.setVisible(true);
		}else if(event.getSource() == A2) {
			A2_Frame a2 = new A2_Frame();
			a2.setVisible(true);
		}else if(event.getSource() == A3) {
			A3_Frame a3 = new A3_Frame();
			a3.setVisible(true);
		}else if(event.getSource() == A4) {
			A4_Frame a4 = new A4_Frame();
			a4.setVisible(true);
		}else if(event.getSource() == A5) {
			A5_Frame a5 = new A5_Frame();
			a5.setVisible(true);
		}else if(event.getSource() == B1) {
			B1_Frame b1 = new B1_Frame();
			b1.setVisible(true);
		}else if(event.getSource() == B2) {
			B2_Frame b2 = new B2_Frame();
			b2.setVisible(true);
		}
	}
}
