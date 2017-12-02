package com.cuwallet.commons.dto;

public class MoneyInformation {

	private String emailId;

	private String phoneNo;

	private String receiverEmailId;

	private String receiverPhoneNo;

	private double transactionMoney;

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

	public String getReceiverEmailId() {
		return receiverEmailId;
	}

	public void setReceiverEmailId(String receiverEmailId) {
		this.receiverEmailId = receiverEmailId;
	}

	public String getReceiverPhoneNo() {
		return receiverPhoneNo;
	}

	public void setReceiverPhoneNo(String receiverPhoneNo) {
		this.receiverPhoneNo = receiverPhoneNo;
	}

	public double getTransactionMoney() {
		return transactionMoney;
	}

	public void setTransactionMoney(double transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
}
