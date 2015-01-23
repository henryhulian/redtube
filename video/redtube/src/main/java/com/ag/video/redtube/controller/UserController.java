package com.ag.video.redtube.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userController")
public class UserController {

	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String getUserList(){
		
		return "OK";
	}
	
}
