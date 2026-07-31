package com.restaurants.FastFoodShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.FastFoodShop.Entity.Student;
import com.restaurants.FastFoodShop.Exception.StudentNotFoundExample;
import com.restaurants.FastFoodShop.Service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;

	//get mapping
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable int id) throws StudentNotFoundExample {
		return service.getStudent(id);
	}
	
	//save student record	
	@PostMapping("/save")
	public Student saveStudent(@RequestBody Student std) {
		return service.save(std);
	}
	
	//put - update 
//	@PutMapping("/update/{id}")
//	public Student updateStudent(@PathVariable Integer id,@RequestBody Student std) throws StudentNotFoundExample {
//		return service.update(id,std);
//	}
	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student std) throws StudentNotFoundExample {
		return service.update(std);
	}
	
	//delete
	//id
	@DeleteMapping("/delete/{id}")
	public String deletStudent(@PathVariable Integer id) throws StudentNotFoundExample {
		service.delete(id);
		return "Deleted student sucessfully...  "+ id;
	}
}
