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

            StringBuilder idList = new StringBuilder();
            for (int id : postids) {
                if (idList.length() > 0) {
                    idList.append(",");
                }
                idList.append("?");
            }
            String que= "select * from likes where postid in ("+idList+")";

            PreparedStatement preparedStatement = connection.prepareStatement(que);
            for (int i = 0; i < postids.size(); i++) {
                preparedStatement.setInt(i+1, postids.get(i));
            }
            rs = statement.executeQuery(que);
            System.out.println(que);

            while(rs.next()){
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
            System.out.println("LikesDao.getLikes(  ) SQLException: " +
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
