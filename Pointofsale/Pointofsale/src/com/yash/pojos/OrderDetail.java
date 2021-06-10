package com.yash.pojos;

public class OrderDetail {
	protected int orderDetailId;
	protected int orderId;
	protected int proId;
	protected String proName;
	protected int prodQuantity;
	protected float prodPrice;
	protected float cgst;
	protected float sgst;

	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetail(int proId, String proName, int prodQuantity, float prodPrice, float cgst, float sgst) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.prodQuantity = prodQuantity;
		this.prodPrice = prodPrice;
		this.cgst = cgst;
		this.sgst = sgst;
	}
	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public float getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(float prodPrice) {
		this.prodPrice = prodPrice;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public int hashCode() {
		return orderDetailId;
	}

	public boolean equals(Object obj) {
		if (obj != null || obj instanceof OrderDetail) {
			OrderDetail od = (OrderDetail) obj;
			if (orderId == od.orderId && proId == od.proId && prodPrice == od.prodPrice && cgst == od.cgst
					&& sgst == od.sgst)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "OderDetail [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", proId=" + proId
				+ ", prodQuantity=" + prodQuantity + ", prodPrice=" + prodPrice + ", cgst=" + cgst + ", sgst=" + sgst
				+ "]";
	}

}
