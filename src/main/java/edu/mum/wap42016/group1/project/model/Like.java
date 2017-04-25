package edu.mum.wap42016.group1.project.model;


import java.util.Date;

/**
 * Created by zaid on 4/24/2017.
 */
public class Like {
    private int likeid;
    private int userid;
    private int postid;
    Date datecreated;
    Date dateupdated;


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

    public Like(int likeid, int userid, int postid, Date datecreatedDate, Date dateupdatedDate) {
        this.likeid = likeid;
        this.userid = userid;
        this.postid = postid;
        this.datecreated = datecreatedDate;
        this.dateupdated = dateupdatedDate;

    }

    public int getLikeid() {
        return likeid;
    }

    public void setLikeid(int likeid) {
        this.likeid = likeid;
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















}
