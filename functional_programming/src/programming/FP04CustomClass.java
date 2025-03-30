package programming;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Course2 {
	
	private String name;
	private String category;
	private int reviewScore;
	private int noOfStudents;
	
	
	
	public Course2(String name, String category, int reviewScore, int noOfStudents) {
		super();
		this.name = name;
		this.category = category;
		this.reviewScore = reviewScore;
		this.noOfStudents = noOfStudents;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", category=" + category + ", reviewScore=" + reviewScore + ", noOfStudents="
				+ noOfStudents + "]";
	}
	
	
	
}



public class FP04CustomClass {

	public static void main(String[] args) {

		List<Course> courses = List.of(
			    new Course("Java Basics", "Programming", 95, 1150),
			    new Course("Spring Boot", "Web Development", 90, 800),
			    new Course("Python for Data Science", "Data Science", 88, 1500),
			    new Course("Machine Learning", "AI", 92, 1100),
			    new Course("JavaScript Fundamentals", "Web Development", 76, 1350),
			    new Course("Docker Essentials", "DevOps", 87, 600),
			    new Course("Kubernetes for Beginners", "DevOps", 89, 750),
			    new Course("React.js Advanced", "Frontend", 86, 750),
			    new Course("SQL & Databases", "Database", 91, 1400),
			    new Course("Cybersecurity Basics", "Security", 73, 540)
			);
		
		
		System.out.println(courses.stream().allMatch(c->c.getReviewScore()<90));
		System.out.println("=================================================");
		courses.stream().sorted((o1, o2) -> o1.getReviewScore()>o2.getReviewScore()?1:-1).forEach(System.out::println);
		System.out.println("=================================================");
		courses.stream().sorted(Comparator.comparing(Course::getNoOfStudents).reversed()).forEach(System.out::println);
		System.out.println("=================================================");
		Comparator<Course> comparingByNoOfStudentsAndNoOfReview = 
				Comparator.comparingInt(Course::getNoOfStudents).thenComparingInt(Course::getReviewScore).reversed();
		courses.stream().sorted(comparingByNoOfStudentsAndNoOfReview).forEach(System.out::println);
		System.out.println("=================================================");
		System.out.println(courses.stream().filter(c->c.getNoOfStudents()>1000).mapToInt(Course::getNoOfStudents).average());
		System.out.println("=================================================");
		System.out.println(courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory, 
						Collectors.maxBy(Comparator.comparingInt(Course::getReviewScore)))));
		System.out.println("=================================================");
		System.out.println(courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory, 
						Collectors.mapping(Course::getName, Collectors.toList()))));
	}


}
