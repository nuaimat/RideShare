package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Like;
import edu.mum.wap42016.group1.project.util.CacheConnection;


import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaid on 4/24/2017.
 */
public class LikesDAO {
    HttpServlet context;

    public LikesDAO(HttpServlet context) {
        this.context = context;
    }

    public void addLikes( int userid, int postid)  {

        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {

            String req = "INSERT INTO Likes"
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

    public List<Like> getLikes(List<Integer> postids){

       // TO DO with likes

        List<Like> result = new ArrayList<>();

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );

        Statement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;
        try {
            // Test the connection
            statement = connection.createStatement();

            String que= "select * from likes where postid in (?);\n";

            PreparedStatement preparedStatement = connection.prepareStatement(que);
            preparedStatement.setArray(1, (Array) postids);
            rs = statement.executeQuery(que);


            while(rs.next()){
                System.out.println("new entry ---  <br />");
                Like like = new Like();
                Timestamp timestamp = rs.getTimestamp("datecreated");
                like.setDatecreated(timestamp);
                Timestamp timestamp2 = rs.getTimestamp("dateupdated");
                like.setDateupdated(timestamp2);
                like.setLikeid(rs.getInt("likedid"));
                like.setPostid(rs.getInt("postid"));

                result.add(like);
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
