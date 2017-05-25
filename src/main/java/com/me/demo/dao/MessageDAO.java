package com.me.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.me.demo.exception.AdException;
import com.me.demo.pojo.Content;
import com.me.demo.pojo.Message;
import com.me.demo.pojo.User;

public class MessageDAO extends DAO{

	public MessageDAO() {
	
	}

	public Message createMessage(Message message)throws Exception{
		
		try {
            begin();
            System.out.println("inside DAO");
            
            
            //user=new User(username,password);
            
            //user.setFirstname(firstName);
            //user.setLastname(lastName);
            //user.setEmailId(emailId);
            
            getSession().save(message);
            
            commit();
            return message;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating message: " + e.getMessage());
        }
		
	}
	
	public List<Message> displayMessage(long userid)throws AdException
	 {
		 try {
	            begin();
	            List<Message> list=new ArrayList<Message>();
	            String uid=String.valueOf(userid);
	            Query q = getSession().createQuery("from Message where receiver = :userid");
	            //q.setString("contenttype",key);
	            q.setString("userid",uid);
	            list =q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get content" + e);
	        }
		 
		 //return null;
	 }
	
public int sendMessageToAll(Message message,List<User> userList,User u)throws Exception{
		
		try {
            //begin();
            System.out.println("inside DAO");
            int i=0;
            for(User ul:userList)
			{	
            	
            	
				//msg=message;
				message.setUser(u);
				message.setReceiver(String.valueOf(ul.getPersonID()));
				Message msg=new Message();
				BeanUtils.copyProperties(message,msg);
				createMessage(msg);
				System.out.println("message sent to"+msg.getReceiver());
				//getSession().save(ul);
				i++;
			}
            
            //commit();
            return i;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating message: " + e.getMessage());
        }
		
	}
}
