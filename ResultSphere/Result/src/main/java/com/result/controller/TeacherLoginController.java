package com.result.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.result.entity.Student;
import com.result.entity.Teacher;
import com.result.repo.StudentRepo;
import com.result.repo.TeacherRepo;

@Controller
public class TeacherLoginController {
	
	@Autowired
	private TeacherRepo tr;
	
	@Autowired
	private StudentRepo sr;

	@GetMapping("/teacherLogin")
	public String teacherLogin() {
		
		return "teacher-login";
	}
	
	
	@PostMapping("/validateTeacher")
	public String validateTeacher(@RequestParam("id") String id, @RequestParam("password")String password, Model model) {
		
		List<Teacher> list = tr.findByIdAndPassword(id, password);
		
		int t = list.size();
		
		if(t==1) {	
			model.addAttribute("teacher_name",list.get(0).getName());
			return "welcome-teacher";
		}
		else {
			return "not-found";
		}
	}

	
}

















