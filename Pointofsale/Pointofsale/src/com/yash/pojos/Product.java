package com.yash.pojos;

public class Product {
	protected int productId;
	protected int categoryId;
	public int productCode;
	protected String productName;
	public float productPrice;
	protected int qih;
	protected int productReorderlevel;
	public float productCgst;
	public float productSgst;
	public String CategoryName;

	public Product(int productId, int categoryId, int productCode, String productName, float productPrice, int qih,
			int productReorderlevel, float productCgst, float productSgst) {
		this.productId = productId;
		this.categoryId = categoryId;
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.qih = qih;
		this.productReorderlevel = productReorderlevel;
		this.productCgst = productCgst;
		this.productSgst = productSgst;
	}
	
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String getCategoryName) {
		this.CategoryName = getCategoryName;
	}
	public Product(int categoryId, int productCode, String productName, float productPrice, int qih,
			int productReorderlevel, float productCgst, float productSgst) {

		this.categoryId = categoryId;
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.qih = qih;
		this.productReorderlevel = productReorderlevel;
		this.productCgst = productCgst;
		this.productSgst = productSgst;
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public Product() {
		
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;

	}

	public int getProductCode() {
		return productCode;
	}
	

	public int getProductReorderlevel() {
		return productReorderlevel;
	}

	public void setProductReorderlevel(int productReoderlevel) {
		this.productReorderlevel = productReoderlevel;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public  int getQih() {
		return qih;
	}

	public void setQih(int qih) {
		this.qih = qih;
	}

	public int getProductReoderLevel() {
		return productReorderlevel;
	}

	public void setProductReoderLevel(int productReoderlevel) {
		this.productReorderlevel = productReoderlevel;
	}

	public float getProductCgst() {
		return productCgst;
	}

	public void setProductCgst(float productCgst) {
		this.productCgst = productCgst;
	}

	public float getProductSgst() {
		return productSgst;
	}

	public void setProductSgst(float productSgst) {
		this.productSgst = productSgst;
	}

	@Override
	public int hashCode() {
		return productId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Product) {
			Product p = (Product) obj;
			if (productId == p.productId && productName.equals(p.productName) && categoryId == p.categoryId
					&& productCode == p.productCode && productSgst == p.productSgst && productPrice == p.productPrice
					&& productCgst == p.productCgst)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productCode=" + productCode
				+ ", productName=" + productName + ", productPrice=" + productPrice + ", qih=" + qih
				+ ", productReorder=" + productReorderlevel + ", productCgst=" + productCgst + ", productSgst="
				+ productSgst;
	}

}
