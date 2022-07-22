package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dictionnaire {

	public static final String FICHIER_MOTS = "D:\\Workspace\\Maximot\\src\\controller/dictionnaire.txt";
	public static final int NB_MOTS = 22506;
	public static Random r = new Random();
	private static List<String> listeMots;
	
	public static List<String> copieMots() throws IOException {
		
		if(Dictionnaire.listeMots == null) {
			Dictionnaire.listeMots = new ArrayList<>(NB_MOTS);
			try (FileInputStream fichier = new FileInputStream(FICHIER_MOTS);
					Scanner s = new Scanner(fichier)) {
				while(s.hasNext()) {
					listeMots.add(s.nextLine().toUpperCase());
				}
			}
		}
		return Dictionnaire.listeMots;
	}
	
	public static boolean dansLeDico(String proposition) throws IOException{
		boolean trouve = false; 
		List<String> liste = Dictionnaire.copieMots(); 
		Iterator<String> iterator = liste.iterator();
		while(!trouve && iterator.hasNext()) {
			trouve = iterator.next().toUpperCase().equals(proposition);
		}
		return trouve; 
		
	}
	
	public static String tirerMotAleatoirement() throws IOException {
		//tirer int aléatoire en fonction du nb de mots ds le dictionnaire
		int numMot = r.nextInt(NB_MOTS);
		return Dictionnaire.copieMots().get(numMot);
		
		}
	}
