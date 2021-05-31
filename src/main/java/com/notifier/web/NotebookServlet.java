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
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.notifier.dao.NotebookDao;

@WebServlet("/notebook")
public class NotebookServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private NotebookDao notebookdao;
	
	public void init() {
		notebookdao = new NotebookDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			displayNotebooks(request,response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void displayNotebooks(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException
	{
		List<String> notebooks = new ArrayList<>();
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			notebooks = notebookdao.listNotebooks(userid);
			request.setAttribute("notebooks",notebooks);
			RequestDispatcher dispatcher = request.getRequestDispatcher("notebooks.jsp");
			dispatcher.forward(request,response);
    	}
	}
}
