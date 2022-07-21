package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class maximot {

	
	public static void main(String[] args) {
		 
		try {
			Scanner scan = new Scanner(System.in);
			
			Tirage tirage = new Tirage();
			System.out.println("Voici le résultat du tirage :");
			tirage.afficher();
			System.out.println("Quel est le mot à trouver ?");
			String proposition = scan.nextLine().toUpperCase();
			if (!tirage.bonnesLettres(proposition)) {
				System.out.println("Lettre incorrecte !");
			}
			else if (!Dictionnaire.dansLeDico(proposition)) {
				System.out.println("Mot non présent dans le dictionnaire");
			} else {
				System.out.println("Bravo, vous marquez " + proposition.length() +
				" points");
			}
			
			System.out.println("Le mot à trouver était " + tirage.getMot());
			scan.close();
			
		
		} catch (IOException e) {
			System.err.println("Erreur de lecture dans le dictionnaire");
		}
	}

}
