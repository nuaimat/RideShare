package edu.mum.wap42016.group1.project.dao;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mum.wap42016.group1.project.dao.CommentsDAO;
import edu.mum.wap42016.group1.project.model.Comment;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zaid on 4/25/2017.
 */

public class JSONCommentsConvertor extends HttpServlet {

    //TO DO
    private int userid=2;
    private int postid=3;
    private String arrayToJson;


    public String createCommentJSON(){


        CommentsDAO commentsDao= new CommentsDAO(this);
        List<Comment> commentList= new ArrayList<>();
        commentList= commentsDao.getComments(userid,postid);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            String arrayToJson = null;
            try {
                arrayToJson = objectMapper.writeValueAsString(commentList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //TO DO just for output
            System.out.println(arrayToJson);






        return arrayToJson;
    }





}




