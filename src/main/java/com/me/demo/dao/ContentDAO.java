package com.me.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.web.multipart.MultipartFile;

import com.me.demo.controller.ContentValidator;
import com.me.demo.exception.AdException;
import com.me.demo.pojo.Content;
import com.me.demo.pojo.User;

public class ContentDAO extends DAO{

	public ContentDAO() {
		
	}
	
	 public String checkContentType(MultipartFile file)
	 {
		 	String type=null;
	        Pattern imgpattern = Pattern.compile(ContentValidator.IMAGE_PATTERN);
	        Matcher imgmatcher;
	        Pattern audpattern=Pattern.compile(ContentValidator.AUDIO_PATTERN);
	        Matcher audmatcher;
	        Pattern vidpattern=Pattern.compile(ContentValidator.VIDEO_PATTERN);
	        Matcher vidmatcher;
	        Pattern docpattern=Pattern.compile(ContentValidator.DOC_PATTERN);
	        Matcher docmatcher;
	        
	        
	        imgmatcher = imgpattern.matcher(file.getOriginalFilename());
	        audmatcher=audpattern.matcher(file.getOriginalFilename());
	        vidmatcher=vidpattern.matcher(file.getOriginalFilename());
	        docmatcher=docpattern.matcher(file.getOriginalFilename());
	        
	        if(imgmatcher.matches()==true)
	             type="Image";
	        else if(audmatcher.matches()==true)
	        	type="Audio";
	        else if(vidmatcher.matches()==true)
	        	type="Video";
	        else if(docmatcher.matches()==true)
	        	type="Article";
	         
		 return type;
	 }

	 public Content create(MultipartFile file,String filename,User userid,String type)
	            throws AdException {
	        try {
	            begin();
	            System.out.println("inside DAO");
	            
	            
	            Content content=new Content();
	            
	            content.setContent_name(filename);
	            content.setSize(file.getSize());
	            content.setContent_type(type);
	            content.setUser(userid);

	            getSession().save(content);
	            
	            commit();
	            return content;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create content " + content_name, e);
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
	    }
	 
	 public List<Content> displayContent(String key,long userid)throws AdException
	 {
		 try {
	            begin();
	            List<Content> list=new ArrayList<Content>();
	            Query q = getSession().createQuery("from Content where content_type = :contenttype and personID = :userid");
	            q.setString("contenttype",key);
	            q.setLong("userid",userid);
	            list =q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get content" + key, e);
	        }
		 
		 //return null;
	 }
	 
	 public List<Content> displayContent(String key)throws AdException
	 {
		 try {
	            begin();
	            List<Content> list=new ArrayList<Content>();
	            Query q = getSession().createQuery("from Content where content_type = :contenttype");
	            q.setString("contenttype",key);
	            //q.setLong("userid",userid);
	            list =q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get content" + key, e);
	        }
		 
		 //return null;
	 }
	 
	 public List<Content> displayMyListContent(long userid)throws AdException
	 {
		 try {
	            begin();
	            List<Content> list=new ArrayList<Content>();
	            Query q = getSession().createQuery("from Content where personID = :userid");
	            //q.setString("contenttype",key);
	            q.setLong("userid",userid);
	            list =q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get content" + userid, e);
	        }
		 
		 //return null;
	 }
	 public Content searchContent(long contentid)throws Exception
	 {
		 try {
	            begin();
	            List<Content> list=new ArrayList<Content>();
	            Query q = getSession().createQuery("from Content where contentID = :contentid");
	            q.setLong("contentid",contentid);
	            //q.setLong("userid",userid);
	             Content content=(Content)q.uniqueResult();
	            commit();
	            return content;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get content" + contentid, e);
	        }
		 
	 }
	 
	 public int setContentStatus(long contentid,String status)throws Exception
	 {
		 try{
			 begin();
			 Query query = getSession().createQuery("update Content set content_status = :status" +
	    				" where contentID = :contentid");
			 query.setParameter("status",status);
			 query.setParameter("contentid",contentid);
			 int result = query.executeUpdate();
	            commit();
	            return result;
			 
		 } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get content" + contentid, e);
	        }
	 }
	 
	 public Content delete(Content content)
	            throws AdException {
	        try {
	            begin();
	            getSession().delete(content);
	            commit();
	            return content;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not delete user " + content, e);
	        }
	    }
}
