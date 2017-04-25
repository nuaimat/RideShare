package edu.mum.wap42016.group1.project.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import edu.mum.wap42016.group1.project.model.User;
import edu.mum.wap42016.group1.project.util.CacheConnection;

public class UserDAO {
	HttpServlet context;

    public UserDAO(HttpServlet context) {
        this.context = context;
    }
	public void addUser(String fullname, int gender,String state, String city, String street, String email, String password,
			int birthyear, int zipcode){
		
        // Turn on verbose output
        CacheConnection.setVerbose(true);


        // Get a cached connection
        Connection connection = CacheConnection.checkOut(context);
        PreparedStatement statement  = null;
        ResultSet rs  = null;
        String     userName   = null;

        try {

            String req = "INSERT INTO users"
                    + "( fullname, gender, state, city, street, zipcode,birthyear,email, password) VALUES"
                    + "(?,?,?,?,?,?,?,?,?)";

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
