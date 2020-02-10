package com.grade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grade.service.TreeService;

@Controller
@RequestMapping("/tree/*")
public class TreeController {

	@Autowired
	private TreeService service;
	
	@RequestMapping("/get")
	public void printTree(Model model) {
		model.addAttribute("tree", service.printTree());
	}
}
