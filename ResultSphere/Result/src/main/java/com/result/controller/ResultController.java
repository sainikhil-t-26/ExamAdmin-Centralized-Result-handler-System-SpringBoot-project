package com.result.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.result.entity.Student;
import com.result.repo.StudentRepo;

@Controller
public class ResultController {
	
	@Autowired
	private StudentRepo sr;

	@GetMapping("/checkResult")
	public String checkResult() {
		
		return "check-result";
	}
	
	
	@PostMapping("/validateResult")
	public String showResult(
			@RequestParam("rollno") String rollno,
			@RequestParam("dob") String dob,
			Model model
			) {
		
		List<Student> list = sr.findByRollnoAndDob(rollno, dob);
		
		if(list.size()==1) {
			model.addAttribute("student", list.get(0));
			if(
					list.get(0).getJava_marks() >=33 && 
					list.get(0).getOracle_marks()>=33 &&
					list.get(0).getReact_marks()>=33
					)	
			{
				model.addAttribute("status", "Passed");
			}
			else {
				model.addAttribute("status", "Failed");
			}
			return "show-result";
		}
		
		else {
			return "student-not-found";
		}
	}
}





