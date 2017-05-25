package com.me.demo.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SanitizingRequest extends HttpServletRequestWrapper{

	public SanitizingRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public String getParameter(String name) {
        String value = super.getParameter(name);
        if(name.equals("title")||name.equals("actor")||name.equals("actress")||name.equals("genre")||name.equals("year"))
           
        {HttpServletRequest req = (HttpServletRequest) super.getRequest();
           
           return validate( req.getParameter( name ) );
        }
           return value;
       }
    
    private String validate(String input)
    {
        return input.replaceAll("[^\\dA-Za-z ]","").replaceAll("\\s+","+").trim();
    }
}
