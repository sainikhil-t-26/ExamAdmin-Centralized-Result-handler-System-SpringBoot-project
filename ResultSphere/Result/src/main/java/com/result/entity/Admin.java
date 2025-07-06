package com.result.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {
	
	@Id
	private String email;
	private String name;
	private String password;
}
