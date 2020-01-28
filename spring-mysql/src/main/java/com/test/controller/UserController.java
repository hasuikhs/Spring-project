package com.test.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.domain.UserVO;
import com.test.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/user/*")
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public String logPage() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute UserVO user, HttpSession session) {
		long result = service.login(user).getUserno();
		ModelAndView mav = new ModelAndView();
		
		Calendar c = Calendar.getInstance();
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		if (result >= 1) {
			mav.setViewName("redirect:../calendar/get?year=" + year + "&month=" + month + "&ms=0");
			mav.addObject("userno", result);
			
		} else {
			mav.setViewName("../user/login");
			mav.addObject("msg", "fail");
		}
		return mav;
	
	}
}
