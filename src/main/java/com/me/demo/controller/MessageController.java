package com.me.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.demo.dao.ContentDAO;
import com.me.demo.dao.MessageDAO;
import com.me.demo.dao.UserDAO;
import com.me.demo.exception.AdException;
import com.me.demo.pojo.Content;
import com.me.demo.pojo.Message;
import com.me.demo.pojo.User;

@Controller
@RequestMapping(value={"/message.htm","/sendmessage.htm"})
public class MessageController {

	@Autowired
	MessageDAO messageDao;
	@Autowired
	ContentDAO contentDao;
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(value="/message.htm",method=RequestMethod.GET)
    protected ModelAndView doClickAction(@ModelAttribute("message")Message message, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		HttpSession session=request.getSession();
		ModelAndView model=new ModelAndView();
		String action=request.getParameter("action");
		String description=request.getParameter("description");
		Boolean flag;
		
		if(action.equalsIgnoreCase("send"))
		{
			flag=false; 
			User user=(User)session.getAttribute("user");
			List<User> userList = new ArrayList<User>();
			userList=userDao.displayUsers(user.getPersonID());
			//Content content=contentDao.searchContent(id);
			model.addObject("description",description);
			request.setAttribute("sendtoall",flag);
			model.addObject("list",userList);
			model.setViewName("message");
			
		}
		else if(action.equalsIgnoreCase("sendToAll"))
		{
			flag=true; 
			model.addObject("description",description);
			request.setAttribute("sendtoall",flag);
			model.setViewName("message");	
		}
		return model;
		
    }
	

	
	@RequestMapping(value="/sendmessage.htm",method=RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("message")Message message,BindingResult result,HttpServletRequest request)throws Exception
	{
		String role=null;
		int number=0;
		try{
			User u=(User)request.getSession().getAttribute("user");
			
			if(message.getReceiver()!=null)
			{
				message.setUser(u);
				messageDao.createMessage(message);
				System.out.println("message sent to"+message.getReceiver());
			}
			
			else
			{
				List<User> userList = new ArrayList<User>();
				userList=userDao.displayUsers(u.getPersonID());
				number=messageDao.sendMessageToAll(message, userList, u);
				
				/*for(User ul:userList);
				{	
					//Message msg=new Message();
					//msg=message;
					message.setUser(u);
					message.setReceiver(String.valueOf(ul.getPersonID()));
					messageDao.createMessage(message);
					System.out.println("message sent to"+message.getReceiver());
				}*/
				
			}

			if(u.getRoletype().equalsIgnoreCase("user"))
				role="index";
			else if(u.getRoletype().equalsIgnoreCase("admin")&& number>0)
				role="adminindex";
			else if(u.getRoletype().equalsIgnoreCase("contentmanager"))
				role="contentManagerIndex";
			
		}
		catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		return role;
	}
}
