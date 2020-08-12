package com.cg.beans;

public class Loan {
private String loanId;
private Double loanAmount;
private String loanType;



public String getLoanId() {
	return loanId;
}
public void setLoanId(String loanId) {
	this.loanId = loanId;
}
public Double getLoanAmount() {
	return loanAmount;
}
public void setLoanAmount(Double loanAmount) {
	this.loanAmount = loanAmount;
}
public String getLoanType() {
	return loanType;
}
public void setLoanType(String loanType) {
	this.loanType = loanType;
}
public Loan(String loanId, Double loanAmount, String loanType) {
	super();
	this.loanId = loanId;
	this.loanAmount = loanAmount;
	this.loanType = loanType;
}
	

}