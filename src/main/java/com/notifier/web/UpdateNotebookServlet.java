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

import com.notifier.dao.NotebookDao;

@WebServlet("/updatenotebook")
public class UpdateNotebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NotebookDao notebookdao;
	
	public void init() {
		notebookdao = new NotebookDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			updateNotebook(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateNotebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String oldName = request.getParameter("nbname");
    	String newName = request.getParameter("updatednbname");
    	HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			boolean result = notebookdao.updateNotebook(oldName,newName,userid);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("notebook");
	    	dispatcher.forward(request, response);
    	}
	}
}
