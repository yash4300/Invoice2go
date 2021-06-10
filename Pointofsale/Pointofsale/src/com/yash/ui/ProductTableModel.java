package com.yash.ui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.yash.daos.*;
import com.yash.pojos.*;

import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;

@SuppressWarnings("serial")
public  class ProductTableModel extends AbstractTableModel   {
	ArrayList<Product> listProduct;
	String column[] = { "Product Id", "Product Name", "Product Code", "Product Price", "Product cgst", "Product sgst",
			"Quantity in hand", "Product Reoder" };

	
	
	public ProductTableModel(int categoryId) {
		ProductDao proDao = new ProductDao();
		listProduct = proDao.findByCategoryId(categoryId);
		// TODO Auto-generated constructor stub
	}


	

	public String getColumnName(int col) {
		return column[col];
	}

	public Class getColumnClass(int c) {
        return column[c].getClass();
    }
         
	public int getColumnCount() {
		return column.length;
	}

	public int getRowCount() {
		return listProduct.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Product p = listProduct.get(arg0);
		if (arg1 == 0)
			return p.getProductId();
		else if (arg1 == 1)
			return p.getProductName();
		else if (arg1 == 2)
			return p.getProductCode();
		else if (arg1 == 3)
			return p.getProductPrice();
		else if (arg1 == 4)
			return p.getProductCgst();
		else if (arg1 == 5)
			return p.getProductSgst();
		else if (arg1 == 6)
			return p.getQih();
		else if (arg1 == 7)
			return p.getProductReoderLevel();
		return null;
	}

}