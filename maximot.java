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
			System.out.println();
			System.out.println("Quel est le mot à trouver ?");
			String proposition = scan.nextLine().toUpperCase();
			System.out.println();
			
			if (!tirage.bonnesLettres(proposition)) {
				System.out.println("Lettre incorrecte !");
				System.out.println();
			} else if (!tirage.estUneSolution(proposition)) {
				System.out.println("Mot non présent dans le dictionnaire");
				System.out.println();
			} else {
				System.out.println("Bravo, vous marquez " + proposition.length() + " points");
				System.out.println();
			}
			
			scan.close();
			System.out.println("Voici quelles étaient les solutions acceptees :");
			System.out.println();
			
			for(int k : tirage.getMot().keySet()) {
				System.out.printf("Mots de %d caractères%n", k);
				for(String mot : tirage.getMot().get(k)) {
					System.out.printf(" - %s%n", mot);
				}
				System.out.println();
			}
		
		} catch (IOException e) {
			System.err.println("Erreur de lecture dans le dictionnaire");
		}
	}

}
