package com.notifier.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.NoteDao;
import com.notifier.model.Notes;


@WebServlet("/insertnote")
public class InsertNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoteDao notedao;
	
	public void init()
	{
		notedao = new NoteDao();
	}
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String note_name = request.getParameter("nname");
		String start_date = request.getParameter("startDate");
		String end_date = request.getParameter("endDate");
		String reminder_date = request.getParameter("reminderDate");
		LocalDate start_date1 = LocalDate.parse(start_date, formatter);
		LocalDate end_date1 = LocalDate.parse(end_date, formatter);
		LocalDate reminder_date1 = LocalDate.parse(reminder_date, formatter);
		String status = request.getParameter("status");
		String tag = request.getParameter("tag");
		String description = request.getParameter("description");
		String notebook_name = request.getParameter("nb");
		
		int notebook_id = 0;
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			try {
				notebook_id = notedao.getID(notebook_name,userid);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Notebook id is" + notebook_id); 
			
			Notes notes = new Notes(notebook_id,note_name,start_date1,end_date1,reminder_date1,status,tag,description);
			try {
				int result = notedao.insertNotes(notes,userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("notes",notes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("notes");
			dispatcher.forward(request, response);
			}
	}
	
	public void insertNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String nname = request.getParameter("nname");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String reminderDate = request.getParameter("reminderDate");
		LocalDate startDate1 = LocalDate.parse(startDate, formatter);
		LocalDate endDate1 = LocalDate.parse(endDate, formatter);
		LocalDate reminderDate1 = LocalDate.parse(reminderDate, formatter);
		String status = request.getParameter("status");
		String tag = request.getParameter("tag");
		String description = request.getParameter("description");
		String nbname = request.getParameter("nb");
		
		int nbid = 0;
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		if(userid == 0)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	}
		else
		{
			nbid = notedao.getNotebookIdByNoteName(userid, nname);
		
		/*PrintWriter out = response.getWriter();
		out.println(nbid);
		out.println(nbname);
		out.println(userid);
		out.println(nname);
		*/
		Notes note = new Notes(nbid,nname,startDate1,endDate1,reminderDate1,status,tag,description);
		try {
			int result = notedao.insertNotes(note,userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("note",note);
		RequestDispatcher dispatcher = request.getRequestDispatcher("notes");
		dispatcher.forward(request, response);
		}


	}

}
