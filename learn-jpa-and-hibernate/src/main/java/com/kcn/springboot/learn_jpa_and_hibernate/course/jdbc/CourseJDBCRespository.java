package com.kcn.springboot.learn_jpa_and_hibernate.course.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kcn.springboot.learn_jpa_and_hibernate.course.Course;



@Repository
public class CourseJDBCRespository {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	private static String INSERT_QUERY=
			"""
			insert into course (id,name,author)
			values (?,?,?)
			
			""";
	
	private static String DELETE_QUERY=
			"""
			delete from course where id=?
			
			""";
	private static String SELECT_QUERY=
			"""
			select * from course where id=?
			
			""";
	private static String SELECT_ALL_QUERY=
			"""
			select * from course
			
			""";
	
	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_QUERY,course.getId(),course.getName(),course.getAuthor());
	}
	public void delete(long id) {
		springJdbcTemplate.update(DELETE_QUERY, id);
	}
	public Course get(long id) {
		//Result Set -> Bean -> RowMapper ->
		return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
	}
	public List<Course> getAll() {
		    return springJdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(Course.class));
	}
	
}
