package edu.mum.wap42016.group1.project.controller;

import edu.mum.wap42016.group1.project.dao.RidesDAO;
import edu.mum.wap42016.group1.project.model.Ride;
import edu.mum.wap42016.group1.project.util.CacheConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
@WebServlet(name = "RidesController")
public class RidesController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Connection connection = CacheConnection.checkOut( this ); // just to cache it
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /* res.setContentType("text/html");
        PrintWriter out = res.getWriter(  );
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cached Connection Servlet</title>");
        out.println("</head>");
        out.println("<body>");

        RidesDAO ridesDAO = new RidesDAO(this);
        List<Ride> currentRides = ridesDAO.getTrips(0);

        for(Ride r:currentRides){
            out.println("Ride( " + r.getPostid() +" ) created on " + r.getDateCreated() + " by user id " +  r.getUserid() + "<br />");
        }


        out.println("Hello  !<p>");
        out.println("You're using a cached connection!<p>");
        out.println("</body>");
        out.println("</html>"); */

        RidesDAO ridesDAO = new RidesDAO(this);
        List<Ride> currentRides = ridesDAO.getTrips(0);

        req.setAttribute("rides", currentRides);
        req.getRequestDispatcher("/rides/list.jsp").forward(req, res);
    }
}
