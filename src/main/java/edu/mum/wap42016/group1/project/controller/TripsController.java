package edu.mum.wap42016.group1.project.controller;

import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
@WebServlet(name = "TripsController")
public class TripsController extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter(  );
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cached Connection Servlet</title>");
        out.println("</head>");
        out.println("<body>");

        // Turn on verbose output
        CacheConnection.setVerbose(true);

        // Get a cached connection
        Connection connection = CacheConnection.checkOut( this );

        Statement statement  = null;
        ResultSet  resultSet  = null;
        String     userName   = null;
        try {
            // Test the connection
            statement = connection.createStatement(  );
            resultSet = statement.executeQuery(
                    "show tables");
            /*if (resultSet.next(  ))
                userName = resultSet.getString(1);*/
            while(resultSet.next()){
                out.print("table " + resultSet.getString(1) + "<br />");
            }
        }
        catch (SQLException e) {
            out.println("DedicatedConnection.doGet(  ) SQLException: " +
                    e.getMessage(  ) + "<p>");
        }
        finally {
            if (resultSet != null)
                try { resultSet.close(  ); } catch (SQLException ignore) { }
            if (statement != null)
                try { statement.close(  ); } catch (SQLException ignore) { }
        }

        // Return the conection
        CacheConnection.checkIn(connection);

        out.println("Hello " + userName + "!<p>");
        out.println("You're using a cached connection!<p>");
        out.println("</body>");
        out.println("</html>");
    }
}
