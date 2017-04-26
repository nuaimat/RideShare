package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Comment;
import edu.mum.wap42016.group1.project.util.CacheConnection;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zaid on 4/24/2017.
 */
public class CommentsDAO {

    HttpServlet context;

    public CommentsDAO(HttpServlet context) {
        this.context = context;

    }
    public void creatCommment(int userid,int postid, String comment){

        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {

            String req = "INSERT INTO comments"
                    + "(userid, postid, comment) VALUES"
                    + "(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, postid);
            preparedStatement.setString(3, comment);

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
        




    public List<Comment> getComments(int postId){
        List<Comment> result = new ArrayList<>();

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );

        PreparedStatement preparedStatement  = null;
        ResultSet rs  = null;
        String     userName   = null;
        try {
            // Test the connection
            String query = "select * from comments where postid=? order by dateupdated desc";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);

            rs = preparedStatement.executeQuery();

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
            System.out.println("CommentsDAO.getComments(  ) SQLException: " +
                    e.getMessage(  ) );
        }
        finally {
            if (rs != null)
                try { rs.close(  ); } catch (SQLException ignore) { }
            if (preparedStatement != null)
                try { preparedStatement.close(  ); } catch (SQLException ignore) { }
        }

        // Return the conection
        CacheConnection.checkIn(connection);

        return result;
    }


    public HashMap<Integer, List<Comment>> getComments(ArrayList<Integer> postIds){
        HashMap<Integer, List<Comment>> result = new HashMap<>();

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );

        PreparedStatement preparedStatement = null;
        ResultSet rs  = null;
        try {
            String query = "select * from comments where postid in (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, StringUtils.join(postIds, ","));
            System.out.println("CommentsDAO.getComments " + preparedStatement);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                int postId = rs.getInt("postid");

                Comment comment = new Comment();
                comment.setUserid(rs.getInt("userid"));
                comment.setPostid(postId);
                comment.setComment(rs.getString("comment"));
                Timestamp timestamp = rs.getTimestamp("datecreated");
                comment.setDatecreated(timestamp);
                Timestamp timestamp2 = rs.getTimestamp("dateupdated");
                comment.setDatecreated(timestamp2);

                System.out.println("Adding comment " + comment );
                if(!result.containsKey(postId)){
                    result.put(postId, new ArrayList<Comment>());
                }

                ((ArrayList<Comment>) result.get(postId)).add(comment);
            }
        }
        catch (SQLException e) {
            System.out.println("CommentsDAO.getComments() SQLException: " +
                    e.getMessage(  ) );
        }
        finally {
            if (rs != null)
                try { rs.close(  ); } catch (SQLException ignore) { }
            if (preparedStatement != null)
                try { preparedStatement.close(  ); } catch (SQLException ignore) { }
        }

        // Return the conection
        CacheConnection.checkIn(connection);

        return result;
    }

    }



