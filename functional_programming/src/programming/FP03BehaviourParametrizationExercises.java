package programming;

import java.util.List;

public class FP03BehaviourParametrizationExercises {

	public static void main(String[] args) {

		// 7. square every number of characters in each course name
		// 8. cube every number in a list and find the sum of cubes
		// 9. find sum of odd numbers in a list
		// 10. create a list with even numbers filtered from numbers list
		// 11. create a list with lengths of all courses
		
		
		List<String> courses = List.of("API", "Spring", "Spring Boot", "AWS", "Microservices", "Azure", "Docker",
				"Python");
		List<Integer> numbers = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
		
		// ad 7.
		System.out.println("========= 7 =========");
		courses.stream().map(c-> c+ " " + c.length()*c.length()).forEach(System.out::println);
		// ad 8.
		System.out.println("========= 8 =========");
		System.out.println("sum= " + numbers.stream().map(n->n*n*n).reduce(0,(x,y)->x+y));
		// ad 9.
		System.out.println("========= 9 =========");
		System.out.println("sum= " + numbers.stream().filter(x->x%2!=0).reduce(0,(x,y)->x+y));
		System.out.println("========= 10 =========");
		List<Integer> evenNumbers=numbers.stream().filter(x->x%2==0).toList();
		System.out.println(evenNumbers);
		System.out.println("========= 11 =========");
		List<Integer> lengths= courses.stream().map(c->c.length()).toList();
		System.out.println(lengths);


	}

}
