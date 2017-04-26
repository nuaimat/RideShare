package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Comment;
import edu.mum.wap42016.group1.project.model.User;
import edu.mum.wap42016.group1.project.util.CacheConnection;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by zaid on 4/24/2017.
 */
public class CommentsDAO {

    HttpServlet context;

    public CommentsDAO(HttpServlet context) {
        this.context = context;

    }
    public Comment createCommment(HttpServletRequest request, UserDAO userDAO, int postid, String commentText){


        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setDatecreated(new Date());
        comment.setUserid(userDAO.getCurrentUserId(request));
        comment.setPostid(postid);

        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {

            String req = "INSERT INTO comments"
                    + "(userid, postid, comment, datecreated) VALUES"
                    + "(?,?,?, NOW())";

            PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, comment.getUserid());
            preparedStatement.setInt(2, comment.getPostid());
            preparedStatement.setString(3, comment.getComment());
            System.out.printf("CommentsDAO.creatCommment " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                comment.setCommentid(rs.getInt(1));
            }

            comment.setUser(userDAO.getCurrentUser(request));

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
        if(comment.getCommentid() != 0)
            return comment;
        return null;

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

        if(postIds.size() < 1){
            return result;
        }

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );

        PreparedStatement preparedStatement = null;
        ResultSet rs  = null;
        try {
            System.out.println("postIds: " + StringUtils.join(postIds, ","));
            StringBuilder idList = new StringBuilder();
            for (int id : postIds) {
                if (idList.length() > 0) {
                    idList.append(",");
                }
                idList.append("?");
            }

            String query = "select c.*,u.* from comments c INNER JOIN  users u on c.userid = u.userid where c.postid in ("+idList+")";
            System.out.println("CommentsDAO.getComments " + query);
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < postIds.size(); i++) {
                preparedStatement.setInt(i+1, postIds.get(i));
            }


            System.out.println("CommentsDAO.getComments " + preparedStatement);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                int postId = rs.getInt("c.postid");

                Comment comment = new Comment();
                comment.setUserid(rs.getInt("c.userid"));
                comment.setPostid(postId);
                comment.setComment(rs.getString("c.comment"));
                Timestamp timestamp = rs.getTimestamp("c.datecreated");
                comment.setDatecreated(timestamp);
                Timestamp timestamp2 = rs.getTimestamp("c.dateupdated");
                comment.setDatecreated(timestamp2);

                User u = new User();
                u.setEmail(rs.getString("u.email"));
                u.setFullName(rs.getString("u.fullname"));
                u.setUserid(comment.getUserid());
                comment.setUser(u);

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



