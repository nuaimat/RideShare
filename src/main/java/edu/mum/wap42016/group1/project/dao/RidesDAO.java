package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Location;
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


    public List<Ride> getRides(int from){
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
            statement = connection.createStatement();
            rs = statement.executeQuery(
                    "select *, AsText(src) as srctxt, AsText(dest) as desttxt from posts order by dateupdated desc limit 30");

            while(rs.next()){
                System.out.println("new entry ---  <br />");
                Ride ride = new Ride();
                Timestamp timestamp = rs.getTimestamp("datecreated");
                ride.setDateCreated(timestamp);
                ride.setSrcHumanReadable(rs.getString("srcReadable"));
                ride.setDestHumanReadable(rs.getString("destReadable"));
                ride.setPost(rs.getString("post"));
                ride.setPostid(rs.getInt("postid"));
                ride.setPosttype(rs.getInt("posttype") == 0? Ride.RideType.OFFERED : Ride.RideType.ASKED);
                ride.setUserid(rs.getInt("userid"));
                ride.setSrc(Location.parseMysqlSpatialFormat(rs.getString("srctxt")));
                ride.setDest(Location.parseMysqlSpatialFormat(rs.getString("desttxt")));
                System.out.println(rs.getString("srctxt"));
                // TODO set loc
                // ride.setDest();
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


    public boolean save(Ride r, int userid){
        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        int last_inserted_id = -1;

        try {

            String req = "INSERT INTO posts"
                    + "(userid, post, posttype, srcReadable, destReadable, src, dest, datecreated) VALUES"
                    + "(?, ?, ?, ?, ?, GeomFromText(?),GeomFromText(?), NOW() )";

            PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, r.getPost());
            preparedStatement.setInt(3, r.getPosttype().typeNum);
            preparedStatement.setString(4, r.getSrcHumanReadable());
            preparedStatement.setString(5, r.getDestHumanReadable());
            preparedStatement.setString(6, r.getSrc().toMysqlSpatialFormat() );
            preparedStatement.setString(7, r.getDest().toMysqlSpatialFormat() );
            System.out.println(preparedStatement.toString());

            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                last_inserted_id = rs.getInt(1);
                System.out.println("inserted with id " + last_inserted_id);
            }

        } catch (SQLException e) {
            System.out.println("RidesDAO.save() SQLException: " +
                    e.getMessage(  ) );
        } finally {

            if (rs != null)
                try { rs.close(  ); } catch (SQLException ignore) { }
            if (statement != null)
                try { statement.close(  ); } catch (SQLException ignore) { }
        }


        // Return the conection
        CacheConnection.checkIn(connection);

        return last_inserted_id != -1;
    }
}
