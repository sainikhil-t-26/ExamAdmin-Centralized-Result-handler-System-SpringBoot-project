package com.result.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Component
@Data
public class Teacher {
	
	@Id
	private String id;
	private String name;
	private String password;
}