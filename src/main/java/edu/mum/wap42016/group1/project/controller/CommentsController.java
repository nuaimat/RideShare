package edu.mum.wap42016.group1.project.controller;

import edu.mum.wap42016.group1.project.dao.CommentsDAO;
import edu.mum.wap42016.group1.project.model.Comment;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by zaid on 4/25/2017.
 */

@WebServlet(name="CommentsController")
public class CommentsController  extends HttpServlet{
    //TODO change to actual user id and postid from req
    private int userid = 1;
    private int postid = 1;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        Connection connection = CacheConnection.checkOut( this ); // just to cache it
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentsDAO comments = new CommentsDAO(this);
        List<Comment> currentComments = comments.getComments(postid,userid);

        req.setAttribute("comments",currentComments);

        //TODO
        //req.getRequestDispatcher("/").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
