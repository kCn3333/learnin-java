package com.kcn.spring_framework1.examples.a1;



import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.kcn.spring_framework1.examples.a1;")
public class SimplySpringContextLaucherApplication {
	
//	@Bean
//	public GameRunner gameRunner(@Qualifier("mario") GamingConsole gamingConsole) {
//		var game= new GameRunner(gamingConsole);
//		return game;
//		
//	} 


	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(SimplySpringContextLaucherApplication.class)){
			
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println("=============================================");
			System.out.println(context.getBean(BusinnesClass.class));
			
		}
	}

}
