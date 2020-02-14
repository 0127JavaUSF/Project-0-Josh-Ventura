package com.revature.project0;

import java.util.Scanner;
import com.revature.daos.Dao;
import com.revature.database.AccountInfo;
import com.revature.database.UserAccountInfo;
import com.revature.util.*;

public class UserAccount {

	Scanner scan = new Scanner(System.in);
	AccountInput accountInput = new AccountInput();
	Checking traverseChecking = new Checking();
	Savings traverseSavings = new Savings();
	Dao dao = new Dao();
	
	public void traverseAccount() {

		//displays sysout statements for switch cases
		traverseChoises();
		
		int choice = accountInput.traverseAccount(1, 6);
		switch(choice) {
		case 1: viewChecking(); 
		case 2: viewSavings();
		case 3: deleteAccount();
		case 4: createJunction(); // creates relationship between user and account
		case 5: logout();
		case 6: leaveBank(); // closes application
		
		}
	}
	public void viewChecking() {
		System.out.println("Do you have a Checking account already?(Yes or No) ");
		while (scan.hasNext()) {
			String answer = scan.next();
			if (answer.equals("Yes") || answer.equals("yes")) {
				traverseChecking.traverse();
			} else if (answer.equals("No") || answer.equals("no")) {
				System.out.println("Would you like to make an Checking account?(Yes or No)");
				String create = scan.next();
				if (create.equals("Yes") || create.equals("yes")) {
					createBankingAccount();
				} else if (create.equals("No") || create.equals("no")) {
					System.out.println("Not a problem! Maybe you just aren't ready to be an adult yet.");
					traverseAccount();
				}
			} else {
				System.out.println("Please enter Yes or No");
			}
		}
	}
	public void viewSavings() {
		System.out.println("Do you have a Savings account already?(Yes or No) ");
		while (scan.hasNext()) {
			String answer = scan.next();
			if (answer.equals("Yes") || answer.equals("yes")) {
				traverseSavings.traverse();
			} else if (answer.equals("No") || answer.equals("no")) {
				System.out.println("Would you like to make an Savings account?(Yes or No)");
				String create = scan.next();
				if (create.equals("Yes") || create.equals("yes")) {
					createBankingAccount();
				} else if (create.equals("No") || create.equals("no")) {
					System.out.println("Not a problem! Maybe you just aren't ready to be a Super adult yet.\n");
					traverseAccount();
				}
			} else {
				System.out.println("Please enter Yes or No");
			}
		}
	}
	//Creates bank account
	public void createBankingAccount() {
		System.out.println("Please enter an amount you would like to deposit to create your account.");
		String balance = scan.next();
		
		
		AccountInfo accountInfo = new AccountInfo(0, Integer.parseInt(balance));
		dao.createAccount(accountInfo);
		
		
		System.out.println("Successfully created bank account\n");
		traverseAccount();
	}
	
	// Links user to account by user id and account id
	public void createJunction() {
		String accountId = null;
		String userId = null;
		
		System.out.println("Please enter Account id: ");
		while (scan.hasNext()) {
			try {
				accountId = scan.next();
				if (Integer.parseInt(accountId) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter Account Id: ");
				} else {
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("You must enter numbers only. \nPlease enter Account Id: ");
			}
		}
		
		
		System.out.println("Please enter User id: ");
		while (scan.hasNext()) {
			try {
				userId = scan.next();
				if(Integer.parseInt(userId) < 0) {
					System.out.println("Numbers entered must be positive. \nPlease enter User Id: ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You must enter numbers only. \nPlease enter User Id: ");
			}
		}
		
		
		UserAccountInfo userAccountInfo = new UserAccountInfo(Integer.parseInt(accountId), Integer.parseInt(userId));
		userAccountInfo = dao.createJunction(userAccountInfo);
		
		System.out.println("Created connection between user and account!");
		traverseAccount();
	}
	
	public void deleteAccount() {
		System.out.println("Please enter Username: ");
		while (scan.hasNext()) {
			String username = scan.next();

			  System.out.println("Please enter Password: "); 
			  String password = scan.next();
			  
			  Dao dao = new Dao();
			  dao.deleteUser(username, password);

			if (username.equals(username) && password.equals(password)) {
				System.out.println("Account has been deleted.");
				Login.haveAccount();
			} else {
				System.out.println("Incorrect Username or Password.");
				traverseAccount();
			}
		}
	}
	
	public void logout() {
		System.out.println("Are you sure you want to logout?(Yes or No)");
		while (scan.hasNext()) {
			String answer = scan.next();
			if (answer.equals("Yes") || answer.equals("yes")) {
				System.out.println("Successfully Logged out, Have a nice day!\n");
				Login.haveAccount();
			} 
			else if (answer.equals("No") || answer.equals("no")) {
				traverseAccount();
			}
			else {
				System.out.println("Please enter Yes or No");
			}
		}
	}
	
	public void leaveBank() {
		System.out.println("Thank you for coming in today! ");
		System.exit(0);
	}
	
	public void traverseChoises() {
		System.out.println("Account Menu\n");
		System.out.println("1. Checking Account");
		System.out.println("2. Savings Account");
		System.out.println("3. Delete User Account");
		System.out.println("4. Add user to Account");
		System.out.println("5. Logout");
		System.out.println("6. Leave Bank");
	}
}
