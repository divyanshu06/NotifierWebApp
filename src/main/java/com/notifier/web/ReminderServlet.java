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

import com.notifier.dao.NoteDao;

/**
 * Servlet implementation class ReminderServlet
 */
@WebServlet("/reminder")
public class ReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoteDao notedao;
	
	public void init() {
		notedao = new NoteDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			getReminders(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getReminders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String> note = new ArrayList<>();
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request,response);
    	} else {
    		note = notedao.getReminder(userid);
    		request.setAttribute("note",note);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reminder.jsp");
			dispatcher.forward(request, response);
    	}
	}
	
}
