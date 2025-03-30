package com.kcn.spring_framework1.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


record Person (String name, int age, Address address) {};
record Address (String street, int number) {};

@Configuration
public class HelloWorldConfiguration {

	
	@Bean
	public String name() {
		return "Bob";
	}
	
	@Bean
	public int age() {
		return 33;
	}
	
	@Bean
	public Person person () {
		return new Person(name(),age(),address());
	}
	@Bean
	public Person person2 (String name, int age, Address address) {
		return new Person(name,age,address);
	}
	
	@Bean
	public Address address() {
		return new Address("White Street", 11);
	}
	
}
