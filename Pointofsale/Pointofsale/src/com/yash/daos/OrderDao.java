package com.yash.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import com.yash.pojos.*;
import com.yash.utilities.*;

public class OrderDao {
	public boolean createOrder(Order order, ArrayList<OrderDetail> orderDetails) {
		Connection con = MyDatabaseConnection.getConnection();
		float totalSgst = 0;
		float totalCgst = 0;
		float totalAmount = 0;

		for (OrderDetail od : orderDetails) {
			totalSgst += od.getSgst() * od.getProdQuantity();
			totalCgst += od.getCgst() * od.getProdQuantity();
			totalAmount += od.getProdPrice() * od.getProdQuantity();
		}
		try {
			String sql = "insert into oders (oder_datetime, cust_id, oder_amount, oder_cgst, oder_sgst) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			java.sql.Date dt = new java.sql.Date(new java.util.Date().getTime());
			ps.setDate(1, dt);
			ps.setInt(2, order.getCustId());
			ps.setFloat(3, totalAmount);
			ps.setFloat(4, totalCgst);
			ps.setFloat(5, totalSgst);
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			int id = 0;
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			for (OrderDetail ord : orderDetails) {
				sql = "insert into oder_details (oder_Id, prod_Id, prod_qty, prod_price, prod_cgst, prod_sgst) values(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setInt(2, ord.getProId());
				ps.setInt(3, ord.getProdQuantity());
				ps.setFloat(4, ord.getProdPrice());
				ps.setFloat(5, ord.getCgst());
				ps.setFloat(6, ord.getSgst());
				ps.executeUpdate();
			}

		} catch (SQLException sq) {
			System.out.print("unable to find rows" + sq);
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		OrderDao od = new OrderDao();
		Order ord = new Order();
		ord.setCustId(1);
		ProductDao pdao = new ProductDao();
 		ArrayList<OrderDetail> odList = new ArrayList<OrderDetail>();
		Product pd = pdao.find(5);
		OrderDetail d = new OrderDetail();
		d.setProId(pd.getProductId());
		d.setProdPrice(pd.getProductPrice());
		d.setProdQuantity(2);
		d.setCgst(pd.getProductCgst());
		d.setSgst(pd.getProductSgst());
		odList.add(d);
		odList.add(d);
		odList.add(d);
		odList.add(d);
		System.out.println(od.createOrder(ord, odList));
	}

}
