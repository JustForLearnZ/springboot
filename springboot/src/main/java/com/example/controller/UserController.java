package com.example.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserDomain;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController<T> {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@ResponseBody
	 @PostMapping("/add") 
	 public int addUser(UserDomain user)
	 { 
		 return userService.addUser(user); 
	} 
	@ResponseBody
	@GetMapping("/all") 
	public Object findAllUser(
			@RequestParam(name="pageNum",defaultValue="1",required=false) Integer pageNum,
			@RequestParam(name="pageSize",defaultValue="2",required=false) Integer pageSize,
			ModelMap model){ 
		
		 
		PageInfo<UserDomain> page=userService.findAllUser(pageNum, pageSize);
	
//		logger.warn("page:{}", page);
		return page;}
	

	@RequestMapping("/hello")
	public String say() {
		logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
		return "index";
	}
}
