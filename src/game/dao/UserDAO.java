package game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import game.model.*;
import game.util.DBUtil;

public class UserDAO {
	
	private Connection connection;
	
	public UserDAO() {
		connection = DBUtil.getConnection();
	}
	
	public void addUser(User user) {
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(
						"insert into users(firstname,lastname,dob,email,password) values (?,?,?,?,?)");
			// Parameters start with 1
			preparedStatement.setString(1,user.getFirstName());
			preparedStatement.setString(2,user.getLastName());
			preparedStatement.setDate(3,new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4,user.getEmail());
			preparedStatement.setString(5,user.getPassword());
			preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleleUser(int userId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from users where userid = ?");
			preparedStatement.setInt(1,userId);
			// Parameters start with 1
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void updateUser(User user) {
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(
						"update users set firstname=?,lastment=?,dob=?,email=?,password=?"
							+ "where userid=?");
			// Parameters start with 1
			preparedStatement.setString(1,user.getFirstName());
			preparedStatement.setString(2,user.getLastName());
			preparedStatement.setDate(3,new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4,user.getEmail());
			preparedStatement.setString(5,user.getPassword());
			preparedStatement.setInt(6, user.getUserid());
			preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<User> geAlltUsers()
	{
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			while(rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserById(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean checkUser(String email) {
		boolean res = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;	
	}
	
	public int checkUser(String email,String password) {
		int userId=0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(password))
					userId = rs.getInt("userid");
				else
					userId = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;	
	}
	
	
}
