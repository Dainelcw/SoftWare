package software;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		//图标
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
      	setIconImage(image);
		
		mypanel0 = new JPanel();
		mypanel0.setLayout(new GridLayout(5, 1));
		
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel3 = new JPanel();
		mypanel4 = new JPanel();
		mypanel5 = new JPanel();
		
		label1 = new JLabel("打字练习程序");
		label1.setFont(new Font("Serif",0,20));
		mypanel1.add(label1);
		
		label2 = new JLabel("随机字符：");
		label2.setFont(new Font("Serif",0,18));
		randomS = new JTextField(12);
		randomS.setText(getRandomData());
		randomS.setEditable(false);
		randomS.setEnabled(false);
		mypanel2.add(label2);
		mypanel2.add(randomS);
		
		label3 = new JLabel("请您输入：");
		label3.setFont(new Font("Serif",0,18));
		input = new JTextField(12);
		mypanel3.add(label3);
		mypanel3.add(input);
		
		btn1 = new JButton("生成字符串");
		btn1.setFont(new Font("Serif",0,16));
		btn1.addActionListener(this);
		//除去获取的焦点框
		btn1.setFocusPainted(false);
		btn2 = new JButton("计算正确率");
		btn2.setFont(new Font("Serif",0,16));
		btn2.addActionListener(this);
		//除去获取的焦点框
		btn2.setFocusPainted(false);
		FlowLayout f = (FlowLayout) mypanel4.getLayout();
		f.setHgap(20);
		mypanel4.add(btn1);
		mypanel4.add(btn2);
		
		label4 = new JLabel("您的正确率：");
		label4.setFont(new Font("Serif",0,18));
		output = new JTextField(6);
		mypanel5.add(label4);
		mypanel5.add(output);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel3);
		mypanel0.add(mypanel4);
		mypanel0.add(mypanel5);
		
		add(mypanel0);
		setTitle("打字练习程序");
		//setBounds(480 , 100, 500, 400);
		setBounds(2400 , 100, 500, 400);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Main.Frametype[1] = 0;
				setVisible(false);
			}
		});
	}
	
	/**
     * 获得随机字符串,该方法仅用于获得随机字符串，可以忽略
     *
     * @return
     */
    private String getRandomData(){
    	String myString = "";
    	String ss = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	int x=6+(int)(Math.random()*6);
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
			//try{
				char[] proString=null, inputString=null;
				
				proString = proStr.toCharArray();
				inputString = inputStr.toCharArray();
				if(inputString.length > 0) {
					for(int i=0;i<inputString.length;i++) {
						if(proString[i] == inputString[i]) {
							temp++;
						}
					}
					output.setText(String.valueOf((int)(((float)temp/proStr.length())*10000)/100.0)+"%");
				}else {
					JOptionPane.showMessageDialog(null, "输入为空", "警告", JOptionPane.INFORMATION_MESSAGE);
				}
				
			//}catch(ArrayIndexOutOfBoundsException e) {
			//	JOptionPane.showMessageDialog(null, "输入为空", "警告", JOptionPane.INFORMATION_MESSAGE);
			//}
		}
		
	}
}
