package com.yash.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.yash.daos.*;
import com.yash.pojos.*;

public class StockOderReport extends JPanel implements ActionListener {
	JTable table;
	JPanel centerPanel;
	DefaultTableModel dtm;

	public StockOderReport() {
		setLayout(new BorderLayout());
		centerPanel = new JPanel();
		dtm = new DefaultTableModel();
		dtm.addColumn("Category Name"); 
		dtm.addColumn("Product Name");
		dtm.addColumn("Quantity");
	//	StockModel stock = new StockModel();
		table = new JTable(dtm);
		centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	ArrayList<Product> list = null;
		for (Product pro : list) {
			Object obj[] = new Object[list.size()];
			for (int i = 0; i < list.size(); i++)
				obj[i] = list.get(i);
			dtm.addRow(obj);
		}

	
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Report");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new StockOderReport());
		f.setMinimumSize(new Dimension(800, 800));
		f.pack();
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

	}
}
