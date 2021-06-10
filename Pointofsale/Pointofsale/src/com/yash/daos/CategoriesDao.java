package com.yash.daos;

import java.sql.*;
import java.util.*;

import com.yash.pojos.Categories;
import com.yash.pojos.Customer;
import com.yash.pojos.Product;
import com.yash.utilities.*;

public class CategoriesDao {

	public boolean createCategory(Categories cat) {
		try {
			Connection con = MyDatabaseConnection.getConnection();
			String sql = "insert into categories(cat_name, " + "cat_detail) values (?,? )";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cat.getCategoryName());
			ps.setString(2, cat.getCategoryDetails());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("unable to create row " + e);
			return false;

		}
		return true;

	}

	public boolean editCategory(Categories cat) {
		try {
			Connection con = MyDatabaseConnection.getConnection();
			String sql = "Update categories set cat_name = ?, " + " cat_detail =? where cat_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cat.getCategoryName());
			ps.setString(2, cat.getCategoryDetails());
			ps.setInt(3, cat.getCategoryId());
			ps.executeUpdate();
		} catch (SQLException s) {
			System.out.print("Unable to edit row");
			return false;
		}
		return true;
	}

	public boolean removeCat(int categoryId) {
		Connection conn = MyDatabaseConnection.getConnection();
		try {
			String sql = "delete from categories where cat_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to remove row." + sq);
			return false;
		}
		return true;
	}

	public Categories find(int categoryId) {
		Connection conn = MyDatabaseConnection.getConnection();
		Categories category = new Categories();
		try {
			String sql = "select * from categories where cat_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category.setCategoryId(categoryId);
				category.setCategoryName(rs.getString("cat_name"));
				category.setCategoryDetails(rs.getString("cat_detail"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch row." + sq);
		}
		return category;
	}

	public ArrayList<String> findallCategories() {
		Connection con = MyDatabaseConnection.getConnection();
		ArrayList<String> listCat = new ArrayList<String>();
		try {
			String sql = "Select * from Categories";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listCat.add(rs.getString("cat_name"));
			}
		} catch (SQLException sq) {
			System.out.print("unable to find rows" + sq);
		}
		return listCat;
	}

	public ArrayList<Integer> findAllByIds() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Integer> listall = new ArrayList<Integer>();
		try {
			String sql = "select * from categories ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int x = 0;
				x = rs.getInt("cat_id");
				listall.add(x);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return listall;
	}
	public int findIdByName(String cat_name) {
		Connection conn = MyDatabaseConnection.getConnection();
		int x=0;
		try {
			String sql = "select cat_id from categories where cat_name=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cat_name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
		
				x = rs.getInt("cat_id");
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return x;
	}
	public ArrayList<String> findAllByCategoryName(String categoryName) {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<String> Listcategory = new ArrayList<String>();
		try {
			String sql = "select * from categories where cat_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = rs.getString("cat_name");
				Listcategory.add(str);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return Listcategory;
	}

	public ArrayList<Categories> findAll() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Categories> Listcategory = new ArrayList<Categories>();
		try {
			String sql = "select * from categories";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categories c = new Categories();
				c.setCategoryId(rs.getInt("cat_id"));
				c.setCategoryName(rs.getString("cat_name"));
				Listcategory.add(c);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return Listcategory;
	}

	public static void main(String args[]) {

		/*
		 * Categories cat = new Categories("Personal care", "Deo&perfume ,skin care  ");
		 * CategoriesDao catDao = new CategoriesDao(); catDao.createCategory(cat);
		 */
		/*
		 * Categories category = new Categories(,"",""); CategoriesDao catDao = new
		 * CategoriesDao(); catDao.editCategory(category);
		 */

		// CategoriesDao catDao = new CategoriesDao();
		// catDao.removeCat(6);

		/*
		 * CategoriesDao catDao= new CategoriesDao();
		 * System.out.println(catDao.find(5));
		 */

		CategoriesDao catDao = new CategoriesDao();
		ArrayList<String> all = catDao.findallCategories();
		for (String cat : all) {
			System.out.println(cat);

			// CategoriesDao catDao = new CategoriesDao();
			// ArrayList<Integer> arr = catDao.findAllByIds();
			// for(Integer cat : arr) {
			// System.out.println(cat);
			// }

			/*
			 * CategoriesDao catDao = new CategoriesDao(); ArrayList<Categories> all =
			 * catDao.findAllByCategoryName("Books"); for(Categories cat : all) {
			 * System.out.println(cat) ; }
			 */
		}

	}
}
