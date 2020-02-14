package com.revature.database;

public class UserAccountInfo {

	private int userId;
	private int accountId;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public UserAccountInfo(int userId, int accountId) {
		super();
		this.userId = userId;
		this.accountId = accountId;
	}
	public UserAccountInfo() {
		super();
	}
	
	
}
