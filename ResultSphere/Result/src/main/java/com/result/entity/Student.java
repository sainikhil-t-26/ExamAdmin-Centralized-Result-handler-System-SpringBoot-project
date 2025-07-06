package com.result.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Component
@Data
public class Student {
	
	@Id
	private String rollno;
	private String name;
	private String mname;
	private String fname;
	private String dob;
	private int java_marks;
	private int react_marks;
	private int oracle_marks;
}
	