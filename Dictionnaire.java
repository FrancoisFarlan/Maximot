package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Dictionnaire {

	public static final String FICHIER_MOTS = "D:\\Workspace\\Maximot\\src\\controller/dictionnaire.txt";
	public static final int NB_MOTS = 22506;
	public static Random r = new Random();
	
	public static boolean dansLeDico(String proposition) throws IOException{
		boolean resultat = false;
		try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS);
				Scanner s = new Scanner(fichier)) {
				String motDictionnaire;
					while(!resultat && s.hasNext()) {
						motDictionnaire = s.nextLine().toUpperCase();
						resultat = motDictionnaire.equals(proposition);
						
					}
		return resultat;
		}
	}
	
	public static String tirerMotAleatoirement() throws IOException {
		//tirer int aléatoire en fonction du nb de mots ds le dictionnaire
		int numMot = r.nextInt(NB_MOTS);
		//lecture dans le dictionnaire jusqu'au mot voulu
		try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS);
		Scanner s = new Scanner(fichier)) {
			for (int i = 1; i <= numMot; i++)
				s.nextLine();
			return s.nextLine().toUpperCase();
		}
	}
}