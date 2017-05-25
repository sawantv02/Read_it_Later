package com.me.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.demo.dao.ContentDAO;
import com.me.demo.exception.AdException;
import com.me.demo.pojo.Content;
import com.me.demo.pojo.User;

@Controller

public class ContentController {

	@Autowired
	//@Qualifier("contentValidator")
	//ContentValidator convalidator;		
	//ServletContext context;*/
	ContentDAO contentDAO;
	
	@Autowired
	ContentValidator cv;
	
	/*
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(convalidator);
	}*/
	
	@RequestMapping(value="/content.htm",method=RequestMethod.POST)
    protected String doSubmitAction(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws AdException
    {
	       File mfile;
	       //String check = file.getOriginalFilename().separator; //Checking if system is linux based or windows based by checking seprator used.*/
	       String path = null;

			User u=(User) request.getSession().getAttribute("user");
	        String role=null;
	        if(file!=null)
	        {
	        	//ContentValidator cv=new ContentValidator();
	        	String fileNameWithExt=System.currentTimeMillis()+file.getOriginalFilename();
	        	cv.validate(fileNameWithExt);
	        	//ContentDAO contentDAO=new ContentDAO();
	            
	            
	           
	            String type=contentDAO.checkContentType(file);
	            if(type.equalsIgnoreCase("audio"))
	            	path="C:\\Users\\vishakha\\Documents\\workspace-sts-3.7.3.RELEASE\\demo\\src\\main\\webapp\\resources\\audios\\";
	            else if(type.equalsIgnoreCase("video"))
	            	path="C:\\Users\\vishakha\\Documents\\workspace-sts-3.7.3.RELEASE\\demo\\src\\main\\webapp\\resources\\videos\\";
	            else if(type.equalsIgnoreCase("article"))
	            	path="C:\\Users\\vishakha\\Documents\\workspace-sts-3.7.3.RELEASE\\demo\\src\\main\\webapp\\resources\\articles\\";
	            else
	            	path="C:\\Users\\vishakha\\Documents\\workspace-sts-3.7.3.RELEASE\\demo\\src\\main\\webapp\\resources\\images\\";
	            mfile=new File(path+fileNameWithExt);
	            try {
		            BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(path + fileNameWithExt)));
	                FileCopyUtils.copy(file.getInputStream(), stream);
					stream.close();
		            //System.out.println(stream.getPath());
		            System.out.println(file.getOriginalFilename());
		            contentDAO.create(file,fileNameWithExt,u,type);
		            
		            if(u.getRoletype().equalsIgnoreCase("user"))
		            role= "index";
		            else if(u.getRoletype().equalsIgnoreCase("admin"))
		            role="adminindex";
		            else if(u.getRoletype().equalsIgnoreCase("contentmanager"))
		            role="contentManagerIndex";
				}
				catch (Exception e) {
					throw new AdException("You failed to upload " + e);
				}            
	        }
	        
	        	
		return role;
    }
	
	@RequestMapping(value="/content.htm",method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("content")Content content) { 
   
        return "index"; 
    } 
	
	@RequestMapping(value="/categorize.htm",method=RequestMethod.GET,params={"status","cid"})
	public ModelAndView categorizeContent(@RequestParam String status,@RequestParam String cid,HttpServletRequest request,HttpServletResponse response)throws AdException
	{
		ModelAndView mv=new ModelAndView();
		//String status=request.getParameter("status");
		long conid=Long.parseLong(cid);
		
		int result;
		try {
			result = contentDAO.setContentStatus(conid, status);
			if(result>0)
			{
				mv.setViewName("contentManagerIndex");
			}
		} catch (Exception e) {
			throw new AdException("Status cannot be set " + e);
		}
	
		return mv;
	}
	
	@RequestMapping(value="/deletecontent.htm",method=RequestMethod.GET)
	public @ResponseBody Content deleteResult(@RequestParam String cid,@RequestParam String action,HttpServletRequest request) throws Exception
	{
		long conid=Long.parseLong(cid);
		//String action=request.getParameter("action");
		Content con=null;
		if(action.equalsIgnoreCase("delete"))
		{
			Content c=contentDAO.searchContent(conid);
			con=contentDAO.delete(c);
			
		}
		return con;
	}
}	
