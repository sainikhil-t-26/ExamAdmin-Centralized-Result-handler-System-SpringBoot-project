package com.result.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.result.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String>{
	
	
	List<Admin> findByEmailAndPassword(String email, String password);
}
