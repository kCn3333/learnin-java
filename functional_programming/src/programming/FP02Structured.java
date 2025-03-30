package programming;

import java.util.List;

public class FP02Structured {

	public static void main(String[] args) {

		// method to pritn all the numbers in the list in seperate lines

		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 0);
	

		System.out.print("sum: ");
		addListStructured(numbers);

	}
	
	private static void addListStructured(List<Integer> numbers) {
		int sum=0;
		for(int number: numbers) {
			sum+=number;
		}
		System.out.println(sum);
		
	}

}
