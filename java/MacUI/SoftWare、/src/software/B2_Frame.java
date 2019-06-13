package software;

/*
 * ���ܸ�����
 * 1���򿪻����ļ�-�������к����ѯ����
 * 2���򿪷����ļ�-�����㻰��-�����滰��-���򿪻����ļ��������к����ѯ����
 * 3�����û��ļ�-�����û�����ѯ����-�����û�����ѯ����
 */

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

public class B2_Frame extends JFrame implements ActionListener{
	//GUI ���
	JMenuBar jmb;
	JMenu file, edit, open, save;
	JMenuItem openTicket, openRate, openUser, openCost, saveCost, checkTicketByMaincall,
		solveCost, checkCostBymaincall, checkTicketByUsername, checkCostByUsername;
	boolean ticketOpened = false;
	static boolean rateOpened = false;
	boolean userlistOpened = false;
	boolean costSolved = false;
	boolean costFileSaved = false;
	JTable table;
	JScrollPane sc;
	// Ĭ�ϱ��ģ��
    private DefaultTableModel model = null;
    
    //����
    ArrayList<Ticket> ticket = new ArrayList<Ticket>();
    Cost[] cost;
    Map<String, String> rate = new HashMap<>();
    Map<String, String> userList = new HashMap<>();
    
	public B2_Frame() {
		//ͼ��
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
      	setIconImage(image);
		
		jmb = new JMenuBar();
		//һ���˵�
		file = new JMenu("�ļ�");
		edit = new JMenu("����ѡ��");
		jmb.add(file);
		jmb.add(edit);
		//�����˵�
		//�ļ�����
		open = new JMenu("��");
		save = new JMenu("����");
		file.add(open);
		file.add(save);
		
		//����ѡ��
		checkTicketByMaincall = new JMenuItem("�����к����ѯ����");
		checkTicketByMaincall.addActionListener(this);
		solveCost = new JMenuItem("���㻰��");
		solveCost.addActionListener(this);
		checkCostBymaincall= new JMenuItem("�����к����ѯ����");
		checkCostBymaincall.addActionListener(this);
		checkTicketByUsername = new JMenuItem("���û�����ѯ����");
		checkTicketByUsername.addActionListener(this);
		checkCostByUsername = new JMenuItem("���û�����ѯ����");
		checkCostByUsername.addActionListener(this);
		edit.add(checkTicketByMaincall);
		edit.add(solveCost);
		edit.add(checkCostBymaincall);
		edit.add(checkTicketByUsername);
		edit.add(checkCostByUsername);
		//�����˵�
		openTicket = new JMenuItem("�򿪻����ļ�");
		openTicket.addActionListener(this);
		openRate = new JMenuItem("�򿪷����ļ�");
		openRate.addActionListener(this);
		openUser = new JMenuItem("���û��ļ�");
		openUser.addActionListener(this);
		openCost = new JMenuItem("�򿪻����ļ�");
		openCost.addActionListener(this);
		open.add(openTicket);
		open.add(openRate);
		open.add(openUser);
		open.add(openCost);
		
		saveCost = new JMenuItem("���滰���ļ�");
		saveCost.addActionListener(this);
		save.add(saveCost);
		
		String[][] datas = {};
        String[] titles = {};
        model = new DefaultTableModel(datas, titles);
		table = new JTable(model);
		//����table���ݾ���
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        //���ñ�ͷ���ݾ���
      	((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.setFont(new Font("Serif",0,16));
        table.setRowHeight(28);
		sc = new JScrollPane(table);
		
		add(sc);
		SetMeunUable();
		setJMenuBar(jmb);
		setTitle("���żƷ�ϵͳ");
		//setBounds(1260, 580, 600, 480);
		setBounds(3180, 580, 600, 480);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Main.Frametype[6] = 0;
				setVisible(false);
			}
		});
	}

	private void SetMeunUable() {
		openCost.setEnabled(false);
		saveCost.setEnabled(false);
		checkTicketByMaincall.setEnabled(false);
		solveCost.setEnabled(false);
		checkCostBymaincall.setEnabled(false);
		checkTicketByUsername.setEnabled(false);
		checkCostByUsername.setEnabled(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
		case "�򿪻����ļ�":
			OpenTicket();
			break;
		case "�򿪷����ļ�":
			OpenRate();
			break;
		case "���û��ļ�":
			OpenUser();
			break;
		case "�򿪻����ļ�":
			OpenCost();
			break;
		case "���滰���ļ�":
			SaveCost();
			break;
		case "�����к����ѯ����":
			checkTicketByMaincall();
			break;
		case "���㻰��":
			SolveCost();
			break;
		case "�����к����ѯ����":
			CheckCostBymaincall();
			break;
		case "���û�����ѯ����":
			CheckTicketByUsername();
			break;
		case "���û�����ѯ����":
			CheckCostByUsername();
			break;
		};
	}

	private void OpenTicket() {
		DoTicket doticket = new DoTicket();
		
		ticket = doticket.readTicketFromFile();
		if(ticket != null) {
			model.setColumnCount(0);
			model.setRowCount(0);
			model.addColumn("��������");
			model.addColumn("���к���");
			model.addColumn("��������");
			model.addColumn("���к���");
			model.addColumn("ͨ��ʱ��(s)");
			
			for(int i=0;i<ticket.size();i++) {
				model.addRow(new String[] { "", "", "", "", ""});
				table.setValueAt(ticket.get(i).getMainCallAC(), i, 0);
				table.setValueAt(ticket.get(i).getMainCallNum(), i, 1);
				table.setValueAt(ticket.get(i).getCalledAC(), i, 2);
				table.setValueAt(ticket.get(i).getCalledNum(), i, 3);
				table.setValueAt(ticket.get(i).getCallTime(), i, 4);
			}
			
			checkTicketByMaincall.setEnabled(true);
			if(rate != null)
				solveCost.setEnabled(true);
			if(userList.size() != 0) 
				checkTicketByUsername.setEnabled(true);;
		}
	}

	private void OpenRate() {
		DoTicket doticket = new DoTicket();
		rate = doticket.readRate();
		try {
			//ʹ�õ���������ȡkey;
			Iterator<String> iter = rate.keySet().iterator();
			int i = 0;

			model.setColumnCount(0);
			model.setRowCount(0);
			model.addColumn("����");
			model.addColumn("����");
			
			while(iter.hasNext()){
				String key=iter.next();
				String value = rate.get(key);
				model.addRow(new String[] { "", "", "", "", ""});
				table.setValueAt(key, i, 0);
				table.setValueAt(value, i, 1);
				i++;
			}
			if(ticket.size() != 0)
				solveCost.setEnabled(true);
		} catch(Exception e) {}
	}

	private void OpenUser() {
		DoUser douser = new DoUser();
		userList = douser.readUser();
		
		try {
			//ʹ�õ���������ȡkey;
			Iterator<String> iter = userList.keySet().iterator();
			int i = 0;

			model.setColumnCount(0);
			model.setRowCount(0);
			model.addColumn("�绰����");
			model.addColumn("�û���");
			
			while(iter.hasNext()){
				String key=iter.next();
				String value = userList.get(key);
				model.addRow(new String[] { "", "", "", "", ""});
				table.setValueAt(key, i, 0);
				table.setValueAt(value, i, 1);
				i++;
			}
			if(ticket.size() != 0)
				checkTicketByUsername.setEnabled(true);
			if(cost != null)
				checkCostByUsername.setEnabled(true);
			if(cost != null) {
				checkCostBymaincall.setEnabled(true);
				checkCostByUsername.setEnabled(true);
			}
		} catch(Exception e) {}
	}

	private void OpenCost() {
		DoCost docost = new DoCost();
		cost = docost.readCostFromFile();
		if(cost != null) {
			model.setColumnCount(0);
			model.setRowCount(0);
			model.addColumn("���к���");
			model.addColumn("ͨ������");
			model.addColumn("���ѽ��");
			
			for(int i=0;i<cost.length;i++) {
				model.addRow(new String[] {});
				table.setValueAt(cost[i].getMainCallNum(), i, 0);
				table.setValueAt(cost[i].getCallType(), i, 1);
				table.setValueAt(cost[i].getCallCost(), i, 2);
			}
			
			if(userList.size() != 0)
				checkCostByUsername.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null, "�ļ��򿪴���", "����", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void SaveCost() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "txt");
		//�����ļ�����
		chooser.setFileFilter(filter);
		//��ѡ�������
		int returnVal = chooser.showSaveDialog(new JPanel());
		//�����ļ����������֣���������ļ���
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getPath();
			if(path != null) {
				File file = new File(path+".dat");
				try {
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					for(int i=0;i<cost.length;i++) {
						String line = cost[i].getMainCallNum()+"\t"+cost[i].getCallType()+"\t"+cost[i].getCallCost()+"\n";
						fos.write(line.getBytes());
					}
					fos.close();
					openCost.setEnabled(true);
				} catch (Exception e) {}
			}
		}
	}

	private void checkTicketByMaincall() {
		String mainCallNum;
		ArrayList<Ticket> checkTicketbyMC = new ArrayList<Ticket>();
		mainCallNum = JOptionPane.showInputDialog("����������Ҫ��ѯ�����к��룺");
		if(mainCallNum != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			for(int i=0;i<ticket.size();i++) {
				if(ticket.get(i).getMainCallNum().equals(mainCallNum)) {
					checkTicketbyMC.add(ticket.get(i));
				}
			}
			if(checkTicketbyMC.size() > 0) {
				model.setColumnCount(0);
				model.setRowCount(0);

				model.addColumn("���к���");
				model.addColumn("�û���");
				model.addColumn("���к���");
				model.addColumn("ͨ��ʱ��(s)");
				for(int j=0;j<checkTicketbyMC.size();j++) {
					model.addRow(new String[] {});
					table.setValueAt(checkTicketbyMC.get(j).getMainCallNum(), j, 0);
					table.setValueAt(userList.get(checkTicketbyMC.get(j).getMainCallNum()), j, 1);
					table.setValueAt(checkTicketbyMC.get(j).getCalledNum(), j, 2);
					table.setValueAt(checkTicketbyMC.get(j).getCallTime(), j, 3);
				}
				
			}else
				JOptionPane.showMessageDialog(null, "û�ҵ���Ӧ�Ļ�����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}

	private void SolveCost() {
		DoTicket doticket = new DoTicket();
		cost = doticket.solveCost(ticket, rate);
		
		model.setColumnCount(0);
		model.setRowCount(0);
		model.addColumn("���к���");
		model.addColumn("ͨ������");
		model.addColumn("���ѽ��");
		
		for(int i=0;i<cost.length;i++) {
			model.addRow(new String[] {});
			table.setValueAt(cost[i].getMainCallNum(), i, 0);
			table.setValueAt(cost[i].getCallType(), i, 1);
			table.setValueAt(cost[i].getCallCost(), i, 2);
		}
		
		saveCost.setEnabled(true);
		if(userList.size() != 0) {
			checkCostByUsername.setEnabled(true);
			checkCostBymaincall.setEnabled(true);
		}
	}

	private void CheckCostBymaincall() {
		String mainCallNum;
		ArrayList<Cost> checkCostbyMC = new ArrayList<Cost>();
		mainCallNum = JOptionPane.showInputDialog("����������Ҫ��ѯ�����к��룺");
		if(mainCallNum != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			for(int i=0;i<cost.length;i++) {
				if(cost[i].getMainCallNum().equals(mainCallNum)) {
					checkCostbyMC.add(cost[i]);
				}
			}
			if(userList.size() == 0) {
				JOptionPane.showMessageDialog(null, "����û��ļ��ٲ飡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}else if(checkCostbyMC.size() > 0 ) {
				float localCost = 0, longCost = 0, overall = 0;
				model.setColumnCount(0);
				model.setRowCount(0);

				model.addColumn("���к���");
				model.addColumn("�û���");
				model.addColumn("ͨ������");
				model.addColumn("ͨ�����");
				int j=0;
				for(;j<checkCostbyMC.size();j++) {
					model.addRow(new String[] {});
					table.setValueAt(checkCostbyMC.get(j).getMainCallNum(), j, 0);
					table.setValueAt(userList.get(checkCostbyMC.get(j).getMainCallNum()), j, 1);
					table.setValueAt(checkCostbyMC.get(j).getCallType(), j, 2);
					table.setValueAt(checkCostbyMC.get(j).getCallCost(), j, 3);
					if(checkCostbyMC.get(j).getCallType().equals("����ͨ��")) {
						localCost +=  checkCostbyMC.get(j).getCallCost();
					}else {
						longCost += checkCostbyMC.get(j).getCallCost();
					}
				}
				localCost = (float)((int)(localCost*100))/100;
				longCost = (float)((int)(longCost*100))/100;
				overall += localCost + longCost;
				model.addRow(new String[] {});
				table.setValueAt("���ػ��ѣ�" + localCost, j, 0);
				table.setValueAt("��;���ѣ�" + longCost, j, 1);
				table.setValueAt("�ܼƻ��ѣ�" + overall, j, 2);
			}else
				JOptionPane.showMessageDialog(null, "û�ҵ���Ӧ�Ļ��ѣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	private void CheckTicketByUsername() {
		String userName, mainCallNum = null;
		ArrayList<Ticket> checkTicketbyUN = new ArrayList<Ticket>();
		userName = JOptionPane.showInputDialog("����������Ҫ��ѯ���û�����");
		if(userName != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			//ʹ�õ���������ȡkey;
			Iterator<String> iter = userList.keySet().iterator();
			while(iter.hasNext()){
				String key=iter.next();
				String value = userList.get(key);
				if(value.equals(userName)) {
					mainCallNum = key;
					break;
				}
			}
			if(mainCallNum != null) {
				for(int i=0;i<ticket.size();i++) {
					if(ticket.get(i).getMainCallNum().equals(mainCallNum)) {
						checkTicketbyUN.add(ticket.get(i));
					}
				}
				if(checkTicketbyUN.size() > 0) {
					model.setColumnCount(0);
					model.setRowCount(0);
					model.addColumn("�û���");
					model.addColumn("���к���");
					model.addColumn("���к���");
					model.addColumn("ͨ��ʱ��(s)");
					for(int j=0;j<checkTicketbyUN.size();j++) {
						model.addRow(new String[] {});
						table.setValueAt(userName, j, 0);
						table.setValueAt(checkTicketbyUN.get(j).getMainCallNum(), j, 1);
						table.setValueAt(checkTicketbyUN.get(j).getCalledNum(), j, 2);
						table.setValueAt(checkTicketbyUN.get(j).getCallTime(), j, 3);
					}
					
				}else
					JOptionPane.showMessageDialog(null, "û�ҵ����û���Ӧ�Ļ�����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "û�ҵ����û���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}

	private void CheckCostByUsername() {
		String userName, mainCallNum = null;
		ArrayList<Cost> checkCostbyUN = new ArrayList<Cost>();
		userName = JOptionPane.showInputDialog("����������Ҫ��ѯ���û�����");
		if(userName != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			//ʹ�õ���������ȡkey;
			Iterator<String> iter = userList.keySet().iterator();
			while(iter.hasNext()){
				String key=iter.next();
				String value = userList.get(key);
				if(value.equals(userName)) {
					mainCallNum = key;
					break;
				}
			}
			if(mainCallNum != null) {
				for(int i=0;i<cost.length;i++) {
					if(cost[i].getMainCallNum().equals(mainCallNum)) {
						checkCostbyUN.add(cost[i]);
					}
				}
				if(checkCostbyUN.size() > 0) {
					float localCost = 0, longCost = 0, overall = 0;
					model.setColumnCount(0);
					model.setRowCount(0);
					model.addColumn("�û���");
					model.addColumn("���к���");
					model.addColumn("ͨ������");
					model.addColumn("ͨ�����");
					int j=0;
					for(;j<checkCostbyUN.size();j++) {
						model.addRow(new String[] {});
						table.setValueAt(userName, j, 0);
						table.setValueAt(mainCallNum, j, 1);
						table.setValueAt(checkCostbyUN.get(j).getCallType(), j, 2);
						table.setValueAt(checkCostbyUN.get(j).getCallCost(), j, 3);
						if(checkCostbyUN.get(j).getCallType().equals("����ͨ��")) {
							localCost +=  checkCostbyUN.get(j).getCallCost();
						}else {
							longCost += checkCostbyUN.get(j).getCallCost();
						}
					}
					localCost = (float)((int)(localCost*100))/100;
					longCost = (float)((int)(longCost*100))/100;
					overall += localCost + longCost;
					model.addRow(new String[] {});
					table.setValueAt("���ػ��ѣ�" + localCost, j, 0);
					table.setValueAt("��;���ѣ�" + longCost, j, 1);
					table.setValueAt("�ܼƻ��ѣ�" + overall, j, 2);
					
				}else
					JOptionPane.showMessageDialog(null, "û�ҵ����û���Ӧ�Ļ��ѣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "û�ҵ����û���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}

	
}

//1��readTick 2���ȶ�ȡ���� 3������cost 4������������ļ�  5����ȡ�����ļ�
class DoTicket{
	//��ȡ�����ļ�	(��������	����)
	public Map<String, String> readRate() {
		Map<String,String> rate = new HashMap<String, String>();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
		chooser.setFileFilter(filter);
		String[] tempstr = new String[4];
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File filex=chooser.getSelectedFile();
			byte[] b = new byte[10];
			try{
				BufferedReader br = null;
				br = new BufferedReader(new FileReader(filex));
				String line = "";
				int i = 0;
				while((line=br.readLine())!=null){
					tempstr[i] = line;
					i++;
				}
				br.close();
				String[] tempst = new String[2];
				int max = i;
				for(i=0;i<max;i++) {
					int j = 0;
					StringTokenizer st = new StringTokenizer(tempstr[i],",!' ';");
				    while(st.hasMoreElements()) {
				    	tempst[j] = st.nextElement().toString();
				    	j++;
				    }
				    rate.put(tempst[0], tempst[1]);
				}
				return rate;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "�ļ�ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
			} 
		}
		return null;
	}
	
	//��ȡͨ���ļ�	(��������	���к���	��������	���к���	ͨ��ʱ��)
	public ArrayList<Ticket> readTicketFromFile() {
		int j=0;
		Ticket ticket;
		JFileChooser chooser = new JFileChooser();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File filex=chooser.getSelectedFile();
			byte[] b = new byte[10];
			try{
				BufferedReader br = null;
				br = new BufferedReader(new FileReader(filex));
				String line = "";
				int i = 0;
				ArrayList a = new ArrayList<String>();
				while((line=br.readLine())!=null){
					a.add(line);
				}
				
				for(i=0;i<a.size();i++) {
					j = 0;
					ticket = new Ticket();
				    StringTokenizer st = new StringTokenizer(a.get(i).toString(),",!' ';");
				    while(st.hasMoreElements()) {
				    	switch(j) {
				    	case 0:
				    		ticket.setMainCallAC(st.nextElement().toString());
				    		break;
				    	case 1:
				    		ticket.setMainCallNum(st.nextElement().toString());break;
				    	case 2:
				    		ticket.setCalledAC(st.nextElement().toString());break;

				    	case 3:
				    		ticket.setCalledNum(st.nextElement().toString());break;
				    	case 4:
				    		ticket.setCallTime(Integer.valueOf(st.nextElement().toString()));break;
				    	}
				    	j++;
				    }
				    tickets.add(ticket);
				}
				br.close();
				
			} catch (IOException e) {}
		}
		if(j == 5) {
			return tickets;
		}else {
			JOptionPane.showMessageDialog(null, "�ļ�ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	//���㻰��(���к���	ͨ������	ͨ������)
	public Cost[] solveCost(ArrayList<Ticket> ticket, Map<String, String> rate) {
		Cost[] cost = null;//maincallNum,calltype,callcost
		String mainCallAC, mainCallNum, calledAC, calledNum;
		int callTime;
		if(ticket != null) {
			cost = new Cost[ticket.size()];
			for(int i=0;i<ticket.size();i++) {
				float result;
				cost[i] = new Cost();
				mainCallAC = ticket.get(i).getMainCallAC();
				mainCallNum = ticket.get(i).getMainCallNum();
				calledAC = ticket.get(i).getCalledAC();
				calledNum = ticket.get(i).getCalledNum();
				callTime = ticket.get(i).getCallTime();
				callTime = callTime%60 == 0 ? callTime/60 : (callTime/60+1);
				cost[i].setMainCallNum(mainCallNum);
				if(mainCallAC.equals(calledAC)) {
					cost[i].setCallType("����ͨ��");
					result = callTime > 3 ? (float)((callTime-3)*0.2+0.3) : (float)0.3;
					cost[i].setCallCost(result);
				}else {
					cost[i].setCallType("��;ͨ��");
					result = (float)(callTime*Float.valueOf(rate.get(calledAC)));
					result = (float)((int)(result*100))/100;
					cost[i].setCallCost(result);
				}
			}
		}
		return cost;
	}
	
}

class DoCost{
	
	//��ȡ�����ļ�	(���к���	ͨ������	ͨ������)
	public Cost[] readCostFromFile() {
		int j = 0;
		Cost[] cost = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File filex=chooser.getSelectedFile();
			byte[] b = new byte[10];
			try{
				ArrayList<String> array = new ArrayList<String>();
				BufferedReader br = null;
				br = new BufferedReader(new FileReader(filex));
				String line = "";
				while((line=br.readLine())!=null){
					array.add(line);
				}
				cost = new Cost[array.size()];
				for(int i=0;i<array.size();i++) {
					StringTokenizer st = new StringTokenizer(array.get(i).toString(),",!' ' \t ;");
					cost[i] = new Cost();
					j = 0;
				    while(st.hasMoreElements()) {
				    	switch(j) {
				    	case 0:
				    		cost[i].setMainCallNum(st.nextElement().toString());break;
				    	case 1:
				    		cost[i].setCallType(st.nextElement().toString());break;
				    	case 2:
				    		cost[i].setCallCost(Float.valueOf(st.nextElement().toString()));break;
				    	}
				    	j++;
				    }
				}
				br.close();

			} catch (IOException e) {
			}
		}
		if(j == 3) {
			return cost;
		}else {
			JOptionPane.showMessageDialog(null, "�ļ�ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	//���滰���ļ�
	public void writeCostToFile(Cost[] cost) {
		if(cost != null) {
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
						for(int i=0;i<cost.length;i++) {
							String line = cost[i].getMainCallNum()+"\t"+cost[i].getCallType()+"\t"+cost[i].getCallCost()+"\n";
							fos.write(line.getBytes());
						}
						fos.close();
					} catch (Exception e) {}
				}
			}
		}
	}
}

class DoUser{
	//��ȡ�û��ļ� (�绰����	�û���)
	public Map<String, String> readUser() {
		Map<String, String> userlist = new HashMap<String, String>();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat&txt File", "dat", "txt");
		chooser.setFileFilter(filter);
		ArrayList<String> array = new ArrayList<String>();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File filex=chooser.getSelectedFile();
			byte[] b = new byte[10];
			try{
				BufferedReader br = null;
				br = new BufferedReader(new FileReader(filex));
				String line = "";
				while((line=br.readLine())!=null){
					array.add(line);
				}
				br.close();
			} catch (Exception e) {
			}
		}
		String[] tempst = new String[2];
		try {
			for(int i=0;i<array.size();i++) {
				int j = 0;
				StringTokenizer st = new StringTokenizer(array.get(i),",!' ';");
				while(st.hasMoreElements()) {
					tempst[j] = st.nextElement().toString();
					j++;
				}
				userlist.put(tempst[0], tempst[1]);
			}
			return userlist;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "�ļ�ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
}

class Ticket{
	private String mainCallAC, mainCallNum, calledAC, calledNum;
	private int callTime;
	
	public String getMainCallAC() {
		return mainCallAC;
	}
	public void setMainCallAC(String mainCallAC) {
		this.mainCallAC = mainCallAC;
	}
	public String getMainCallNum() {
		return mainCallNum;
	}
	public void setMainCallNum(String mainCallNum) {
		this.mainCallNum = mainCallNum;
	}
	public String getCalledAC() {
		return calledAC;
	}
	public void setCalledAC(String calledAC) {
		this.calledAC = calledAC;
	}
	public String getCalledNum() {
		return calledNum;
	}
	public void setCalledNum(String calledNum) {
		this.calledNum = calledNum;
	}
	public int getCallTime() {
		return callTime;
	}
	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}
	public String toString() {
		return "mainCallAC:"+ mainCallAC+",mainCallNum:"+mainCallNum+",calledAC:"+ 
				calledAC+",calledNum:" +calledNum+",callTime:"+callTime;
	}
}
class Cost extends Ticket{
	private String callType;
	private float callCost;
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public float getCallCost() {
		return callCost;
	}
	public void setCallCost(float callCost) {
		this.callCost = callCost;
	}
}

class User{
	private String userName, userNum;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
}