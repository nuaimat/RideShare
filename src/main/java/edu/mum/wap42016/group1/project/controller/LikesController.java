package edu.mum.wap42016.group1.project.controller;

/**
 * Created by zaid on 4/24/2017.
 */

import edu.mum.wap42016.group1.project.dao.LikesDAO;
import edu.mum.wap42016.group1.project.dao.UserDAO;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@WebServlet(name = "LikesController")
public class LikesController extends HttpServlet {



    @Override
    public void init() throws ServletException {
        Connection connection = CacheConnection.checkOut( this ); // just to cache it
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int res;
        int postid  = Integer.parseInt(request.getParameter("postid"));
        UserDAO userDAO = new UserDAO(this);
        LikesDAO likes= new LikesDAO(this);

        if(request.getParameter("action").equals("dislike")){
            res = likes.removeLikes(userDAO.getCurrentUserId(request), postid);
        } else {
            res = likes.addLikes(userDAO.getCurrentUserId(request), postid);
        }


        response.setContentType("application/json");
        response.getWriter().println("{\"result\" :" + res + "}");

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


    }
}
