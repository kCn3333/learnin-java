package programming;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;




class Course {
	
	private String name;
	private String category;
	private int reviewScore;
	private int noOfStudents;
	
	
	
	public Course(String name, String category, int reviewScore, int noOfStudents) {
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



public class FP04Exercises {

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
			    new Course("Cybersecurity Basics", "Security", 73, 540),
			    new Course("Advanced Java", "Programming", 96, 1200),
			    new Course("Spring Security", "Web Development", 93, 650),
			    new Course("Deep Learning", "AI", 94, 900),
			    new Course("Node.js & Express", "Web Development", 80, 1100),
			    new Course("Linux Administration", "DevOps", 85, 700),
			    new Course("C++ Advanced", "Programming", 90, 1000),
			    new Course("Cloud Computing", "DevOps", 92, 850),
			    new Course("TypeScript Mastery", "Frontend", 84, 780),
			    new Course("MongoDB & NoSQL", "Database", 88, 1300),
			    new Course("Penetration Testing", "Security", 78, 500)
			);
		
		System.out.println("=======================1=========================");		
		// 1.Wypisz wszystkie kursy, których nazwa zaczyna się na literę "J".
		courses.stream().map(Course::getName).filter(c->c.startsWith("J")).forEach(System.out::println);
		System.out.println("=======================2=========================");
		// 2. Wypisz kursy, które mają liczbę studentów większą niż 700, ale mniejszą niż 1200.
		Predicate<Course> moreThan700StudentsAndLessThen1200=c->c.getNoOfStudents()>700&&c.getNoOfStudents()<1200;
		courses.stream().filter(moreThan700StudentsAndLessThen1200).forEach(System.out::println);
		System.out.println("=======================3=========================");
		// 3. Posortuj kursy alfabetycznie według nazwy.
		courses.stream().map(Course::getName).sorted().forEach(System.out::println);
		System.out.println("=======================4=========================");
		// 4. Znajdź kurs, który ma dokładnie 750 studentów.
		courses.stream().filter(c->c.getNoOfStudents()==750).forEach(System.out::println);
		System.out.println("=======================5=========================");
		// 5. Wypisz wszystkie kursy w odwrotnej kolejności niż są zapisane na liście.
		courses.stream().toList().reversed().forEach(System.out::println);
		System.out.println("=======================6=========================");
		// 6. Wypisz unikalne liczby studentów, jakie występują w kursach.
		courses.stream().map(Course::getNoOfStudents).distinct().forEach(System.out::println);
		System.out.println("=======================7=========================");
		// 7. Znajdź kursy, które mają taką samą liczbę studentów.
		courses.stream().collect(Collectors.groupingBy(Course::getNoOfStudents))
			.entrySet().stream()
			.filter(c->c.getValue().size()>1)
			.flatMap(c->c.getValue().stream())
			.forEach(System.out::println);
		System.out.println("=======================8=========================");
		// 8. Wypisz średnią ocenę kursów z kategorii "DevOps".
		System.out.println(courses.stream().filter(c->c.getCategory().equals("DevOps")).collect(Collectors.averagingInt(Course::getReviewScore)));
		System.out.println("=======================9=========================");
		// 9. Znajdź kurs z drugą najwyższą oceną.
		System.out.println(courses.stream().sorted(Comparator.comparing(Course::getReviewScore).reversed()).skip(1).findFirst());
		System.out.println("=======================10========================");	
		// 10. Znajdź kategorię, która ma największą liczbę kursów.
		//System.out.println(courses.stream().collect(Collectors.groupingBy(Course::getCategory)).entrySet().stream().map();
		System.out.println("=======================11========================");
		// 11. Wypisz kursy, których liczba studentów jest wielokrotnością 100.
		courses.stream().filter(c->c.getNoOfStudents()%100==0).forEach(System.out::println);
		System.out.println("=======================12========================");
		// 12. Policz, ile kursów ma ocenę powyżej 85.
		System.out.println(courses.stream().filter(c->c.getReviewScore()>85).count());
		System.out.println("=======================13========================");	
		// Znajdź najmniej popularny kurs w każdej kategorii (najmniejsza liczba studentów).
		courses.stream()
	    	.collect(Collectors.groupingBy(Course::getCategory))
	    	.forEach((category, courseList) -> {
	        Optional<Course> leastPopularCourse = courseList.stream()
	            .min(Comparator.comparing(Course::getNoOfStudents));
	           
	        System.out.println("Category: " + category + ", Least Popular Course: " + leastPopularCourse);
	    });
		System.out.println("=======================14========================");	
		// 14. Wypisz kursy, w których nazwa zawiera słowo "Advanced".
		courses.stream().filter(c->c.getName().contains("Advanced")).forEach(System.out::println);
		System.out.println("=======================15========================");	
		// 15. Znajdź łączną liczbę kursów w kategoriach "Programming" i "Web Development".
		System.out.println(courses.stream().filter(c->c.getCategory().equals("Programming") || c.getCategory().equals("Web Development")).count());
		System.out.println("=======================16========================");	
		// 16. Stwórz mapę, gdzie kluczem jest pierwsza litera nazwy kursu, a wartością lista kursów.
		Map<Object, List<Course>> coursesByFirstLetter = courses.stream()
			    .collect(Collectors.groupingBy(c -> c.getName().substring(0, 1).toUpperCase()));

			coursesByFirstLetter.forEach((letter, courseList) -> {
			    System.out.println("Courses starting with " + letter + ": " + courseList);
			});
		System.out.println("=======================17========================");	
		// 17. Wypisz liczbę kursów w każdej kategorii, ale tylko dla tych, gdzie jest ich więcej niż 2.
		courses.stream()
	    	.collect(Collectors.groupingBy(Course::getCategory, Collectors.counting()))
	    	.forEach((category, count) -> {
	        if (count > 2) {
	            System.out.println("Category: " + category + ", Number of courses: " + count);
	        }
	    });
		System.out.println("=======================18========================");	
		// 18. Znajdź kurs, który ma najmniejszą liczbę studentów, ale ocenę powyżej 80.
		System.out.println(courses.stream().filter(c->c.getReviewScore()>80).sorted(Comparator.comparing(Course::getNoOfStudents)).findFirst());
		System.out.println("=======================19========================");	
		// 19. Wypisz trzy kursy z najmniejszą liczbą studentów, ale posortowane według oceny malejąco.
		courses.stream().sorted(Comparator.comparing(Course::getNoOfStudents)).limit(3).sorted(Comparator.comparing(Course::getReviewScore).reversed()).forEach(System.out::println);
		System.out.println("=======================20========================");	
		// 20. Sprawdź, czy istnieje kurs, który ma dokładnie 1000 studentów i ocenę powyżej 90.
		System.out.println(courses.stream().filter(c->c.getNoOfStudents()==1000 && c.getReviewScore()>90).findAny());		
	}


}
