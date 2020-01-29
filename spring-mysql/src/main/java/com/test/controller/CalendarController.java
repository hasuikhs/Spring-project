package com.test.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.GlobalDefine;
import com.test.domain.ScheduleVO;
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
	@RequestMapping("/schedule/create")
	public void createSchedule() {
		
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/schedule/create")
	public String createSchedule(@RequestParam("userno") int userno, @RequestParam("annititle") String annititle, @RequestParam("annidate") String annidate, @RequestParam("isholiday") String isholiday) {
		
		ScheduleVO schedule = new ScheduleVO();
		schedule.setUserno(userno);
		schedule.setAnnititle(annititle);
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = transFormat.parse(annidate);
			schedule.setAnnidate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		schedule.setIsholiday(isholiday);
		
		scheduleservice.insertSchedule(schedule);

		int year = schedule.getAnnidate().getYear() + GlobalDefine.CORRECTED_YEAR;
		int month = schedule.getAnnidate().getMonth() + GlobalDefine.CORRECTED_MONTH;
		int day = schedule.getAnnidate().getDate();
		
		return "redirect:/calendar/schedule/get?year=" + year + "&month=" + month + "&day=" + day + "&userno=" + userno;
	}
}
