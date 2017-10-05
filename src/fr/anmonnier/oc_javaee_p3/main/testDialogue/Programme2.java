package fr.anmonnier.oc_javaee_p3.main.testDialogue;

import java.util.ArrayList;

public class Programme2 implements Observateur, Observable {
	private ArrayList<Observateur> listeObservateur = new ArrayList<Observateur>();
	private int compteur = 1;
	private int nbEssais;

	public Programme2() {}

	public void Dialogue() {
		this.updateObservateur();

	}

	public void addObservateur(Observateur obs) {
		this.listeObservateur.add(obs);
	}

	public void updateObservateur() {
		for (Observateur obs : this.listeObservateur) {
			obs.update(compteur, this.nbEssais);
		}

	}

	public void delObservateur() {
		this.listeObservateur = new ArrayList<Observateur>();

	}

	public void update(int compteur, int nbEssais) {
		this.compteur = compteur;
		this.nbEssais = nbEssais;
		while (this.nbEssais > 0) {
			System.out.println("Message reçu par le programme 2 : " + this.compteur);
			System.out.println("Message envoyé par le programme 2 : " + (++this.compteur));
			this.nbEssais--;
			this.updateObservateur();
		}

	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	public String toString() {
		return "Programme 2";
	}
}
