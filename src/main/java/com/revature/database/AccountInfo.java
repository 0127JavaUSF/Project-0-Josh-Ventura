package com.revature.database;

public class AccountInfo {

	private int account_id;
	private int balance;
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public AccountInfo(int account_id, int balance) {
		super();
		this.account_id = account_id;
		this.balance = balance;
	}
	
	public AccountInfo() {
		super();
	}
	
	
}
