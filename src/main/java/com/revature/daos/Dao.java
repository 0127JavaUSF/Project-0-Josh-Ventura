
package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.database.AccountInfo;
import com.revature.database.UserAccountInfo;
import com.revature.database.UserInfo;
import com.revature.project0.Login;
import com.revature.util.ConnectionUtil;

public class Dao {

	public UserInfo getUserInfo(int user_info_id) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT user_info_id, first_name, last_name, pin, username, password FROM user_info WHERE user_info_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, user_info_id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				String first_name = result.getString("first_name");
				String last_name = result.getString("last_name");
				int pin = result.getInt("pin");
				String username = result.getString("username");
				String password = result.getString("password");
				return new UserInfo(user_info_id, first_name, last_name, pin, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserInfo extractUserInfo(ResultSet result) throws SQLException {
		int user_info_id = result.getInt("user_info_id");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		int pin = result.getInt("pin");
		String username = result.getString("username");
		String password = result.getString("password");
		return new UserInfo(user_info_id, firstName, lastName, pin, username, password);
	}
	
	public UserInfo createUser(UserInfo userInfo) {		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO user_info (first_name, last_name, pin, username, password) VALUES (?, ?, ?, ?, ?) RETURNING *";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, userInfo.getFirstName());
			statement.setString(2, userInfo.getLastName());
			statement.setInt(3, userInfo.getPin());
			statement.setString(4, userInfo.getUsername());
			statement.setString(5, userInfo.getPassword());
	
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				return extractUserInfo(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteUser(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM user_info Where username = ? AND password = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, username);
			statement.setString(2, password);

			int result = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean verifyAccount(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM user_info WHERE username = ? AND password = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet result = statement.executeQuery();

			//if user input doesn't match, returns false and sends user back to login
			if (result.next() == false) {
				System.out.println("Incorrect Username or password");
				Login.login();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private AccountInfo extractAccountInfo(ResultSet result) throws SQLException {
		int account_id = result.getInt("account_id");
		int balance = result.getInt("balance");
		
		return new AccountInfo(account_id, balance);
	}
	
	public AccountInfo createAccount(AccountInfo accountInfo) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO account (balance) VALUES (?) RETURNING *";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, accountInfo.getBalance());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractAccountInfo(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AccountInfo getAccountId(int accountId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
		
			String sql = "SELECT account_id, balance FROM account WHERE account_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, accountId);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				int balance = result.getInt("balance");
				System.out.println(accountId);
				return new AccountInfo(accountId, balance);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static UserAccountInfo extractUserAccountInfo(ResultSet result) throws SQLException{
		int userId = result.getInt("user_info_id");
		int accountId = result.getInt("account_id");
		return new UserAccountInfo(userId, accountId);
	}
	
	//creates junction between user and account
	public UserAccountInfo createJunction(UserAccountInfo userAccountInfo) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO account_user (account_id, user_info_id) VALUES (?, ?) RETURNING *";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, userAccountInfo.getAccountId());
			statement.setInt(2, userAccountInfo.getUserId());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractUserAccountInfo(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userAccountInfo;
	}
	
	// updates balance of account
	public boolean UpdateAccount(int accountId, int balance) {
		boolean verify = false;
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account SET balance = ? WHERE account_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, balance);
			statement.setInt(2, accountId);
			
			statement.executeUpdate();
			verify = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
