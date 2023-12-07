package com.company;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	JFrame obj = new JFrame();
	Game game = new Game();
	obj.setBounds(10, 10, 700, 600);
	obj.setTitle("Brick Breaker");
	obj.setVisible(true);
	obj.add(game);
    }
}
