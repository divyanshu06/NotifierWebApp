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

@WebServlet("/updateuser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDao userdao;
	
	public void init() {
		userdao = new UserDao();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			saveDetails(request,response);
		} catch (IOException | SQLException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveDetails(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, ServletException
	{
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			String username = request.getParameter("username");
			Long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
	
				UserBean userbean = new UserBean();
				userbean.setUsername(username);
				userbean.setMobileNumber(mobileNumber);
				userbean.setEmail(email);
				userbean.setPassword(password);
				
				if(userdao.updateUser(userid,userbean))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("home");
					dispatcher.forward(request,response);
					
				}
    	}
	}
	
}
