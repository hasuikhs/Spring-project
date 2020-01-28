package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.service.CalendarService;

@Controller
@RequestMapping("/calendar/*")
public class CalendarController {
	
	@Autowired
	private CalendarService service;
	
	@RequestMapping("/get")
	public void calendar(Model model, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("ms") int ms, @RequestParam("userno") long userno) {
		System.out.println("userno------------------" + userno);
		model.addAttribute("calendar", service.calendar(year, month, ms, userno));
	}
}
