package software;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class A3_Frame extends JFrame implements ActionListener, ComponentListener{
	JTextArea input;
	JLabel title;
	JPanel mypanel0, mypanel1, mypanel2, mypanel3;
	JButton openbtn, savebtn;
	JScrollPane sc;
	public static int width, height;
	public A3_Frame(){

		//setBounds(960,100,500,400);
		setBounds(2880,100,500,400);
		//图标
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
      	setIconImage(image);
		width = getWidth();
		height = getHeight();
      	
		mypanel0 = new JPanel();
		mypanel1 = new JPanel();
		mypanel2 = new JPanel();
		mypanel3 = new JPanel();
		
		title = new JLabel("文本编辑器");
		title.setFont(new Font("Serif",0,20));
		title.setSize(160,120);
		mypanel1.add(title);		
		input = new JTextArea(height/40,width/12);
		sc = new JScrollPane(input);
		mypanel2.add(sc);
		
		FlowLayout f = (FlowLayout) mypanel3.getLayout();
		f.setHgap(60);
		openbtn = new JButton("打开文件");
		openbtn.setFont(new Font("Serif",0,16));
		openbtn.addActionListener(this);
		openbtn.setFocusPainted(false);
		savebtn = new JButton("保存文件");
		savebtn.setFont(new Font("Serif",0,16));
		savebtn.addActionListener(this);
		savebtn.setFocusPainted(false);
		mypanel3.add(openbtn);
		mypanel3.add(savebtn);
		
		mypanel0.setLayout(new BorderLayout());
		mypanel0.add(mypanel1,BorderLayout.NORTH);
		mypanel0.add(mypanel2,BorderLayout.CENTER);
		mypanel0.add(mypanel3,BorderLayout.SOUTH);
		
		add(mypanel0);
		addComponentListener(this);
		setTitle("文本编辑器");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Main.Frametype[2] = 0;
				setVisible(false);
			}
		});
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
					input.setText("");
					while((line=br.readLine())!=null){
						input.append(line+"\n");
					}
					br.close();
				} catch (Exception e) {
				}
			}
		}else if(event.getSource() == savebtn) {
			if(input.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "输入内容为空", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "txt");
				//设置文件类型
				chooser.setFileFilter(filter);
				//打开选择器面板
				int returnVal = chooser.showSaveDialog(new JPanel());
				//保存文件从这里入手，输出的是文件名
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getPath();
					System.out.println(path);
					if(path != null) {
						File file = new File(path+".dat");
						try {
							file.createNewFile();
							FileOutputStream fos = new FileOutputStream(file);
							fos.write(input.getText().getBytes());
							fos.close();
						} catch (Exception e) {}
					}
				}
			}
		}
	}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentResized(ComponentEvent e) {
		width = getWidth();
		height = getHeight();
		input.setRows(height/40);
		input.setColumns(width/12);
	}
	@Override
	public void componentShown(ComponentEvent e) {}
}
