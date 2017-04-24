package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Ride;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.http.HttpServlet;
import java.sql.*;

/**
 * Created by zaid on 4/24/2017.
 */
public class LikesDAO {
    HttpServlet context;

    public LikesDAO(HttpServlet context) {
        this.context = context;
    }

    public void addLikes(int likedid, int userid, int postid, Date datecreated, Date dateupdated) throws SQLException {

        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        Statement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {



            String req = "INSERT INTO Likes"
                    + "(likeid, userid, postid, datecreated,dateupdated) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, likedid);
            preparedStatement.setInt(2, userid);
            preparedStatement.setInt(3, postid);
            preparedStatement.setTimestamp(4, new Timestamp(datecreated.getTime()));
            preparedStatement.setTimestamp(5, new Timestamp(dateupdated.getTime()));
// execute insert SQL stetement
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
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

}
