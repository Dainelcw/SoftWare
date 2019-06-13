package software;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class A1_Frame extends JFrame{
	// Ĭ�ϱ��ģ��
    private DefaultTableModel model = null;
    private JTable table = null;

    private JButton addBtn = null, decBtn = null, solveBtn = null;

    String number = "0";

    public A1_Frame()
    {
        super("ѧ���ɼ�����");
        String[][] datas = {};
        String[] titles = { "���", "����","�ɼ�" };
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);

        addBtn = new JButton("�������");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                number = String.valueOf(Integer.valueOf(number)+1);
                model.addRow(new String[] { number, "", "" });
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1));
        decBtn = new JButton("ɾ������");
        decBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		if(table.getRowCount() == 0) {
            			JOptionPane.showMessageDialog(null, "���������", "����", JOptionPane.INFORMATION_MESSAGE);
            		}else {
                		model.removeRow(table.getSelectedRow());
            		}
            	}catch(ArrayIndexOutOfBoundsException e1) {
            		JOptionPane.showMessageDialog(null, "δѡ����Ҫɾ������Ŀ", "����", JOptionPane.INFORMATION_MESSAGE);
            	}
            }
        });
        
        solveBtn = new JButton("����");
        
        btnPanel.add(decBtn);
        btnPanel.add(solveBtn);
        
        add(addBtn, BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);
        add(new JScrollPane(table));

        setBounds(300, 0, 400, 300);
        setVisible(true);
        	
        //table������ʾ
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tcr);
        table.setFont(new Font("Serif",0,18));
        table.setRowHeight(30);
        main_solve();
        
        table.addMouseListener(new MouseAdapter(){
        	 public void mouseClicked(MouseEvent e) {
        	   table.clearSelection();
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
							JOptionPane.showMessageDialog(null, "��������Ϣ", "����", JOptionPane.INFORMATION_MESSAGE);
							break;
						}else {
							try {
								name[i] = (String) table.getModel().getValueAt(i,1);
					    		score[i] = Float.valueOf(table.getModel().getValueAt(i,2).toString());
					    		infoCom = true;
							}catch(NumberFormatException except) {
								JOptionPane.showMessageDialog(null, "�����Ϊ"+table.getModel().getValueAt(i,0)+"�ĳɼ���ʽ����", "����", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "���������", "����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}
