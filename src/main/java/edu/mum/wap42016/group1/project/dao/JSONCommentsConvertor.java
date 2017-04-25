package edu.mum.wap42016.group1.project.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mum.wap42016.group1.project.dao.CommentsDAO;
import edu.mum.wap42016.group1.project.model.Comment;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaid on 4/25/2017.
 */

public class JSONCommentsConvertor extends HttpServlet {

   //TO DO
   private int userid=2;
   private int postid=3;


   public void createCommentJSON(ArrayList<Comment> commentsL){
      try {

         CommentsDAO commentsDao= new CommentsDAO(this);
         ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.writeValue(new File("/jsons/comments.json"), commentsDao.getComments(userid,postid));
      }
      catch (Exception e){

      }
      finally {
   }

}





}




