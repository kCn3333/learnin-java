package com.kcn.spring_framework1.examples.a0;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kcn.spring_framework1.examples.a1;")
public class DependencyInjectionLaucherApplication {
	
//	@Bean
//	public GameRunner gameRunner(@Qualifier("mario") GamingConsole gamingConsole) {
//		var game= new GameRunner(gamingConsole);
//		return game;
//		
//	} 


	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(DependencyInjectionLaucherApplication.class)){
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}

}
