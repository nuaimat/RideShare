package edu.mum.wap42016.group1.project.dao;

import edu.mum.wap42016.group1.project.model.User;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class UserDAO {
    HttpServlet context;

    public UserDAO(HttpServlet context) {
        this.context = context;
    }

    public void addUser(String fullname, int gender, String state, String city, String street, String email,
                        String password, int birthyear, int zipcode) {
        System.out.println("it is in DAO");
        // Turn on verbose output
        CacheConnection.setVerbose(true);
        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement = null;
        ResultSet rs = null;
        String userName = null;
        try {

            String req = "INSERT INTO users"
                    + "( fullname, gender, state, city, street, zipcode, birthyear,email, password, datecreated) VALUES"
                    + "(?,?,?,?,?,?,?,?,?, NOW())";

            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, fullname);
            preparedStatement.setInt(2, gender);
            preparedStatement.setString(3, state);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, street);
            preparedStatement.setInt(6, zipcode);
            preparedStatement.setInt(7, birthyear);
            preparedStatement.setString(8, email);
            preparedStatement.setString(9, password);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("UserDAO.addUser(  ) SQLException: " + e.getMessage());
        } finally {

            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
        }
        // Return the conection
        CacheConnection.checkIn(connection);

    }

    public boolean validate(String userName, String password, HttpServletRequest request, HttpServletResponse response) {

        CacheConnection.setVerbose(true);
        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        User myuser = null;
        boolean isUser = false;
        Statement statement = null;
        ResultSet rs = null;
        try {

            String req = "Select * from users where email=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();


            while (rs.next()) {
                isUser = true;
                myuser = new User();
                myuser.setFullName(rs.getString("fullname"));
                myuser.setBirthYear(rs.getInt("birthyear"));
                myuser.setEmail(rs.getString("email"));
                myuser.setCity(rs.getNString("city"));
                myuser.setStreet(rs.getString("street"));
                //myuser.setPassword(rs.getString("password"));
                myuser.setUserid(rs.getInt("userid"));
            }

            if (isUser) {
                HttpSession session = request.getSession();
                session.setAttribute("user", myuser);
            }

        } catch (SQLException e) {
            System.out.println("DedicatedConnection.doGet(  ) SQLException: " +
                    e.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
        }

        // Return the conection
        CacheConnection.checkIn(connection);

        return isUser;
    }


    public boolean isLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }

    public void logout(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
    }


    public int getCurrentUserId(HttpServletRequest req) {
        if (isLoggedIn(req))
            return ((User) req.getSession().getAttribute("user")).getUserid();
        else
            return -1;
    }

    public User getCurrentUser(HttpServletRequest req) {
        if (isLoggedIn(req))
            return ((User) req.getSession().getAttribute("user"));
        else
            return null;
    }
}
            
		

	

	