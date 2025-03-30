package com.kcn.spring_framework1.examples.a2;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class DataService {

	private DataBase dataBase;
	
	public DataService(DataBase dataBase){
		this.dataBase=dataBase;
	}

	public int findMax() {
		return Arrays.stream(dataBase.retriveData()).max().orElse(0);
	}
	
}
