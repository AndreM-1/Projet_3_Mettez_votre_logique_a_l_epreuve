package fr.anmonnier.oc_javaee_p3.main.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import fr.anmonnier.oc_javaee_p3.main.observer.Observable;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

public class ModeleDonnees implements Observable{

	private ArrayList<Observateur> listeObservateur=new ArrayList<Observateur>();
	private String propositionJoueurModeChallenger="", propositionSecreteModeChallenger="",reponseModeChallenger="", choixFinDePartie="";
	private String propositionSecreteModeDefenseur="",reponseJoueurModeDefenseur="",propositionOrdinateurModeDefenseur="";
	private int modeDeJeu=0,compteurReponseJoueurModeDefenseur=0;
	private String propositionSecreteOrdinateurModeDuel="",propositionSecreteJoueurModeDuel="",propositionJoueurModeDuel="",
			reponseOrdinateurModeDuel="",affichage="",reponseJoueurModeDuel="",propositionOrdinateurModeDuel="";
	
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.propositionJoueurModeChallenger=propositionJoueur;
		this.analysePropositionJoueurModeChallenger();
		this.updateObservateur();
	}
	
	public void setPropositionSecreteModeChallenger(String propositionSecrete) {
		this.propositionSecreteModeChallenger=propositionSecrete;
	}
	
	public void analysePropositionJoueurModeChallenger() {
		char[] tabReponse=new char [this.propositionSecreteModeChallenger.length()];
		reponseModeChallenger="";
		for (int i=0;i<this.propositionSecreteModeChallenger.length();i++) {
			if(this.propositionJoueurModeChallenger.charAt(i)==this.propositionSecreteModeChallenger.charAt(i)) {
				tabReponse[i]='=';	
			}
			else if (this.propositionJoueurModeChallenger.charAt(i)<this.propositionSecreteModeChallenger.charAt(i)) {
				tabReponse[i]='+';	
			}
			else {
				tabReponse[i]='-';	
			}
			reponseModeChallenger+=String.valueOf(tabReponse[i]);
		}
	}
	
	public void setPropositionSecreteModeDefenseur(String propositionSecrete) {
		this.propositionSecreteModeDefenseur=propositionSecrete;
		//En cas de relance d'une partie
		reponseJoueurModeDefenseur="";
		compteurReponseJoueurModeDefenseur=0;
		propositionOrdinateurModeDefenseur="";
		this.propositionOrdinateurModeDefenseur();
		this.updateObservateur();
	}
	
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		compteurReponseJoueurModeDefenseur++;
		this.reponseJoueurModeDefenseur=reponseJoueur;		
		this.updateObservateur();
		if(!this.reponseJoueurModeDefenseur.equals("====")&&compteurReponseJoueurModeDefenseur!=10) {
			this.propositionOrdinateurModeDefenseur();
			this.updateObservateur();
		}
		
	}
	
	public void propositionOrdinateurModeDefenseur() {
		int tabAnalyse[]=new int[this.propositionSecreteModeDefenseur.length()];
		int tabReponse[]=new int[this.propositionSecreteModeDefenseur.length()];
		boolean verifChiffreSaisie=true;
		if (reponseJoueurModeDefenseur.equals("")){
			for (int i=0;i<propositionSecreteModeDefenseur.length();i++) {
				propositionOrdinateurModeDefenseur+=String.valueOf((int)(Math.random()*10));
			}
		}
		else {
			for (int i=0;i<this.propositionSecreteModeDefenseur.length();i++) {
				tabAnalyse[i]=Integer.valueOf(String.valueOf(propositionOrdinateurModeDefenseur.charAt(i)));
				if(reponseJoueurModeDefenseur.charAt(i)=='-') {
					tabReponse[i]=tabAnalyse[i]-1;
					if(tabReponse[i]<0) {
						tabReponse[i]=0;
						verifChiffreSaisie=false;
					}
						
				}
				else if(reponseJoueurModeDefenseur.charAt(i)=='+') {
					tabReponse[i]=tabAnalyse[i]+1;
					if(tabReponse[i]>9) {
						tabReponse[i]=9;
						verifChiffreSaisie=false;
					}			
				}
				else {
					tabReponse[i]=tabAnalyse[i];
				}	
			}
			
			if(!verifChiffreSaisie) {
				JOptionPane.showMessageDialog(null,"Attention : Seuls les chiffres entre 0 et 9 sont acceptés", 
					"Message d'avertissement", JOptionPane.WARNING_MESSAGE);
			}
			
			propositionOrdinateurModeDefenseur="";
			
			for(int i=0;i<this.propositionSecreteModeDefenseur.length();i++) {
				propositionOrdinateurModeDefenseur+=String.valueOf(tabReponse[i]);
			}
			reponseJoueurModeDefenseur="";
		}
	}
	
	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		this.propositionSecreteOrdinateurModeDuel=propositionSecrete;
		System.out.println("COMBINAISON SECRETE ORDINATEUR MODE DUEL - Modèle Données:"+this.propositionSecreteOrdinateurModeDuel);
	}
	
	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		this.propositionSecreteJoueurModeDuel=propositionSecrete;
		System.out.println("COMBINAISON SECRETE JOUEUR MODE DUEL - Modèle Données:"+this.propositionSecreteJoueurModeDuel);
	}
	
	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		this.propositionJoueurModeDuel=propositionJoueur;
		affichage=this.propositionJoueurModeDuel;
		this.updateObservateur();
		this.analysePropositionJoueurModeDuel();
		affichage=reponseOrdinateurModeDuel;
		this.updateObservateur();
		if(!reponseOrdinateurModeDuel.equals("====")) {
			this.propositionOrdinateurModeDuel();
			affichage=propositionOrdinateurModeDuel;
			this.updateObservateur();
		}
	}
	
	public void setReponseJoueurModeDuel(String reponseJoueur) {
		this.reponseJoueurModeDuel=reponseJoueur;
		affichage=this.reponseJoueurModeDuel;
		this.updateObservateur();
	}
	
	public void analysePropositionJoueurModeDuel() {
		char[] tabReponse=new char [this.propositionSecreteOrdinateurModeDuel.length()];
		reponseOrdinateurModeDuel="";
		for (int i=0;i<this.propositionSecreteOrdinateurModeDuel.length();i++) {
			if(this.propositionJoueurModeDuel.charAt(i)==this.propositionSecreteOrdinateurModeDuel.charAt(i)) {
				tabReponse[i]='=';	
			}
			else if (this.propositionJoueurModeDuel.charAt(i)<this.propositionSecreteOrdinateurModeDuel.charAt(i)) {
				tabReponse[i]='+';	
			}
			else {
				tabReponse[i]='-';	
			}
			reponseOrdinateurModeDuel+=String.valueOf(tabReponse[i]);
		}
	}
	
	public void propositionOrdinateurModeDuel() {
		int tabAnalyse[]=new int[this.propositionSecreteOrdinateurModeDuel.length()];
		int tabReponse[]=new int[this.propositionSecreteOrdinateurModeDuel.length()];
		boolean verifChiffreSaisie=true;
		if (reponseJoueurModeDuel.equals("")){
			for (int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
				propositionOrdinateurModeDuel+=String.valueOf((int)(Math.random()*10));
			}
		}
		else {
			for (int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
				tabAnalyse[i]=Integer.valueOf(String.valueOf(propositionOrdinateurModeDuel.charAt(i)));
				if(reponseJoueurModeDuel.charAt(i)=='-') {
					tabReponse[i]=tabAnalyse[i]-1;
					if(tabReponse[i]<0) {
						tabReponse[i]=0;
						verifChiffreSaisie=false;
					}
						
				}
				else if(reponseJoueurModeDuel.charAt(i)=='+') {
					tabReponse[i]=tabAnalyse[i]+1;
					if(tabReponse[i]>9) {
						tabReponse[i]=9;
						verifChiffreSaisie=false;
					}			
				}
				else {
					tabReponse[i]=tabAnalyse[i];
				}	
			}
			
			if(!verifChiffreSaisie) {
				JOptionPane.showMessageDialog(null,"Attention : Vous aviez indiqué dans votre réponse précédente un \"-\" ou un \"+\" "
					+ "alors que le chiffre était déjà à 0 ou 9 ", "Message d'avertissement", JOptionPane.WARNING_MESSAGE);
			}
			
			propositionOrdinateurModeDuel="";
			
			for(int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
				propositionOrdinateurModeDuel+=String.valueOf(tabReponse[i]);
			}
			reponseJoueurModeDuel="";
		}
	}
	
	
	public void setModeDeJeu(int modeDeJeu) {
		this.modeDeJeu=modeDeJeu;
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
	
	
	/**********************************
	 * Mise à jour des observateurs
	 **********************************/
	public void addObservateur(Observateur obs) {
		listeObservateur.add(obs);
	}

	public void updateObservateur() {
		for (Observateur obs : listeObservateur) {
			if(modeDeJeu==0) {
				obs.update(propositionJoueurModeChallenger,reponseModeChallenger);
			}
			else if (modeDeJeu==1) {
				obs.update(propositionOrdinateurModeDefenseur,reponseJoueurModeDefenseur);
			}
			else
				obs.updateDuel(affichage);
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
			this.propositionSecreteOrdinateurModeDuel="";
			this.propositionSecreteJoueurModeDuel="";
			this.reponseJoueurModeDuel="";
			this.propositionOrdinateurModeDuel="";
			obs.relancerPartie();
		}
	}

}
