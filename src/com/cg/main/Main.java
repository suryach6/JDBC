package com.cg.main;

import java.util.Scanner;

import com.cg.Dao.Dao;
import com.cg.beans.Account;
import com.cg.beans.Loan;
import com.cg.validation.Validation;

public class Main {
public static void main(String[] args) {
	String name, id, address, loanId, loanType;
	double deposit, withdraw, loanAmount;
	
	Dao dao = new Dao();
	Validation vld = new Validation();

	while (true) {
		System.out.println("1. Open Account");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Apply Loan");
		System.out.println("5. Show Account Details");
		System.out.println("6. Pay Loan");
		System.out.println("7. Show Loan Details");
		System.out.println("8. Exit");

		Scanner scanner = new Scanner(System.in);
		System.out.println("enter option");
		int option = scanner.nextInt();

		switch (option) {

		case 1: {
			while (true) {
				System.out.println("Enter your name (First letter Capital)");
				name = scanner.next();
				if (vld.nameValidation(name)) {
					System.out.println("Enter account Id (eg. 1234567-ABCD)");
					id = scanner.next();
					if (vld.idValidation(id)) {
						System.out.println("Enter address");
						address = scanner.next();
						System.out.println("Enter deposit");
						deposit = scanner.nextDouble();
						Account acc = new Account(id, name, address, deposit);
						dao.createAccount(acc);
						break;
					} else {
						System.out.println("Please enter valid Id");
					}
				} else
					System.out.println("Please enter valid name ");
			}
			break;
		}
		case 2: {
			System.out.println("Enter account Id and amount");
			id = scanner.next();
			deposit = scanner.nextDouble();
			System.out.println("New balance after deposit is: "+ dao.deposit(id, deposit));
			break;
		}

		case 3: {
			System.out.println("Enter account Id and amount");
			id = scanner.next();
			withdraw = scanner.nextDouble();
			System.out.println("New balance after withdrawl is: " +dao.withdraw(id, withdraw));
			break;
		}
		case 4: {
			System.out.println("Enter account Id");
			id = scanner.next();
			System.out.println("Enter Loan Id");
			loanId = scanner.next();
			if (id.equals(loanId)) {
				System.out.println("Select Loan Type :");
				loanType = scanner.next();
				System.out.println("Enter loan amount");
				loanAmount = scanner.nextDouble();
				Loan loan = new Loan(loanId, loanAmount, loanType);
				dao.applyLoan(loan);
				break;
			} else
				System.out.println("Check your Loan Id");
			break;
		}

		case 5: {
			System.out.println("Enter account Id");
			id = scanner.next();
			Account b = dao.getAccountDetails(id);
			System.out.println("Your account details are: ");
			System.out.println(b);
			break;

		}

		case 6: {
			System.out.println("Enter account Id");
			id = scanner.next();
			System.out.println("Enter Loan Id");
			loanId = scanner.next();
			if (id.equals(loanId)) {
				System.out.println("Enter amount to pay");
				loanAmount = scanner.nextDouble();
				System.out.println("New Balance after paying loan is : " + dao.payLoan(loanId, loanAmount));
				break;
			}
		}
		case 7:

		{
			System.out.println("Enter loan Id");
			loanId = scanner.next();
			Loan l = dao.getLoanDetails(loanId);
			System.out.println("Loan Details are: ");
			System.out.println(l);
			break;

		}
		
		case 8: 
		{
			System.exit(0);
		}
		
		default:
			System.out.println("Please enter correct choice");
		}
	}

}
}
