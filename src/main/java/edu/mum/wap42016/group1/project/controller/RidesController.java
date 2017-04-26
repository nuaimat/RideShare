package edu.mum.wap42016.group1.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mum.wap42016.group1.project.dao.RidesDAO;
import edu.mum.wap42016.group1.project.dao.UserDAO;
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
        //Connection connection = CacheConnection.checkOut( this ); // just to cache it
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
        r.setUserid(1); // TODO change with user id

        RidesDAO ridesDAO = new RidesDAO(this);
        UserDAO userDAO = new UserDAO(this);


        // TODO change user id
        boolean saved = ridesDAO.save(r, userDAO.getCurrentUserId(req));
        res.setContentType("text/html");
        if(saved) {
            //ObjectMapper objectMapper = new ObjectMapper();
            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            //res.getWriter().print(objectMapper.writeValueAsString(r));
            req.setAttribute("ride_obj", r);
            req.getRequestDispatcher("/rides/ride_panel.jsp").forward(req, res);
        } else {
            res.getWriter().print("");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String format = req.getParameter("format");
        RidesDAO ridesDAO = new RidesDAO(this);
        UserDAO userDAO = new UserDAO(this);

        if("ajax".equals(format)){
            List<Ride> currentRides = ridesDAO.getRides(Integer.parseInt(req.getParameter("page")), userDAO.getCurrentUserId(req)); // 1 is page number
            req.setAttribute("rides", currentRides);
            req.getRequestDispatcher("/rides/ajax_list.jsp").forward(req, res);
        } else {
            List<Ride> currentRides = ridesDAO.getRides(1,  userDAO.getCurrentUserId(req)); // 1 is page number
            req.setAttribute("rides", currentRides);
            req.getRequestDispatcher("/rides/list.jsp").forward(req, res);
        }



    }
}
