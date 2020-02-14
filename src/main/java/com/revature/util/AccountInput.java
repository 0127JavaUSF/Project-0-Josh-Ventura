package com.revature.util;

import java.util.Scanner;

public class AccountInput {

	protected Scanner scan = new Scanner(System.in);
	
	public int traverseAccount(int min, int max) {
		int choice = 0;
		
		outer: do {
		
		System.out.println("Enter a number between " + min + " and " + max);
		
		while(!scan.hasNext()) {
			scan.nextLine();
			continue outer;
			
		}
		choice = scan.nextInt();
		scan.hasNextLine();
		} while(choice < min || choice > max);
		
		return choice;
	}
}
