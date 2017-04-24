package edu.mum.wap42016.group1.project.model;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
public class Location {
    private double lon;
    private double lat;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }



}
