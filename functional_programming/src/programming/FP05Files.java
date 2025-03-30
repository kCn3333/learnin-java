package programming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class FP05Files {

	public static void main(String[] args) throws IOException {
		
		Files.lines(Paths.get("file.txt")).forEach(System.out::println);
		System.out.println("=========================================");
		Files.lines(Paths.get("file.txt")).map(s->s.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
		System.out.println("=========================================");
		Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);



	}

}
