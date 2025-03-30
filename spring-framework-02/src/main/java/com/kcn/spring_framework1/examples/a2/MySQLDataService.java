package com.kcn.spring_framework1.examples.a2;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;


@Component
@Priority(value = 1)
public class MySQLDataService implements DataBase{

	@Override
	public int[] retriveData() {
		System.out.println("in MySQLDB");
		return new int[] {5,21,1,5325,2,534,234,63,567,684,23423};
	}

}
