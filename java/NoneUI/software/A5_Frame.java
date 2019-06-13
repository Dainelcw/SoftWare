package software;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class A5_Frame extends JFrame implements ActionListener{
	
	JPanel mypanel0, mypanel1, mypanel2, mypanel3, mypanel4, mypanel5;
	JLabel title0, title1, title2, title3;
	JTextField number;
	JButton solveBtn;
	JTextArea log;
	JScrollPane sc;
	JComboBox<String> inChoose, outChoose;
	String[] jinzhiStr = {"������", "�˽���", "ʮ����", "ʮ������"};
	public A5_Frame() {
		mypanel0 = new JPanel();
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel2.setLayout(new GridLayout(2, 1));
		mypanel3 = new JPanel();
		mypanel4 = new JPanel();
		mypanel5 = new JPanel();
		
		title0 = new JLabel("����ת������");
		title0.setFont(new Font("Serif",0,18));
		title1 = new JLabel("������ƣ�");
		title2 = new JLabel("���룺");
		title2.setBorder(new EmptyBorder(0, 10, 0, 0));
		title3 = new JLabel("ת������ѡ��");
		
		inChoose = new JComboBox<>(jinzhiStr);
		number = new JTextField(5);
		
		outChoose = new JComboBox<>(jinzhiStr);
		solveBtn = new JButton("ת��");
		solveBtn.addActionListener(this);
		outChoose.setBorder(new EmptyBorder(0, 0, 0, 30));
		
		log = new JTextArea(6,24);
		log.append("ת����¼��\n");
		sc = new JScrollPane(log);
		
		mypanel1.add(title0);
		mypanel3.add(title1);
		mypanel3.add(inChoose);
		mypanel3.add(title2);
		mypanel3.add(number);
		mypanel4.add(title3);
		mypanel4.add(outChoose);
		mypanel4.add(solveBtn);
		
		mypanel2.add(mypanel3);
		mypanel2.add(mypanel4);
		
		mypanel5.add(sc);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel5);
		
		add(mypanel0);
		setTitle("����ת������");
		setBounds(1010, 300, 400, 300);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			String inputNumber = number.getText();
			if(inputNumber.equals("")) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			}else {
				int inputJinzhi = 2, outputJinzhi = 2;
				switch (inChoose.getSelectedItem().toString()) {
				case "������":
					inputJinzhi = 2;
					break;
				case "�˽���":
					inputJinzhi = 8;
					break;
				case "ʮ����":
					inputJinzhi = 10;
					break;
				case "ʮ������":
					inputJinzhi = 16;
					break;
				default:
					break;
				}
				switch (outChoose.getSelectedItem().toString()) {
				case "������":
					outputJinzhi = 2;
					break;
				case "�˽���":
					outputJinzhi = 8;
					break;
				case "ʮ����":
					outputJinzhi = 10;
					break;
				case "ʮ������":
					outputJinzhi = 16;
					break;
				default:
					break;
				}
				log.append(inChoose.getSelectedItem().toString()+"ת"+outChoose.getSelectedItem().toString()+":"+ChangeNum(inputJinzhi, outputJinzhi, inputNumber)+"\n");
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "�����ʽ����", "����", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private String ChangeNum(int inputJinzhi, int outputJinzhi, String inputNumber) {
		String result = "";
		if(inputJinzhi != 10) {
			result = _NTo10(inputJinzhi,inputNumber);
			result = _10ToN(outputJinzhi,result);
		}else {
			result = _10ToN(outputJinzhi,inputNumber);
		}
		return result;
	}
	
	private String _10ToN(int outputJinzhi, String inputNumber) {
		String number = "";
		if(outputJinzhi == 2){
			number = Integer.toBinaryString(Integer.parseInt(inputNumber));
		}else if(outputJinzhi == 8){
			number = Integer.toOctalString(Integer.parseInt(inputNumber));
		}else if(outputJinzhi == 16){
			number = Integer.toHexString(Integer.parseInt(inputNumber));
		}else if(outputJinzhi == 10) {
			number = inputNumber;
		}
		return number;
	}
	private String _NTo10(int inputJinzhi, String inputNmuber) {
		String number = "";
		number = Integer.valueOf(inputNmuber,inputJinzhi).toString();
		return number;
	}
}
