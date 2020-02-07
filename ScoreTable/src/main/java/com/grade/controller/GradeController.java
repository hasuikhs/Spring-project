package com.grade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grade.service.GradeService;

@Controller
@RequestMapping("/grade/*")
public class GradeController {

	@Autowired
	private GradeService service;

	@RequestMapping("/get")
	public void gradeSort(Model model, @RequestParam(value="sort", required = false) String sort) {
		if (sort == null || sort.isEmpty()) {
			model.addAttribute("grade",service.readData());
		} else {
			model.addAttribute("grade", service.sort(sort));
		}
		model.addAttribute("summary", service.calculDataBySubject());
	}
}
