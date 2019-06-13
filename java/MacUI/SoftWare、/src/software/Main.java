package software;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Main extends JFrame implements MouseListener{
	
	public static Main mainFrame;
	public static int iconlength = 80, textFieldlength = 100, textFieldheight = 20;
	public static int marginleft = 20, margintop = 20;
	public static int pressTime = 0;
	public static int[] Frametype = {0, 0, 0, 0, 0, 0, 0};
	
	static JButton A1, A2, A3, A4, A5, B1, B2, close;
	int x_location, y_location;
	
	A1_Frame a1;
	A2_Frame a2;
	A3_Frame a3;
	A4_Frame a4;
	A5_Frame a5;
	B1_Frame b1;
	B2_Frame b2;
	
	public static void main(String[] args) {
		try{
			//风格设置
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			mainFrame = new Main();
			UIManager.put("RootPane.setupButtonVisible", false);
			SwingUtilities.updateComponentTreeUI(mainFrame);
			
			Init();
			mainFrame.setVisible(true);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	public Main() {
		setTitle("B16031114");
		setSize(760,600);
		setResizable(false);
		x_location = getCenterLocation(1);
		y_location = getCenterLocation(2);
		setLocation(x_location, y_location);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		A1 = new JButton();
		A1.addMouseListener(this);
		
		A2 = new JButton();
		A2.addMouseListener(this);
		
		A3 = new JButton();
		A3.addMouseListener(this);
		
		A4 = new JButton();
		A4.addMouseListener(this);
		
		A5 = new JButton();
		A5.addMouseListener(this);
		
		B1 = new JButton();
		B1.addMouseListener(this);
		
		B2 = new JButton();
		B2.addMouseListener(this);
		
		close = new JButton();
		close.addMouseListener(this);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(Frametype[0] == 1) {
					a1.setVisible(true);
				}else if(Frametype[1] == 1) {
					a2.setVisible(true);
				}else if(Frametype[2] == 1) {
					a3.setVisible(true);
				}else if(Frametype[3] == 1) {
					a4.setVisible(true);
				}else if(Frametype[4] == 1) {
					a5.setVisible(true);
				}else if(Frametype[5] == 1) {
					b1.setVisible(true);
				}else if(Frametype[6] == 1) {
					b2.setVisible(true);
				}else
					System.exit(0);
			}
		});
	}

	public static void Init() {
		//图标
		Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
		mainFrame.setIconImage(image);
		 //背景
		ImageIcon img=new ImageIcon(System.getProperty("user.dir")+"\\image\\bg1.jpg");//设置的图片路径	
		img.setImage(img.getImage().getScaledInstance(mainFrame.getWidth(), mainFrame.getHeight(),Image.SCALE_DEFAULT ));
		//将背景图片放在标签里
		JLabel imgLabel=new JLabel(img);
		//将背景标签添加到jfram的LayeredPane面板里。
		mainFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//设置背景标签的位置 
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		JPanel jp = (JPanel)mainFrame.getContentPane();
		jp.setOpaque(false);
		
		//学生成绩排名图标
		ImageIcon logo1=new ImageIcon(System.getProperty("user.dir")+"\\image\\1icon.png");
		//图片大小自适应
		logo1.setImage(logo1.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
		
		A1.setBounds(marginleft, margintop, iconlength, iconlength);
		//除去默认背景填充
		A1.setContentAreaFilled(false);
		//除去获取的焦点框
		A1.setFocusPainted(false);
		A1.setIcon(logo1);
		
		mainFrame.add(A1);
		JLabel lb1=new JLabel("成绩排名");
		lb1.setBounds(marginleft + (int)((textFieldlength - iconlength)/2), margintop + iconlength, textFieldlength, textFieldheight);;
		mainFrame.add(lb1);
		mainFrame.setLayout(null);
		lb1.setForeground(Color.white);
		lb1.setBackground(Color.white);
		lb1.setFont(new Font("宋体", Font.BOLD, 14));
		
		//打字练习图标
		ImageIcon logo2=new ImageIcon(System.getProperty("user.dir")+"\\image\\2icon.png");
		//图片大小自适应
		logo2.setImage(logo2.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
		A2.setBounds(marginleft, 2*margintop + iconlength + textFieldheight, iconlength, iconlength);
		//除去默认背景填充
		A2.setContentAreaFilled(false);
		//除去获取的焦点框
		A2.setFocusPainted(false);
		A2.setIcon(logo2);
				
		mainFrame.add(A2);
		JLabel lb2=new JLabel("打字练习");
		lb2.setBounds(marginleft + (int)((textFieldlength - iconlength)/2), 2*margintop + 2*iconlength + textFieldheight, textFieldlength, textFieldheight);
		mainFrame.add(lb2);
		mainFrame.setLayout(null);
		lb2.setForeground(Color.white);
		lb2.setBackground(Color.white);
		lb2.setFont(new Font("宋体", Font.BOLD, 14));
		
		//文本编辑器图标
		ImageIcon logo3=new ImageIcon(System.getProperty("user.dir")+"\\image\\3icon.png");
		//图片大小自适应
		logo3.setImage(logo3.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
		A3.setBounds(marginleft, 3*margintop + 2*iconlength + 2*textFieldheight, iconlength, iconlength);
		//除去默认背景填充
		A3.setContentAreaFilled(false);
		//除去获取的焦点框
		A3.setFocusPainted(false);
		A3.setIcon(logo3);
						
		mainFrame.add(A3);
		JLabel lb3=new JLabel("文本编辑");
		lb3.setBounds(marginleft + (int)((textFieldlength - iconlength)/2), 3*margintop + 3*iconlength + 2*textFieldheight, textFieldlength, textFieldheight);
		mainFrame.add(lb3);
		mainFrame.setLayout(null);
		lb3.setForeground(Color.white);
		lb3.setBackground(Color.white);
		lb3.setFont(new Font("宋体", Font.BOLD, 14));
		
		//加解密程序图标
		ImageIcon logo4=new ImageIcon(System.getProperty("user.dir")+"\\image\\4icon.png");
		//图片大小自适应
		logo4.setImage(logo4.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
				
		A4.setBounds(2*marginleft + iconlength, margintop, iconlength, iconlength);
		//除去默认背景填充
		A4.setContentAreaFilled(false);
		//除去获取的焦点框
		A4.setFocusPainted(false);
		A4.setIcon(logo4);
				
		mainFrame.add(A4);
		JLabel lb4=new JLabel("加密解密");
		lb4.setBounds(2*marginleft + iconlength + (int)((textFieldlength - iconlength)/2), margintop + iconlength, textFieldlength, textFieldheight);;
		mainFrame.add(lb4);
		mainFrame.setLayout(null);
		lb4.setForeground(Color.white);
		lb4.setBackground(Color.white);
		lb4.setFont(new Font("宋体", Font.BOLD, 14));
				
		//学生成绩排名图标
		ImageIcon logo5=new ImageIcon(System.getProperty("user.dir")+"\\image\\5icon.png");
		//图片大小自适应
		logo5.setImage(logo5.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
		A5.setBounds(2*marginleft + iconlength, 2*margintop + iconlength + textFieldheight, iconlength, iconlength);
		//除去默认背景填充
		A5.setContentAreaFilled(false);
		//除去获取的焦点框
		A5.setFocusPainted(false);
		A5.setIcon(logo5);
						
		mainFrame.add(A5);
		JLabel lb5=new JLabel("进制转换");
		lb5.setBounds(2*marginleft + iconlength + (int)((textFieldlength - iconlength)/2), 2*margintop + 2*iconlength + textFieldheight, textFieldlength, textFieldheight);
		mainFrame.add(lb5);
		mainFrame.setLayout(null);
		lb5.setForeground(Color.white);
		lb5.setBackground(Color.white);
		lb5.setFont(new Font("宋体", Font.BOLD, 14));
				
		//学生成绩核算系统图标
		ImageIcon logo6=new ImageIcon(System.getProperty("user.dir")+"\\image\\6icon.png");
		//图片大小自适应
		logo6.setImage(logo6.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
		B1.setBounds(3*marginleft + 2*iconlength, margintop, iconlength, iconlength);
		//除去默认背景填充
		B1.setContentAreaFilled(false);
		//除去获取的焦点框
		B1.setFocusPainted(false);
		B1.setIcon(logo6);
								
		mainFrame.add(B1);
		JLabel lb6=new JLabel("成绩核算");
		lb6.setBounds(3*marginleft + 2*iconlength + (int)((textFieldlength - iconlength)/2), margintop + iconlength, textFieldlength, textFieldheight);
		mainFrame.add(lb6);
		mainFrame.setLayout(null);
		lb6.setForeground(Color.white);
		lb6.setBackground(Color.white);
		lb6.setFont(new Font("宋体", Font.BOLD, 14));
				
		//学生成绩排名图标
		ImageIcon logo7=new ImageIcon(System.getProperty("user.dir")+"\\image\\7icon.png");
		//图片大小自适应
		logo7.setImage(logo7.getImage().getScaledInstance(iconlength, iconlength,Image.SCALE_DEFAULT ));
		B2.setBounds(3*marginleft + 2*iconlength, 2*margintop + iconlength + textFieldheight, iconlength, iconlength);
		//除去默认背景填充
		B2.setContentAreaFilled(false);
		//除去获取的焦点框
		B2.setFocusPainted(false);
		B2.setIcon(logo7);
								
		mainFrame.add(B2);
		JLabel lb7=new JLabel("电信计费");
		lb7.setBounds(3*marginleft + 2*iconlength + (int)((textFieldlength - iconlength)/2), 2*margintop + 2*iconlength + textFieldheight, textFieldlength, textFieldheight);
		mainFrame.add(lb7);
		mainFrame.setLayout(null);
		lb7.setForeground(Color.white);
		lb7.setBackground(Color.white);
		lb7.setFont(new Font("宋体", Font.BOLD, 14));
		
		//电信计费系统图标
		ImageIcon logo8=new ImageIcon(System.getProperty("user.dir")+"\\image\\switch.png");
		//图片大小自适应
		logo8.setImage(logo8.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT ));
		close.setBounds(26, 428, 60, 60);
		//除去默认背景填充
		close.setContentAreaFilled(false);
		//除去获取的焦点框
		close.setFocusPainted(false);
		close.setIcon(logo8);
						
		mainFrame.add(close);
		JLabel lb8=new JLabel("关闭程序");
		lb8.setBounds(26, 488, 100, 20);;
		mainFrame.add(lb8);
		mainFrame.setLayout(null);
		lb8.setForeground(Color.white);
		lb8.setBackground(Color.white);
		lb8.setFont(new Font("宋体", Font.BOLD, 14));	
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
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			if(e.getSource() == A1) {
				if(Frametype[0] == 0 ) {
					a1 = new A1_Frame();
					a1.setVisible(true);
					Frametype[0] = 1;
				}else {
					a1.setVisible(true);
				}
			}else if(e.getSource() == A2) {
				if(Frametype[1] == 0 ) {
					a2 = new A2_Frame();
					a2.setVisible(true);
					Frametype[1] = 1;
				}else {
					a2.setVisible(true);
				}
			}else if(e.getSource() == A3) {
				if(Frametype[2] == 0 ) {
					a3 = new A3_Frame();
					a3.setVisible(true);
					Frametype[2] = 1;
				}else {
					a3.setVisible(true);
				}
			}else if(e.getSource() == A4) {
				if(Frametype[3] == 0 ) {
					a4 = new A4_Frame();
					a4.setVisible(true);
					Frametype[3] = 1;
				}else {
					a4.setVisible(true);
				}
			}else if(e.getSource() == A5) {
				if(Frametype[4] == 0 ) {
					a5 = new A5_Frame();
					a5.setVisible(true);
					Frametype[4] = 1;
				}else {
					a5.setVisible(true);
				}
			}else if(e.getSource() == B1) {
				if(Frametype[5] == 0 ) {
					b1 = new B1_Frame();
					b1.setVisible(true);
					Frametype[5] = 1;
				}else {
					b1.setVisible(true);
				}
			}else if(e.getSource() == B2) {
				if(Frametype[6] == 0 ) {
					b2 = new B2_Frame();
					b2.setVisible(true);
					Frametype[6] = 1;
				}else {
					b2.setVisible(true);
				}
			}
		}else if(e.getSource() == close){
			if(Frametype[0] == 1) {
				a1.setVisible(true);
			}else if(Frametype[1] == 1) {
				a2.setVisible(true);
			}else if(Frametype[2] == 1) {
				a3.setVisible(true);
			}else if(Frametype[3] == 1) {
				a4.setVisible(true);
			}else if(Frametype[4] == 1) {
				a5.setVisible(true);
			}else if(Frametype[5] == 1) {
				b1.setVisible(true);
			}else if(Frametype[6] == 1) {
				b2.setVisible(true);
			}else
				System.exit(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == close) {
			ImageIcon logo=new ImageIcon(System.getProperty("user.dir")+"\\image\\swithon.png");
			//图片大小自适应
			logo.setImage(logo.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT ));
			//除去默认背景填充
			close.setIcon(logo);
		}else if(e.getSource() == A1) {
			A1.setContentAreaFilled(true);
		}else if(e.getSource() == A2) {
			A2.setContentAreaFilled(true);
		}else if(e.getSource() == A3) {
			A3.setContentAreaFilled(true);
		}else if(e.getSource() == A4) {
			A4.setContentAreaFilled(true);
		}else if(e.getSource() == A5) {
			A5.setContentAreaFilled(true);
		}else if(e.getSource() == B1) {
			B1.setContentAreaFilled(true);
		}else if(e.getSource() == B2) {
			B2.setContentAreaFilled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == close) {
			ImageIcon logo8=new ImageIcon(System.getProperty("user.dir")+"\\image\\switch.png");
			//图片大小自适应
			logo8.setImage(logo8.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT ));
			close.setIcon(logo8);
		}else if(e.getSource() == A1) {
			A1.setContentAreaFilled(false);
		}else if(e.getSource() == A2) {
			A2.setContentAreaFilled(false);
		}else if(e.getSource() == A3) {
			A3.setContentAreaFilled(false);
		}else if(e.getSource() == A4) {
			A4.setContentAreaFilled(false);
		}else if(e.getSource() == A5) {
			A5.setContentAreaFilled(false);
		}else if(e.getSource() == B1) {
			B1.setContentAreaFilled(false);
		}else if(e.getSource() == B2) {
			B2.setContentAreaFilled(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
