package software;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class B1_Frame extends JFrame implements ActionListener{
	JMenuBar jmb;
	JMenu file, edit;
	JMenuItem open, save, solve_Grade, select_grade, rank, select_stuNum, Grade_lay;
	boolean fileOpened = false, solved = false;
	JTable table;
	JScrollPane sc;
	String[][] stulist = new String[20][6];
	int datatGroup = 0;
	// Ĭ�ϱ��ģ��
    private DefaultTableModel model = null;
	public B1_Frame() {
		
		jmb = new JMenuBar();
		file = new JMenu("�ļ�");
		edit = new JMenu("����");
		jmb.add(file);
		jmb.add(edit);
		
		open = new JMenuItem("��");
		open.addActionListener(this);
		save = new JMenuItem("����");
		save.addActionListener(this);
		save.setEnabled(false);
		solve_Grade = new JMenuItem("����ȼ�");
		solve_Grade.addActionListener(this);
		select_grade = new JMenuItem("���ȼ���ѯ");
		select_grade.addActionListener(this);
		rank = new JMenuItem("����������");
		rank.addActionListener(this);
		select_stuNum = new JMenuItem("��ѧ�Ų�ѯ");
		select_stuNum.addActionListener(this);
		Grade_lay = new JMenuItem("�ȼ��ֲ���ѯ");
		Grade_lay.addActionListener(this);
		
		file.add(open);
		file.add(save);
		
		edit.add(solve_Grade);
		edit.add(select_grade);
		edit.add(rank);
		edit.add(select_stuNum);
		edit.add(Grade_lay);
		edit_menu_enableF();

        String[][] datas = {};
        String[] titles = { "���", "ѧ��", "ƽʱ�ɼ�", "���п��Գɼ�", "��ĩ���Գɼ�"};
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        table.setFont(new Font("Serif",0,16));
        table.setRowHeight(28);
		sc = new JScrollPane(table);
		
		add(sc);
		
		setTitle("ѧ���ɼ�����ϵͳ");
		setJMenuBar(jmb);
		setBounds(240, 600, 600, 480);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == open) {
			openPro();
			save.setEnabled(true);
			fileOpened = true;
			solve_Grade.setEnabled(true);
		}else if(event.getSource() == save) {
			savePro();
		}else if(event.getSource() == solve_Grade) {
			if(!solved) {
				solve_GradePro();
				select_stuNum.setEnabled(true);
				rank.setEnabled(true);
				select_grade.setEnabled(true);
				Grade_lay.setEnabled(true);
			}
		}else if(event.getSource() == select_grade) {
			select_gradePro();
		}else if(event.getSource() == rank) {
			rankPro();
		}else if(event.getSource() == select_stuNum) {
			select_stuNumPro();
		}else if(event.getSource() == Grade_lay) {
			Grade_layPro();
		}
	}
	
	public void openPro() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File filex=chooser.getSelectedFile();
			byte[] b = new byte[10];
			try{
				String[] tempstr = new String[15];
				BufferedReader br = null;
				br = new BufferedReader(new FileReader(filex));
				String line = "";
				int i = 0;
				datatGroup = Integer.valueOf(br.readLine());
				while((line=br.readLine())!=null){
					tempstr[i] = line;
					i++;
				}
				for(i=0;i<datatGroup;i++) {
					handle(tempstr[i], i);
				}
				br.close();
			} catch (Exception e) {
			}
		}
		
		for(int i=0;i<datatGroup;i++) {
			model.addRow(new String[] { "", "", "", "" });
			table.setValueAt(i+1, i, 0);
			table.setValueAt(stulist[i][0], i, 1);
			table.setValueAt(stulist[i][1], i, 2);
			table.setValueAt(stulist[i][2], i, 3);
			table.setValueAt(stulist[i][3], i, 4);
		}
	}
	
	public void savePro() {
		if(fileOpened) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "txt");
			//�����ļ�����
			chooser.setFileFilter(filter);
			//��ѡ�������
			int returnVal = chooser.showSaveDialog(new JPanel());
			//�����ļ����������֣���������ļ���
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getPath();
			System.out.println(path);
			if(path != null) {
				File file = new File(path+".dat");
				System.out.println(file.getAbsolutePath());
				try {
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					for(int i=0;i<datatGroup;i++) {
						String line = stulist[i][0]+"\t"+stulist[i][1]+"\t"+stulist[i][2]+"\t"+stulist[i][3]+"\t"+stulist[i][4]+"\t"+stulist[i][5]+"\n";
						fos.write(line.getBytes());
					}
					fos.close();
				} catch (Exception e) {
					
				}
			}
			}
		}
	}
	
	public void solve_GradePro() {
		model.addColumn("����");
		model.addColumn("�ȼ�");
		float overall_rating;
			for(int i=0;i<datatGroup;i++) {
				overall_rating = (float) ((int)(((float) (Float.valueOf(stulist[i][1])*0.3)+(float) (Float.valueOf(stulist[i][2])*0.3)+(float) (Float.valueOf(stulist[i][3])*0.4))*100)/100.0);
				if(overall_rating<60) {
					table.setValueAt(overall_rating, i, 5);
					table.setValueAt("E", i, 6);
					stulist[i][4] = String.valueOf(overall_rating);
					stulist[i][5] = "E";
				}else if(overall_rating>=60&&overall_rating<70) {
					table.setValueAt(overall_rating, i, 5);
					table.setValueAt("D", i, 6);
					stulist[i][4] = String.valueOf(overall_rating);
					stulist[i][5] = "D";
				}else if(overall_rating>=70&&overall_rating<80){
					table.setValueAt(overall_rating, i, 5);
					table.setValueAt("C", i, 6);
					stulist[i][4] = String.valueOf(overall_rating);
					stulist[i][5] = "C";
				}else if(overall_rating>=80&&overall_rating<90) {
					table.setValueAt(overall_rating, i, 5);
					table.setValueAt("B", i, 6);
					stulist[i][4] = String.valueOf(overall_rating);
					stulist[i][5] = "B";
				}else if(overall_rating>=90) {
					table.setValueAt(overall_rating, i, 5);
					table.setValueAt("A", i, 6);
					stulist[i][4] = String.valueOf(overall_rating);
					stulist[i][5] = "A";
				}
			}
		solved = true;
	} 
	
	public void select_gradePro() {
		String selectStr;
		selectStr = JOptionPane.showInputDialog("����������Ҫ��ѯ�ĵȼ���");
		if(selectStr != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			int xuhao = 0;
			if(selectStr.toCharArray()[0]>='a'&&selectStr.toCharArray()[0]<='z')
				selectStr = selectStr.toUpperCase();
			for(int j=0;j<datatGroup;j++) {
				if(stulist[j][5].equals(selectStr)) {
					model.addRow(new String[] {});
					table.setValueAt(xuhao+1, xuhao, 0);
					table.setValueAt(stulist[j][0], xuhao, 1);
					table.setValueAt(stulist[j][1], xuhao, 2);
					table.setValueAt(stulist[j][2], xuhao, 3);
					table.setValueAt(stulist[j][3], xuhao, 4);
					table.setValueAt(stulist[j][4], xuhao, 5);
					table.setValueAt(stulist[j][5], xuhao, 6);
					xuhao++;
				}
			}
		}
	}
	
	public void rankPro() {
		String[][] tempStr = new String[datatGroup][6];
		String[] temps = new String[6];
		tempStr = stulist;
		for(int i=0;i<datatGroup;i++) {
			for(int j=0;j<datatGroup;j++) {
				if(Float.valueOf(tempStr[i][4]) > Float.valueOf(tempStr[j][4])) {
					temps = tempStr[i];
					tempStr[i] = tempStr[j];
					tempStr[j] = temps;
				}
			}
		}
		while(table.getRowCount() > 0) {
			model.removeRow(0);
		}
		model.setColumnCount(0);
		model.addColumn("���");
		model.addColumn("ѧ��");
		model.addColumn("ƽʱ�ɼ�");
		model.addColumn("���п��Գɼ�");
		model.addColumn("��ĩ���Գɼ�");
		model.addColumn("����");
		model.addColumn("�ȼ�");
		
		for(int i=0;i<datatGroup;i++) {
			model.addRow(new String[] {});
			table.setValueAt(i+1, i, 0);
			for(int j=0;j<6;j++) {
				table.setValueAt(tempStr[i][j], i, j+1);
			}
		}
	} 
	
	public void select_stuNumPro() {
		String selectStr;
		selectStr = JOptionPane.showInputDialog("����������Ҫ��ѯ��ѧ�ţ�");
		if(selectStr != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			int xuhao = 0;
			if(selectStr.toCharArray()[0]>='a'&&selectStr.toCharArray()[0]<='z')
				selectStr = selectStr.toUpperCase();
			for(int j=0;j<datatGroup;j++) {
				if(stulist[j][0].equals(selectStr)) {
					model.addRow(new String[] {});
					table.setValueAt(xuhao+1, xuhao, 0);
					table.setValueAt(stulist[j][0], xuhao, 1);
					table.setValueAt(stulist[j][1], xuhao, 2);
					table.setValueAt(stulist[j][2], xuhao, 3);
					table.setValueAt(stulist[j][3], xuhao, 4);
					table.setValueAt(stulist[j][4], xuhao, 5);
					table.setValueAt(stulist[j][5], xuhao, 6);
					xuhao++;
				}
			}
			if(xuhao == 0) {
				JOptionPane.showMessageDialog(null, "û�в�ѯ������Ҫ�����ݣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	} 
	
	public void Grade_layPro() {
		model.setColumnCount(0);
		model.addColumn("�ȼ�");
		model.addColumn("����");
		model.addColumn("�ٷֱ�");
		float[] Percent = new float[5];
		int[] Num = {0, 0, 0, 0, 0};
		for(int i=0;i<datatGroup;i++) {
			switch(stulist[i][5]) {
			case "A":
				Num[0]++;
				break;
			case "B":
				Num[1]++;
				break;
			case "C":
				Num[2]++;
				break;
			case "D":
				Num[3]++;
				break;
			case "E":
				Num[4]++;
				break;
			}
		}
		String[] grade = {"A", "B", "C", "D", "E"};
		model.setRowCount(0);
		for(int i=0;i<5;i++) {
			model.addRow(new String[] {});
			Percent[i] = (float)((int)(((float)Num[i]/datatGroup)*1000))/1000;
			table.setValueAt(grade[i], i, 0);
			table.setValueAt(Num[i], i, 1);
			table.setValueAt(Percent[i], i, 2);
		}
		String class_ave;
		float ave = 0;
		for(int i=0;i<datatGroup;i++) {
			ave += Float.valueOf(stulist[i][4]);
		}
		ave /= datatGroup; 
		class_ave = "����ƽ���ɼ�" + (float)((int)(ave*100))/100;
		model.addRow(new String[] {});
		table.setValueAt(class_ave, 5, 1);
	}
	public void handle(String eString ,int i) {
		int j=0;
	    StringTokenizer st = new StringTokenizer(eString,",!' ';");
	    while(st.hasMoreElements()) {
	    	stulist[i][j] = st.nextElement().toString();
	    	j++;
	    }
	  }

	private void edit_menu_enableF() {
		solve_Grade.setEnabled(false);
		select_stuNum.setEnabled(false);
		rank.setEnabled(false);
		select_grade.setEnabled(false);
		Grade_lay.setEnabled(false);
	}
	
	private void edit_menu_enableT() {
		solve_Grade.setEnabled(true);
		select_stuNum.setEnabled(true);
		rank.setEnabled(true);
		select_grade.setEnabled(true);
		Grade_lay.setEnabled(true);
	}

}
