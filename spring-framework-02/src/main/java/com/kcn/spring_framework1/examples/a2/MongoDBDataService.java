package com.kcn.spring_framework1.examples.a2;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;


@Component

public class MongoDBDataService implements DataBase{

	@Override
	public int[] retriveData() {
		System.out.println("in MongoDB");
		return new int[] {2,5435,32,21,6,0,213,523,153,742,325,741};
	}

}
