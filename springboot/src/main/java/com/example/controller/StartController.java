package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {
	@RequestMapping
	public String start() {
		System.out.println("test");
		return "page";
	}

}
