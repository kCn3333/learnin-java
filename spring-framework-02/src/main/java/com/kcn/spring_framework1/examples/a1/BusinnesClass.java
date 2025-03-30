package com.kcn.spring_framework1.examples.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinnesClass {
	
	//@Autowired	// Constructor Injection
	public BusinnesClass(Dependency1 dependency1, Dependency2 dependency2) {
		System.out.println(">> Constructor Injection");
		this.dependency1=dependency1;
		this.dependency2=dependency2;
		
	}
	//@Autowired // by field Injection
	private Dependency1 dependency1;
	//@Autowired // by setter Injection
	public void setDependency1(Dependency1 dependency1) {
		System.out.println(">> by setter Injection");
		this.dependency1 = dependency1;
	}
	//@Autowired // by field Injection
	private Dependency2 dependency2;
	//@Autowired // by setter Injection
	public void setDependency2(Dependency2 dependency2) {
		System.out.println(">> by setter Injection");
		this.dependency2 = dependency2;
	}



	public String toString() {
		return "BusinnesClass using " + dependency1 + "and " + dependency2;
	}

}
