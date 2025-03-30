package com.kcn.spring_framework1.examples.a2;


import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kcn.spring_framework1.examples.a2;")
public class LaucherApplication {
	
	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(LaucherApplication.class)){
			
			System.out.println(context.getBean(BusinessCalculationService.class).findMax());
			
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
		}
	}

}
