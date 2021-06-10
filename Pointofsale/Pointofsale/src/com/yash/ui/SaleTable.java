
package com.yash.ui;

import javax.swing.*;

import com.yash.daos.*;
import com.yash.pojos.*;

import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class SaleTable extends AbstractTableModel {
	ArrayList<OrderDetail> listOd;
	String[] column = { "Product Id", "Product ", "Quantity", " Price", "Cgst", "Sgst", "Total" };

	public SaleTable(ArrayList<OrderDetail> od) {
		ProductDao productDao = new ProductDao();
		listOd = od;
	}

	public String getColumnName(int col) {
		return column[col];
	}

	public Class getColumnClass(int c) {
		return column[c].getClass();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}

	@Override
	public int getRowCount() {
		return listOd.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		OrderDetail p = listOd.get(arg0);
		if (arg1 == 0)
			return p.getProId();
		else if (arg1 == 1)
			return p.getProName();
		else if (arg1 == 2)
			return p.getProdQuantity();
		else if (arg1 == 3)
			return p.getProdPrice();
		else if (arg1 == 4)
			return p.getCgst();
		else if (arg1 == 5)
			return p.getSgst();
		else if (arg1 == 6)
			return p.getProdPrice() * p.getProdQuantity();

		return null;
	}

	

}
