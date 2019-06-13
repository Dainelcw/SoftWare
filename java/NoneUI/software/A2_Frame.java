package software;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class A2_Frame extends JFrame implements ActionListener{
	JPanel mypanel0, mypanel1, mypanel2, mypanel3, mypanel4, mypanel5;
	JLabel label1, label2, label3, label4;
	JTextField randomS, input, output;
	JButton btn1, btn2;
	public A2_Frame() {
		mypanel0 = new JPanel();
		mypanel0.setLayout(new GridLayout(5, 1));
		
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel3 = new JPanel();
		mypanel4 = new JPanel();
		mypanel5 = new JPanel();
		
		label1 = new JLabel("打字练习程序");
		label1.setFont(new Font("Serif",0,18));
		mypanel1.add(label1);
		
		label2 = new JLabel("随机字符：");
		randomS = new JTextField(10);
		randomS.setText(getRandomData());
		randomS.setEditable(false);
		mypanel2.add(label2);
		mypanel2.add(randomS);
		
		label3 = new JLabel("请您输入：");
		input = new JTextField(10);
		mypanel3.add(label3);
		mypanel3.add(input);
		
		btn1 = new JButton("生成字符串");
		btn1.addActionListener(this);
		btn2 = new JButton("计算正确率");
		btn2.addActionListener(this);
		mypanel4.add(btn1);
		mypanel4.add(btn2);
		
		label4 = new JLabel("您的正确率：");
		output = new JTextField(4);
		mypanel5.add(label4);
		mypanel5.add(output);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel3);
		mypanel0.add(mypanel4);
		mypanel0.add(mypanel5);
		
		add(mypanel0);
		setTitle("打字练习");
		setBounds(760 , 0, 400, 300);
	}
	
	/**
     * 获得随机字符串,该方法仅用于获得随机字符串，可以忽略
     *
     * @return
     */
    private String getRandomData(){
    	String myString = "";
    	String ss = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	int x=4+(int)(Math.random()*12);
    	for(int i=0;i<x;i++) {
    		myString = myString + ss.toCharArray()[1+(int)(Math.random()*52)];
    	}
		return myString;
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btn1) {
			randomS.setText(getRandomData());
		}else if(event.getSource() == btn2) {
			String proStr = randomS.getText();
			String inputStr = input.getText();
			int temp = 0;
			try{
				for(int i=0;i<proStr.length();i++) {
					if(proStr.toCharArray()[i] == inputStr.toCharArray()[i]) {
						temp++;
					}
				}
				output.setText(String.valueOf((int)(((float)temp/proStr.length())*10000)/100.0)+"%");
			}catch(ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "输入为空", "警告", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
}
