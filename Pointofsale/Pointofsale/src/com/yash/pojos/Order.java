package com.yash.pojos;

import java.util.*;

public class Order {
	protected int oderId;
	protected int custId;
	protected Date dt;
	protected float amount;
	protected float cgst;
	protected float sgst;

	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(int oderId, int custId, Date dt, float amount, float cgst, float sgst) {

		this.oderId = oderId;
		this.custId = custId;
		this.dt = dt;
		this.amount = amount;
		this.cgst = cgst;
		this.sgst = sgst;
	}

	public int getOderId() {
		return oderId;
	}

	public void setOderId(int oderId) {
		this.oderId = oderId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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
		return oderId;
	}

	public boolean equals(Object obj) {
		if (obj != null || obj instanceof Order) {
			Order o = (Order) obj;
			if (oderId == o.oderId && custId == o.custId && amount == o.amount && cgst == o.cgst && sgst == o.sgst)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Oders [oderId=" + oderId + ", custId=" + custId + ", dt=" + dt + ", amount=" + amount + ", cgst=" + cgst
				+ ", sgst=" + sgst + "]";
	}

}
