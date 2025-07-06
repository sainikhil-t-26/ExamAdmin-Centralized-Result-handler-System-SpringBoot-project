package com.result.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.result.entity.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, String>{
	
	List<Teacher> findByIdAndPassword(String id, String password);
}
