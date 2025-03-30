package com.kcn.spring_framework1;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kcn.spring_framework1.game.*;


@Configuration
public class GameConfiguration {

	@Bean
	@Qualifier("pacman")
	public GamingConsole game1() {
		return new PacmanGame();
	}
	@Bean
	@Qualifier("contra")
	public GamingConsole game2() {
		return new SuperContraGame();
	}

	@Bean
	@Qualifier("mario")
	public GamingConsole game3() {
		return new MarioGame();
	}
	@Bean
	public GameRunner gameRunner(@Qualifier("mario") GamingConsole gamingConsole) {
		return new GameRunner(gamingConsole);
		
	} 



	
}
