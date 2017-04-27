package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Comment;
import edu.mum.wap42016.group1.project.model.Location;
import edu.mum.wap42016.group1.project.model.Ride;
import edu.mum.wap42016.group1.project.model.User;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.*;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
public class RidesDAO {
    HttpServlet context;
    public RidesDAO(HttpServlet context) {
        this.context = context;
    }


    public List<Ride> getRides(Set<Integer> rideIds, int currentUserId){
        HashMap<Integer, Ride> result = new HashMap<>();
        List<Integer> postIds = new ArrayList<>(rideIds);

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
            String query = "select p.*, u.*, AsText(p.src) as srctxt, AsText(p.dest) as desttxt from posts p " +
                    "INNER JOIN users u on p.userid = u.userid " +
                    "order by p.dateupdated desc";
            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next()){
                int postid = rs.getInt("p.postid");
                Ride ride = new Ride();
                Timestamp timestamp = rs.getTimestamp("p.datecreated");
                ride.setDateCreated(timestamp);
                ride.setSrcHumanReadable(rs.getString("p.srcReadable"));
                ride.setDestHumanReadable(rs.getString("p.destReadable"));
                ride.setPost(rs.getString("p.post"));
                ride.setPostid(postid);
                ride.setPosttype(rs.getInt("p.posttype") == 0? Ride.RideType.OFFERED : Ride.RideType.ASKED);
                ride.setUserid(rs.getInt("p.userid"));
                ride.setSrc(Location.parseMysqlSpatialFormat(rs.getString("srctxt")));
                ride.setDest(Location.parseMysqlSpatialFormat(rs.getString("desttxt")));

                User u = new User();
                u.setFullName(rs.getString("u.fullname"));
                u.setUserid(rs.getInt("u.userid"));
                ride.setUser(u);


                result.put(postid, ride);
            }
        }
        catch (SQLException e) {
            System.out.println("RidesDAO.getRides(  ) SQLException: " +
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

        CommentsDAO commentsDAO = new CommentsDAO(context);
        HashMap<Integer, List<Comment>> allComments = commentsDAO.getComments((ArrayList<Integer>) postIds); // TODO should be user id from session
        LikesDAO likesDAO = new LikesDAO(context);
        HashMap<Integer, Map.Entry<Integer, Boolean>> allLikes = likesDAO.getLikes((ArrayList<Integer>) postIds, currentUserId);
        System.out.println("Found " + result.size() + " rides");
        System.out.println("allComments: " + allComments);
        List<Ride> ret = new ArrayList<>();
        for(Integer pid:result.keySet()){
            Ride ride = result.get(pid);
            if(allComments.containsKey(pid)){
                ride.setCommentList(allComments.get(pid));
            }

            if(allLikes.containsKey(pid)){
                Map.Entry<Integer, Boolean> likesEntry = allLikes.get(pid);
                ride.setLikedByCurrentUser(likesEntry.getValue());
                ride.setLikesCount(likesEntry.getKey());
            }
            ret.add(ride);
        }

        Collections.sort(ret);

        return ret;
    }

    public List<Ride> getRides(int page, int currentUserId){
        HashMap<Integer, Ride> result = new HashMap<>();
        List<Integer> postIds = new ArrayList<>();
        int row_count = 5;
        int offset = (page - 1)*row_count ;


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
            String query = "select p.*, u.*, AsText(p.src) as srctxt, AsText(p.dest) as desttxt from posts p " +
                    "INNER JOIN users u on p.userid = u.userid " +
                    "order by p.dateupdated desc limit " + offset  + ","  + row_count;
            System.out.println(query);
            rs = statement.executeQuery(query);

            while(rs.next()){
                int postid = rs.getInt("p.postid");
                postIds.add(postid);
                Ride ride = new Ride();
                Timestamp timestamp = rs.getTimestamp("p.datecreated");
                ride.setDateCreated(timestamp);
                ride.setSrcHumanReadable(rs.getString("p.srcReadable"));
                ride.setDestHumanReadable(rs.getString("p.destReadable"));
                ride.setPost(rs.getString("p.post"));
                ride.setPostid(postid);
                ride.setPosttype(rs.getInt("p.posttype") == 0? Ride.RideType.OFFERED : Ride.RideType.ASKED);
                ride.setUserid(rs.getInt("p.userid"));
                ride.setSrc(Location.parseMysqlSpatialFormat(rs.getString("srctxt")));
                ride.setDest(Location.parseMysqlSpatialFormat(rs.getString("desttxt")));

                User u = new User();
                u.setFullName(rs.getString("u.fullname"));
                u.setUserid(rs.getInt("u.userid"));
                ride.setUser(u);


                result.put(postid, ride);
            }
        }
        catch (SQLException e) {
            System.out.println("RidesDAO.getRides(  ) SQLException: " +
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

        CommentsDAO commentsDAO = new CommentsDAO(context);
        HashMap<Integer, List<Comment>> allComments = commentsDAO.getComments((ArrayList<Integer>) postIds); // TODO should be user id from session
        LikesDAO likesDAO = new LikesDAO(context);
        HashMap<Integer, Map.Entry<Integer, Boolean>> allLikes = likesDAO.getLikes((ArrayList<Integer>) postIds, currentUserId);
        System.out.println("Found " + result.size() + " rides");
        System.out.println("allComments: " + allComments);
        List<Ride> ret = new ArrayList<>();
        for(Integer pid:result.keySet()){
            Ride ride = result.get(pid);
            if(allComments.containsKey(pid)){
                ride.setCommentList(allComments.get(pid));
            }

            if(allLikes.containsKey(pid)){
                Map.Entry<Integer, Boolean> likesEntry = allLikes.get(pid);
                ride.setLikedByCurrentUser(likesEntry.getValue());
                ride.setLikesCount(likesEntry.getKey());
            }
            ret.add(ride);
        }

        Collections.sort(ret);

        return ret;
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
            preparedStatement.setInt(3, r.getPosttype() == Ride.RideType.ASKED ? 1 : 0);
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
            if(e.getMessage().startsWith("Communications link failure")){
                try {
                    connection.close();
                    Thread.sleep(200);
                } catch (SQLException e1) {

                } catch (InterruptedException e1) {

                }

                return save(r, userid);
            }
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
