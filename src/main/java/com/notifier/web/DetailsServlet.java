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
@WebServlet("/details")
public class DetailsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private NoteDao notesdao;
	
	public void init()
	{
		notesdao = new NoteDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		displayNotes(request, response);
	}
	
	private void displayNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nname = request.getParameter("note");
		int nbid = 0;
		Notes details = null;
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request,response);
    	} else {
			try {
				nbid = notesdao.getNoteID(nname,userid);
				details = notesdao.displayNote(nbid,userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("details",details);
			RequestDispatcher dispatcher = request.getRequestDispatcher("details.jsp");
			dispatcher.forward(request,response);
		}
	}

}
