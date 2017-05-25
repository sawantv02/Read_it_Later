package com.me.demo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.demo.dao.ContentDAO;
import com.me.demo.dao.MessageDAO;
import com.me.demo.pojo.Content;
import com.me.demo.pojo.Message;
import com.me.demo.pojo.User;

@Controller
@RequestMapping(value={"/display.htm"})
public class DisplayContentsController {
	
	@Autowired
	ContentDAO contentDAO;



	@RequestMapping(value="/display.htm",method=RequestMethod.GET)
	public @ResponseBody List<Content> handleRequest(HttpServletRequest request) throws Exception
	{
		HttpSession session=request.getSession();
        String action = request.getParameter("action");
        System.out.println(action);
        //if (action.equalsIgnoreCase("Image")) {
            List<Content> contentList = new ArrayList<Content>();
            String key = request.getParameter("key");
            if(action.equalsIgnoreCase("display"))	
            {
            //String searchKey = hsr.getParameter("flag");
            User user=(User)session.getAttribute("user");
            
            //ContentDAO contentDAO=new ContentDAO();
            contentList=contentDAO.displayContent(key, user.getPersonID());
            System.out.println(contentList.size());
            }
            else if(action.equalsIgnoreCase("displayall"))
            {
            	
            	contentList=contentDAO.displayContent(key);
            	System.out.println(contentList.size());
            	
            }
            else if(action.equalsIgnoreCase("mylist"))
            {
            	User user=(User)session.getAttribute("user");
            	contentList=contentDAO.displayMyListContent(user.getPersonID());
            	System.out.println(contentList.size());
            }
            for (Content content:contentList)
            {
            	System.out.println(content.getContentID());
            }
        //}

        return contentList;
	}
	

}

