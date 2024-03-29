package software;

/*
 * 功能概述：
 * 1、打开话单文件-》按主叫号码查询话单
 * 2、打开费率文件-》计算话费-》保存话费-》打开话费文件、按主叫号码查询话费
 * 3、打开用户文件-》按用户名查询话单-》按用户名查询话费
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
	//GUI 组件
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
	// 默认表格模型
    private DefaultTableModel model = null;
    
    //数据
    ArrayList<Ticket> ticket = new ArrayList<Ticket>();
    Cost[] cost;
    Map<String, String> rate = new HashMap<>();
    Map<String, String> userList = new HashMap<>();
    
	public B2_Frame() {
		//图标
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
      	setIconImage(image);
		
		jmb = new JMenuBar();
		//一级菜单
		file = new JMenu("文件");
		edit = new JMenu("功能选择");
		jmb.add(file);
		jmb.add(edit);
		//二级菜单
		//文件操作
		open = new JMenu("打开");
		save = new JMenu("保存");
		file.add(open);
		file.add(save);
		
		//功能选择
		checkTicketByMaincall = new JMenuItem("按主叫号码查询话单");
		checkTicketByMaincall.addActionListener(this);
		solveCost = new JMenuItem("计算话费");
		solveCost.addActionListener(this);
		checkCostBymaincall= new JMenuItem("按主叫号码查询话费");
		checkCostBymaincall.addActionListener(this);
		checkTicketByUsername = new JMenuItem("按用户名查询话单");
		checkTicketByUsername.addActionListener(this);
		checkCostByUsername = new JMenuItem("按用户名查询话费");
		checkCostByUsername.addActionListener(this);
		edit.add(checkTicketByMaincall);
		edit.add(solveCost);
		edit.add(checkCostBymaincall);
		edit.add(checkTicketByUsername);
		edit.add(checkCostByUsername);
		//三级菜单
		openTicket = new JMenuItem("打开话单文件");
		openTicket.addActionListener(this);
		openRate = new JMenuItem("打开费率文件");
		openRate.addActionListener(this);
		openUser = new JMenuItem("打开用户文件");
		openUser.addActionListener(this);
		openCost = new JMenuItem("打开话费文件");
		openCost.addActionListener(this);
		open.add(openTicket);
		open.add(openRate);
		open.add(openUser);
		open.add(openCost);
		
		saveCost = new JMenuItem("保存话费文件");
		saveCost.addActionListener(this);
		save.add(saveCost);
		
		String[][] datas = {};
        String[] titles = {};
        model = new DefaultTableModel(datas, titles);
		table = new JTable(model);
		//设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        //设置表头内容居中
      	((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.setFont(new Font("Serif",0,16));
        table.setRowHeight(28);
		sc = new JScrollPane(table);
		
		add(sc);
		SetMeunUable();
		setJMenuBar(jmb);
		setTitle("电信计费系统");
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
		case "打开话单文件":
			OpenTicket();
			break;
		case "打开费率文件":
			OpenRate();
			break;
		case "打开用户文件":
			OpenUser();
			break;
		case "打开话费文件":
			OpenCost();
			break;
		case "保存话费文件":
			SaveCost();
			break;
		case "按主叫号码查询话单":
			checkTicketByMaincall();
			break;
		case "计算话费":
			SolveCost();
			break;
		case "按主叫号码查询话费":
			CheckCostBymaincall();
			break;
		case "按用户名查询话单":
			CheckTicketByUsername();
			break;
		case "按用户名查询话费":
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
			model.addColumn("主叫区号");
			model.addColumn("主叫号码");
			model.addColumn("被叫区号");
			model.addColumn("被叫号码");
			model.addColumn("通话时长(s)");
			
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
			//使用迭代器，获取key;
			Iterator<String> iter = rate.keySet().iterator();
			int i = 0;

			model.setColumnCount(0);
			model.setRowCount(0);
			model.addColumn("区号");
			model.addColumn("费率");
			
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
			//使用迭代器，获取key;
			Iterator<String> iter = userList.keySet().iterator();
			int i = 0;

			model.setColumnCount(0);
			model.setRowCount(0);
			model.addColumn("电话号码");
			model.addColumn("用户名");
			
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
			model.addColumn("主叫号码");
			model.addColumn("通话类型");
			model.addColumn("话费金额");
			
			for(int i=0;i<cost.length;i++) {
				model.addRow(new String[] {});
				table.setValueAt(cost[i].getMainCallNum(), i, 0);
				table.setValueAt(cost[i].getCallType(), i, 1);
				table.setValueAt(cost[i].getCallCost(), i, 2);
			}
			
			if(userList.size() != 0)
				checkCostByUsername.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null, "文件打开错误！", "错误", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void SaveCost() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "txt");
		//设置文件类型
		chooser.setFileFilter(filter);
		//打开选择器面板
		int returnVal = chooser.showSaveDialog(new JPanel());
		//保存文件从这里入手，输出的是文件名
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
		mainCallNum = JOptionPane.showInputDialog("请输入您所要查询的主叫号码：");
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

				model.addColumn("主叫号码");
				model.addColumn("用户名");
				model.addColumn("被叫号码");
				model.addColumn("通话时长(s)");
				for(int j=0;j<checkTicketbyMC.size();j++) {
					model.addRow(new String[] {});
					table.setValueAt(checkTicketbyMC.get(j).getMainCallNum(), j, 0);
					table.setValueAt(userList.get(checkTicketbyMC.get(j).getMainCallNum()), j, 1);
					table.setValueAt(checkTicketbyMC.get(j).getCalledNum(), j, 2);
					table.setValueAt(checkTicketbyMC.get(j).getCallTime(), j, 3);
				}
				
			}else
				JOptionPane.showMessageDialog(null, "没找到对应的话单！", "提示", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}

	private void SolveCost() {
		DoTicket doticket = new DoTicket();
		cost = doticket.solveCost(ticket, rate);
		
		model.setColumnCount(0);
		model.setRowCount(0);
		model.addColumn("主叫号码");
		model.addColumn("通话类型");
		model.addColumn("话费金额");
		
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
		mainCallNum = JOptionPane.showInputDialog("请输入您所要查询的主叫号码：");
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
				JOptionPane.showMessageDialog(null, "请打开用户文件再查！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else if(checkCostbyMC.size() > 0 ) {
				float localCost = 0, longCost = 0, overall = 0;
				model.setColumnCount(0);
				model.setRowCount(0);

				model.addColumn("主叫号码");
				model.addColumn("用户名");
				model.addColumn("通话类型");
				model.addColumn("通话金额");
				int j=0;
				for(;j<checkCostbyMC.size();j++) {
					model.addRow(new String[] {});
					table.setValueAt(checkCostbyMC.get(j).getMainCallNum(), j, 0);
					table.setValueAt(userList.get(checkCostbyMC.get(j).getMainCallNum()), j, 1);
					table.setValueAt(checkCostbyMC.get(j).getCallType(), j, 2);
					table.setValueAt(checkCostbyMC.get(j).getCallCost(), j, 3);
					if(checkCostbyMC.get(j).getCallType().equals("本地通话")) {
						localCost +=  checkCostbyMC.get(j).getCallCost();
					}else {
						longCost += checkCostbyMC.get(j).getCallCost();
					}
				}
				localCost = (float)((int)(localCost*100))/100;
				longCost = (float)((int)(longCost*100))/100;
				overall += localCost + longCost;
				model.addRow(new String[] {});
				table.setValueAt("本地话费：" + localCost, j, 0);
				table.setValueAt("长途话费：" + longCost, j, 1);
				table.setValueAt("总计话费：" + overall, j, 2);
			}else
				JOptionPane.showMessageDialog(null, "没找到对应的话费！", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	private void CheckTicketByUsername() {
		String userName, mainCallNum = null;
		ArrayList<Ticket> checkTicketbyUN = new ArrayList<Ticket>();
		userName = JOptionPane.showInputDialog("请输入您所要查询的用户名：");
		if(userName != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			//使用迭代器，获取key;
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
					model.addColumn("用户名");
					model.addColumn("主叫号码");
					model.addColumn("被叫号码");
					model.addColumn("通话时长(s)");
					for(int j=0;j<checkTicketbyUN.size();j++) {
						model.addRow(new String[] {});
						table.setValueAt(userName, j, 0);
						table.setValueAt(checkTicketbyUN.get(j).getMainCallNum(), j, 1);
						table.setValueAt(checkTicketbyUN.get(j).getCalledNum(), j, 2);
						table.setValueAt(checkTicketbyUN.get(j).getCallTime(), j, 3);
					}
					
				}else
					JOptionPane.showMessageDialog(null, "没找到此用户对应的话单！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "没找到此用户！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}

	private void CheckCostByUsername() {
		String userName, mainCallNum = null;
		ArrayList<Cost> checkCostbyUN = new ArrayList<Cost>();
		userName = JOptionPane.showInputDialog("请输入您所要查询的用户名：");
		if(userName != null) {
			while(table.getRowCount() > 0) {
				model.removeRow(0);
			}
			//使用迭代器，获取key;
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
					model.addColumn("用户名");
					model.addColumn("主叫号码");
					model.addColumn("通话类型");
					model.addColumn("通话金额");
					int j=0;
					for(;j<checkCostbyUN.size();j++) {
						model.addRow(new String[] {});
						table.setValueAt(userName, j, 0);
						table.setValueAt(mainCallNum, j, 1);
						table.setValueAt(checkCostbyUN.get(j).getCallType(), j, 2);
						table.setValueAt(checkCostbyUN.get(j).getCallCost(), j, 3);
						if(checkCostbyUN.get(j).getCallType().equals("本地通话")) {
							localCost +=  checkCostbyUN.get(j).getCallCost();
						}else {
							longCost += checkCostbyUN.get(j).getCallCost();
						}
					}
					localCost = (float)((int)(localCost*100))/100;
					longCost = (float)((int)(longCost*100))/100;
					overall += localCost + longCost;
					model.addRow(new String[] {});
					table.setValueAt("本地话费：" + localCost, j, 0);
					table.setValueAt("长途话费：" + longCost, j, 1);
					table.setValueAt("总计话费：" + overall, j, 2);
					
				}else
					JOptionPane.showMessageDialog(null, "没找到此用户对应的话费！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "没找到此用户！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}

	
}

//1、readTick 2、先读取费率 3、计算cost 4、输出费用至文件  5、读取费用文件
class DoTicket{
	//读取费率文件	(被叫区号	费率)
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
				JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
			} 
		}
		return null;
	}
	
	//读取通话文件	(主叫区号	主叫号码	被叫区号	被叫号码	通话时长)
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
			JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	//计算话费(主叫号码	通话类型	通话费用)
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
					cost[i].setCallType("本地通话");
					result = callTime > 3 ? (float)((callTime-3)*0.2+0.3) : (float)0.3;
					cost[i].setCallCost(result);
				}else {
					cost[i].setCallType("长途通话");
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
	
	//读取话费文件	(主叫号码	通话类型	通话费用)
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
			JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	//保存话费文件
	public void writeCostToFile(Cost[] cost) {
		if(cost != null) {
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
	//读取用户文件 (电话号码	用户名)
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
			JOptionPane.showMessageDialog(null, "文件选择错误！", "错误", JOptionPane.ERROR_MESSAGE);
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