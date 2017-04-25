package edu.mum.wap42016.group1.project.controller;

import edu.mum.wap42016.group1.project.dao.RidesDAO;
import edu.mum.wap42016.group1.project.model.Location;
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
        this.getServletContext().setAttribute("gmap_api_key",
                this.getServletContext().getInitParameter("gmap_api_key"));
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Ride r = new Ride();

        int ridetype = Integer.parseInt(req.getParameter("ridetype"));
        r.setPosttype(ridetype == 0 ? Ride.RideType.OFFERED : Ride.RideType.ASKED);
        r.setSrc(Location.parse(req.getParameter("ridesrc_coords")));
        r.setSrcHumanReadable(req.getParameter("ridesrc"));
        r.setDest(Location.parse(req.getParameter("ridedest_coords")));
        r.setDestHumanReadable(req.getParameter("ridedest"));
        r.setPost(req.getParameter("ridedesc"));

        RidesDAO ridesDAO = new RidesDAO(this);

        boolean saved = ridesDAO.save(r, 1);
        res.getWriter().println(req.getParameter("ridedesc"));

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RidesDAO ridesDAO = new RidesDAO(this);
        List<Ride> currentRides = ridesDAO.getRides(0);

        req.setAttribute("rides", currentRides);
        req.getRequestDispatcher("/rides/list.jsp").forward(req, res);
    }
}
