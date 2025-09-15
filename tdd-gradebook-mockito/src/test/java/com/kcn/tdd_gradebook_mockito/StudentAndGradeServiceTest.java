package com.kcn.tdd_gradebook_mockito;

import com.kcn.tdd_gradebook_mockito.model.*;
import com.kcn.tdd_gradebook_mockito.repository.HistoryGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.MathGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.ScienceGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.StudentDao;
import com.kcn.tdd_gradebook_mockito.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class StudentAndGradeServiceTest {

    @Autowired
    StudentAndGradeService studentService;
    @Autowired
    StudentDao studentDao;
    @Autowired
    MathGradesDao mathGradesDao;
    @Autowired
    ScienceGradesDao scienceGradesDao;
    @Autowired
    HistoryGradesDao historyGradesDao;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @BeforeEach
    void insertSampleData() {
        Student student = new Student();
        student.setFirstName("Boby");
        student.setLastName("Nowak");
        student.setEmailAddress("boby@email.com");
        studentDao.save(student);

        MathGrade mathGrade = new MathGrade();
        mathGrade.setStudentId(student.getId());
        mathGrade.setGrade(5.0);
        mathGradesDao.save(mathGrade);

        ScienceGrade scienceGrade = new ScienceGrade();
        scienceGrade.setStudentId(student.getId());
        scienceGrade.setGrade(3.0);
        scienceGradesDao.save(scienceGrade);

        HistoryGrade historyGrade = new HistoryGrade();
        historyGrade.setStudentId(student.getId());
        historyGrade.setGrade(4.0);
        historyGradesDao.save(historyGrade);
    }

    @AfterEach
    void cleanSampleData() {
        jdbcTemplate.execute("DELETE FROM math_grade");
        jdbcTemplate.execute("DELETE FROM science_grade");
        jdbcTemplate.execute("DELETE FROM history_grade");
        jdbcTemplate.execute("DELETE FROM student");

        jdbcTemplate.execute("ALTER TABLE student ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE math_grade ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE science_grade ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE history_grade ALTER COLUMN id RESTART WITH 1");
    }


    @Test
    void createStudentService(){
        studentService.createStudent("John","Bobsky","john@email.com");

        Student student=studentDao.findByEmailAddress("john@email.com");

        assertEquals("john@email.com", student.getEmailAddress());
    }


    @Test
    void isStudentNullCheck(){
        assertTrue(studentService.checkIsStudentNull(1));
        assertFalse(studentService.checkIsStudentNull(0));

    }

    @Test
    void deleteStudentService(){
        assertTrue(studentDao.findById(1).isPresent());
        assertTrue(mathGradesDao.findGradeByStudentId(1).iterator().hasNext());
        assertTrue(scienceGradesDao.findGradeByStudentId(1).iterator().hasNext());
        assertTrue(historyGradesDao.findGradeByStudentId(1).iterator().hasNext());

        studentService.deleteStudent(1);

        assertFalse(studentDao.findById(1).isPresent());
        assertFalse(mathGradesDao.findGradeByStudentId(1).iterator().hasNext());
        assertFalse(scienceGradesDao.findGradeByStudentId(1).iterator().hasNext());
        assertFalse(historyGradesDao.findGradeByStudentId(1).iterator().hasNext());
    }

    @Sql("/insertData.sql")
    @Test
    void getGradeBookService(){
        Iterable<Student> collegeStudents=studentService.getGradebook();

        List<Student> collegeStudentList = new ArrayList<>();
        for(Student collegeStudent : collegeStudents){
            collegeStudentList.add(collegeStudent);
        }
        assertEquals(3,collegeStudentList.size());
    }

    @Test
    void createGradeService(){
        Iterable<Student> students =studentDao.findAll();
        assertEquals(1,((Collection<Student>) students).size());

        //create grade
        assertTrue(studentService.createGrade(5.0,1,"math"));
        assertTrue(studentService.createGrade(4.0,1,"science"));
        assertTrue(studentService.createGrade(3.0,1,"history"));

        //get all grades with StudentId
        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(1);

        // verify that there is a grade
        assertEquals(2, ((Collection<MathGrade>) mathGrades).size(), "student has 2 math grades");
        assertEquals(2, ((Collection<ScienceGrade>) scienceGrades).size(), "student has 2 science grades");
        assertEquals(2, ((Collection<HistoryGrade>) historyGrades).size(), "student has 2 history grades");
    }

    @Test
    void createGradeServiceReturnFalse(){
        assertFalse(studentService.createGrade(-5.0,1,"math"));
        assertFalse(studentService.createGrade(120.0,1,"math"));
        assertFalse(studentService.createGrade(5.0,22,"math"));
        assertFalse(studentService.createGrade(5.0,1,"english"));

    }

    @Test
    void deleteGradeService(){
        assertEquals(1, studentService.deleteGrade(1,"math"), "return studentId id after delete ");
        assertEquals(1, studentService.deleteGrade(1,"science"), "return studentId id after delete ");
        assertEquals(1, studentService.deleteGrade(1,"history"), "return studentId id after delete ");

        assertEquals(0, studentService.deleteGrade(11,"history"), "return studentId id after delete ");
        assertEquals(0, studentService.deleteGrade(1,"literature"), "return studentId id after delete ");
    }

    @Test
    void studentInformation(){
        GradebookStudent gradebookStudent = studentService.studentInformation(1);
        assertNotNull(gradebookStudent);
        GradebookStudent gradebookStudent0 = studentService.studentInformation(0);
        assertNull(gradebookStudent0);
        assertEquals(1, gradebookStudent.getId());

        assertEquals("Boby", gradebookStudent.getFirstName());
        assertEquals("Nowak", gradebookStudent.getLastName());
        assertEquals("boby@email.com", gradebookStudent.getEmailAddress());
        assertEquals(1, gradebookStudent.getStudentGrades().getMathGradeResults().size());
        assertEquals(1, gradebookStudent.getStudentGrades().getHistoryGradeResults().size());
        assertEquals(1, gradebookStudent.getStudentGrades().getScienceGradeResults().size());

    }
    @Test
    void studentInformationThatNonExist(){
        assertNull(studentService.studentInformation(0));
    }


}
