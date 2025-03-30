package programming;


import java.util.List;
import java.util.function.Predicate;

public class FP01Functional {


	
	
	public static void main(String[] args) {
		
		// method to pritn all the numbers in the list in seperate lines
		
		List<Integer> numbers = List.of(10,9,8,7,6,5,4,3,2,1,0);
		
		System.out.println(" === all numbers === ");
		numbers.stream().forEach(System.out::println);
		// even numbers
		Predicate<Integer> predicate= (t) -> (t%2!=0);
		System.out.println(" === squares of odd numbers === ");
		numbers.stream().filter(predicate).map(c->c*c).map(c-> c + " ").forEach(System.out::print);	

	}

}
