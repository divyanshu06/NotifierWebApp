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

@WebServlet("/deletenote")
public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoteDao notesdao;
	private NotebookDao notebookdao;
	
	public void init() {
		notesdao = new NoteDao();
		notebookdao = new NotebookDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			deleteNote(request,response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		String nname = request.getParameter("note");
		int nbid=0;
		String nbname = new String();
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		Object userSession = session.getAttribute("userid");
		if(userSession == null)
    	{
    		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request,response);
    	} else {
			try {
				nbid = notesdao.getNotebookIdByNoteName(userid, nname);
				nbname = notebookdao.getNotebookNameByNbId(userid, nbid);
				notesdao.deleteNote(notesdao.getNoteID(nname,userid));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("nbname",nbname);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewnotesafterdeleting");
			dispatcher.forward(request, response);
    	}
	}

}
