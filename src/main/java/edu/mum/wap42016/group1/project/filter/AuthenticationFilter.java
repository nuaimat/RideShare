package edu.mum.wap42016.group1.project.filter;

import edu.mum.wap42016.group1.project.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mo nuaimat on 4/18/17.
 */
@WebFilter
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig config)
            throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String requestPath = request.getRequestURI();
        UserDAO userDAO = new UserDAO(null);
        System.out.println("Request Path: " + requestPath + " is userLoggedin? " + userDAO.isLoggedIn(request));
        if (needsAuthentication(requestPath, request) && !userDAO.isLoggedIn(request)) {
        	System.out.println("Redirecting to " + request.getContextPath() + "/login");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }


    //basic validation of pages that do not require authentication
    private boolean needsAuthentication(String url, HttpServletRequest request) {
        if(url.endsWith(".css") || url.endsWith(".js") || 
        		url.equals(request.getContextPath() + "/login") || 
        		url.equals(request.getContextPath() + "/logout")){
            return false;
        }
        return true; // for everything but login
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }
}
