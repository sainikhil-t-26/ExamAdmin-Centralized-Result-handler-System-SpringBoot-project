package com.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.result.entity.Teacher;
import com.result.repo.TeacherRepo;

@Controller
public class AdminBasedTeacherController {
	
	@Autowired
	private TeacherRepo tr;
	
	@PostMapping("/addTeacher")
	public String addTeacher(@RequestParam("name")String name, @RequestParam("id") String id, @RequestParam("password")String password) {
		
		Teacher t = new Teacher();
		t.setId(id);		
		t.setName(name);
		t.setPassword(password);
		tr.save(t);
		return "success";
	}
	
	@PostMapping("/editTeacher")
	public String editTeacher(@RequestParam("name")String name, @RequestParam("id") String id, @RequestParam("password")String password) {
		
		Teacher t = new Teacher();
		t.setId(id);		
		t.setName(name);
		t.setPassword(password);
		tr.save(t);
		return "success";
		
	}
	
	@PostMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("id") String id) {
		
		tr.deleteById(id);
		return "success";
	}
}











