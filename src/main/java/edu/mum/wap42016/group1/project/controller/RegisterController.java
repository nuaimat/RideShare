package edu.mum.wap42016.group1.project.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap42016.group1.project.dao.UserDAO;
import edu.mum.wap42016.group1.project.model.User;

/**
 * Servlet implementation class RegisterController
 */
//@WebServlet("/registerController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hi samiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		if(request.getAttribute("edit")!=null){
			request.getRequestDispatcher("editProfiler.jsp").forward(request, response);	
		}
		else{
		request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	System.out.println("hi samiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
	String name= request.getParameter("name");
	String password= request.getParameter("password");
	String state=request.getParameter("state");
	String email=request.getParameter("email");
	int sex=request.getParameter("sex").equals("on")?0:1;
	String city=request.getParameter("city");
	int zip=Integer.parseInt(request.getParameter("zip"));
	int year=Integer.parseInt(request.getParameter("year"));
	String street=request.getParameter("street");
//	User user= new User(name,sex,state,city,street,email,password,year,zip);
	UserDAO myUsers= new UserDAO(this);
	myUsers.addUser(name,sex,state,city,street,email,password,year,zip);	
	response.sendRedirect("home.jsp");
	}

}
