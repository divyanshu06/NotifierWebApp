package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/allnotes")
public class AllNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoteDao notedao;
	
	public void init()
	{
		notedao = new NoteDao();
	}
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		displayAllNotes(request, response);
	}
	
	private void displayAllNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notes> notes = new ArrayList<>();
		HttpSession session = request.getSession();
    	int userid = (int) session.getAttribute("userid");
    	Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			try {
				notes = notedao.displayAllNotes(userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			request.setAttribute("notes",notes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("allnotes.jsp");
			dispatcher.forward(request,response);
    	}
	}

}
