package software;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class A1_Frame extends JFrame {
	// 默认表格模型
    private DefaultTableModel model = null;
    private JTable table = null;

    private JButton addBtn = null, decBtn = null, solveBtn = null;

    String number = "0";

    public A1_Frame(){
        super("学生成绩排名");
        
        //图标
        Toolkit tk=Toolkit.getDefaultToolkit();
      	Image image=tk.createImage(System.getProperty("user.dir")+"\\image\\icon.png"); 
      	setIconImage(image);
      	
        String[][] datas = {};
        String[] titles = { "序号", "姓名","成绩" };
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);

        addBtn = new JButton("添加数据");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                number = String.valueOf(Integer.valueOf(number)+1);
                model.addRow(new String[] { number, "", "" });
            }
        });
        //焦点获取
        addBtn.setFocusPainted(false);
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(1, 1));
        addPanel.add(addBtn);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1));
        decBtn = new JButton("删除数据");
        //焦点获取
        decBtn.setFocusPainted(false);
        decBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		if(table.getRowCount() == 0) {
            			JOptionPane.showMessageDialog(null, "请添加数据", "警告", JOptionPane.INFORMATION_MESSAGE);
            		}else {
                		model.removeRow(table.getSelectedRow());
            		}
            	}catch(ArrayIndexOutOfBoundsException e1) {
            		JOptionPane.showMessageDialog(null, "未选择所要删除的栏目", "警告", JOptionPane.INFORMATION_MESSAGE);
            	}
            }
        });
        
        solveBtn = new JButton("排序");
        //焦点获取
        solveBtn.setFocusPainted(false);
        
        btnPanel.add(decBtn);
        btnPanel.add(solveBtn);
        
        
        add(addPanel, BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);
        add(new JScrollPane(table));

        //setBounds(0, 100, 500, 400);
        setBounds(1920, 100, 500, 400);
        setVisible(true);
        	
        //table数据居中显示
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        //设置表头内容居中
      	((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.setFont(new Font("Serif",0,18));
        table.setRowHeight(30);
        main_solve();
        
        table.addMouseListener(new MouseAdapter(){
        	 public void mouseClicked(MouseEvent e) {
        	   table.clearSelection();
        	 }
        });
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Main.Frametype[0] = 0;
				setVisible(false);
			}
		});
    }

    private void main_solve() {
    	
    	solveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean infoCom = false;
				String[] name = new String[table.getRowCount()];
				float[] score = new float[table.getRowCount()];
				if(table.getRowCount() != 0) {
					for(int i=0;i<table.getRowCount();i++) {
						if(table.getModel().getValueAt(i,1).equals("")||table.getModel().getValueAt(i,2).toString().equals("")) {
							JOptionPane.showMessageDialog(null, "请完善信息", "警告", JOptionPane.INFORMATION_MESSAGE);
							break;
						}else {
							try {
								name[i] = (String) table.getModel().getValueAt(i,1);
					    		score[i] = Float.valueOf(table.getModel().getValueAt(i,2).toString());
					    		infoCom = true;
							}catch(NumberFormatException except) {
								JOptionPane.showMessageDialog(null, "行序号为"+table.getModel().getValueAt(i,0)+"的"+ table.getModel().getValueAt(i,1) +"的成绩格式错误", "错误", JOptionPane.ERROR_MESSAGE);
								infoCom = false;
								break;
							}
						}
			    	}
					if(infoCom) {
						for(int i=0;i<table.getRowCount();i++) {
				    		float scoreTemp;
				    		String nameTemp;
				    		for(int j=0;j<table.getRowCount();j++) {
					    		if(score[j]<score[i]) {
					    			scoreTemp = score[i];
					    			score[i] = score[j];
					    			score[j] = scoreTemp;
					    			nameTemp = name[i];
					    			name[i] = name[j];
					    			name[j] = nameTemp;
					    		}
					    	}
				    	}
						for(int i=0;i<table.getRowCount();i++) {
							table.setValueAt(i+1, i, 0);
							table.setValueAt(name[i], i, 1);
							table.setValueAt(score[i], i, 2);
				    	}
					}
				}else {
					JOptionPane.showMessageDialog(null, "请添加数据", "警告", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}
