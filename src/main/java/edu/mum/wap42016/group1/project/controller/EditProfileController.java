package edu.mum.wap42016.group1.project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap42016.group1.project.dao.UserDAO;
import edu.mum.wap42016.group1.project.model.User;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("i am hereeeeeeeee");
		// TODO Auto-generated method stub
		String name= ((User) request.getSession().getAttribute("user")).getFullName();	
		System.out.println(name);
		String state=request.getParameter("state");
		System.out.println(name);
		String email=request.getParameter("email");
		String city=request.getParameter("city");
		System.out.println(name);
		int zip=Integer.parseInt(request.getParameter("zip"));
		System.out.println(name);
		int year=Integer.parseInt(request.getParameter("year"));
		String street=request.getParameter("street");
		UserDAO myUsers= new UserDAO(this);
		myUsers.updateuser(name, state, email, city, zip, year, street);
	}

}
