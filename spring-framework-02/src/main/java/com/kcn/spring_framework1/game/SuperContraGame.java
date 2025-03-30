package com.kcn.spring_framework1.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("contra")

public class SuperContraGame implements GamingConsole {
	
	public SuperContraGame() {
		System.out.println("  ______                                            ______                        __                        ");
        System.out.println(" /      \\                                          /      \\                      |  \\                       ");
        System.out.println("|  $$$$$$\\ __    __   ______    ______    ______  |  $$$$$$\\  ______   _______  _| $$_     ______   ______  ");
        System.out.println("| $$___\\$$|  \\  |  \\ /      \\  /      \\  /      \\ | $$   \\$$ /      \\ |       \\|   $$ \\   /      \\ |      \\ ");
        System.out.println(" \\$$    \\ | $$  | $$|  $$$$$$\\|  $$$$$$\\|  $$$$$$\\| $$      |  $$$$$$\\| $$$$$$$\\\\$$$$$$  |  $$$$$$\\ \\$$$$$$\\");
        System.out.println(" _\\$$$$$$\\| $$  | $$| $$  | $$| $$    $$| $$   \\$$| $$   __ | $$  | $$| $$  | $$ | $$ __ | $$   \\$$/      $$");
        System.out.println("|  \\__| $$| $$__/ $$| $$__/ $$| $$$$$$$$| $$      | $$__/  \\| $$__/ $$| $$  | $$ | $$|  \\| $$     |  $$$$$$$");
        System.out.println(" \\$$    $$ \\$$    $$| $$    $$ \\$$     \\| $$       \\$$    $$ \\$$    $$| $$  | $$  \\$$  $$| $$      \\$$    $$");
        System.out.println("  \\$$$$$$   \\$$$$$$ | $$$$$$$   \\$$$$$$$ \\$$        \\$$$$$$   \\$$$$$$  \\$$   \\$$   \\$$$$  \\$$       \\$$$$$$$");
        System.out.println("                    | $$                                                                                     ");
        System.out.println("                    | $$                                                                                     ");
        System.out.println("                     \\$$                                                                                     "+"\n");
    }

	
	public void up() {
		System.out.println("go up");
	}
	public void down() {
		System.out.println("sit down");
	}
	public void right() {
		System.out.println("shoot a bullet");
	}
	public void left() {
		System.out.println("go back");
	}
	

}
