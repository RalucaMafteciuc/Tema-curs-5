package com.ralucamafteciuc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/bazadate?user=sqluser&password=sqluserpw");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String selectAllExample(String dbTable) throws Exception{
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from " + dbTable);
		String usersTable = "<table cellspacing=\"2\" cellpadding=\"10\" border=1>\r\n" + 
				"	<tr>\r\n" + 
				"		<td>Username</td>\r\n" + 
				"		<td>Email</td>\r\n" + 
				"	</tr>\r\n";
		
		while (resultSet.next()) {
			String user = resultSet.getString("username");
			String email = resultSet.getString("email");
			usersTable += "	<tr>\r\n" + 
					"		<td>" + user + "</td>\r\n" + 
					"		<td>" + email + "</td>\r\n" + 
					"	</tr>";		
		}
		
		usersTable += "</table>";
		
		System.out.println("users table");
		
		return usersTable;
	}
	
	public Boolean checkIfUserExists(String dbTable, String newUsername) throws Exception{
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from " + dbTable + " where username='" + newUsername + "'");

		return resultSet.first();
	}	
	
	public Boolean checkCredentialsForLogin(String username, String password, String dbTable) throws SQLException {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from " + dbTable + " where username='" + username + "' and password='" + password + "'");

		return resultSet.first();
	}
	
	public void insertExample(String username, String password, String email, String dbTable) throws SQLException {
		preparedStatement = connect.prepareStatement("insert into " + dbTable + " values (?, ?, ?, ?)");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, "");		
		preparedStatement.setString(4, email);
		preparedStatement.executeUpdate();
	}
	
	public void deleteExample(String dbTable, String referinta) throws SQLException {
		preparedStatement = connect.prepareStatement("delete from " + dbTable + " where username= ? ;");
		preparedStatement.setString(1, referinta);
		preparedStatement.executeUpdate();
	}
	
	public void updateExample(String referinta, String email, String dbTable) throws SQLException {
		preparedStatement = connect.prepareStatement("update " + dbTable + " set email=? where username=?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, referinta);
		preparedStatement.executeUpdate();
	}
}