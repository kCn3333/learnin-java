package com.kcn.spring_framework1;

import com.kcn.spring_framework1.game.*;


public class App01GamingBasicJava {

	public static void main(String[] args) {
		
		//var game =new MarioGame();
		//var game = new SuperContraGame();
		var game = new PacmanGame();
		var gameRunner=new GameRunner(game);
		gameRunner.run();

	}

}
