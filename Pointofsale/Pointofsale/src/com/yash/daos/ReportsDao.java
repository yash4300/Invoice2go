package com.yash.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.yash.pojos.Categories;
import com.yash.pojos.Product;
import com.yash.utilities.MyDatabaseConnection;

public class ReportsDao {

	public ArrayList<ArrayList<String>> getCategoryWiseSales(int catId, String fromDate, String toDate) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		CategoriesDao catDao = new CategoriesDao();
		ArrayList<Categories> catList = null;
		if (catId == 0) {
			catList = catDao.findAll();
		} else {
			Categories cat = catDao.find(catId);
			catList = new ArrayList<Categories>();
			catList.add(cat);
		}
		for (Categories c : catList) {
			ArrayList<String> alString = new ArrayList<String>();
			alString.add(c.getCategoryName());
			Connection con = MyDatabaseConnection.getConnection();
			String sql = "SELECT sum(o.prod_price * o.prod_qty) from oder_details o, product p, categories c, oders od"
					+ " where c.cat_id = ? and p.cat_id = c.cat_id and o.oder_id = od.oder_id and od.oder_datetime >= ? and od.oder_datetime <= ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, c.getCategoryId());
				ps.setString(2, fromDate);
				ps.setString(3, toDate);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					float amount = rs.getFloat(1);
					alString.add("" + amount);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			alist.add(alString);
		}
		// System.out.print(alist);
		return alist;
	}

	public ArrayList<Product> getStockOrderReport() {
	
			Connection conn = MyDatabaseConnection.getConnection();
			ArrayList<Product> listproducts = new ArrayList<Product>();	
			try {
				String sql = "select prod_name, cat_id, prod_qih from product where prod_qih < prod_reoder_level";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Product pro = new Product();
					pro.setCategoryId(rs.getInt("cat_id"));
					pro.setProductName(rs.getString("prod_name"));
					pro.setQih(rs.getInt("prod_qih"));
					listproducts.add(pro);
				}
			} catch (SQLException sq) {
				System.out.println("Unable to fetch rows.");
			}
			return listproducts;
		}


	public static void main(String[] args) {
		ReportsDao reportsDao = new ReportsDao();
		/*
		 * ArrayList<ArrayList<String>> alist = reportsDao.getCategoryWiseSales(0,
		 * "2019-1-1", "2020-12-1"); for (ArrayList al : alist) {
		 * System.out.println(al); }
		 */
		ArrayList<Product> list = reportsDao.getStockOrderReport();
		for (Product al : list) {
			System.out.println(al);
		}
	}
}
