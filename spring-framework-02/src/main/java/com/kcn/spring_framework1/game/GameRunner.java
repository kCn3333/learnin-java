package com.kcn.spring_framework1.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	
	
	private GamingConsole game;
	
	//@Autowired
	public GameRunner(@Qualifier("contra")GamingConsole game){
		this.game=game;
		
	}
	

	public void run() {

		System.out.println("game started..." +game);
		game.down();
		game.up();
		game.right();
		game.left();
	}

}
