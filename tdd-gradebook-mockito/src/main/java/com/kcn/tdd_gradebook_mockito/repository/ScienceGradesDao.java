package com.kcn.tdd_gradebook_mockito.repository;

import com.kcn.tdd_gradebook_mockito.model.ScienceGrade;
import org.springframework.data.repository.CrudRepository;

public interface ScienceGradesDao extends CrudRepository <ScienceGrade, Integer> {
    public  Iterable<ScienceGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
