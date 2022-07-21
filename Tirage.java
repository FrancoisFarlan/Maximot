package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tirage {
	
	Random r = new Random();
	private String mot; 
	private List<Character> liste = new ArrayList<>(); 

	public Tirage() throws IOException {
		this.mot = Dictionnaire.tirerMotAleatoirement(); 
		for(int i = 0; i < mot.length(); i++) {
			liste.add(r.nextInt(this.liste.size() +1), this.mot.charAt(i));
		}
	}
	
	
	public String getMot() {
		return mot;
	}


	public boolean bonnesLettres(String motJoueur) {
		
		List<Character> clone = new ArrayList<>(this.liste); 
		
		int i =0;
		while(i < motJoueur.length() && clone.contains(motJoueur.charAt(i))) {
			clone.remove(new Character(motJoueur.charAt(i)));
			i++;
		}
		
		return i == motJoueur.length();
	}
	
	public void afficher() {
		for (char c : this.liste) {
		System.out.print(c);
		}
		System.out.println();
		}
	
}
