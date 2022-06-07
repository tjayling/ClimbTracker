package com.qa.climbtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Root {
	@GetMapping("/login")
	public String login() {
		return("/html/login.html");
	}
	
	@GetMapping("/")
	public String index() {
		return("/html/index.html");
	}
	
	@GetMapping("/home.html")
	public String home() {
		return("/html/home.html");
	}
}
