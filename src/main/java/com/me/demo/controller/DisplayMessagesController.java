package com.me.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.demo.dao.MessageDAO;
import com.me.demo.pojo.Message;
import com.me.demo.pojo.User;

@Controller
@RequestMapping("/showmessage.htm")
public class DisplayMessagesController {
	
	@Autowired
	MessageDAO messageDAO;

	
	@RequestMapping(value="/showmessage.htm",method=RequestMethod.GET)
	public @ResponseBody List<Message> displayResult(HttpServletRequest request) throws Exception
	{
		HttpSession session=request.getSession();
        //String action = hsr.getParameter("action");
        //if (action.equalsIgnoreCase("searchuser")) {
            List<Message> messageList = new ArrayList<Message>();

            //String key = request.getParameter("display");
            //String searchKey = hsr.getParameter("flag");
            User user=(User)session.getAttribute("user");
            
            //ContentDAO contentDAO=new ContentDAO();
            messageList=messageDAO.displayMessage(user.getPersonID());
            System.out.println(messageList.size());

        //}
		
		return messageList;
	}
}
