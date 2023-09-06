package com.example.todo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
	@GetMapping(path="login")
	String login(Model model) {
		return "/Login";
	}

	@PostMapping(path = "logout")
	String logout(HttpServletRequest httpServletRequest){
		httpServletRequest.getSession().invalidate();
		return "/Login";
	}
}
