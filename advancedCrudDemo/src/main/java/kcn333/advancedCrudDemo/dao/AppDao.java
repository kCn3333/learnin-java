package kcn333.advancedCrudDemo.dao;

import kcn333.advancedCrudDemo.entity.Course;
import kcn333.advancedCrudDemo.entity.Instructor;
import kcn333.advancedCrudDemo.entity.InstructorDetail;
import kcn333.advancedCrudDemo.entity.Student;

import java.util.List;

public interface AppDao {

    void save(Instructor instructor);
    void update(Instructor instructor);
    void update(Course course);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    void deleteCourseById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructor(int id);
    Instructor findInstructorWithCoursesByIdJoinFetch(int id);
    Course findCourseById(int id);
    void save(Course course);
    Course findCourseAndReviewsById(int id);
    Course findCourseAndStudentsById(int id);
    Student findStudentAndCoursesByStudentId(int id);
    void update(Student student);
    void deleteStudentById(int id);


}
