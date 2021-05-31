package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.NoteDao;
import com.notifier.model.Notes;

@WebServlet("/editnote")
public class EditNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoteDao notesdao;
	
	public void init() {
		notesdao = new NoteDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			editNote(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		String nname = request.getParameter("note");;
		int nid = 0;
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
	    	try {
				nid = notesdao.getNoteID(nname,userid);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	Notes note = new Notes();
	    	try {
				note = notesdao.displayNote(nid,userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//System.out.println(note.getNote_name());
	    	//System.out.println(note.getNote_id());	
	    	request.setAttribute("note",note);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("editnote.jsp");
	    	dispatcher.forward(request, response);

	}
}
}
