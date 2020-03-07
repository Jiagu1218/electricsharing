package com.cyxy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("**.do")
public class LinkController {
	
	@RequestMapping({"/","/login"})
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/*")    //做转发    
	public String doForward(HttpServletRequest request){
		String url = request.getServletPath();    //goods.do
		String path = url.substring(0,url.indexOf("."));   //goods
		return path;
	}
}
