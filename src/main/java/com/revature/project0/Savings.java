package com.revature.project0;

import com.revature.util.AccountInput;
import java.util.Scanner;
import com.revature.daos.Dao;

public class Savings implements AccountType {

	int balance;
	static Scanner scan = new Scanner(System.in);
	Dao dao = new Dao();

	public void traverse() {
		AccountInput accountInput = new AccountInput();
		UserAccount userAccount = new UserAccount();

		// displays sysout statements for switch cases
		traverseChoices();

		int choice = accountInput.traverseAccount(1, 5);
		switch (choice) {
		case 1:
			checkBalance();
		case 2:
			withdraw();
		case 3:
			deposit();
		case 4:
			transfer();
		case 5:
			userAccount.traverseAccount();
		}
	}

	public void withdraw() {
		String withdrawlAmount = null;
		String accountId = null;

		System.out.println("Please enter Account Id you wish to withdraw money from: ");
		while (scan.hasNext()) {
			try {
				accountId = scan.next();
				if (Integer.parseInt(accountId) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter Account Id you wish to withdraw money from: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter whole numbers only. \nPlease enter Account Id you wish to withdraw money from: ");
			}
		}

		System.out.println("Please enter how much you would like to withdraw: ");
		while (scan.hasNext()) {
			try {
				withdrawlAmount = scan.next();
				if (Integer.parseInt(withdrawlAmount) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter how much you would like to withdraw: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter whole numbers only. \nPlease enter how much you would like to withdraw: ");
			}
		}

		// gets balance of account from accountId
		int oldBalance = dao.getAccountId(Integer.parseInt(accountId)).getBalance();

		balance = oldBalance - Integer.parseInt(withdrawlAmount);

		dao.UpdateAccount(Integer.parseInt(accountId), balance);
		System.out.println("Account has been updated, new balance " + balance + " dolla dolla bills.\n");
		traverse();
	}

	public void deposit() {
		String depositAmount = null;
		String accountId = null;

		System.out.println("Please enter Account Id you wish to deposit money to: ");
		while (scan.hasNext()) {
			try {
				accountId = scan.next();
				if (Integer.parseInt(accountId) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter Account Id you wish to deposit money to: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter whole numbers only. \nPlease enter Account Id you wish to deposit money to: ");
			}
		}
		
		System.out.println("Please enter how much you would like to deposit: ");
		while (scan.hasNext()) {
			try {
				depositAmount = scan.next();
				if (Integer.parseInt(depositAmount) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter how much you would like to deposit: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter whole numbers only. \nPlease enter how much you would like to deposit: ");
			}
		}
		
		int oldBalance = dao.getAccountId(Integer.parseInt(accountId)).getBalance();

		balance = oldBalance + Integer.parseInt(depositAmount);

		dao.UpdateAccount(Integer.parseInt(accountId), balance);
		System.out.println("Account has been updated, new balance " + balance + " dolla dolla bills.\n");
		traverse();
	}

	public void checkBalance() {
		String accountId = null;
		
		System.out.println("Please enter Account Id to see balance: ");
		while (scan.hasNext()) {
			try {
			accountId = scan.next();
			if(Integer.parseInt(accountId) < 0) {
				System.out.println("Numbers entered must be positive. \nPlease enter Account Id to see balance: ");
			} else {
				break;
			}
			} catch (NumberFormatException e) {
				System.out.println("You must enter whole numbers only. \nPlease enter Account Id to see balance: ");
			}
		}
			// gets balance of account from accountId
			int oldBalance = dao.getAccountId(Integer.parseInt(accountId)).getBalance();
			System.out.println("Your current balance is " + oldBalance + " dolla dolla bills.\n");

			traverse();

	}

	public void transfer() {
		String withdrawlAmount = null;
		String accountId = null;

		System.out.println("Please enter Account Id you wish to withdraw money from: ");
		while (scan.hasNext()) {
			try {
				accountId = scan.next();
				if (Integer.parseInt(accountId) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter Account Id you wish to withdraw money from: ");
				} else {
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("You must enter numbers only. \nPlease enter Account Id you wish to withdraw money from: ");
			}
		}

		System.out.println("Please enter how much you would like to withdraw: ");
		while (scan.hasNext()) {
			try {
				withdrawlAmount = scan.next();
				if (Integer.parseInt(withdrawlAmount) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter how much you would like to withdraw: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter numbers only. \nPlease enter how much you would like to withdraw: ");
			}
		}

		// gets balance of account from accountId
		int oldBalance = dao.getAccountId(Integer.parseInt(accountId)).getBalance();

		balance = oldBalance - Integer.parseInt(withdrawlAmount);

		dao.UpdateAccount(Integer.parseInt(accountId), balance);
		System.out.println("Account has been updated, new balance " + balance + " dolla dolla bills.\n");
		
		
		String accountId2 = null;

		System.out.println("Please enter Account Id you wish to deposit money to: ");
		while (scan.hasNext()) {
			try {
				accountId2 = scan.next();
				if (Integer.parseInt(accountId2) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter Account Id you wish to deposit money to: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter numbers only. \nPlease enter Account Id you wish to deposit money to: ");
			}
		}

		int newBalance = dao.getAccountId(Integer.parseInt(accountId)).getBalance();
		
		balance = Integer.parseInt(withdrawlAmount) + newBalance;

		dao.UpdateAccount(Integer.parseInt(accountId), balance);
		System.out.println("Account has been updated, new balance " + balance + " dolla dolla bills.\n");
		
		traverse();
	}

	@Override
	public void traverseChoices() {
		System.out.println("Savings Menu\n");
		System.out.println("1. Account Balance");
		System.out.println("2. Withdraw Funds");
		System.out.println("3. Deposit Funds");
		System.out.println("4. Transfer Funds");
		System.out.println("5. Go Back");
	}
}
