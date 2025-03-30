package com.kcn.spring_framework1;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kcn.spring_framework1.game.GameRunner;
import com.kcn.spring_framework1.game.PacmanGame;


public class App03GamingSpringBean {

	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(GameConfiguration.class)){
			
			context.getBean(PacmanGame.class).up();
			context.getBean(GameRunner.class).run();
			
			
		} catch (BeansException e) {

			e.printStackTrace();
		} 
	}

}
