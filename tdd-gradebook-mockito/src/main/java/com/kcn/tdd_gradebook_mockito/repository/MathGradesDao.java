package com.kcn.tdd_gradebook_mockito.repository;

import com.kcn.tdd_gradebook_mockito.model.MathGrade;
import org.springframework.data.repository.CrudRepository;

public interface MathGradesDao extends CrudRepository<MathGrade, Integer> {
    Iterable<MathGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
