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
	childFrame child;
	public A4_Frame() {
		//ͼ��
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
      	setIconImage(image);
		
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
		
		label1 = new JLabel("�ӽ��ܳ���");
		label1.setFont(new Font("Serif",0,20));
		mypanel1.add(label1);
		
		addSerbtn = new JButton("��Ҫ����");
		addSerbtn.setFont(new Font("Serif",0,16));
		addSerbtn.addActionListener(this);
		addSerbtn.setFocusPainted(false);
		decSerbtn = new JButton("��Ҫ����");
		decSerbtn.setFont(new Font("Serif",0,16));
		decSerbtn.addActionListener(this);
		decSerbtn.setFocusPainted(false);
		mypanel2.setBorder(new EmptyBorder(0, 28, 0, 28));
		mypanel2.add(addSerbtn);
		mypanel2.add(decSerbtn);
		
		label2 = new JLabel("������¼��");
		label2.setFont(new Font("Serif",0,18));
		mypanel21.add(label2);
		
		log = new JTextArea(6,20);
		log.setEditable(false);
		sc = new JScrollPane(log);
		log2 = new JTextArea(6,20);
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
		
		setTitle("�ӽ��ܳ���");
		//setBounds(1440, 100, 500, 400);
		setBounds(3360, 100, 500, 400);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			if(child == null) {
				Main.Frametype[3] = 0;
				setVisible(false);
			}else if(!child.isVisible()) {
				Main.Frametype[3] = 0;
				setVisible(false);
			}else {
				child.setVisible(true);
			}
		}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addSerbtn) {
			child = new childFrame("����");
		}else if(event.getSource() == decSerbtn) {
			child = new childFrame("����");
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
		//ͼ��
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage("src/image/icon.png"); 
      	setIconImage(image);
		
		jiajiemi = type;
		
		mypanel0 = new JPanel();
		mypanel0.setLayout(new GridLayout(3, 1));
		
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel3 = new JPanel();
		
		title1 = new JLabel("��������Կ��");
		title1.setFont(new Font("Serif",0,18));
		input1 = new JTextField(10);
		mypanel1.add(title1);
		mypanel1.add(input1);
		
		title2 = new JLabel("���������ģ�");
		title2.setFont(new Font("Serif",0,18));
		input2 = new JTextField(10);
		mypanel2.add(title2);
		mypanel2.add(input2);
		
		FlowLayout f = (FlowLayout) mypanel3.getLayout();
		f.setHgap(20);
		okbtn = new JButton("ȷ��");
		okbtn.addActionListener(this);
		okbtn.setFont(new Font("Serif",0,16));
		canclebtn = new JButton("ȡ��");
		canclebtn.addActionListener(this);
		canclebtn.setFont(new Font("Serif",0,16));
		mypanel3.add(okbtn);
		mypanel3.add(canclebtn);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel3);
		
		add(mypanel0);
		setTitle("��Ϣ����");
		setBounds(600,300,260,220);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event1) {
		if(jiajiemi.equals("����")) {
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
				JOptionPane.showMessageDialog(null, "������Կ�ѱ����ܣ��뻻����Կ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}else {
				Strfun(1, input[1], miyao);
				A4_Frame.mingwen[miyao-1] = input[1];
				A4_Frame.miwen[miyao-1] = Strfun(1,input[1],miyao);
				A4_Frame.log.append("���ļ��ܳɹ���\n");
				A4_Frame.log2.append("���ģ�" + A4_Frame.miwen[miyao-1] + "����Կ��" + miyao + "\n");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "��Կ����Ϊ�ջ���ĸ��", "����", JOptionPane.ERROR_MESSAGE);
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
					A4_Frame.log.append("��" + miyao + "�����Ľ��ܳɹ���\n");
					A4_Frame.log2.append("�������ģ�" + A4_Frame.mingwen[miyao-1] + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
					A4_Frame.log.append("��" + miyao + "�����Ľ���ʧ�ܣ�\n");
					A4_Frame.log2.append("\n");
				}
			}else {
				JOptionPane.showMessageDialog(null, "�������Ĳ����ڣ�", "����", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "��Կ����Ϊ�ջ���ĸ��", "����", JOptionPane.ERROR_MESSAGE);
		}
		
		setVisible(false);
	}
}