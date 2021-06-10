package com.yash.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import com.yash.daos.*;
import com.yash.pojos.*;

public class Table extends JPanel implements ActionListener {
	
	
	

	public Table() {
		setLayout(new BorderLayout());
	
		
	}
	

	private void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

public static void main(String args[]) {
	JFrame f = new JFrame("Product");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setContentPane(new Table());
	f.setMinimumSize(new Dimension(700, 700));
	f.setLocationRelativeTo(null);
	f.setResizable(false);
	f.setVisible(true);
}
}