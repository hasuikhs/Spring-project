package com.grade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grade.service.GradeService;



@Controller
@RequestMapping("/grade/*")
public class GradeController {

	@Autowired
	private GradeService service;

	@RequestMapping("/get")
	public void grade(Model model) {
		model.addAttribute("grade", service.readData());
		model.addAttribute("summary", service.calculDataBySubject());
	}
}
