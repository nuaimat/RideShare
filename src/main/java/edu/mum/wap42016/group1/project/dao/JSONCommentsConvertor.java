package edu.mum.wap42016.group1.project.dao;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
    ObjectMapper objectMapper = new ObjectMapper();
    List<Comment> commentList= new ArrayList<>();


    public String createCommentsJSON(){

        CommentsDAO commentsDao= new CommentsDAO(this);
        commentList= commentsDao.getComments(postid);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            String arrayToJson = null;
            try {
                arrayToJson = objectMapper.writeValueAsString(commentList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }



        return arrayToJson;
    }

    public   List<Comment>  creatCommentsObject(){


        TypeReference<List<Comment>> mapType = new TypeReference<List<Comment>>() {};
       // List<Comment> jsonToComments = objectMapper.readValue(arrayToJson, mapType);
        System.out.println("\n2. Convert JSON to List of person objects :");

       // return jsonToComments;
        return null;
    }




}




