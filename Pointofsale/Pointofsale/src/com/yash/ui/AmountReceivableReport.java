package com.yash.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

public class AmountReceivableReport extends JPanel implements ActionListener{
	JLabel lblDateFrom, lblDateTo;	
	JTextField txtDateFrom, txtDateTo;	
	JPanel northPanel;
	JButton btn1;
	JTable table;
	JPanel centerPanel; 
	public AmountReceivableReport() {
		
		northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(northPanel, BorderLayout.NORTH);
		
		setLayout(new BorderLayout());
		northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(northPanel, BorderLayout.NORTH);
		lblDateFrom= new JLabel("From Date");
		txtDateFrom = new JTextField(10);
		try {
			MaskFormatter format = new MaskFormatter("****-**-**");
			format.setPlaceholderCharacter('_');
			txtDateFrom = new JFormattedTextField(format);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		northPanel.add(lblDateFrom);
		northPanel.add(txtDateFrom);

		lblDateTo = new JLabel("To Date");
		txtDateTo = new JTextField(10);
		try {
			MaskFormatter format = new MaskFormatter("****-**-**");
			format.setPlaceholderCharacter('_');
			txtDateTo = new JFormattedTextField(format);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		northPanel.add(lblDateTo);
		northPanel.add(txtDateTo);

		btn1 = new JButton("GO");
		btn1.setActionCommand("go");
		btn1.addActionListener(this);
		northPanel.add(btn1);
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		AmountModel pt = new AmountModel();
		table = new JTable();
		table.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		JScrollPane js = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		centerPanel.add(js, BorderLayout.CENTER);
		js.setBounds(50, 430, 500, 100);


}
	public static void main(String[] args) {
		JFrame f = new JFrame("Report");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new AmountReceivableReport());
		f.setMinimumSize(new Dimension(800, 800));
		f.pack();
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
		
	}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

}
