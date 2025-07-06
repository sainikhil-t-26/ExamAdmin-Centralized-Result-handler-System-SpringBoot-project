package com.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.result.repo.AdminRepo;

@Controller
public class AdminLoginController {
	
	@Autowired
	private AdminRepo ar;
	
	@GetMapping("/adminLogin")
	public String adminLogin() {
		return "admin-login";
	}
	
	@PostMapping("/validateAdmin")
	public String validateAdmin(@RequestParam("email") String email, @RequestParam("password") String password) {
		
		if(email.equals("shivam@gmail.com")&&password.equals("1234567890")) {
			return "welcome-admin";
		}
		else {
			return "not-found";
		}
		
	}
}
