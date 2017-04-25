package edu.mum.wap42016.group1.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mo nuaimat on 4/24/17.
 */
public class Ride {
    public enum RideType {
        OFFERED(0),
        ASKED(1);

        public final int typeNum;
        RideType(int t){
            this.typeNum = t;
        }
    }


    private Location src;
    private String srcHumanReadable;
    private Location dest;
    private String destHumanReadable;
    private Date dateCreated;
    private RideType posttype;
    private String post;
    private int postid;
    private int userid;
    //User user;
    private List<Comment> commentList = new ArrayList<>();



    public RideType getPosttype() {
        return posttype;
    }

    public void setPosttype(RideType posttype) {
        this.posttype = posttype;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Location getSrc() {
        return src;
    }

    public void setSrc(Location src) {
        this.src = src;
    }

    public Location getDest() {
        return dest;
    }

    public void setDest(Location dest) {
        this.dest = dest;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPost() {
        return post.substring(0, 1).toUpperCase() + post.substring(1);
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSrcHumanReadable() {
        return srcHumanReadable;
    }

    public void setSrcHumanReadable(String srcHumanReadable) {
        this.srcHumanReadable = srcHumanReadable;
    }

    public String getDestHumanReadable() {
        return destHumanReadable;
    }

    public void setDestHumanReadable(String destHumanReadable) {
        this.destHumanReadable = destHumanReadable;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
