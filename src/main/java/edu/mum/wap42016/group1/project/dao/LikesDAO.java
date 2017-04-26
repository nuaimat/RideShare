package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.Like;
import edu.mum.wap42016.group1.project.util.CacheConnection;


import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.*;

/**
 * Created by zaid on 4/24/2017.
 */
public class LikesDAO {
    HttpServlet context;

    public LikesDAO(HttpServlet context) {
        this.context = context;
    }

    public int addLikes( int userid, int postid)  {
        int last_inserted_id = -1;
        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {

            String req = "INSERT INTO Likes"
                    + "( userid, postid, datecreated) VALUES"
                    + "(?,?, NOW())";

            PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, postid);

            System.out.println("LikesDAO.addLikes " + preparedStatement.toString());

            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                last_inserted_id = rs.getInt(1);
                System.out.println("like inserted with id " + last_inserted_id);
            }

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

        return last_inserted_id;

    }

    public HashMap<Integer, Map.Entry<Integer, Boolean>> getLikes(List<Integer> postids, int currentUserId){

       // TO DO with likes

        HashMap<Integer, Map.Entry<Integer, Boolean>> result = new HashMap<>();

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( context );
        ResultSet rs  = null;
        PreparedStatement preparedStatement = null;
        try {

            StringBuilder idList = new StringBuilder();
            for (int id : postids) {
                if (idList.length() > 0) {
                    idList.append(",");
                }
                idList.append("?");
            }
            String que = "SELECT g.c, g.postid, l.liked from\n" +
                    "     (select count(*) c , postid from likes where postid in ("+idList+") group by postid) as g\n" +
                    "     left join (select userid, postid, (case when (userid = ?) \n" +
                    "      THEN\n" +
                    "           1 \n" +
                    "      ELSE\n" +
                    "           0 \n" +
                    "      END)\n" +
                    "      as liked \n" +
                    "      from likes group by userid) l on g.postid = l.postid \n" +
                    "      group by g.postid";

            //System.out.println("LikesDAO.getLikesbefore " + que);
            preparedStatement = connection.prepareStatement(que);
            int i = 0;
            for (i = 0; i < postids.size(); i++) {
                preparedStatement.setInt(i+1, postids.get(i));
            }
            preparedStatement.setInt(i+1, currentUserId);

            rs = preparedStatement.executeQuery();
            System.out.println("LikesDAO.getLikes " + preparedStatement);

            while(rs.next()){
                AbstractMap.SimpleEntry<Integer, Boolean> likesCountVsLiked =
                        new AbstractMap.SimpleEntry<Integer, Boolean>(rs.getInt("c"), rs.getInt("liked") > 0);

                result.put(rs.getInt("postid"), likesCountVsLiked);
            }
        }
        catch (SQLException e) {
            System.out.println("LikesDao.getLikes(  ) SQLException: " +
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

    public int removeLikes(int userid, int postid) {
        int affectedRows = 0;
        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;

        try {

            String req = "delete from Likes where userid=? and postid=?";


            PreparedStatement preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, postid);

            System.out.println("LikesDAO.removeLikes " + preparedStatement.toString());

            affectedRows = preparedStatement.executeUpdate();



        }
        catch (SQLException e) {
            System.out.println("LikesDAO.removeLikes() SQLException: " +
                    e.getMessage(  ) );
        } finally {

            if (rs != null)
                try { rs.close(  ); } catch (SQLException ignore) { }
            if (statement != null)
                try { statement.close(  ); } catch (SQLException ignore) { }
        }


        // Return the conection
        CacheConnection.checkIn(connection);

        return affectedRows;
    }
}
