package programming;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class FP02Functional {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 0);
		List<String> courses = List.of("API", "Spring", "Spring Boot", "AWS", "Microservices", "Azure", "Docker",
				"Python");
	
		BinaryOperator<Integer> accumulator = new BinaryOperator<Integer>() {
			
			@Override
			public Integer apply(Integer t, Integer u) {
				return t+u;
			}
		};
		numbers.stream().reduce(accumulator)
	    .ifPresent(sum -> System.out.println("Sum: " + sum));
		System.out.println("==== max number ====");
		System.out.println(numbers.stream().reduce(Integer.MIN_VALUE,(x,y)->x>y?x:y));
		System.out.println("==== sorting ====");
		courses.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("==== sorting by lenght ====");
		courses.stream().sorted(Comparator.comparing(c->c.length())).forEach(System.out::println);
		System.out.println("==== doubled list ====");
		List<Integer> doubledNumbers=numbers.stream().map(x->x*2).toList();			// unmutable, mutable -> collect(Collectors.toList())
		System.out.println(doubledNumbers);

		


	}
}
