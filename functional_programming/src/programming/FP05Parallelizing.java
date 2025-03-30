package programming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class FP05Parallelizing {

	public static void main(String[] args) {
		

		
		long timeStart = System.currentTimeMillis();
		System.out.println(LongStream.range(0,1000000000).sum());

		System.out.println("time:"+(System.currentTimeMillis()-timeStart)+"ms");
		
		timeStart=System.currentTimeMillis();
		System.out.println(LongStream.range(0,1000000000).parallel().sum());

		System.out.println("time:"+(System.currentTimeMillis()-timeStart)+"ms");
		
		
		
		
		List<String> list=new ArrayList<>(List.of("aaa","bbb","ccc"));
		
		list.replaceAll(x->x.toUpperCase());
		list.removeIf(x->x.contains("A"));
		System.out.println(list);
		
	}
		

}


