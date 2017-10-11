package fr.anmonnier.oc_javaee_p3.main.model;

import java.util.ArrayList;

import fr.anmonnier.oc_javaee_p3.main.observer.Observable;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

public class ModeleDonnees implements Observable{

	private ArrayList<Observateur> listeObservateur=new ArrayList<Observateur>();
	private String propositionJoueur="", propositionSecrete="",reponse="", choixFinDePartie="";
	
	public void setPropositionJoueur(String propositionJoueur) {
		this.propositionJoueur=propositionJoueur;
		this.analysePropositionJoueur();
		this.updateObservateur();
	}
	
	public void setPropositionSecrete(String propositionSecrete) {
		this.propositionSecrete=propositionSecrete;
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartie=choixFinDePartie;
		if(this.choixFinDePartie.equals("Quitter l'application"))
			this.quitterApplicationObservateur();
		else if(this.choixFinDePartie.equals("Lancer un autre jeu"))
			this.acceuilObservateur();
		else {
			this.relancerPartieObservateur();
		}
	}
	
	public void analysePropositionJoueur() {
		char[] tabReponse=new char [this.propositionSecrete.length()];
		reponse="";
		for (int i=0;i<this.propositionSecrete.length();i++) {
			if(this.propositionJoueur.charAt(i)==this.propositionSecrete.charAt(i)) {
				tabReponse[i]='=';	
			}
			else if (this.propositionJoueur.charAt(i)<this.propositionSecrete.charAt(i)) {
				tabReponse[i]='+';	
			}
			else {
				tabReponse[i]='-';	
			}
			reponse+=String.valueOf(tabReponse[i]);
		}
	}
	
	public void addObservateur(Observateur obs) {
		listeObservateur.add(obs);
	}

	public void updateObservateur() {
		for (Observateur obs : listeObservateur) {
			obs.update(propositionJoueur,reponse);
		}
	}

	public void delObservateur() {
		listeObservateur=new ArrayList<Observateur>();
	}

	public void quitterApplicationObservateur() {
		for (Observateur obs : listeObservateur) {
			obs.quitterApplication();
		}
		
	}

	public void acceuilObservateur() {
		for (Observateur obs : listeObservateur) {
			obs.acceuilObservateur();
		}
		
	}
	public void relancerPartieObservateur() {
		for (Observateur obs : listeObservateur) {
			obs.relancerPartie();
		}
	}

}
