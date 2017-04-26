package edu.mum.wap42016.group1.project.controller;

import edu.mum.wap42016.group1.project.dao.UserDAO;
import edu.mum.wap42016.group1.project.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zaid on 4/26/2017.
 */
@WebServlet(name = "WeatherController")
public class WeatherController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO(this);
        User user = userDAO.getCurrentUser(req);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/weather.jsp").forward(req, res);
    }
}
