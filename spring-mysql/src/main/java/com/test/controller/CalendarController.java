package com.test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.GlobalDefine;
import com.test.service.CalendarService;
import com.test.service.ScheduleService;

@Controller
@RequestMapping("/calendar/*")
public class CalendarController {
	
	@Autowired
	private CalendarService calendarservice;
	
	@Autowired
	private ScheduleService scheduleservice;
	
	@RequestMapping("/get")
	public void calendar(Model model, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("ms") int ms, @RequestParam("userno") long userno) {
		model.addAttribute("calendar", calendarservice.calendar(year, month, ms, userno));
	}
	
	@RequestMapping("/schedule/get")
	public void getSchedule(Model model, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("day") int day, @RequestParam("userno") int userno) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("userno", userno);
		
		model.addAttribute("schedule", scheduleservice.getScheduleOfDayByUserno(map));
		model.addAttribute("date",scheduleservice.getDayName(map));
	}
}
