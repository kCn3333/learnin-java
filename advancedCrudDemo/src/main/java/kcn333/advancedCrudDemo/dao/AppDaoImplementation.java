package kcn333.advancedCrudDemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kcn333.advancedCrudDemo.entity.Course;
import kcn333.advancedCrudDemo.entity.Instructor;
import kcn333.advancedCrudDemo.entity.InstructorDetail;
import kcn333.advancedCrudDemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class AppDaoImplementation implements AppDao{

    private EntityManager entityManager;

    @Autowired
    public AppDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Transactional
    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor=entityManager.find(Instructor.class,id);
        List<Course> courses=instructor.getCourses();
        for(Course course: courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);

    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course=entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail=entityManager.find(InstructorDetail.class,id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructor(int id) {
        TypedQuery<Course> query=entityManager.createQuery("from Course where instructor.id= :id", Course.class);
        query.setParameter("id", id);
        List<Course> courses;
        courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorWithCoursesByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail WHERE i.id = :id", Instructor.class);
        query.setParameter("id", id);

        return query.getSingleResult(); // Uwaga: rzuci NoResultException jeśli nie znajdzie
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
       TypedQuery<Course> query=entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id= :id", Course.class);
       query.setParameter("id",id);
       return query.getSingleResult();

    }

    @Override
    public Course findCourseAndStudentsById(int id) {
        TypedQuery<Course> query=entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.students WHERE c.id= :id",Course.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query=entityManager.createQuery("SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id= :id",Student.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student=entityManager.find(Student.class, id);
        if(student!=null){
            List<Course> courses =student.getCourses();
            for(Course course: courses){
                course.getStudents().remove(student);
            }
        }
        entityManager.remove(student);
    }

}
