package fizzbuzz;

public class FizzBuzz {
    // If number is divisible by 3 -> print Fizz
    // If number is divisible by 5 -> print Buzz
    // If number is divisible by 3 and 5 -> print FizzBuzz
    // If number is NOT divisible by 3 and 5 -> print the number

//    public static String compute(int i) {
//        return (i%3==0 && i%5==0)?"FizzBuzz":(i%3==0)?"Fizz":(i%5==0)?"Buzz":""+i;
//    }

    public static String compute(int i) {
        StringBuilder result= new StringBuilder();
        if (i%3==0) result.append("Fizz");
        if (i%5==0) result.append("Buzz");
        return result.isEmpty()?""+i:result.toString();
    }


//    private static final Map<Integer, String> rules = Map.of(
//            3, "Fizz",
//            5, "Buzz"
//    );
//
//    public static String compute2(int i) {
//        String result = rules.entrySet().stream()
//                .filter(e -> i % e.getKey() == 0)
//                .map(Map.Entry::getValue)
//                .collect(Collectors.joining());
//
//        return result.isEmpty() ? String.valueOf(i) : result;
//    }
}