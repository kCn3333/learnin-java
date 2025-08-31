package com.kcn.springboot.learn_jpa_and_hibernate.course.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcn.springboot.learn_jpa_and_hibernate.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{

}
