package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Ride;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
public class RidesDAO {
    HttpServlet context;
    public RidesDAO(HttpServlet context) {
        this.context = context;
    }


    public List<Ride> getTrips(int from){
        List<Ride> result = new ArrayList<>();

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );

        Statement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;
        try {
            // Test the connection
            statement = connection.createStatement( );
            rs = statement.executeQuery(
                    "select * from posts order by dateupdated desc limit 30");

            while(rs.next()){
                System.out.println("new entry ---  <br />");
                Ride ride = new Ride();
                Timestamp timestamp = rs.getTimestamp("datecreated");
                ride.setDateCreated(timestamp);
                //ride.setDest(new Location(rs.)); // TODO check later
                //ride.setSrc(); // TODO check later
                ride.setPost(rs.getString("post"));
                ride.setPostid(rs.getInt("postid"));
                ride.setPosttype(rs.getInt("posttype") == 0? Ride.RideType.OFFERED : Ride.RideType.ASKED);
                ride.setUserid(rs.getInt("userid"));
                result.add(ride);
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
