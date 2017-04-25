package edu.mum.wap42016.group1.project.util;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
import java.sql.*;
import java.util.*;

public class Database {
    private static boolean verbose  = true;
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static final Connection getConnection(String username, String password, String db, String host) {
        Connection conn = null;
        String url = null;
        try {
            url                 = generateUrlString(host, db);
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            return conn;
        }
        try {
            if (verbose) {
                System.out.println("driver=" + driver);
                System.out.println("url=" + url);
                System.out.println("username=" + username);
                System.out.println("password=" + password);
            }

            conn = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("in Database.getConnection");
            System.err.println("on getConnection");
            conn = null;
        }
        finally {
            return conn;
        }
    }

    public static void setVerbose(boolean v) {
        verbose = v;
    }

    public static String generateUrlString(String host, String db) {
        return "jdbc:mysql://" + host + "/" + db + "?autoReconnect=true&autoReconnectForPools=true";
    }
}