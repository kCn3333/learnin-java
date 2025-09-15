package com.kcn.tdd_gradebook_mockito.repository;

import com.kcn.tdd_gradebook_mockito.model.HistoryGrade;
import org.springframework.data.repository.CrudRepository;

public interface HistoryGradesDao extends CrudRepository <HistoryGrade, Integer> {
    public  Iterable<HistoryGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
