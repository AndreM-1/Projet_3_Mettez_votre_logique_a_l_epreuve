package fr.anmonnier.oc_javaee_p3.main.model;

import java.util.ArrayList;
import java.util.Arrays;

import fr.anmonnier.oc_javaee_p3.main.observer.ObservableMastermind;
import fr.anmonnier.oc_javaee_p3.main.observer.ObservateurMastermind;

public class ModeleDonneesMastermind implements ObservableMastermind  {
	private ArrayList<ObservateurMastermind> listeObservateurMastermind=new ArrayList<ObservateurMastermind>();
	private String propositionSecreteOrdinateurModeChallenger="",propositionJoueurModeChallenger="",choixFinDePartieMastermind="",
			reponseOrdinateurModeChallenger="";
	private int modeDeJeuMastermind,nbEssaisMastermind,nbreCasesMastermind;

	/*****************************************
	 * M�thodes relatives au mode Challenger
	 *****************************************/

	public void setPropositionSecreteOrdinateurModeChallenger(String propositionSecrete) {
		this.propositionSecreteOrdinateurModeChallenger=propositionSecrete;
		System.out.println("COMBINAISON SECRETE Modele Donn�es Mastermind :"+this.propositionSecreteOrdinateurModeChallenger);
	}

	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.propositionJoueurModeChallenger=propositionJoueur;
		System.out.println("Proposition joueur en mode challenger :"+this.propositionJoueurModeChallenger);
		this.analysePropositionJoueurModeChallenger();
		this.updateObservateurMastermind();
	}

	private void analysePropositionJoueurModeChallenger() {

		//Analyse des boules bien plac�es (pions rouges) et mal plac�es (pions blancs). Pour faciliter le traitement, on va dire 
		//que pions rouges �quivaut � 1, pions blancs � 2 et emplacement vide � 3.
		int[] tabReponse=new int[this.nbreCasesMastermind];
		for (int i=0;i<this.nbreCasesMastermind;i++) {
			tabReponse[i]=3;
		}
		reponseOrdinateurModeChallenger="";

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			if(this.propositionJoueurModeChallenger.charAt(i)==this.propositionSecreteOrdinateurModeChallenger.charAt(i)) {
				tabReponse[i]=1;
			}
		}

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			for(int j=0;j<this.nbreCasesMastermind;j++) {
				if(this.propositionJoueurModeChallenger.charAt(i)==this.propositionSecreteOrdinateurModeChallenger.charAt(j)&&(i!=j)
						&&tabReponse[j]!=1&&tabReponse[i]!=1) {
					tabReponse[i]=2;
				}
			}
		}

		//On r�ordonne le tableau d'entiers dans l'ordre num�rique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :
		//pions rouges (si pr�sents), pions blancs (si pr�sents), et emplacement vide
		Arrays.sort(tabReponse);

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			if(tabReponse[i]==1)
				reponseOrdinateurModeChallenger+="R";
			else if(tabReponse[i]==2)
				reponseOrdinateurModeChallenger+="B";
			else
				reponseOrdinateurModeChallenger+="V";
		}

		System.out.println("reponseOrdinateurModeChallenger :"+reponseOrdinateurModeChallenger);

	}

	/**********************************************
	 * M�thodes commnunes � tous les modes de jeu
	 *********************************************/

	public void setModeDeJeu(int modeDeJeu) {
		this.modeDeJeuMastermind=modeDeJeu;
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssaisMastermind=nbEssais;
	}

	public void setNbreCases(int nbreCases) {
		this.nbreCasesMastermind=nbreCases;
	}

	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartieMastermind=choixFinDePartie;
		if(this.choixFinDePartieMastermind.equals("Quitter l'application"))
			this.quitterApplicationObservateurMastermind();
		else if(this.choixFinDePartieMastermind.equals("Lancer un autre jeu"))
			this.acceuilObservateurMastermind();
		else {
			this.relancerPartieObservateurMastermind();
		}
	}

	/**********************************
	 * Mise � jour des observateurs
	 **********************************/


	public void addObservateurMastermind(ObservateurMastermind obs) {
		listeObservateurMastermind.add(obs);
	}


	public void updateObservateurMastermind() {
		for(ObservateurMastermind obs:listeObservateurMastermind) {
			if(modeDeJeuMastermind==0)
				obs.updateMastermind(reponseOrdinateurModeChallenger);
		}
	}


	public void delObservateurMastermind() {
		listeObservateurMastermind=new ArrayList<ObservateurMastermind>();
	}


	public void quitterApplicationObservateurMastermind() {
		for (ObservateurMastermind obs : listeObservateurMastermind) {
			obs.quitterApplicationMastermind();
		}
	}

	public void acceuilObservateurMastermind() {
		for (ObservateurMastermind obs : listeObservateurMastermind) {
			obs.acceuilObservateurMastermind();
		}
	}


	public void relancerPartieObservateurMastermind() {
		for (ObservateurMastermind obs : listeObservateurMastermind) {
			obs.relancerPartieMastermind();
		}
	}

}
