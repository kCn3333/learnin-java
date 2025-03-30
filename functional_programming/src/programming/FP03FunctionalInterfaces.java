package programming;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class FP03FunctionalInterfaces {

	public static void main(String[] args) {
		
		List<String> courses = List.of("API", "Spring", "Spring Boot", "AWS", "Microservices", "Azure", "Docker",
				"Python");
		List<Integer> numbers = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
		
		courses.stream().map(String::toUpperCase).forEach(System.out::println);
//		
		Stream.of(1,2,3);
		int[] tab= {1,2,3};
		Arrays.stream(tab);
	}
		
		Predicate<Integer> predicate=t->t>2;		
		BiPredicate<Integer, Integer> biPredicate=(x,y)->x>y;
		Function<Integer,Integer> function=x->x*x;
		BiFunction<Integer, Integer, String> biFunction= (x,y)->x+" "+y;
		BinaryOperator<Integer> binaryOperator=(x,y)->x+y;
		UnaryOperator<Integer> unaryOperartor=x->x*10;
		Consumer<Integer> consumer=x->System.out.print(x+" ");
		BiConsumer<Integer, BinaryOperator<Integer>> biConsume=(x,y)->System.out.print(y.apply(x, x));
		Supplier<Integer> supplier =()-> {Random r=new Random(); return r.nextInt(10);};

}


