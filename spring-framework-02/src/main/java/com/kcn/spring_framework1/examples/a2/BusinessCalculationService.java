package com.kcn.spring_framework1.examples.a2;


import org.springframework.stereotype.Component;

@Component
public class BusinessCalculationService {

	private DataService dataService;
	
	public BusinessCalculationService(DataService dataService) {
		System.out.println("BusinessCalculationService");
		this.dataService=dataService;
	}
	public int findMax() {
		return dataService.findMax();
	}
}
