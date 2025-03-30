package com.kcn.spring_framework1.game;




public class GameRunner {
	
	
	private GamingConsole game;
	

	public GameRunner(GamingConsole game){
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
