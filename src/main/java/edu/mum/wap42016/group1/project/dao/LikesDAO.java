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
                    + "( userid, postid, datecreated) VALUES"
                    + "(?,?, NOW())";

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

    public HashMap<Integer, Map.Entry<Integer, Boolean>> getLikes(List<Integer> postids, int currentUserId){

       // TO DO with likes

        HashMap<Integer, Map.Entry<Integer, Boolean>> result = new HashMap<>();

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
            String que = " SELECT g.c, g.postid, l.liked from\n" +
                    "   (select count(*) c , postid from likes where postid in (\"+idList+\") group by postid) as g\n" +
                    "   inner join (select postid, (case when (userid = 2) \n" +
                    "    THEN\n" +
                    "         1 \n" +
                    "    ELSE\n" +
                    "         0 \n" +
                    "    END)\n" +
                    "    as liked \n" +
                    "    from likes) l on g.postid = l.postid  ";

            //String que= "select * from likes where postid in ("+idList+")";

            PreparedStatement preparedStatement = connection.prepareStatement(que);
            for (int i = 0; i < postids.size(); i++) {
                preparedStatement.setInt(i+1, postids.get(i));
            }
            rs = statement.executeQuery(que);
            System.out.println("LikesDAO.getLikes " + que);

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
            if (statement != null)
                try { statement.close(  ); } catch (SQLException ignore) { }
        }

        // Return the conection
        CacheConnection.checkIn(connection);

        return result;
    }

}
