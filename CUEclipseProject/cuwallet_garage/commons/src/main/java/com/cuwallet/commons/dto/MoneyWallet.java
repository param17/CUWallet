package com.cuwallet.commons.dto;

public class MoneyWallet {

	private String emailId;
	
	private String phoneNo;
	
	private double balance;
	
	public MoneyWallet() {
		
	}

	public MoneyWallet(String emailId, String phoneNo, double balance) {
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.balance = balance;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
