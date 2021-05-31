package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.notifier.model.LoginBean;
import com.notifier.utils.JDBCUtils;


public class LoginDao {
	
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        String Query = "select * from users where email = ? and password = ? ";
        try (Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement(Query)) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
	
	public int getUserID(String username) throws ClassNotFoundException
	{
		int userid = 0;
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select id from users where email = ?")) {
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				userid = rs.getInt("id");
			}

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return userid;	

	}

	public String fetchUname(String email) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String username = null;
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select username from users where email = ?")) {
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				username = rs.getString("username");
			}

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return username;	


	}

	
}
