package com.me.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.demo.dao.UserDAO;
import com.me.demo.pojo.Message;
import com.me.demo.pojo.User;

@Controller

public class AdminController {

	@Autowired
	UserDAO userDao;
	
	@RequestMapping(value="/showusers.htm",method=RequestMethod.GET,params={"userid"})
	public @ResponseBody User displayResult(@ModelAttribute("user")User user,@RequestParam String userid,HttpServletRequest request) throws Exception
	{
		
		long uid=Long.parseLong(userid);
		//String action=request.getParameter("action");
		//if(action.equalsIgnoreCase("display"))
		{
			
			User u=userDao.get(uid);
            System.out.println(u);
    		return u;
		}
		//else
			//return null;

        //}
		
		
	}
	
	@RequestMapping(value="/deleteusers.htm",method=RequestMethod.GET)
	public @ResponseBody User deleteResult(@RequestParam String uid,@RequestParam String action,HttpServletRequest request) throws Exception
	{
		long userid=Long.parseLong(uid);
		//String action=request.getParameter("action");
		User userl=null;
		if(action.equalsIgnoreCase("delete"))
		{
			User u=userDao.get(userid);
			userl=userDao.delete(u);
			
		}
		return userl;
	}
	
	@RequestMapping(value="/signout.htm",method=RequestMethod.GET)
	public ModelAndView signout(@ModelAttribute("user")User user,HttpServletRequest request) throws Exception
	{

		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		if(session!=null)
		session.invalidate();
		
		mv.setViewName("signin");
		
		return mv;
	}
}
