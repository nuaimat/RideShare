package edu.mum.wap42016.group1.project.controller;

/**
 * Created by zaid on 4/24/2017.
 */

import edu.mum.wap42016.group1.project.dao.LikesDAO;
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


    //TODO change to actual user id and postid
   private int userid = 1;
   private int postid = 1;


    @Override
    public void init() throws ServletException {
        Connection connection = CacheConnection.checkOut( this ); // just to cache it
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        LikesDAO likes= new LikesDAO(this);
        likes.addLikes(userid, postid);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


    }
}
