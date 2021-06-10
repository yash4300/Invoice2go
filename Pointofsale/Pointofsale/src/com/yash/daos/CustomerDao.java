
package com.yash.daos;

import java.util.*;

import com.yash.pojos.Customer;
import com.yash.utilities.*;

import java.sql.*;

public class CustomerDao {
	public boolean createCustomer(Customer cus) {
		try {
			Connection con = MyDatabaseConnection.getConnection();
			String sql = "insert into customer(cust_name, " + "cust_mobile, " + "cust_address, " + "city, "
					+ " reg_date) values(?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cus.getCustomerName());
			ps.setString(2, cus.getCustomerMobile());
			ps.setString(3, cus.getCustomerAddress());
			ps.setString(4, cus.getCity());
			java.sql.Date dt = new java.sql.Date(cus.getRegistrationDate().getTime());
			ps.setDate(5, dt);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("unable to create row " + e);
			return false;

		}
		return true;

	}

	public boolean editCustomer(Customer cus) {
		Connection conn = MyDatabaseConnection.getConnection();
		try {
			String sql = "update customer set cust_name = ?, " + "cust_mobile = ?, " + "cust_address = ?, "
					+ "city = ?, " + " reg_date = ? where cust_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cus.getCustomerName());
			ps.setString(2, cus.getCustomerMobile());
			ps.setString(3, cus.getCustomerAddress());
			ps.setString(4, cus.getCity());
			java.sql.Date dt = new java.sql.Date(cus.getRegistrationDate().getTime());
			ps.setDate(5, dt);
			ps.setInt(6, cus.getCustomerId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit row." + sq);
			return false;
		}
		return true;
	}

	public boolean removeCustomer(int customerId) {
		Connection conn = MyDatabaseConnection.getConnection();
		try {
			String sql = "delete from customer where cust_Id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete row." + sq);
			return false;
		}
		return true;
	}

	public static Customer find(int customerId) {
		Connection conn = MyDatabaseConnection.getConnection();
		Customer cus = new Customer();
		try {
			String sql = "select * from customer where cust_Id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cus.setCustomerId(customerId);
				cus.setCustomerName(rs.getString("cust_name"));
				cus.setCustomerMobile(rs.getString("cust_mobile"));
				cus.setCustomerAddress(rs.getString("cust_address"));
				cus.setCity(rs.getString("city"));
				java.sql.Date dt = rs.getDate("reg_date");
				cus.setRegistrationDate((new java.util.Date(dt.getTime())));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch row." + sq);
		}
		return cus;
	}

	public ArrayList<Customer> findAll() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Customer> Listcustomer = new ArrayList<Customer>();
		try {
			String sql = "select * from customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer cust = new Customer();
				cust.setCustomerId((rs.getInt("cust_id")));
				cust.setCustomerName((rs.getString("cust_name")));
				cust.setCustomerMobile(rs.getString("cust_mobile"));
				cust.setCustomerAddress(rs.getString("cust_address"));
				cust.setCity(rs.getString("city"));
				Listcustomer.add(cust);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return Listcustomer;
	}

	public ArrayList<Integer> findAllByIds() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Integer> listall = new ArrayList<Integer>();
		try {
			String sql = "select * from customer ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int x = 0;
				x = rs.getInt("cust_id");
				listall.add(x);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return listall;
	}

	public ArrayList<Customer> searchCustomer(String customerName) {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Customer> listCustomer = new ArrayList<Customer>();
		try {
			String sql = "select * from customer where cust_name = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customerName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer cust = new Customer();
				cust.setCustomerId(rs.getInt("cust_id"));
				cust.setCustomerName(rs.getString("cust_name"));
				cust.setCustomerName(rs.getString("cust_name"));
				cust.setCustomerMobile(rs.getString("cust_mobile"));
				cust.setCustomerAddress(rs.getString("cust_address"));
				cust.setCity(rs.getString("city"));
				java.sql.Date dt = rs.getDate("reg_date");
				cust.setRegistrationDate(new java.util.Date(dt.getTime()));
				listCustomer.add(cust);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return listCustomer;
	}

	public Customer findMobile(String customerMobile) {
		Connection conn = MyDatabaseConnection.getConnection();
		Customer cust = new Customer();

		try {

			String sql = "select * from customer where cust_mobile = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customerMobile);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cust.setCustomerId(rs.getInt("cust_id"));
				cust.setCustomerName(rs.getString("cust_name"));
				cust.setCustomerMobile(rs.getString("cust_mobile"));
				cust.setCustomerAddress(rs.getString("cust_address"));
				cust.setCity(rs.getString("city"));

			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return cust;
	}

	public static void main(String args[]) {
		java.util.Date dt = DateUtils.convertDate("22-10-2018");
		/*
		 * Customer cust = new Customer("Akansha Jain","9083248844",
		 * "E-3 arera colony","bhopal", dt); CustomerDao customerDao = new
		 * CustomerDao(); customerDao.createCustomer(cust);
		 */
		/*
		 * Customer cus = new Customer(1,"Abhay",
		 * "89898989899","E-8 arera colony","bhopal", dt); CustomerDao customerDao = new
		 * CustomerDao(); customerDao.editCustomer(cus);
		 */
		// CustomerDao customerDao = new CustomerDao();
		// customerDao.removeCustomer(2);

		// CustomerDao customerDao = new CustomerDao();
		// System.out.println(CustomerDao.find(1));

		/*
		 * CustomerDao custDao = new CustomerDao(); ArrayList<Customer> find =
		 * custDao.findAll(); for (Customer customer : find)
		 * System.out.println(customer);
		 */

		/*
		 * CustomerDao customerDao = new CustomerDao(); ArrayList<Customer> al =
		 * customerDao.searchCustomer("Abhinav rathod"); for (Customer cust : al) {
		 * System.out.println(cust); }
		 */
		CustomerDao customerDao = new CustomerDao();
		Customer cust = customerDao.findMobile("8871276187");
		System.out.println(cust);

	}
}
