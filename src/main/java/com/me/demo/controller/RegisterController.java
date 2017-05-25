package com.me.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.demo.controller.UserValidator;
import com.me.demo.dao.UserDAO;
import com.me.demo.exception.AdException;
import com.me.demo.pojo.User;



/**
 * Handles requests for the application register page.
 */
@Controller
@RequestMapping (value={"/signup.htm","/register.htm"})
public class RegisterController {
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	UserDAO userDao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value="/register.htm",method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("user")User user,BindingResult result) throws Exception
    {
		validator.validate(user, result);
		if(result.hasErrors()){
			return "register";
		}
        try
        {
            //UserDAO userDao = new UserDAO();
            //userDao.create(user.getUsername(), user.getPassword(),user.getEmailId(),user.getFirstname(),user.getLastname());
            //user.setRoletype("AccountHolder");
        	userDao.create(user);
        	//DAO.close();
        }
        catch (AdException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return "signin";
    }
    
	@RequestMapping(value="/register.htm",method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("user")User user, BindingResult result) { 
   
        return "register"; 
    } 
	
}
