package com.kcn.spring_framework1.game;

import org.springframework.stereotype.Component;

@Component
public class PacmanGame implements GamingConsole{
	
	public PacmanGame() {
		System.out.println(" _______                                                       ");
        System.out.println("|       \\                                                      ");
        System.out.println("| $$$$$$$\\ ______    _______  ______ ____    ______   _______  ");
        System.out.println("| $$__/ $$|      \\  /       \\|      \\    \\  |      \\ |       \\ ");
        System.out.println("| $$    $$ \\$$$$$$\\|  $$$$$$$| $$$$$$\\$$$$\\  \\$$$$$$\\| $$$$$$$\\");
        System.out.println("| $$$$$$$ /      $$| $$      | $$ | $$ | $$ /      $$| $$  | $$");
        System.out.println("| $$     |  $$$$$$$| $$_____ | $$ | $$ | $$|  $$$$$$$| $$  | $$");
        System.out.println("| $$      \\$$    $$ \\$$     \\| $$ | $$ | $$ \\$$    $$| $$  | $$");
        System.out.println(" \\$$       \\$$$$$$$  \\$$$$$$$ \\$$  \\$$  \\$$  \\$$$$$$$ \\$$   \\$$"+"\n");
		
	}
	
	public void up() {
		System.out.println("go up");
	}
	public void down() {
		System.out.println("go down");
	}
	public void right() {
		System.out.println("go right");
	}
	public void left() {
		System.out.println("go left");
	}

}
