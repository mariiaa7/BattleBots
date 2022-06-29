package main;
import javax.swing.JFrame;

import game.TorneoFrame;
import init.Iniziale;

public class Main {

	public static void main(String[] args) {
		JFrame init = new Iniziale();
		init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init.setVisible(true);
	}

}
