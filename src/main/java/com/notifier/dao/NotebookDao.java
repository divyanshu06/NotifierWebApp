package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.notifier.model.Notebook;
import com.notifier.utils.JDBCUtils;

public class NotebookDao {
	
	private static final String insertNotebookSQL = "INSERT INTO notebook (userid,nbname) VALUES (?,?)";
	private static final String selectNotebooks = "SELECT * FROM notebook WHERE userid = ?";
	private static final String deleteNotebookSQL = "DELETE FROM notebook WHERE userid = ? AND nbname = ? ";
	private static final String updateNotebookSQL = "UPDATE notebook SET nbname = ? WHERE userid = ? AND nbname = ?";
	
	public String getNotebookNameByNbId(int userid,int nbid) throws SQLException
	{
		String nbname = new String();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nbname from notebook where nbid = ? AND userid = ?"))
		{
			preparedstatement.setInt(1, nbid);
			preparedstatement.setInt(2, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				nbname = rs.getString("nbname");
				
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return nbname;
	}
	
	public int getNotebookIdByNbName(int userid,String nbname) throws SQLException
	{
		int nbid = 0;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nbid from notebook where nbname = ? AND userid = ?"))
		{
			preparedstatement.setString(1, nbname);
			preparedstatement.setInt(2, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				nbid = rs.getInt("nbid");
				
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return nbid;
	}
	
	public int insertNotebook(int userid,String nbName) throws SQLException{
		
		int result = 0;
		
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertNotebookSQL)) {

			preparedStatement.setInt(1,userid);
			preparedStatement.setString(2,nbName);
			
			result = preparedStatement.executeUpdate();
			
		} catch(SQLException ex) {
			JDBCUtils.printSQLException(ex);
		}
		return result;
	}
	
	public List<String> listNotebooks(int userid) throws SQLException
	{
		List<String> notebooks = new ArrayList<>();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(selectNotebooks);)
		{
			preparedstatement.setInt(1,userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				String notebook_name = rs.getString("nbname");
				notebooks.add(notebook_name);
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return notebooks;
	}
	
	public boolean deleteNotebook(int userid,String nbname) throws SQLException {
		boolean rowDeleted;		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteNotebookSQL);) {

			statement.setInt(1,userid);
			statement.setString(2,nbname);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updateNotebook(String oldName, String newName, int userid) throws SQLException
	{
		boolean rowUpdated;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(updateNotebookSQL);)
		{
			preparedstatement.setString(1,newName);
			preparedstatement.setInt(2,userid);
			preparedstatement.setString(3,oldName);
						
			rowUpdated = preparedstatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
}
