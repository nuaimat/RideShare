package edu.mum.wap42016.group1.project.controller;

import edu.mum.wap42016.group1.project.dao.CommentsDAO;
import edu.mum.wap42016.group1.project.dao.JSONCommentsConvertor;
import edu.mum.wap42016.group1.project.model.Comment;
import edu.mum.wap42016.group1.project.model.User;
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
        int postid=Integer.parseInt(req.getParameter("postid"));

        String commenttext=req.getParameter("comment");
        CommentsDAO comments = new CommentsDAO(this);
        List<Comment> currentComments = comments.getComments(postid);
        req.setAttribute("comments",currentComments);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commenttext=req.getParameter("comment");
        int postid=Integer.parseInt(req.getParameter("postid"));

        User u = (User) req.getSession().getAttribute("user");

        CommentsDAO comments= new CommentsDAO(this);
        Comment comment= comments.creatCommment(u.getUserid(),postid,commenttext);


        JSONCommentsConvertor convertor= new JSONCommentsConvertor();
        String  jsoncomment = convertor.createCommentJSON(comment);

        //TO DO identify which attribute in the JSP needs the json Stringfied comment
        /*
      if (comment != null){
        req.setAttribute("comment", jsoncomment);
        req.getRequestDispatcher("/rides/ride_comments.jsp").forward(req, resp);
    } else {
        resp.getWriter().print("");
    }*/


    }


}
