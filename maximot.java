package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class maximot {

	public static final String FICHIER_MOTS = "D:\\Workspace\\Maximot\\src\\controller/dictionnaire.txt";
	public static final int NB_MOTS = 22506;
	public static Random r = new Random();
	
	private static char[] tirerMotAleatoirement() throws IOException {
		//tirer int aléatoire en fonction du nb de mots ds le dictionnaire
		int numMot = r.nextInt(NB_MOTS);
		//lecture dans le dictionnaire jusqu'au mot voulu
		try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS);
		Scanner s = new Scanner(fichier)) {
			for (int i = 1; i <= numMot; i++)
				s.nextLine();
			return s.nextLine().toUpperCase().toCharArray();
		}
	}
	
	private static char[] melanger(char[] mot) {
		//créer nouveau tableau pr stocker le mot mélangé
		char[] motMelange = new char[mot.length]; 
		for (int i = 0; i < mot.length ; i++) {
			int index=0;
			//tirage d'un index tant que l'index associé n'est pas vide
			do {
				index = r.nextInt(mot.length);
				
			} while (motMelange[index]!='\u0000');
			//on met la lettre mot[i] dans l'index tiré
			motMelange[index] = mot[i];
		}
		
		return motMelange;
		
	}
	
	private static void afficher(char[] mot) {
		for(int i = 0; i < mot.length ; i++) {
			System.out.print(mot[i]);
		}
		System.out.println();
	}
	
	private static boolean bonnesLettres(char[] motTire, char[] motJoueur) {
		boolean resultat = true;
		//copie du tableau mot tire pour qu'il puisse être modifié sans modifier l'original
		char[] copieMot = new char[motTire.length];
		for(int i = 0; i<copieMot.length; i++) {
			copieMot[i] = motTire[i];
		}
		int j = 0;
		while(resultat && j < motJoueur.length) {
			int k = 0; 
			while(k < copieMot.length && copieMot[k]!=motJoueur[j]) {
				k++;
			}
			if (k == copieMot.length) {
				resultat = false;
			} else {
				copieMot[k] = '\u0000'; 
				j++;
			}
			
		}
		
		return resultat; 
	}
	
	private static boolean dansLeDico(char[] proposition) throws IOException{
		boolean resultat = false;
		try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS);
				Scanner s = new Scanner(fichier)) {
				char[] motDictionnaire;
					while(!resultat && s.hasNext()) {
						motDictionnaire = s.nextLine().toUpperCase().toCharArray();
						if(proposition.length == motDictionnaire.length) {
							int i = 0; 
							boolean ok = true;
							while(i < proposition.length && ok) {
								ok = proposition[i] == motDictionnaire[i];
								i++;
							}
							if (i == proposition.length && ok) {
								resultat = true;
							}
						}
					}
		}
		return resultat;
	}
	
	public static void main(String[] args) {

		try {
			int choix;
			int total = 0;
			Scanner scan = new Scanner(System.in); 
			
			System.out.println("****************************************************************");
			System.out.println("****Entrez un chiffre pour demarrer le jeu (0 pour quitter)*****");
			System.out.println("****************************************************************");
			choix = scan.nextInt();
			scan.nextLine();
			while(choix != 0) {
				
				
				char[] mot = tirerMotAleatoirement();
				char[] motMelange = melanger(mot);
			
				System.out.println("Voici le tirage de lettres, vous devez trouver le mot le plus long en utilisant ces lettres :");
				afficher(motMelange);
				
				
				System.out.println("Veuillez saisir une proposition :");
				char[] proposition = scan.nextLine().toUpperCase().toCharArray();	
				
				boolean lettresOk = bonnesLettres(motMelange, proposition); 
				if(!lettresOk) {
					System.out.println("Raté ! Ce n'est pas les bonnes lettres");
				} else {
					boolean dicoOk = dansLeDico(proposition);
					if(dicoOk) {
						System.out.println("Bravo, vous gagnez " + proposition.length + " points");
						total += proposition.length;
					} else {
						System.out.println("Perdu, mot non présent dans le dictionnaire");
					}
				}
				
				System.out.println("Le mot était :");
				afficher(mot); 
				
				System.out.println("Votre score actuel est de " + total);
				
				System.out.println("*****************************************************************");
				System.out.println("****Entrez un chiffre pour continuer le jeu (0 pour quitter)*****");
				System.out.println("*****************************************************************");
				choix = scan.nextInt();
				scan.nextLine();
				
			} 
			
			scan.close();
		} catch (IOException e) {
			System.err.println("Erreur de lecture dans le dictionnaire");
		}
	}

}
