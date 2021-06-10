package com.yash.daos;

import java.util.*;

import com.yash.pojos.Product;
import com.yash.utilities.*;

import java.sql.*;


public class ProductDao {
	public boolean createProduct(Product pro) {
		try {
			Connection con = MyDatabaseConnection.getConnection();
			String sql = "insert into product(cat_id, "+"prod_code,"+"prod_name,"+"prod_price,"+"prod_qih,"+"prod_reoder_level,"+"prod_sgst,"+"prod_cgst) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,pro.getCategoryId() );
			ps.setInt(2, pro.getProductCode());
			ps.setString(3,pro.getProductName());
			ps.setFloat(4,pro.getProductPrice());
			ps.setInt(5,pro.getQih());
			ps.setInt(6,pro.getProductReoderLevel());
			ps.setFloat(7,pro.getProductCgst());
			ps.setFloat(8,pro.getProductSgst());
			
			ps.executeUpdate();
			}catch(SQLException e){
				System.out.print("unable to create row "+ e);
				return false;
			
			}
			return true;
	}
			public boolean editProduct(Product pro){
				try {
					Connection con = MyDatabaseConnection.getConnection();
					String sql = "Update product set cat_id=?, "+" prod_code=?, "+" prod_name=?, "+" prod_price=?, "+" prod_qih=?, "+" prod_reoder_level=?, "+" prod_sgst=?, "+" prod_cgst=? where prod_id= ? ";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1,pro.getCategoryId() );
					ps.setInt(2, pro.getProductCode());
					ps.setString(3,pro.getProductName());
					ps.setFloat(4,pro.getProductPrice());
					ps.setInt(5,pro.getQih());
					ps.setInt(6,pro.getProductReoderLevel());
					ps.setFloat(7,pro.getProductCgst());
					ps.setFloat(8,pro.getProductSgst());
					ps.setInt(9,pro.getProductId());
					ps.executeUpdate();
					
				}catch(SQLException e) {
					e.printStackTrace();
					return false;
					
				}
				return true;
			}
			
			public boolean removeProduct(int productId) {
				try {
				Connection con =MyDatabaseConnection.getConnection();
				String sql = "delete from Product where Prod_id=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, productId);
				ps.executeUpdate();
				} catch (SQLException e) {
						System.out.println("Unable to delete product item");
				}
				
				return false;
				
			}
			
			public Product  find(int productId) {
				Connection conn = MyDatabaseConnection.getConnection();
				Product pro = new Product();
				try {
					String sql = "select * from product where prod_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, productId);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						pro.setProductId(productId);
						pro.setCategoryId(rs.getInt("cat_id"));
						pro.setProductCode(rs.getInt("prod_code"));
						pro.setProductName(rs.getString("prod_name"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setQih(rs.getInt("prod_qih"));
						pro.setProductReoderLevel(rs.getInt("prod_reoder_level"));
						pro.setProductCgst(rs.getFloat("prod_cgst"));
						pro.setProductSgst(rs.getFloat("prod_sgst"));
					}
					} catch (SQLException sq) {
						System.out.println("Unable to fetch row." + sq);
					}
				return pro;
			}	
			public ArrayList<String> findallProduct(){
				Connection con = MyDatabaseConnection.getConnection();
				ArrayList<String> list = new ArrayList<String>();
				try {
					String sql = "Select * from Product";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()){
						list.add((rs.getString("prod_name")));
					}
;					
				}catch(SQLException sq) {
					System.out.print("unable to find rows"+sq);
				}
 				return list;
			}
			
			
			public ArrayList<Integer> findAllProductByIds() {
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<Integer> listall = new ArrayList<Integer>();
				try {
					String sql = "select * from product ";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()){
						int x = 0;
						x = rs.getInt("prod_id");
						listall.add(x);
						
				
					 }
					} catch (SQLException sq) {
					System.out.println("Unable to fetch rows." + sq);
				}
				return listall;
			}		
				
			public ArrayList<Product> searchProducts(String productName){
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<Product> Listproduct = new ArrayList<Product>();
				try {
					String sql = "select * from product where prod_name = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, productName);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Product pro = new Product();
						
						pro.setProductName(rs.getString("prod_name"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setProductId(rs.getInt("prod_Id"));
						pro.setProductCode(rs.getInt("prod_code"));
						Listproduct.add(pro);
					}
				} catch (SQLException sq) {
					System.out.println("Unable to fetch rows." + sq);
				}
				return Listproduct;
			}
			public ArrayList<Product> findByCategoryId(int categoryId){
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<Product> Listproduct = new ArrayList<Product>();
				try {
					String sql = "select * from product where cat_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, categoryId);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Product pro = new Product();
						pro.setProductName(rs.getString("prod_name"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setProductId(rs.getInt("prod_Id"));
						pro.setProductCode(rs.getInt("prod_code"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setQih(rs.getInt("prod_qih"));
						pro.setProductReoderLevel(rs.getInt("prod_reoder_level"));
						pro.setProductCgst(rs.getFloat("prod_cgst"));
						pro.setProductSgst(rs.getFloat("prod_sgst"));
						pro.setCategoryName(rs.getString("cat_name"));
						Listproduct.add(pro);
					}
				} catch (SQLException sq) {
					System.out.println("Unable to fetch rows." + sq);
				}
				return Listproduct;
			}

			public ArrayList<Product> findAll() {
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<Product> listproducts = new ArrayList<Product>();	
				try {
					String sql = "select * from products";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Product pro = new Product();
						pro.setProductId(rs.getInt("prod_id"));
						pro.setProductName(rs.getString("prod_name"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setProductCode(rs.getInt("prod_code"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setQih(rs.getInt("prod_qih"));
						pro.setProductReoderLevel(rs.getInt("prod_reoder_level"));
						pro.setProductCgst(rs.getFloat("prod_cgst"));
						pro.setProductSgst(rs.getFloat("prod_sgst"));
						listproducts.add(pro);
					}
				} catch (SQLException sq) {
					System.out.println("Unable to fetch rows.");
				}
				return listproducts;
			}

			public ArrayList<Product> findAllByQyt() {
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<Product> listproducts = new ArrayList<Product>();	
				try {
					String sql = "select * from products";
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

			
			public ArrayList<Integer> findByCategoryIdInt(int categoryId){
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<Integer> Listproduct = new ArrayList<Integer>();
				try {
					String sql = "select * from product where cat_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, categoryId);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Listproduct.add(rs.getInt("prod_Id"));
					}
				} catch (SQLException sq) {
					System.out.println("Unable to fetch rows." + sq);
				}
				return Listproduct;
			}

			public ArrayList<String> findByCategoryIdString(int categoryId){
				Connection conn = MyDatabaseConnection.getConnection();
				ArrayList<String> Listproduct = new ArrayList<String>();
				try {
					String sql = "select * from product where cat_id = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, categoryId);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Listproduct.add(rs.getString("prod_name"));
					}
				} catch (SQLException sq) {
					System.out.println("Unable to fetch rows." + sq);
				}
				return Listproduct;
			}
			
			

			 public ArrayList<Product> getByCategory(String categoryName){
				 Connection conn = MyDatabaseConnection.getConnection();
				 ArrayList<Product> list = new ArrayList<Product>();
				 try {
						String sql = "select * from product where cat_name = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, categoryName);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
						Product pro = new Product();
						pro.setProductName(rs.getString("prod_name"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setProductId(rs.getInt("prod_Id"));
						pro.setProductCode(rs.getInt("prod_code"));
						pro.setProductPrice(rs.getFloat("prod_price"));
						pro.setQih(rs.getInt("prod_qih"));
						pro.setProductReoderLevel(rs.getInt("prod_reoder_level"));
						pro.setProductCgst(rs.getFloat("prod_cgst"));
						pro.setProductSgst(rs.getFloat("prod_sgst"));
						list.add(pro);
					}
				} catch (SQLException sq) {
					System.out.println("Unable to fetch rows." + sq);
				}
				return list;
			}
			
			
			
	public static void main(String args[]) {
		Product pro = new Product(9, 3010, "Fog", 130, 12, 8, 1.3f, 1.11f);
		ProductDao proDao = new ProductDao();
		proDao.createProduct(pro); 
		
	/*	Product pro = new Product( 2, 3, 2999,"friction",54.2,56,544,96.00,87.88);
		ProductDao proDao = new ProductDao();
		proDao.editProduct(pro); 
		*/
	/*	ProductDao  pro = new ProductDao();
		pro.removeProduct(1); */

	/*	ProductDao product = new ProductDao();
		System.out.println(product.find(2)); */
		
	/*	ProductDao productDao = new ProductDao ();
		ArrayList<String> all = productDao.findallProduct();
		for (String product : all) {
			  System.out.println(product);
		}
		*/
	/*0	ProductDao productDao = new ProductDao();
		ArrayList<Integer> arr = productDao.findAllProductByIds();
		for(Integer pro  : arr) {
		System.out.println(pro);
		}
	*/	
	/*	ProductDao productDao = new ProductDao();
		 ArrayList<Product> all = productDao.searchProducts("Friction");
		 for(Product pro : all) {
		 System.out.println(pro) ;
		 }
		*/
		ProductDao pDao = new ProductDao();
		ArrayList<Product> al = pDao.findByCategoryId(9);
		 for (Product p : al)
		System.out.println(p);
		
		
	/*	ProductDao pDao = new ProductDao();
		ArrayList<Product> al = pDao.getByCategory("Bevrages");
		 for (Product p : al)
		System.out.println(p);
	}*/
}
}
 