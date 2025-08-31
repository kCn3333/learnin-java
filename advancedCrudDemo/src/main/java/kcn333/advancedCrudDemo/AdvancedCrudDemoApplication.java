package kcn333.advancedCrudDemo;

import kcn333.advancedCrudDemo.dao.AppDao;
import kcn333.advancedCrudDemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedCrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedCrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner -> {
			System.out.println(">> hello world");
			//deleteInstructorById(appDao, 2);
			//findInstructorDeteailById(appDao,1);
			//createInstructor(appDao);
			//deleteInstructorDetailById(appDao,4);
			//createInstructorWithCourses(appDao);
			//findInstructorWithCourses(appDao,9);
			//findInstructorWithCoursesJoinFetch(appDao, 9);
			//updateInstructor(appDao);
			//updateCourse(appDao);
			//deleteInstructorById(appDao,9);
			//deleteCourseById(appDao,10);
			//createCourseAndReviews(appDao);
			//findCourseAndReviews(appDao,10);
			//createCourseAndStudents(appDao);
			//findCourseAndStudents(appDao,11);
			//findStudentAndCourses(appDao,1);
			//deleteCourseById(appDao,13);
			//addMoreCoursesForStudent(appDao);

			//deleteStudent(appDao,3);
		};
	}

	private void deleteStudent(AppDao appDao, int id) {
		System.out.println("deleting Student id: " + id);
		appDao.deleteStudentById(id);
		System.out.println("done");
	}

	private void addMoreCoursesForStudent(AppDao appDao) {
		int id=1;
		Student student=appDao.findStudentAndCoursesByStudentId(id);
		student.addCourse(new Course("cooking"));
		appDao.update(student);
	}

	private void findStudentAndCourses(AppDao appDao, int id) {
		Student student=appDao.findStudentAndCoursesByStudentId(id);
		System.out.println("found student: "+student);
		System.out.println("\tcourses: "+student.getCourses());
	}

	private void findCourseAndStudents(AppDao appDao, int id) {
		Course course=appDao.findCourseAndStudentsById(id);
		System.out.println("found course: "+course);
		System.out.println("\tstudents: "+course.getStudents());
	}

	private void createCourseAndStudents(AppDao appDao) {
		Course course=new Course("playing the piano");
		course.addStudent(new Student("Johny", "Cage", "jphny@cage.tw"));
		course.addStudent(new Student("Sonya", "Blade", "sonya@o2.pl"));
		course.addStudent(new Student("Bobby","Builder", "bob@gmail.com"));
		appDao.save(course);
	}

	private void findCourseAndReviews(AppDao appDao, int id) {
		Course course=appDao.findCourseAndReviewsById(id);
		System.out.println("found course: "+course);
		System.out.println("\t reviews: "+course.getReviews());
	}

	private void createCourseAndReviews(AppDao appDao) {
		Course course = new Course("Playing the guitar");
		course.addReview(new Review("really great course"));
		course.addReview(new Review("fantastic course"));
		appDao.save(course);
	}

	private void deleteCourseById(AppDao appDao, int id) {
		System.out.println("deleting Course id: " + id);
		appDao.deleteCourseById(id);
		System.out.println("done");
	}

	private void updateCourse(AppDao appDao) {
		Course course=appDao.findCourseById(9);
		course.setTitle("Professional Skateboarding");
		appDao.update(course);
	}

	private void updateInstructor(AppDao appDao) {
		Instructor instructor=appDao.findInstructorById(9);
		instructor.setFirstName("Mary");
		instructor.setLastName("Soprano");
		instructor.setEmail("m_soprano@mail.com");
		appDao.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao, int id) {
		Instructor instructor= appDao.findInstructorWithCoursesByIdJoinFetch(id);
		System.out.println(instructor);
		System.out.println("\tInstructorCourses: "+instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDao appDao, int id) {
		Instructor instructor= appDao.findInstructorById(id);
		System.out.println("Instructor: "+instructor);
		List<Course> courses=appDao.findCoursesByInstructor(id);
		instructor.setCourses(courses);
		System.out.println("\tInstructorCourses: "+instructor.getCourses());

	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor instructor = new Instructor("Susan", "Nowakowa", "susan@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("youtube.com/bobby","cheese");
		instructor.setInstructorDetail(instructorDetail);
		Course course1= new Course("Advanced Java Course");
		Course course2 = new Course("Polish Language");
		instructor.addCourse(course1);
		instructor.addCourse(course2);
		appDao.save(instructor);

	}

	private void deleteInstructorDetailById(AppDao appDao, int id) {
		System.out.println("deleting InstructorDetail id: " + id);
		appDao.deleteInstructorDetailById(id);
		System.out.println("done");

	}

	private void findInstructorDeteailById(AppDao appDao, int id) {
		InstructorDetail instructorDetail= appDao.findInstructorDetailById(id);
		Instructor instructor=instructorDetail.getInstructor();
		System.out.println("InstructorDetail: "+instructorDetail);
		System.out.println("\tInstructor: "+instructor);
	}

	private void deleteInstructorById(AppDao appDao, int id) {
		System.out.println("deleting Instructor id: " + id);
		appDao.deleteInstructorById(id);
		System.out.println("done");
	}

	private void findInstructorById(AppDao appDao, int id) {
		Instructor instructor = appDao.findInstructorById(id);
		System.out.println("["+id+"]Instructor : "+instructor);
		System.out.println("\tInstructorDetail: "+instructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		Instructor instructor = new Instructor("Tobby", "Nowak", "tobby@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("youtube.com/bobby","tennis");
		instructor.setInstructorDetail(instructorDetail);
		System.out.println("saving instructor: " + instructor);
		appDao.save(instructor);
	}

}
