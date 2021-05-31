package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.NoteDao;
import com.notifier.model.Notes;


@WebServlet("/updatenote")
public class UpdateNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDao notedao;
	
	public void init()
	{
		notedao = new NoteDao();
	}
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		updateNote(request, response);
	}
	
	private void updateNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int nid = Integer.parseInt(request.getParameter("nid"));
		
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	}
		else
		{
		
		int nbid = 0;
		try {
			nbid = notedao.getNotebookID(nid, userid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Notes note = new Notes(nbid,nid,nname,startDate1,endDate1,reminderDate1,status,tag,description);
		List<Notes> notes = new ArrayList<>();
		
		try {
			boolean result = notedao.updateNote(note,userid);
			notes = notedao.viewNotes(nbid,userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		request.setAttribute("notes",notes);
		request.setAttribute("nbid",nbid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("notes.jsp");
		dispatcher.forward(request, response);
		}
	}

}
