package programming;

import java.util.stream.IntStream;

public class FP05Threads {

	public static void main(String[] args) {
		
		Runnable runnable=() -> IntStream.range(0, 1000).forEach(i->System.out.println("["+Thread.currentThread().getId()+"] :" +i));


		
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();

	}

}
