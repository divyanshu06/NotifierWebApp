package com.notifier.dao;

import java.sql.*;
import com.notifier.model.UserBean;
import com.notifier.utils.JDBCUtils;

public class UserDao {
	
	public int registerUser(UserBean userBean) throws ClassNotFoundException{
		int result=0;
		String insertUsers = "INSERT INTO users (username,mobileNumber,email,password) VALUES (?,?,?,?);";
		try(Connection con = JDBCUtils.getConnection();
				PreparedStatement stmt = con.prepareStatement(insertUsers)){
			stmt.setString(1, userBean.getUsername());
			stmt.setLong(2, userBean.getMobileNumber());
			stmt.setString(3,userBean.getEmail());
			stmt.setString(4, userBean.getPassword());
			
			System.out.println(stmt);
			result=stmt.executeUpdate();
		
		} catch(SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
	
	public UserBean getUserDetails(int userid) throws ClassNotFoundException, SQLException
	{
		UserBean userbean = null;
		
		String getuserdetail = "select * from users where id = ?";
		
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(getuserdetail))
		{
			preparedstatement.setInt(1,userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				String username = rs.getString("username");
				long mobileNumber = rs.getLong("mobileNumber");
				String email = rs.getString("email");
				String password = rs.getString("password");
				userbean = new UserBean(username,mobileNumber,email,password);
			}
		}
		catch(SQLException e)
		{
			JDBCUtils.printSQLException(e);
		}
		return userbean;
	}
	
	public boolean updateUser(long id,UserBean userbean) throws SQLException
	{
		boolean rowUpdated;
		String update = "update users set username = ?,mobileNumber = ?, email = ?,password = ? where id = ?;";
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(update);)
		{
			preparedstatement.setString(1,userbean.getUsername());
			preparedstatement.setLong(2,userbean.getMobileNumber());
			preparedstatement.setString(3,userbean.getEmail());
			preparedstatement.setString(4,userbean.getPassword());
			preparedstatement.setLong(5,id);
			
			rowUpdated = preparedstatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
}
