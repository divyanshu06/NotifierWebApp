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

import com.notifier.dao.UserDao;
import com.notifier.model.UserBean;

@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDao userdao;
	
	public void init()
	{
		userdao = new UserDao();
	}
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			display(request,response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void display(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException
	{
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
    		UserBean userbean = userdao.getUserDetails(userid);
			request.setAttribute("userbean",userbean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("edituser.jsp");
			dispatcher.forward(request,response);
    	}
	}
	
}
