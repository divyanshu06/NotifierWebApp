package com.notifier.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.UserDao;
import com.notifier.model.UserBean;


@WebServlet("/signup")
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserDao userdao;
	
	public void init() {
		userdao = new UserDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        register(request, response);
		    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		        response.sendRedirect("index.jsp");
		    }
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String username = request.getParameter("username");
        long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");	
        
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setMobileNumber(mobileNumber);
        userBean.setEmail(email);
        userBean.setPassword(password);

        try {
            int result = userdao.registerUser(userBean);
            if (result == 1) {
                request.setAttribute("successmsg", "Great! You are one of our members now");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
