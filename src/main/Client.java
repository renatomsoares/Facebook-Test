package main;

import gui.ConsoleGUI;
import gui.IGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static IGUI console;
	static boolean prossegue;
	static String op;

	public static void main(String[] args) throws IOException {
		
		System.out.println("teste");
		console = new ConsoleGUI();
		prossegue = true;

		while (prossegue) {

			console.showMenu();
			op = in.readLine();

			switch (op) {
			
			case "1":
				console.imprimirAmigos();
				break;

			case "2":
				console.imprimirGrafo();
				break;
				
			//case "3":
				//console.consultaSolteirosSemSerNull();
				//break;
			case "4":
				console.imprimirCasados();
				break;
			
			case "5":
				System.out.println("Bye!");
				prossegue = false;
				break;
			}
		}
	}
}