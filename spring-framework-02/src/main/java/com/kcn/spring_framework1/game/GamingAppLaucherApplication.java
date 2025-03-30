package com.kcn.spring_framework1.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kcn.spring_framework1.game;")
public class GamingAppLaucherApplication {
	
//	@Bean
//	public GameRunner gameRunner(@Qualifier("mario") GamingConsole gamingConsole) {
//		var game= new GameRunner(gamingConsole);
//		return game;
//		
//	} 


	public static void main(String[] args) {
		
		try(var context=new AnnotationConfigApplicationContext(GamingAppLaucherApplication.class)){
			
			//context.getBean(PacmanGame.class).up();
			context.getBean(GameRunner.class).run();
			
			
		};
	}

}
