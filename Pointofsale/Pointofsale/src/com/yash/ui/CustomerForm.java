package com.yash.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import com.yash.daos.*;
import com.yash.pojos.*;

public class CustomerForm extends JPanel implements ActionListener {

	JLabel lblCustomerId, lblCustomerName, lblCustomerAdd, lblCustomerMobile, lblCity, lblReg, statusLabel;
	JTextField tfId, tfName, tfAddress, tfMobile, tfCity;
	JFormattedTextField tfDate;
	JToolBar jToolBar;
	Customer customer;
	CustomerDao custDao;
	int currentRow, totalRow;
	ArrayList<Integer> ids;

	public CustomerForm() {
		setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(30, 50, 100, 25);
		tfName = new JTextField();
		tfName.setBounds(170, 50, 200, 25);
		
		lblCustomerMobile = new JLabel("Customer Mobile");
		lblCustomerMobile.setBounds(30, 100, 100, 25);
		tfMobile = new JTextField();
		tfMobile.setBounds(170, 100, 200, 25);
		
		lblCustomerAdd = new JLabel("Customer Address");
		lblCustomerAdd.setBounds(30, 150, 100, 25);
		tfAddress = new JTextField();
		tfAddress.setBounds(170, 150, 200, 25);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(30, 200, 100, 25);
		tfCity = new JTextField();
		tfCity.setBounds(170, 200, 200, 25);
		
		lblReg = new JLabel("Registration Date");
		lblReg.setBounds(30, 250, 100, 25);
		tfDate.setBounds(170, 250, 200, 25);
		

		centerPanel.add(lblCustomerName);
		centerPanel.add(tfName);
		centerPanel.add(lblCustomerMobile);
		centerPanel.add(tfMobile);
		centerPanel.add(lblCustomerAdd);
		centerPanel.add(tfAddress);
		centerPanel.add(lblCity);
		centerPanel.add(tfCity);
		centerPanel.add(lblReg);
		centerPanel.add(tfDate);
		add(centerPanel, BorderLayout.CENTER);
		createToolBar();

		statusLabel = new JLabel();
		add(statusLabel, BorderLayout.SOUTH);
		customer = new Customer();
		custDao = new CustomerDao();
		ids = custDao.findAllByIds();
		if (ids.size() > 0) {
			currentRow = 1;
			totalRow = ids.size();
			customer = custDao.find(ids.get(currentRow - 1));
		}
		updateCustomer();

	}

	public void fetchCustomer() {
		customer.setCustomerName(tfName.getText());
		customer.setCustomerMobile(tfMobile.getText());
		customer.setCustomerAddress(tfAddress.getText());
		customer.setCity(tfCity.getText());
	}

	private void updateCustomer() {
		tfName.setText(customer.getCustomerName());
		tfMobile.setText(customer.getCustomerMobile());
		tfAddress.setText(customer.getCustomerAddress());
		tfCity.setText(customer.getCity());
		statusLabel.setText("Row " + currentRow + " of " + totalRow);
	}

	public void createToolBar() {
		JToolBar jToolBar = new JToolBar();

		JButton btn2 = new JButton(new ImageIcon(getClass().getResource("/icon/Back.png")));
		btn2.setActionCommand("Previous");
		btn2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btn2.setToolTipText("Next Row");
		btn2.addActionListener(this);
		jToolBar.add(btn2);

		JButton btn1 = new JButton(new ImageIcon(getClass().getResource("/icon/next.png")));
		btn1.setActionCommand("First");
		btn1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btn1.setToolTipText("First Row");
		btn1.addActionListener(this);
		jToolBar.add(btn1);

		jToolBar.addSeparator();

		JButton btn3 = new JButton(new ImageIcon(getClass().getResource("/icon/create.png")));
		btn3.setActionCommand("create");
		btn3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btn3.setToolTipText("create");
		btn3.addActionListener(this);
		jToolBar.add(btn3);

		jToolBar.addSeparator();

		JButton btn5 = new JButton(new ImageIcon(getClass().getResource("/icon/update.png")));
		btn5.setActionCommand("update");
		btn5.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btn5.setToolTipText("update");
		btn5.addActionListener(this);
		jToolBar.add(btn5);

		jToolBar.addSeparator();

		JButton btn6 = new JButton(new ImageIcon(getClass().getResource("/icon/delete.png")));
		btn6.setActionCommand("delete");
		btn6.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btn6.setToolTipText("Delete Row");
		btn6.addActionListener(this);
		jToolBar.add(btn6);

		add(jToolBar, BorderLayout.NORTH);
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("First")) {
			if (currentRow != totalRow) {
				currentRow = currentRow + 1;
				customer = custDao.find(ids.get(currentRow - 1));
				updateCustomer();
			}
		} else if (str.equals("Previous")) {
			if (currentRow != 1) {
				currentRow = currentRow - 1;
				customer = custDao.find(ids.get(currentRow - 1));
				updateCustomer();
			}
		} else if (str.equals("update")) {
			fetchCustomer();
			custDao.editCustomer(customer);
			JOptionPane.showMessageDialog(this, "Updated successfully" );

		} else if (str.equals("create")) {
			customer = new Customer();
			custDao.createCustomer(customer);
			ids = custDao.findAllByIds();
			totalRow = ids.size();
			customer = custDao.find(ids.get(totalRow - 1));
			updateCustomer();

		} else if (str.equals("delete")) {
			if (currentRow != 0) {
				int x = JOptionPane.showConfirmDialog(this, "Are you Sure, you want to delete it !", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_OPTION) {
					boolean answer = custDao.removeCustomer(customer.getCustomerId());
					if (!answer) {
						JOptionPane.showMessageDialog(this, "Unable to Delete Course !", "Delete",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					ids = custDao.findAllByIds();
					totalRow = ids.size();
					currentRow = currentRow - 1;
					if (currentRow == 0 && totalRow >= 1) {
						currentRow = totalRow;
						customer = custDao.find(ids.get(currentRow - 1));
						updateCustomer();
					} else if (currentRow != 0) {
						customer = custDao.find(ids.get(currentRow - 1));
						updateCustomer();
					} else {
						tfName.setText("");
						tfMobile.setText("");
						tfAddress.setText("");
						tfCity.setText("");
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("Customer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new CustomerForm());
		f.setMinimumSize(new Dimension(500, 500));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
	}

}
