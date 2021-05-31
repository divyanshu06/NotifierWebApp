package com.notifier.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.LoginDao;
import com.notifier.model.LoginBean;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			authenticate(request, response);
		} catch (ClassNotFoundException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String uName = loginDao.fetchUname(username);
        System.out.println("Username is " + uName);
        LoginBean loginBean = new LoginBean();
        
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
            	HttpSession session = request.getSession();
				session.setAttribute("user", uName);
				int userid = loginDao.getUserID(username);
				session.setAttribute("userid",userid);
				//response.sendRedirect("/home");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
                dispatcher.forward(request, response);
            } else {
            	RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			    rd.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
