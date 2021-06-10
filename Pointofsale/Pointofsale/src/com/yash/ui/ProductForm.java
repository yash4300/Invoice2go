package com.yash.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import com.yash.daos.*;
import com.yash.pojos.*;

public class ProductForm extends JPanel implements ActionListener {

	JLabel lblProductName, lblProductCode, lblcategoryId, lblqih, lblCatname, lblproductReorderlevel, lblProductPrice,
			statusLabel, lblProductCgst, lblProductSgst, lblCat;
	JTextField tfProductName, tfProductCode, tfcatId, tfCname, tfQih, tfReoder;
	JFormattedTextField tfPrice, tfCgst, tfSgst;
	JToolBar jToolBar;
	JComboBox<String> bcategoryName;
	ArrayList<Integer> ids;
	JPanel centerPanel, northPanel;
	ProductDao prodDao;
	Product product;
	int currentRow, totalRow;
	JComboBox<String> cmbCategoryName;
	ArrayList<Integer> catId;
	ArrayList<String> category;
	JTable table;

	public ProductForm() {
		setLayout(new BorderLayout());

		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(centerPanel, BorderLayout.CENTER);

		lblProductName = new JLabel("Product Name");
		lblProductCode = new JLabel("Product Code");
		lblProductPrice = new JLabel("Product Price");
		lblProductCgst = new JLabel("Product Cgst");
		lblProductSgst = new JLabel("Product Sgst");
		lblcategoryId = new JLabel("Category Id");
		lblproductReorderlevel = new JLabel("Reoder Level");
		lblqih = new JLabel("Quantity in hand");
		lblCatname = new JLabel("Category Name");

		tfProductName = new JTextField();
		tfProductCode = new JTextField();
		tfPrice = new JFormattedTextField();
		tfCgst = new JFormattedTextField();
		tfSgst = new JFormattedTextField();
		tfcatId = new JTextField();
		tfReoder = new JTextField();
		tfQih = new JTextField();
		tfCname = new JTextField();

		lblProductName.setBounds(30, 20, 100, 25);
		tfProductName.setBounds(170, 20, 200, 25);
		lblProductCode.setBounds(30, 60, 100, 25);
		tfProductCode.setBounds(170, 60, 200, 25);
		lblcategoryId.setBounds(30, 100, 100, 25);
		tfcatId.setBounds(170, 100, 200, 25);
		lblCatname.setBounds(30, 140, 100, 25);
		tfCname.setBounds(170, 140, 200, 25);
		lblProductPrice.setBounds(30, 180, 100, 25);
		tfPrice.setBounds(170, 180, 200, 25);
		lblproductReorderlevel.setBounds(30, 220, 100, 25);
		tfReoder.setBounds(170, 220, 200, 25);
		lblqih.setBounds(30, 260, 100, 25);
		tfQih.setBounds(170, 260, 200, 25);
		lblProductCgst.setBounds(30, 300, 100, 25);
		tfCgst.setBounds(170, 300, 200, 25);
		lblProductSgst.setBounds(30, 340, 100, 25);
		tfSgst.setBounds(170, 340, 200, 25);

		centerPanel.add(lblProductName);
		centerPanel.add(tfProductName);
		centerPanel.add(lblProductCode);
		centerPanel.add(tfProductCode);
		centerPanel.add(lblProductPrice);
		centerPanel.add(tfPrice);
		centerPanel.add(lblProductCgst);
		centerPanel.add(tfCgst);
		centerPanel.add(lblProductSgst);
		centerPanel.add(tfSgst);
		centerPanel.add(lblqih);
		centerPanel.add(tfQih);
		centerPanel.add(lblproductReorderlevel);
		centerPanel.add(tfReoder);
		centerPanel.add(lblcategoryId);
		centerPanel.add(lblCatname);
		centerPanel.add(tfCname);
		centerPanel.add(tfcatId);

		createToolBar();

		statusLabel = new JLabel("Row 1 of 1");
		statusLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(statusLabel, BorderLayout.SOUTH);

		product = new Product();
		prodDao = new ProductDao();
		ids = prodDao.findAllProductByIds();
		if (ids.size() > 0) {
			currentRow = 1;
			totalRow = ids.size();
			product = prodDao.find(ids.get(currentRow - 1));

		}
		updateProduct();

		CategoriesDao cDao = new CategoriesDao();
		catId = cDao.findAllByIds();
		category = cDao.findallCategories();

	
		JLabel cName =new JLabel("category");
		cName.setBounds(30, 380, 100, 30);
		centerPanel.add(cName);
		String s[] = new String[0];
		s = category.toArray(s);
		cmbCategoryName = new JComboBox<String>(s);
		cmbCategoryName.addActionListener(this);
		centerPanel.add(cmbCategoryName);
		cmbCategoryName.setBounds(100, 380, 100, 30);
	
		
		ProductTableModel pt = new ProductTableModel(catId.get(0));
		table = new JTable(pt);
		table.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		JScrollPane js = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		centerPanel.add(js, BorderLayout.CENTER);
		js.setBounds(50, 430, 500, 100);

	}

	public void fetchProduct() {

		product.setProductName(tfProductName.getText());
		product.setProductCode(Integer.parseInt(tfProductCode.getText()));
		product.setProductPrice(Float.parseFloat(tfPrice.getText()));
		product.setProductCgst(Float.parseFloat(tfCgst.getText()));
		product.setProductSgst(Float.parseFloat(tfSgst.getText()));
		product.setCategoryId(Integer.parseInt(tfcatId.getText()));
		product.setProductReoderLevel(Integer.parseInt(tfReoder.getText()));
		product.setQih(Integer.parseInt(tfQih.getText()));
		product.setCategoryName(tfCname.getText());
	}

	private void updateProduct() {
		tfProductName.setText(product.getProductName());
		tfProductCode.setText((product.getProductCode() + ""));
		tfPrice.setText((product.getProductPrice() + ""));
		tfCgst.setText(product.getProductCgst() + "");
		tfSgst.setText(product.getProductSgst() + "");
		tfcatId.setText(product.getCategoryId() + "");
		tfCname.setText(product.getCategoryName());
		tfQih.setText(product.getQih() + "");
		tfReoder.setText(product.getProductReoderLevel() + "");
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
				product = prodDao.find(ids.get(currentRow - 1));
				updateProduct();
			}
		} else if (str.equals("Previous")) {
			if (currentRow != 1) {
				currentRow = currentRow - 1;
				product = prodDao.find(ids.get(currentRow - 1));
				updateProduct();
			}
		} else if (str.equals("update")) {
			fetchProduct();
			prodDao.editProduct(product);
			JOptionPane.showMessageDialog(this, "Updated successfully");

		} else if (str.equals("create")) {
			product = new Product();
			prodDao.createProduct(product);
			ids = prodDao.findAllProductByIds();
			totalRow = ids.size();
			product = prodDao.find(ids.get(totalRow - 1));
			updateProduct();

		} else if (str.equals("delete")) {
			product = new Product();
			prodDao = new ProductDao();
			if (currentRow != 0) {
				int x1 = JOptionPane.showConfirmDialog(this, "Are you Sure, you want to delete it !", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (x1 == JOptionPane.YES_OPTION) {
					boolean answer = prodDao.removeProduct(product.getProductId());
					if (!answer) {
						JOptionPane.showMessageDialog(this, "Unable to Delete product !", "Delete",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					ids = prodDao.findAllProductByIds();
					totalRow = ids.size();
					currentRow = currentRow - 1;
					if (currentRow == 0 && totalRow >= 1) {
						currentRow = totalRow;
						product = prodDao.find(ids.get(currentRow - 1));
						updateProduct();
					} else if (currentRow != 0) {
						product = prodDao.find(ids.get(currentRow - 1));
						updateProduct();
					} else {
						tfProductName.setText("");
						tfProductCode.setText("");
						tfPrice.setText("");
						tfCgst.setText("");
						tfSgst.setText("");
					}
				}
			}
		}

		int x = cmbCategoryName.getSelectedIndex();
		int id = catId.get(x);
		ProductTableModel st = new ProductTableModel(id);
		table.setModel(st);
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("Product");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new ProductForm());
		f.setMinimumSize(new Dimension(600, 900));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
	}

}
