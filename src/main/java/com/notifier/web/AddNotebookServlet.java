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

@WebServlet("/addnotebook")
public class AddNotebookServlet extends HttpServlet {
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
		String name = request.getParameter("nbname");
    	HttpSession session = request.getSession();
    	int userid = (int) session.getAttribute("userid");
    	Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else { 
			try {
				if(notebookdao.insertNotebook(userid,name) == 1);
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/notebook");
					dispatcher.forward(request,response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
