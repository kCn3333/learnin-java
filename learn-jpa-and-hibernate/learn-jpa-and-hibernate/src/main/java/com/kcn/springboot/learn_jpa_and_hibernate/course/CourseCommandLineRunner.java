package com.kcn.springboot.learn_jpa_and_hibernate.course;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kcn.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;




@Component
public class CourseCommandLineRunner implements CommandLineRunner{
	
//	@Autowired
//	private CourseJDBCRespository repo;
//	@Autowired
//	private CourseJpaRepository repo;
	@Autowired
	private CourseSpringDataJpaRepository repo;

	@Override
	public void run(String... args) throws Exception {
			repo.save(new Course(102,"C++","Telusko"));
			repo.save(new Course(101,"Java","Telusko"));
			repo.save(new Course(103,"AWS","in28minutes"));
			repo.save(new Course(104,"Azure","in28minutes"));
			repo.deleteById(101l);
			System.out.println(repo.findById(103l));
			System.out.println("===========================================");
			System.out.println(repo.findAll());
		
	}
	

}
