package com.kcn.spring_framework1.helloworld;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kcn.spring_framework1.game.*;



public class App02GamingBasicJava2 {

	public static void main(String[] args) {
		
		// 1.Launch a Spring Context
		try (var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
			System.out.println(context.getBean("person2"));
			//System.out.println(context.getBean(Person.class));
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		
		} catch (BeansException e) {

			e.printStackTrace();
		} 

		// 2.Configure the things that we want to Spring manage @Configuration

	
	
	
	}

	
}
