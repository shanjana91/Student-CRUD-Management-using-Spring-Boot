package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.StudentDAO;
import com.example.Repository.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	StudentRepo repo;
	
	public List<StudentDAO> getStudents() {
		return repo.findAll();
		}
	
	public StudentDAO getById(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public void create(StudentDAO student) {
		repo.save(student);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	// CASE 2 : METHOD DEFINITION IF MODELANDVIEW CASE NOT USED
	public void editsave(StudentDAO student) {
		StudentDAO existing=repo.findById(student.getId()).orElse(null);
		existing.setStudent_name(student.getStudent_name());
		existing.setCourse(student.getCourse());
		existing.setFee(student.getFee());
		repo.save(existing);
		
	}
	
}
