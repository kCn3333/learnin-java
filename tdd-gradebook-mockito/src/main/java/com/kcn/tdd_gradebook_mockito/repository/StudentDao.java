package com.kcn.tdd_gradebook_mockito.repository;

import com.kcn.tdd_gradebook_mockito.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Integer> {


    public Student findByEmailAddress(String mail);
}
