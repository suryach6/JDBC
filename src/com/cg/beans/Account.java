package com.cg.beans;

public class Account {
	private String accountId;
	private String accountName;
	private String address;
	private Double DepositAmount;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String accountid, String accountname, String address, Double depositamount) {
		super();
		this.accountId = accountid;
		this.accountName = accountname;
		this.address = address;
		this.DepositAmount = depositamount;
	}
	public String getAccountid() {
		return accountId;
	}
	public void setAccountid(String accountid) {
		this.accountId = accountid;
	}
	public String getAccountname() {
		return accountName;
	}
	public void setAccountname(String accountname) {
		this.accountName = accountname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getDepositamount() {
		return DepositAmount;
	}
	public void setDepositamount(Double depositamount) {
		this.DepositAmount = depositamount;
	}
	@Override
	public String toString() {
		return "Account [accountid=" + accountId + ", accountname=" + accountName + ", address=" + address
				+ ", depositamount=" + DepositAmount + "]";
	}
	
}
