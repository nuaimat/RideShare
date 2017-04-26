package edu.mum.wap42016.group1.project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap42016.group1.project.dao.UserDAO;

/**
 * Servlet implementation class LogInController
 */
//@WebServlet("/login")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("it is post log in");
		boolean isValid;
		String userName =  request.getParameter("email");
		String password =  request.getParameter("password");
		UserDAO user= new UserDAO(this);
		isValid= user.validate(userName, password,request, response);
		if(isValid){
			response.sendRedirect(request.getContextPath() + "/");
		}
		else{
			response.sendRedirect(request.getContextPath() + "/login?msg=Wrong username or password");
		}
		
	}

}
