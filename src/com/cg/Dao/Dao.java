package com.cg.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cg.beans.Account;
import com.cg.beans.Loan;

public class Dao {

	PreparedStatement ps = null;
	PreparedStatement ps1 = null;
	PreparedStatement ps2 = null;
	Connection con = null;

	public void createAccount(Account account) {
		String name = account.getAccountname();
		String id = account.getAccountid();
		String address = account.getAddress();
		double deposit = account.getDepositamount();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");
			ps = con.prepareStatement("insert into Account values(?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setDouble(4, deposit);

			int result = ps.executeUpdate();

			if (result == 1) {
				System.out.println("Account created successfully!");
			}

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public double deposit(String id, double deposit) {
		double newBalance;
		try {
			if (deposit > 0) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");

				ps2 = con.prepareStatement("select deposit from account");
				ResultSet rs = ps2.executeQuery();

				ps = con.prepareStatement("update account set deposit= ? where id=?");

				if (rs.next()) {
					double bal1 = rs.getDouble("deposit");
					newBalance = bal1 + deposit;

					ps.setDouble(1, newBalance);
					ps.setString(2, id);

					int result = ps.executeUpdate();

					if (result > 0) {
						System.out.println("Money deposited scucessfully!");
						return newBalance;
					}
					con.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public double withdraw(String id, double withdraw) {
		double newBalance;
		try {
			if (withdraw > 0) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");
				ps2 = con.prepareStatement("select deposit from account");
				ResultSet rs = ps2.executeQuery();
				ps = con.prepareStatement("update account set deposit= ? where id=?");
				if (rs.next()) {
					double bal1 = rs.getDouble("deposit");
					newBalance = bal1 - withdraw;
					ps.setDouble(1, newBalance);
					ps.setString(2, id);
					int result = ps.executeUpdate();
					if (result > 0) {
						System.out.println("Money withdrawn successfully! ");
						return newBalance;
					}
					con.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public void applyLoan(Loan loan) {
		String loan_id = loan.getLoanId();
		String loan_type = loan.getLoanType();
		double loan_amount = loan.getLoanAmount();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");
			ps = con.prepareStatement("insert into Loan values(?,?,?)");
			ps.setString(1, loan_id);
			ps.setString(2, loan_type);
			ps.setDouble(3, loan_amount);

			int result = ps.executeUpdate();

			if (result == 1) {
				System.out.println("Loan applied successfully!");
			}

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public double payLoan(String loan_id, double loan_amount) {
		double newBalance;
		try {
			if (loan_amount > 0) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");

				ps2 = con.prepareStatement("select loan.loanAmount, account.deposit from loan inner join account on loan.loanId = account.id");
				ResultSet rs = ps2.executeQuery();

				ps = con.prepareStatement("update account set deposit = ?  where id= ?");
				ps1 = con.prepareStatement("update loan set loanAmount= ? where loanId = ?");

				if (rs.next()) {
					double bal1 = rs.getDouble("deposit");
					newBalance = bal1 - loan_amount;

					double bal2 = rs.getDouble("loanAmount");
					double loanRem = bal2 - loan_amount;

					ps.setDouble(1, newBalance);
					ps.setString(2, loan_id);
					ps1.setDouble(1, loanRem);
					ps1.setString(2, loan_id);

					int sa = ps.executeUpdate();
					int sq = ps1.executeUpdate();

					if (sa == 1) {
						System.out.println("Loan paid successfully! Remaining loan amount is: "+ loanRem);
						return newBalance;
					}
					con.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public Account getAccountDetails(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");
			ps = con.prepareStatement("select id, name, address, deposit from Account where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account acc = new Account();
				acc.setAccountid(rs.getString("id"));
				acc.setAccountname(rs.getString("name"));
				acc.setAddress(rs.getString("address"));
				acc.setDepositamount(rs.getDouble("deposit"));
				return acc;
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public Loan getLoanDetails(String loan_id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "chop123");
			ps = con.prepareStatement("select loanId, loanType, loanAmount from loan where loanId = ?");
			ps.setString(1, loan_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Loan l = new Loan(loan_id, null, loan_id);
				l.setLoanId(rs.getString("loanId"));
				l.setLoanType(rs.getString("loanType"));
				l.setLoanAmount(rs.getDouble("loanAmount"));
				return l;
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
