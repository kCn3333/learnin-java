package programming;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP02Exercises {

	public static void main(String[] args) {

		// 13. do behaviour parametrization for the method in numbers.stream().map(x->x*2).collect(Collectors.toList());
		
		
		List<String> courses = List.of("API", "Spring", "Spring Boot", "AWS", "Microservices", "Azure", "Docker",
				"Python");
		List<Integer> numbers = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
		

		System.out.println("========= 13 =========");
		List<Integer> newNumbers=extracted(numbers,x->x*2);
		
		
		
		System.out.println(newNumbers);
	


	}

	private static List<Integer> extracted(List<Integer> numbers,Function<? super Integer, ? extends Integer> mapper) {
		return numbers.stream().map(mapper).collect(Collectors.toList());
	}

}
