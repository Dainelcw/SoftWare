package software;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class A4_Frame extends JFrame implements ActionListener{
	public static String[] mingwen = new String[100];
	public static String[] miwen = new String[100];
	JPanel mypanel0, mypanel1, mypanel2, mypanel21, mypanel3, mypanel4, mypanel41, mypanel5;
	JLabel label1, label2;
	JButton addSerbtn, decSerbtn;
	static JTextArea log;
	static JTextArea log2;
	JScrollPane sc, sc2;
	public A4_Frame() {
		mypanel0 = new JPanel();
		mypanel0.setLayout(new GridLayout(3, 1));
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel2.setLayout(new GridLayout(1, 2));
		mypanel21 = new JPanel();
		mypanel3 = new JPanel();
		mypanel3.setLayout(new GridLayout(1, 2));
		mypanel4 = new JPanel();
		mypanel41 = new JPanel();
		mypanel5 = new JPanel(new GridLayout(2,1));
		
		label1 = new JLabel("加解密程序");
		label1.setFont(new Font("Serif",0,20));
		mypanel1.add(label1);
		
		addSerbtn = new JButton("我要加密");
		addSerbtn.addActionListener(this);
		decSerbtn = new JButton("我要解密");
		decSerbtn.addActionListener(this);
		mypanel2.setBorder(new EmptyBorder(0, 28, 0, 28));
		mypanel2.add(addSerbtn);
		mypanel2.add(decSerbtn);
		
		label2 = new JLabel("操作记录：");
		mypanel21.add(label2);
		
		log = new JTextArea(6,14);
		log.setEditable(false);
		sc = new JScrollPane(log);
		log2 = new JTextArea(6,14);
		log2.setEditable(false);
		sc2 = new JScrollPane(log2);
		mypanel4.add(sc);
		mypanel41.add(sc2);
		mypanel3.add(mypanel4);
		mypanel3.add(mypanel41);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel21);
		
		mypanel5.add(mypanel0);
		mypanel5.add(mypanel3);
		
		add(mypanel5);
		
		setTitle("加解密程序");
		setBounds(510, 300, 400, 300);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addSerbtn) {
			new childFrame("加密");
		}else if(event.getSource() == decSerbtn) {
			new childFrame("解密");
		}
	}
	
}

class childFrame extends JFrame implements ActionListener{
	
	JLabel title1, title2;
	JTextField input1, input2;
	JButton okbtn, canclebtn;
	JPanel mypanel0, mypanel1, mypanel2, mypanel3;
	String mystr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String jiajiemi;
	public childFrame(String type) {
		jiajiemi = type;
		
		mypanel0 = new JPanel();
		mypanel0.setSize(3,1);
		
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel3 = new JPanel();
		
		title1 = new JLabel("请输入密钥：");
		input1 = new JTextField(8);
		mypanel1.add(title1);
		mypanel1.add(input1);
		
		title2 = new JLabel("请输入明文：");
		input2 = new JTextField(8);
		mypanel2.add(title2);
		mypanel2.add(input2);
		
		okbtn = new JButton("确认");
		okbtn.addActionListener(this);
		canclebtn = new JButton("取消");
		canclebtn.addActionListener(this);
		mypanel3.add(okbtn);
		mypanel3.add(canclebtn);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel3);
		
		add(mypanel0);
		setTitle("信息输入");
		setBounds(600,300,240,160);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event1) {
		if(jiajiemi.equals("加密")) {
			if(event1.getSource() == okbtn) {
				jiami();
			}else if(event1.getSource() == canclebtn) {
				setVisible(false);
			}
		}else {
			if(event1.getSource() == okbtn) {
				jiemi();
			}else if(event1.getSource() == canclebtn) {
				setVisible(false);
			}
		}
		
	}
	
	private void jiami() {
		String input[] = new String[2];
		int miyao = -1;
		input[0] = input1.getText();
		input[1] = input2.getText();
		try {
			miyao = Integer.parseInt(input[0]);
			if(A4_Frame.miwen[miyao-1]!=null) {
				JOptionPane.showMessageDialog(null, "该条密钥已被加密！请换条密钥！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				Strfun(1, input[1], miyao);
				A4_Frame.mingwen[miyao-1] = input[1];
				A4_Frame.miwen[miyao-1] = Strfun(1,input[1],miyao);
				A4_Frame.log.append("明文加密成功！\n");
				A4_Frame.log2.append("密文：" + A4_Frame.miwen[miyao-1] + "，密钥：" + miyao + "\n");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "密钥不能为空或字母！", "错误", JOptionPane.ERROR_MESSAGE);
		}
		setVisible(false);
	}
	
	private String Strfun(int chagetype, String input, int miyao) {
		String result = "";
		char temp = '0';
		
		for(int i=0;i<input.length();i++) {
			int j = 0;
			while(mystr.toCharArray()[j] != input.toCharArray()[i]) {
				j++;
			}
			if(chagetype == 1) {
				j = j + miyao;
				if(j>61) {
					j = j - 62;
				}
			}else {
				if(j<miyao) {
					j = 62 - miyao + j;
				}
			}
			temp = mystr.toCharArray()[j];
			result = result + temp;
		}
		return result;
	}

	private void jiemi() {
		int miyao = -1;
		String input[] = new String[2];
		input[0] = input1.getText();
		input[1] = input2.getText();
		try {
			miyao = Integer.parseInt(input[0]);
			if(A4_Frame.miwen[miyao-1] != null) {
				if(Strfun(0, input[1], miyao).equals(A4_Frame.mingwen[miyao-1])) {
					A4_Frame.log.append("第" + miyao + "条密文解密成功！\n");
					A4_Frame.log2.append("解密明文：" + A4_Frame.mingwen[miyao-1] + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "解密失败！", "错误", JOptionPane.ERROR_MESSAGE);
					A4_Frame.log.append("第" + miyao + "条密文解密失败！\n");
					A4_Frame.log2.append("\n");
				}
			}else {
				JOptionPane.showMessageDialog(null, "解密明文不存在！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "密钥不能为空或字母！", "错误", JOptionPane.ERROR_MESSAGE);
		}
		
		setVisible(false);
	}
}