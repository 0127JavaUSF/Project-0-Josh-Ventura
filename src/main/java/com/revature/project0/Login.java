package com.revature.project0;

import java.util.Scanner;
import com.revature.daos.Dao;

public class Login {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void haveAccount() {
		System.out.println("Hello and welcome to the wonderful Bank of Life!\n");
		System.out.println("Here at the Bank of Life we put your needs above all else! \nBut only if you give us your money.\n");
		System.out.println("Do you currently have an account with us?(Yes or No)");

		while (scan.hasNext()) {
			String answer = scan.next();
			if (answer.equals("Yes") || answer.equals("yes")) {
				System.out.println("Welcome back! ");
				login();
			} else if (answer.equals("No") || answer.equals("no")) {
				System.out.println("Would you like to make an account?(Yes or No)");
				String create = scan.next();
				if (create.equals("Yes") || create.equals("yes")) {
					CreateUserAccount.createAccount();
				} else if (create.equals("No") || create.equals("no")) {
					System.out.println("That's too bad, maybe you will get a life .. account next time!\n");
					haveAccount();
				}
			} else {
				System.out.println("Please enter Yes or No");
			}
		}
		scan.close();
	}

	public static void login() {
		System.out.println("Login\n");

		System.out.println("Please enter Username: ");
		while (scan.hasNext()) {
			String username = scan.next();

			System.out.println("Please enter Password: ");
			String password = scan.next();

			// calling verify method and doing a check to see if input matches database
			Dao dao = new Dao();
			dao.verifyAccount(username, password);

			//pipe dream of keeping user logged in
			//UserInfo userInfo = dao.getUserInfo(username, password);
			
			if (username.equals(username) && password.equals(password)) {
				System.out.println("\nCongrats you did it!\n");
				
				UserAccount userAccount = new UserAccount();
				userAccount.traverseAccount();
			} else {
				// not needed, but remaining just in case. <- handled in Dao class
				System.out.println("Incorrect Username or Password.");
				login();
			}

		}
		scan.close();
	}
}
