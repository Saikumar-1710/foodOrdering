package com.restaurants.FastFoodShop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.FastFoodShop.Entity.Student;
import com.restaurants.FastFoodShop.Exception.StudentNotFoundExample;
import com.restaurants.FastFoodShop.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	public Student getStudent(int id) throws StudentNotFoundExample {
		return repository.findById(id).orElseThrow(()-> 
										new StudentNotFoundExample("Student Not Found with Id :"+id)); 
	}
	
	public Student save(Student std) {
		return repository.save(std);
	}
	
//	public Student update(Integer id, Student std) throws StudentNotFoundExample{
//		
//		Student student = repository.findById(id).orElseThrow(()-> 
//		new StudentNotFoundExample("Student Not Found with Id :"+id));
//	
//		student.setSname(std.getSname());
//		student.setEmail(std.getEmail());
//		student.setMarks(std.getMarks());
//		
//		return repository.save(student);
//	}
	
	public Student update(Student std){
			return repository.save(std);
		}

	public void delete(Integer id) throws StudentNotFoundExample {
		Student std = repository.findById(id).orElseThrow(()-> new StudentNotFoundExample("Student Not Found"));
		repository.delete(std);
	}
}
