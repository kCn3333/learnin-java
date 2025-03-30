package com.kcn.spring_framework1.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Component
@Priority(value = 1)
@Qualifier("mario")
public class MarioGame implements GamingConsole{
	
	public MarioGame() {
		        System.out.println(" __       __                      __             ______                                    ");
		        System.out.println("|  \\     /  \\                    |  \\           /      \\                                   ");
		        System.out.println("| $$\\   /  $$  ______    ______   \\$$  ______  |  $$$$$$\\  ______   ______ ____    ______  ");
		        System.out.println("| $$$\\ /  $$$ |      \\  /      \\ |  \\ /      \\ | $$ __\\$$ |      \\ |      \\    \\  /      \\ ");
		        System.out.println("| $$$$\\  $$$$  \\$$$$$$\\|  $$$$$$\\| $$|  $$$$$$\\| $$|    \\  \\$$$$$$\\| $$$$$$\\$$$$\\|  $$$$$$\\");
		        System.out.println("| $$\\$$ $$ $$ /      $$| $$   \\$$| $$| $$  | $$| $$ \\$$$$ /      $$| $$ | $$ | $$| $$    $$");
		        System.out.println("| $$ \\$$$| $$|  $$$$$$$| $$      | $$| $$__/ $$| $$__| $$|  $$$$$$$| $$ | $$ | $$| $$$$$$$$");
		        System.out.println("| $$  \\$ | $$ \\$$    $$| $$      | $$ \\$$    $$ \\$$    $$ \\$$    $$| $$ | $$ | $$ \\$$     \\");
		        System.out.println(" \\$$      \\$$  \\$$$$$$$ \\$$       \\$$  \\$$$$$$   \\$$$$$$   \\$$$$$$$ \\$$  \\$$  \\$$  \\$$$$$$$"+"\n");
		    }

	
	public void up() {
		System.out.println("Jump");
	}
	public void down() {
		System.out.println("jump into a hole");
	}
	public void right() {
		System.out.println("accelerate");
	}
	public void left() {
		System.out.println("go back");
	}
	
	

	
	
}
