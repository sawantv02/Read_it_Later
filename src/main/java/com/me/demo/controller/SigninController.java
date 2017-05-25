package com.me.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.demo.controller.UserValidator;
import com.me.demo.dao.UserDAO;
import com.me.demo.exception.AdException;
import com.me.demo.pojo.User;

@Controller
@RequestMapping(value={"/","/login.htm"})
public class SigninController {

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	UserDAO userDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("user")User user,BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession();
		validator.validate(user, result);
		User u;
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()){
			return new ModelAndView("signin");
		}
        try
        {
            
            //userDao.create(user.getUsername(), user.getPassword(),user.getEmailId(),user.getFirstname(),user.getLastname());
            //DAO.close();
            u=userDao.verifyUser(user.getUsername(), user.getPassword(),user.getRoletype());
            

            if(u!=null)
            {	
                String cb[]=request.getParameterValues("rememberme");
                System.out.println(request.getParameter("username")+request.getParameter("password")+request.getParameter("roletype"));
                if(cb.length!=0)
                {	
                Cookie userCookie = new Cookie("userName", request.getParameter("username"));
                Cookie passwordCookie = new Cookie("password", request.getParameter("password"));
                Cookie rolecookie=new Cookie("roletype",request.getParameter("roletype"));
                
                response.addCookie(userCookie);
                response.addCookie(passwordCookie);
                response.addCookie(rolecookie);
                }	
            session.setAttribute("user",u);
            mav.addObject("user",u);
            if(u.getRoletype().equalsIgnoreCase("user"))
            {  
            mav.setViewName("index");
            }
            else if(u.getRoletype().equalsIgnoreCase("content manager"))
            {
            mav.setViewName("contentManagerIndex");
            }
            else if(u.getRoletype().equalsIgnoreCase("admin"))
            {
            	//User user=(User)session.getAttribute("user");
    			List<User> userList = new ArrayList<User>();
    			userList=userDao.displayUsers(u.getPersonID());
    			//Content content=contentDao.searchContent(id);
    			mav.addObject("list",userList);
    			mav.setViewName("adminindex");
            }
            }
            else
            {
            	mav.setViewName("error");
            }
            
        }
        catch (AdException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

        return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("user")User user, BindingResult result) { 
   
        return "signin"; 
    } 
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(@ModelAttribute("user")User user) {
	    
	    return "signin";
	}*/
	
	
}
