package edu.mum.wap42016.group1.project.model;

import java.util.Date;

/**
 * Created by zaid on 4/24/2017.
 */
public class Comment {
    private int commentid;
    private int userid;
    private int postid;
    private  String comment;
    private Date datecreated;
    private Date dateupdated;
    private User user;

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(Date dateupdated) {
        this.dateupdated = dateupdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
