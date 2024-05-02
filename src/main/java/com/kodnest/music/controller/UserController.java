 package com.kodnest.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.music.entity.User;
import com.kodnest.music.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired	
	UserService	us;
	@PostMapping(value="/registration")	
	public String postUser(@ModelAttribute User user) {
		User existDetails=us.emailExist(user);

		if(existDetails==null) {
			us.postUser(user);
			System.err.println("user added successfull");
		}
		else {
			System.out.println("duplicate records");
		}
		return "login";

	}
	@PostMapping(value="/validate")
	public String addUser(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session) {

		System.out.println(email+"   "+ password+"  login");
		if(us.validUser(email, password)==true) {
			
			session.setAttribute("email", email);
			
			String role=us.getRole(email);
			if(role.equals("Admin")) {
				return "adminhome";
			}
			else {
				return "customerhome";
			}
		}
		else {
			return "login";
		}
		
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	@PostMapping("/updatepassword")
	public String updatePassword(@RequestParam ("email") String email,@RequestParam("password") String password,@RequestParam ("password") String confirmPassword ) {
		if(password.equals(confirmPassword)) {
			us.updatePassword(email,password);
		}
		return "login";
	}
		
	
}
