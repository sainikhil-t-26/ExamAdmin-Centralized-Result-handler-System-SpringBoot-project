package com.result.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.result.entity.Student;
import com.result.repo.StudentRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class TeacherBasedStudentController {
	
	@Autowired
	private StudentRepo sr;
	
	@PostMapping("/findStudent")
	public String findStudent(@RequestParam("rollno") String rollno,@RequestParam("teacher_name") String t_name, Model model, HttpSession session) {
		
		List<Student> list = sr.findByRollno(rollno);
		
		if(list.size()==1){
//			model.addAttribute("stu_id", list.get(0).getRollno());
//			model.addAttribute("stu_name", list.get(0).getName());
			
			
			
			model.addAttribute("teacher_name",t_name);
			model.addAttribute("student",list.get(0));
			return "add-marks";
		}
		else {
			return "not-found";
		}
		
	}

	@PostMapping("/submitStudentMarks")
	public String addMarks(
			@RequestParam("rollno")String rollno, 
			@RequestParam("java") String java_marks, 
			@RequestParam("react") String react_marks, 
			@RequestParam("oracle") String oracle_marks,
			@RequestParam("teacher_name") String t_name,
			Model model) {
		
		List<Student> list = sr.findByRollno(rollno);
		
		Student s = list.get(0);
		s.setJava_marks(Integer.parseInt(java_marks));
		s.setReact_marks(Integer.parseInt(react_marks));
		s.setOracle_marks(Integer.parseInt(oracle_marks));

		sr.save(s);
		model.addAttribute("teacher_name", t_name);
		return "welcome-teacher";
	}
}



