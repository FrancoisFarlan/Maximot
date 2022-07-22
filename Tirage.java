package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tirage {
	
	Random r = new Random();
	private String mot; 
	private List<Character> liste = new ArrayList<>(); 
	private Map<Integer, List<String>> dico = new Hashtable<>();

	public Tirage() throws IOException {
		this.mot = Dictionnaire.tirerMotAleatoirement(); 
		for(int i = 0; i < mot.length(); i++) {
			liste.add(r.nextInt(this.liste.size() +1), this.mot.charAt(i));
		}
		
		for (String motDico : Dictionnaire.copieMots()) {
			if (bonnesLettres(motDico)) {
				List<String> motsDeLaMemeTaille = this.dico.get(motDico.length());
				if (motsDeLaMemeTaille == null) {
					this.dico.put(motDico.length(),new ArrayList<>());
					motsDeLaMemeTaille = this.dico.get(motDico.length());
				}
			motsDeLaMemeTaille.add(motDico);
			}
		}
	}
	
	public Iterable<Character> getLettres() {
		return liste;
		}
	
	public Map<Integer, List<String>> getMot() {
		return this.dico;
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
	
	public boolean estUneSolution(String prop) {
		List<String> motsSolutionsDeCetteTaille =this.dico.get(prop.length());
		return motsSolutionsDeCetteTaille != null && motsSolutionsDeCetteTaille.contains(prop);
		}
	
}
