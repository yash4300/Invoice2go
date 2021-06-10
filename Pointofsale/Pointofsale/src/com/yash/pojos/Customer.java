package com.yash.pojos;

import java.util.*;

public class Customer {
	protected int customerId;
	protected String customerName;
	protected String customerMobile;
	protected String customerAddress;
	protected String city;
	protected Date registrationDate;

	public Customer() {
		customerName = new String();
		customerAddress = new String();
		registrationDate = new Date();
	}

	public Customer(String customerName, String customerMobile, String customerAddress, String city,
			Date registrationDate) {
		super();
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerAddress = customerAddress;
		this.city = city;
		this.registrationDate = registrationDate;
	}

	public Customer(int customerId, String customerName, String customerMobile, String customerAddress, String city,
			Date registrationDate) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerAddress = customerAddress;
		this.city = city;
		this.registrationDate = registrationDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", city=" + city + ", registrationDate=" + registrationDate + "]";
	}

}
