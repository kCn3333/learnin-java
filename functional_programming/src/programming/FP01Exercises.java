package programming;

import java.util.List;

public class FP01Exercises {

	public static void main(String[] args) {

		// 1. only odd numbers from the list
		// 2. Courses in seperate lines
		// 3. Courses containing the word "Spring"
		// 4. Courses whose name has at least 4 letters
		// 5. cubes of odd numbers
		// 6. number of characters in each course name 
		
		List<String> courses = List.of("API", "Spring", "Spring Boot", "AWS", "Microservices", "Azure", "Docker",
				"Python");
		List<Integer> numbers = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
		
		// ad 1.
		System.out.println("========= 1 =========");
		numbers.stream().filter(n -> n % 2 != 0).forEach(System.out::println);
		// ad 2.
		System.out.println("========= 2 =========");
		courses.stream().forEach(System.out::println);
		// ad 3.
		System.out.println("========= 3 =========");
		courses.stream().filter(c -> c.contains("Spring")).forEach(System.out::println);
		// ad 4.
		System.out.println("========= 4 =========");
		courses.stream().filter(c -> c.length() >= 4).forEach(System.out::println);
		// ad 5.
		System.out.println("========= 5 =========");
		numbers.stream().filter(n->n%2!=0).map(n->n*n*n).forEach(System.out::println);
		// ad 6.
		System.out.println("========= 6 =========");
		courses.stream().map(c->c+" "+c.length()).forEach(System.out::println);

	}

}
