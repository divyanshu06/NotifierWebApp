package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.notifier.model.Notes;
import com.notifier.utils.JDBCUtils;

public class NoteDao {
	
	public List<String> getReminder(int userid) throws SQLException{
		List<String> note = new ArrayList<>();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nname from note where reminderDate=curdate() and userid=?"))
		{
			preparedstatement.setInt(1, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				note.add(rs.getString("nname"));
				
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return note;
	}
	
	
	public int getID(String notebook_name,int user_id) throws SQLException
	{
		int notebook_id = 0;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nbid from notebook where nbname = ? AND userid = ?"))
		{
			preparedstatement.setString(1, notebook_name);
			preparedstatement.setInt(2, user_id);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				notebook_id = rs.getInt("nbid");
				
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return notebook_id;
	}
	
	public int getNotebookID(int nid,int userid) throws SQLException
	{
		int nbid = 0;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nbid from note where nid = ? AND userid = ?"))
		{
			preparedstatement.setInt(1, nid);
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
	
	public int getNoteID(String nname,int userid) throws SQLException
	{
		int nid = 0;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nid from note where nname = ? and userid = ?"))
		{
			preparedstatement.setString(1, nname);
			preparedstatement.setInt(2, userid);
			ResultSet rs = preparedstatement.executeQuery();
			if(rs.next())
			{
				nid = rs.getInt("nid");
				
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return nid;
	}

	public int getNotebookIdByNoteName(int userid, String nname) throws SQLException
	{
		int nbid = 0;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nbid from note where nname = ? AND userid = ?;"))
		{
			preparedstatement.setString(1, nname);
			preparedstatement.setInt(2, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				nbid = rs.getInt("nbid");
				System.out.println(userid);
				System.out.println(nname);
				System.out.println(nbid);
				
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return nbid;
	}
	
	public List<Notes> viewNotes(int nbid,int userid) throws SQLException
	{
		List<Notes> notes = new ArrayList<>();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select * from note where nbid = ? and userid = ?"))
		{
			preparedstatement.setInt(1, nbid);
			preparedstatement.setInt(2, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				String nname = rs.getString("nname");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				Date reminderDate = rs.getDate("reminderDate");
				String status = rs.getString("status");
				String tag = rs.getString("tag");
				String description = rs.getString("description");
				LocalDate startDate1 = ((java.sql.Date) startDate).toLocalDate();
				LocalDate endDate1 = ((java.sql.Date) endDate).toLocalDate();
				LocalDate reminder_date1 = ((java.sql.Date) reminderDate).toLocalDate();
				notes.add(new Notes(nbid,nname,startDate1,endDate1,reminder_date1,status,tag,description));
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return notes;
	}
	
	
	
	public List<Notes> viewAllNotes(int userid) throws SQLException
	{
		List<Notes> notes = new ArrayList<>();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select * from note where userid = ?"))
		{
			preparedstatement.setInt(1, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				String nname = rs.getString("nname");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				Date reminderDate = rs.getDate("reminderDate");
				String status = rs.getString("status");
				String tag = rs.getString("tag");
				String description = rs.getString("description");
				LocalDate startDate1 = ((java.sql.Date) startDate).toLocalDate();
				LocalDate endDate1 = ((java.sql.Date) endDate).toLocalDate();
				LocalDate reminderDate1 = ((java.sql.Date) reminderDate).toLocalDate();
				notes.add(new Notes(nname,startDate1,endDate1,reminderDate1,status,tag,description));
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return notes;
	}
	
	public int insertNotes(Notes notes,int userid) throws SQLException
	{
		int result = 0;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("insert into note(nbid,userid,nname,startDate,endDate,reminderDate,status,tag,description) VALUES (?,?,?,?,?,?,?,?,?)"))
		{
			preparedstatement.setInt(1,notes.getNbid());
			preparedstatement.setInt(2,userid);
			preparedstatement.setString(3,notes.getNname());
			preparedstatement.setDate(4,JDBCUtils.getSQLDate(notes.getStartDate()));
			preparedstatement.setDate(5,JDBCUtils.getSQLDate(notes.getEndDate()));
			preparedstatement.setDate(6,JDBCUtils.getSQLDate(notes.getReminderDate()));
			preparedstatement.setString(7,notes.getStatus());
			preparedstatement.setString(8,notes.getTag());
			preparedstatement.setString(9,notes.getDescription());
			
			result = preparedstatement.executeUpdate();
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return result;
	}
	
	public boolean deleteNote(int nid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM note where nid = ?");) {
			statement.setInt(1,nid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean deleteNoteByNbId(int nbid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM note where nbid = ?");) {
			statement.setInt(1,nbid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Notes displayNote(int nid,int userid) throws SQLException
	{
		Notes note = null;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM note WHERE nid = ? AND userid = ?"))
		{
			preparedstatement.setInt(1,nid);
			preparedstatement.setInt(2,userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				String nname = rs.getString("nname");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				Date reminderDate = rs.getDate("reminderDate");
				String status = rs.getString("status");
				String tag = rs.getString("tag");
				String description = rs.getString("description");
				LocalDate startDate1 = ((java.sql.Date) startDate).toLocalDate();
				LocalDate endDate1 = ((java.sql.Date) endDate).toLocalDate();
				LocalDate reminderDate1 = ((java.sql.Date) reminderDate).toLocalDate();
				note = new Notes(nname,startDate1,endDate1,reminderDate1,status,tag,description,nid);
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return note;
	}
	
	public boolean updateNote(Notes note,int userid) throws SQLException
	{
		boolean rowUpdated;
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("UPDATE note set nname=?,startDate = ?,endDate = ?,reminderDate = ?,status = ?,tag = ?,description = ? where nid = ? and userid = ?");)
		{
			preparedstatement.setString(1,note.getNname());
			preparedstatement.setDate(2,JDBCUtils.getSQLDate(note.getStartDate()));
			preparedstatement.setDate(3,JDBCUtils.getSQLDate(note.getEndDate()));
			preparedstatement.setDate(4,JDBCUtils.getSQLDate(note.getReminderDate()));
			preparedstatement.setString(5,note.getStatus());
			preparedstatement.setString(6,note.getTag());
			preparedstatement.setString(7,note.getDescription());
			preparedstatement.setInt(8,note.getNid());
			preparedstatement.setInt(9,userid);
						
			rowUpdated = preparedstatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Notes> displayAllNotes(int userid) throws SQLException
	{
		List<Notes> noteslist = new ArrayList<>();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM note WHERE userid = ?"))
		{
			preparedstatement.setInt(1, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				String nname = rs.getString("nname");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				Date reminderDate = rs.getDate("reminderDate");
				String status = rs.getString("status");
				String tag = rs.getString("tag");
				String description = rs.getString("description");
				LocalDate startDate1 = ((java.sql.Date) startDate).toLocalDate();
				LocalDate endDate1 = ((java.sql.Date) endDate).toLocalDate();
				LocalDate reminderDate1 = ((java.sql.Date) reminderDate).toLocalDate();
				int nbid = rs.getInt("nbid");
				noteslist.add(new Notes(nbid,nname,startDate1,endDate1,reminderDate1,status,tag,description));
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return noteslist;
	}
	
	public List<Notes> checkReminder(int userid,LocalDate today) throws SQLException
	{
		List<Notes> reminders = new ArrayList<>();
		try(Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement("select nid,nname,reminderDate,status from note where userid = ?"))
		{
			preparedstatement.setInt(1, userid);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next())
			{
				int nid = rs.getInt("nid");
				String nname = rs.getString("nname");
				Date reminderDate = rs.getDate("reminderDate");
				String status = rs.getString("status");
				LocalDate reminderDate1 = ((java.sql.Date) reminderDate).toLocalDate();
				if(reminderDate1.compareTo(today) == 0)
				{
					reminders.add(new Notes(nname,reminderDate1,status,nid));
				}
			}
		}
		catch(SQLException exception)
		{
			JDBCUtils.printSQLException(exception);
		}
		return reminders;
	}
	
	
}
