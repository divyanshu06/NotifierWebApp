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

import com.notifier.model.Notes;
import com.notifier.dao.NoteDao;
import com.notifier.dao.NotebookDao;

@WebServlet("/viewnotesafterdeleting")
public class ViewNotesAfterDeletingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDao notesdao;
	private NotebookDao notebookdao;
	
	public void init()
	{
		notesdao = new NoteDao();
		notebookdao = new NotebookDao();
	}
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String nbname = request.getAttribute("nbname").toString();
    	List<Notes> notes = new ArrayList<>();
    	int nbid=0;
    	HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
	    	System.out.println("user id " + userid);
	    	try {
	    		nbid = notebookdao.getNotebookIdByNbName(userid,nbname);
				notes = notesdao.viewNotes(nbid,userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	request.setAttribute("notes", notes);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("notes.jsp");
	    	dispatcher.forward(request,response);
    	}
    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
