package edu.mum.wap42016.group1.project.model;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
public class Location {
    private double lon;
    private double lat;
    private String googleFormat;

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


    public static Location parse(String coords) {
        String parts [] = coords.split(",");

        return new Location(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]));
    }

    public String toMysqlSpatialFormat() {
        return String.format("POINT(%.14f %.14f)", this.getLon(), this.getLat());
    }

    public static Location parseMysqlSpatialFormat(String spoint) {
        //POINT(-91.95520782470703 41.00830459594727)
        spoint = spoint.replace("POINT(", "");
        spoint = spoint.replace(")", "");
        String[] co = spoint.split(" ");

        Double lon = Double.parseDouble(co[0]);
        Double lat = Double.parseDouble(co[1]);

        Location l = new Location(lat, lon);
        return l;
    }

    @Override
    public String toString(){
        return this.getLon() + "," + this.getLat();
    }

    public String getGoogleFormat() {
        return this.getLat() + "," + this.getLon();
    }
}
