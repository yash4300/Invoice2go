package com.yash.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import com.yash.daos.*;
import com.yash.pojos.*;

public class CategoryForm extends JPanel implements ActionListener {

	JLabel lblCategoryName, lblCategoryDetails, statusLabel;
	JTextField tfCategoryName, tfCategoryDetail;
	JToolBar jToolBar;
	Categories category;
	CategoriesDao categoryDao;
	int currentRow, totalRow;
	ArrayList<Integer> ids;

	public CategoryForm() {
		setLayout(new BorderLayout());
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	
		lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setBounds(30, 50, 100, 25);
		tfCategoryName = new JTextField();
		tfCategoryName.setBounds(170, 50, 200, 25);

		lblCategoryDetails = new JLabel("Category Id");
		lblCategoryDetails.setBounds(30, 100, 100, 25);
		tfCategoryDetail = new JTextField();
		tfCategoryDetail.setBounds(170, 100, 200, 25);
	
		centerPanel.add(lblCategoryName);
		centerPanel.add(tfCategoryName);
		centerPanel.add(lblCategoryDetails);
		centerPanel.add(tfCategoryDetail);
		add(centerPanel, BorderLayout.CENTER);
		
		createToolBar();

		statusLabel = new JLabel(" Row 1 of 1");
		statusLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(statusLabel, BorderLayout.SOUTH);

		category = new Categories();
		categoryDao = new CategoriesDao();
		ids = categoryDao.findAllByIds();
		if (ids.size() > 0) {
			currentRow = 1;
			totalRow = ids.size();
			category = categoryDao.find(ids.get(currentRow - 1));
		}
		updateCategory();
	}

	public void fetchCategory() {
		category.setCategoryName(tfCategoryName.getText());
		category.setCategoryDetails(tfCategoryDetail.getText());

	}

	private void updateCategory() {
		tfCategoryName.setText(category.getCategoryName());
		tfCategoryDetail.setText(category.getCategoryDetails());
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

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("First")) {
			if (currentRow != totalRow) {
				currentRow = currentRow + 1;
				category = categoryDao.find(ids.get(currentRow - 1));
				updateCategory();
			}
		} else if (str.equals("Previous")) {
			if (currentRow != 1) {
				currentRow = currentRow - 1;
				category = categoryDao.find(ids.get(currentRow - 1));
				updateCategory();
			}
		} else if (str.equals("update")) {
			fetchCategory();
			categoryDao.editCategory(category);

		} else if (str.equals("create")) {
			category = new Categories();
			categoryDao.createCategory(category);
			ids = categoryDao.findAllByIds();
			totalRow = ids.size();
			category = categoryDao.find(ids.get(totalRow - 1));
			updateCategory();

		} else if (str.equals("delete")) {
			if (currentRow != 0) {
				int x = JOptionPane.showConfirmDialog(this, "Are you Sure, you want to delete item !", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_OPTION) {
					boolean answer = categoryDao.removeCat(category.getCategoryId());
					if (!answer) {
						JOptionPane.showMessageDialog(this, "Unable to Delete Category !", "Delete",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					ids = categoryDao.findAllByIds();
					totalRow = ids.size();
					currentRow = currentRow - 1;
					if (currentRow == 0 && totalRow >= 1) {
						currentRow = totalRow;
						category = categoryDao.find(ids.get(currentRow - 1));
						updateCategory();
					} else if (currentRow != 0) {
						category = categoryDao.find(ids.get(currentRow - 1));
						updateCategory();
					} else {
						tfCategoryName.setText("");
						tfCategoryDetail.setText("");

					}
				}
			}
		} 
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("Category");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new CategoryForm());
		f.setMinimumSize(new Dimension(400, 300));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
	}
	}