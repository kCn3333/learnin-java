package programming;

import java.util.List;

public class FP01Structured {

	private static void printAllNumbersInListStructured(List<Integer> numbers) {
		for (int number : numbers) {
			System.out.println(number);
		}
	}

	private static void printEvenNumbersInListStructured(List<Integer> numbers) {
		for (int number : numbers) {
			if (number % 2 == 0)
				System.out.println(number);
		}
	}

	public static void main(String[] args) {

		// method to pritn all the numbers in the list in seperate lines

		List<Integer> numbers = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);

		printEvenNumbersInListStructured(numbers);

	}

}
