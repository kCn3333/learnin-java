package com.kcn.springboot.learn_spring_boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourseController {
	
	@RequestMapping("/courses")
	public List<Course> retriveAllCourses(){
	return Arrays.asList(
			new Course(101,"Java","Telusko"),
			new Course(102,"C++","Bob"),
			new Course(103,"Learn AWS","28minutes"),
			new Course(104,"Learn Azure","28minutes"),
			new Course(105,"Learn GoogleCloud","28minutes")
			
			);	
	}

}
