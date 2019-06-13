package software;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class A3_Frame extends JFrame implements ActionListener{
	JTextArea input;
	JLabel title;
	JPanel mypanel0, mypanel1, mypanel2, mypanel3;
	JButton openbtn, savebtn;
	JScrollPane sc;
	public A3_Frame(){
		mypanel0 = new JPanel();
		
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel3 = new JPanel();
		
		title = new JLabel("文本编辑器");
		title.setFont(new Font("Serif",0,20));
		mypanel1.add(title);		
		
		input = new JTextArea(8,30);
		sc = new JScrollPane(input);
		mypanel2.add(sc);
		
		openbtn = new JButton("打开文件");
		openbtn.addActionListener(this);
		savebtn = new JButton("保存文件");
		savebtn.addActionListener(this);
		mypanel3.add(openbtn);
		mypanel3.add(savebtn);
		
		mypanel0.add(mypanel1);
		mypanel0.add(mypanel2);
		mypanel0.add(mypanel3);
		
		add(mypanel0);
		setTitle("文本编辑器");
		setBounds(1220,0,400,300);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == openbtn) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File filex=chooser.getSelectedFile();
				byte[] b = new byte[10];
				try {
					//使用BufferedReader读取
					BufferedReader br = new BufferedReader(new FileReader(filex));
					String line = "";
					while((line=br.readLine())!=null){
						input.append(line+"\n");
					}
					br.close();
				} catch (Exception e) {
				}
			}
		}else if(event.getSource() == savebtn) {
			String filename;
			String inputstr = JOptionPane.showInputDialog("请输入您所要保存的文件名：","output.dat");
			if(inputstr != null) {
				filename = inputstr;
				try {
					FileOutputStream fos = new FileOutputStream(filename);
					if(input.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "输入内容为空", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					fos.write(input.getText().getBytes());
					fos.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
}
