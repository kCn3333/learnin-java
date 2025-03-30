package com.kcn.spring_framework1.examples.c1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;




@Component
class ClassB{
	
	private ClassA classA;
	
	ClassB(ClassA classA){
		System.out.println("ClassB constructor is loading dependiences");
		this.classA=classA;
		
	}
	
	void methodB() {
		classA.methodA();
		System.out.println("method from classB");
	}
	
	@PostConstruct
	public void initialize() {
		classA.methodA();
	}
	@PreDestroy
	public void clenup() {
		System.out.println("cleaning....");
	}
	
	
}
@Component
class ClassA{
	ClassA(){
		System.out.println("ClassA constructor");
	}
	
	void methodA() {
		System.out.println("method from classA loading");
	}
}





@Configuration
@ComponentScan("com.kcn.spring_framework1.examples.c1;")
public class PrePostAnnotationsContextLaucherApplication {
	
	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(PrePostAnnotationsContextLaucherApplication.class)){
			
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}

}
