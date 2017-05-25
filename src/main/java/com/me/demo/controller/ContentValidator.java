package com.me.demo.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.me.demo.exception.AdException;
import com.me.demo.pojo.Content;


public class ContentValidator {

	public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
	public static final String VIDEO_PATTERN = "([^\\s]+(\\.(?i)(flv|wmv|mkv|mov|mp4))$)";
	public static final String AUDIO_PATTERN = "([^\\s]+(\\.(?i)(mp3|wma|mkv))$)";
	public static final String DOC_PATTERN="([^\\s]+(\\.(?i)(pdf))$)";
	


    public void validate(String name)throws AdException
    {
    	
        Pattern imgpattern = Pattern.compile(IMAGE_PATTERN);
        Matcher imgmatcher;
        Pattern audpattern=Pattern.compile(AUDIO_PATTERN);
        Matcher audmatcher;
        Pattern vidpattern=Pattern.compile(VIDEO_PATTERN);
        Matcher vidmatcher;
        Pattern docpattern=Pattern.compile(DOC_PATTERN);
        Matcher docmatcher;
        //MultipartFile photo;
        //String photo;
        
        //Content content = (Content) obj;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.email.emailId", "Email Required");
        
        //photo=content.getContent_link();
        imgmatcher = imgpattern.matcher(name);
        audmatcher=audpattern.matcher(name);
        vidmatcher=vidpattern.matcher(name);
        docmatcher=docpattern.matcher(name);
        
        /*if(0 == photo.getSize()) {
           errors.rejectValue("photo","Test","File is empty");
        }*/
              if(!imgmatcher.matches()) {
             //errors.rejectValue("photo","Test","Invalid Image Format");
            	  System.out.println("invalid image format");
            	  //throw new AdException("Could not get content");
        }
              else   if(!audmatcher.matches()){
             //errors.rejectValue("photo","Test","Invalid audio Format");
            	  System.out.println("invalid audio format");
            	  //throw new AdException("Could not get content");
              }
              
              else if(!vidmatcher.matches())
             {
             //errors.rejectValue("photo","Test","Invalid video Format");
            	 System.out.println("invalid video format");
            	 //throw new AdException("Could not get content");
             }
             
              else  if(!docmatcher.matches())
             {
             //errors.rejectValue("photo","Test","Invalid video Format");
            	 System.out.println("invalid doc format");
            	 //throw new AdException("Could not get content");
             }
             else
            	 
            	 throw new AdException("Could not get content");
             
        
        /*if(5000000 < photo.getSize()) {
             errors.rejectValue("photo","Test","File size is over 5mb !");
        }*/
    }

}
