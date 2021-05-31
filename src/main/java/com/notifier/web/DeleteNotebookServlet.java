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
import com.notifier.dao.NotebookDao;

@WebServlet("/deletenotebook")
public class DeleteNotebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NotebookDao notebookdao;
	private NoteDao notedao;
	
	public void init() {
		notebookdao = new NotebookDao();
		notedao = new NoteDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			deleteNotebook(request, response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteNotebook(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException
	{
		String nbName = request.getParameter("nb");
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			notedao.deleteNoteByNbId(notedao.getID(nbName,userid));
			notebookdao.deleteNotebook(userid,nbName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("notebook");
			dispatcher.forward(request,response);
    	}
	}
	
}
