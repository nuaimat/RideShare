package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Comment;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaid on 4/24/2017.
 */
public class CommentsDAO {

    HttpServlet context;

    public CommentsDAO(HttpServlet context) {
        this.context = context;

    }
    public void  creatCommment(int userid,int postid){

        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {

            String req = "INSERT INTO comments"
                    + "( userid, postid) VALUES"
                    + "(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, postid);

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println("DedicatedConnection.doPost(  ) SQLException: " +
                    e.getMessage(  ) );
        } finally {

            if (rs != null)
                try { rs.close(  ); } catch (SQLException ignore) { }
            if (statement != null)
                try { statement.close(  ); } catch (SQLException ignore) { }
        }


        // Return the conection
        CacheConnection.checkIn(connection);

    }
        




    public List<Comment> getComments(int postId,int userId){
        List<Comment> result = new ArrayList<>();

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );

        Statement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;
        try {
            // Test the connection
            statement = connection.createStatement(  );
            rs = statement.executeQuery(
                    "select * from comments where postid=postId and userid=userId order by dateupdated desc limit 20");

            while(rs.next()){
                Comment comment = new Comment();
                comment.setUserid(rs.getInt("userid"));
                comment.setPostid(rs.getInt("postid"));
                comment.setComment(rs.getString("comment"));
                Timestamp timestamp = rs.getTimestamp("datecreated");
                comment.setDatecreated(timestamp);
                Timestamp timestamp2 = rs.getTimestamp("dateupdated");
                comment.setDatecreated(timestamp2);

                result.add(comment);
            }
        }
        catch (SQLException e) {
            System.out.println("DedicatedConnection.doGet(  ) SQLException: " +
                    e.getMessage(  ) );
        }
        finally {
            if (rs != null)
                try { rs.close(  ); } catch (SQLException ignore) { }
            if (statement != null)
                try { statement.close(  ); } catch (SQLException ignore) { }
        }

        // Return the conection
        CacheConnection.checkIn(connection);

        return result;
    }


    }



