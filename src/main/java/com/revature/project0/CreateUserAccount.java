package com.revature.project0;

import java.util.Scanner;

import com.revature.daos.Dao;
import com.revature.database.UserInfo;

public class CreateUserAccount {


	public static void createAccount() {
		// Input user information then send information to database table
		Scanner scan = new Scanner(System.in);

		System.out.println("\nCreate an Account\n");
		
		System.out.println("Enter First Name: ");
		String firstName = scan.next();

		System.out.println("Enter Last Name: ");
		String lastName = scan.next();

		System.out.println("Enter a 4 digit Pin: ");
		String pin = null;
		
		while(scan.hasNext()) {
		try {
			pin = scan.next();
			Integer.parseInt(pin);
			if (Integer.parseInt(pin) < 0) {
				System.out.println("Numbers entered must be positive. \nEnter a 4 digit Pin:");
			}
			else if(pin.length() >= 5 || pin.length() <= 3) {
			System.out.println("Pin must be exactly 4 numbers long. \nEnter a 4 digit Pin:");
			}
			else {
				break;
			}
			} catch(NumberFormatException e) {
				System.out.println("You must enter numbers only. \nEnter a 4 digit Pin:");
			}
		}
		
		System.out.println("Enter a Username: ");
		String userName = scan.next();

		System.out.println("Enter a Password: ");
		String password = scan.next();

		System.out.println("\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nPin: " + pin + "\nUserName: " + 
		userName + "\nPassword: " + password + "\nIs this information correct?(Yes or No) ");
		
		String answer = scan.next();
		if (answer.equals("Yes") || answer.equals("yes")) {
			//add information to data base
			Dao dao = new Dao();
			UserInfo userInfo = new UserInfo(0, firstName, lastName, Integer.parseInt(pin), userName, password);
			userInfo = dao.createUser(userInfo);
			
			System.out.println("Welcome, your account has successfully been created! \nPlease log in\n ");
			Login.login();
		} else if (answer.equals("No") || answer.equals("no")) {
			System.out.println("Please correct and renter information.");
			createAccount();
		}
		scan.close();

		Login.login();
	}
}
