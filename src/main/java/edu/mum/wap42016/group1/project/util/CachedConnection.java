package edu.mum.wap42016.group1.project.util;

import java.sql.Connection;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
public class CachedConnection {
    private boolean    inUse;
    private Connection conn;
    private long       lastUsed;
    private String     url; // used just as a unique identifier

    public CachedConnection(  ) {
        conn     = null;
        inUse    = false;
        lastUsed = System.currentTimeMillis(  );
        url = "jdbc:mysql://localhost/db";
    }

    public CachedConnection(Connection conn, boolean inUse) {
        this.conn     = conn;
        this.inUse    = inUse;
        this.lastUsed = System.currentTimeMillis(  );
        this.url = "jdbc:mysql://localhost/db";
    }

    public CachedConnection(Connection conn, boolean inUse, String url) {
        this.conn     = conn;
        this.inUse    = inUse;
        this.lastUsed = System.currentTimeMillis(  );
        this.url = url;
    }

    public Connection getConnection(  ) {
        return conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public boolean getInUse(  ) {
        return inUse;
    }

    public boolean isInUse(  ) {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        if (!inUse)
            lastUsed = System.currentTimeMillis(  );
        this.inUse = inUse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLastUsed(  ) {
        return lastUsed;
    }
}