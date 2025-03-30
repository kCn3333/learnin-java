package com.kcn.spring_framework1.examples.b1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
class ClassA{
	public ClassA() {
		System.out.println("ClassA Constructor");
	}
	
}
@Component
@Lazy
class ClassB{
	private ClassA classA;
	
	public ClassB(ClassA classA) {
		System.out.println("ClassB Constructor");
		this.classA=classA;
	}
	
	void method() {
		System.out.println("method runnig...");
	}
}



@Configuration
@ComponentScan("com.kcn.spring_framework1.examples.b1;")
public class LazyInitializationLaucherApplication {
	

	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(LazyInitializationLaucherApplication.class)){
			System.out.println("elo");
			
			context.getBean(ClassB.class);
			
			
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}

}
