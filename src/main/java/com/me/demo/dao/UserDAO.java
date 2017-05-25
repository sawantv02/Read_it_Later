package com.me.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.demo.exception.AdException;
import com.me.demo.pojo.Content;
import com.me.demo.pojo.User;




public class UserDAO extends DAO {

	 public UserDAO() {
	    }

	    public User get(long userid)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from User where personID = :userid");
	            q.setLong("userid", userid);
	            User user = (User) q.uniqueResult();
	            commit();
	            return user;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + userid, e);
	        }
	    }

	    public User create(User user)
	            throws AdException {
	        try {
	            begin();
	            System.out.println("inside DAO");
	            
	            
	            //user=new User(username,password);
	            
	            //user.setFirstname(firstName);
	            //user.setLastname(lastName);
	            //user.setEmailId(emailId);
	            
	            getSession().save(user);
	            
	            commit();
	            return user;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
	    }

	    public User delete(User user)
	            throws AdException {
	        try {
	            begin();
	            getSession().delete(user);
	            commit();
	            return user;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not delete user " + user.getUsername(), e);
	        }
	    }
	    
	    public User verifyUser(String username,String password,String roletype) throws AdException
	    {
	    	try{
	    		begin();
	    		System.out.println("verifying user");
	    		Query q=getSession().createQuery("from User where username = :username and password=:password and roletype=:roletype");
	    		q.setString("username", username);
	    		q.setString("password",password);
	    		q.setString("roletype",roletype);
	    		User user = (User) q.uniqueResult();
		        commit();
		        return user;
	    	}
	    	catch(HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + username, e);
	        }
	    	
	    }
	    
	    public List<User> displayUsers(long personid)
	    {
	    	try {
	            begin();
	            List<User> list=new ArrayList<User>();
	            //Query q = getSession().createQuery("from Person where ");
	            Criteria crit=getSession().createCriteria(User.class);
	            crit.add(Restrictions.ne("personID",personid));
	            list=crit.list();
	            //q.setString("contenttype",key);
	            //q.setLong("userid",userid);
	            //list =q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not get Users" + , e);
	            return null;
	        }
			//return list;

	    }
}
