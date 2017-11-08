package fr.anmonnier.oc_javaee_p3.main.model;

import java.util.ArrayList;

import fr.anmonnier.oc_javaee_p3.main.observer.Observable;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

/***********************************************************************************
 * Pattern MVC - Classe relative au Modèle de données du jeu RecherchePlusMoins.
 * Le modèle de données va réceptionner les données du controler, les analyser et
 * mettre à jour les observateurs. Cette classe implémente donc l'interface
 * Observable.
 * @author André Monnier
 * @see Observable
 ************************************************************************************/
public class ModeleDonnees implements Observable{

	/**
	 * ArrayList contenant la liste des observateurs.
	 */
	private ArrayList<Observateur> listeObservateur=new ArrayList<Observateur>();

	/**
	 * Variable de type chaine de caractères relative au mode challenger.
	 */
	private String propositionJoueurModeChallenger="", propositionSecreteModeChallenger="",reponseModeChallenger="";

	/**
	 * Choix du joueur en fin de partie.
	 */
	private String choixFinDePartie="";

	/**
	 * Variable de type chaine de caractères relative au mode défenseur.
	 */
	private String propositionSecreteModeDefenseur="",reponseJoueurModeDefenseur="",propositionOrdinateurModeDefenseur="";

	/**
	 * Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel. 
	 */
	private int modeDeJeu=0;

	/**
	 * Variable permettant d'effectuer des contrôles.
	 */
	private int compteurReponseJoueurModeDefenseur=0;

	/**
	 * Nombre d'essais.
	 */
	private int nbEssais=0;

	/**
	 * Bornes min.
	 */
	private int[] bornesMin;

	/**
	 * Bornes max.
	 */
	private int[]bornesMax;

	/**
	 * Variable de type chaine de caractères relative au mode duel.
	 */
	private String propositionSecreteOrdinateurModeDuel="",propositionSecreteJoueurModeDuel="",propositionJoueurModeDuel="",
			reponseOrdinateurModeDuel="",affichage="",reponseJoueurModeDuel="",propositionOrdinateurModeDuel="";

	/* ***************************************
	 * Méthodes relatives au mode Challenger
	 *****************************************/

	/**
	 * Méthode relative au mode Challenger qui permet de récupérer la proposition du joueur.
	 * Suite à la proposition du joueur, l'ordinateur devra répondre.
	 * @param propositionJoueur Proposition du joueur en mode challenger.
	 */
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.propositionJoueurModeChallenger=propositionJoueur;
		this.analysePropositionJoueurModeChallenger();
		this.updateObservateur();
	}

	/**
	 * Méthode relative au mode Challenger qui permet de récupérer la combinaison secrète de l'ordinateur.
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode challenger.
	 */
	public void setPropositionSecreteModeChallenger(String propositionSecrete) {
		this.propositionSecreteModeChallenger=propositionSecrete;
	}

	/**
	 * Méthode relative au mode Challenger qui permet d'analyser la proposition du joueur en la comparant
	 * à la combinaison secrète de l'ordinateur.
	 */
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

	/* ***************************************
	 * Méthodes relatives au mode Défenseur
	 *****************************************/

	/**
	 * Méthode relative au mode Défenseur qui permet de récupérer la combinaison secrète du joueur.
	 * Après récupération de la combinaison secrète du joueur, l'ordinateur devra faire une proposition.
	 * @param propositionSecrete Combinaison secrète du joueur en mode défenseur.
	 */
	public void setPropositionSecreteModeDefenseur(String propositionSecrete) {
		this.propositionSecreteModeDefenseur=propositionSecrete;
		//En cas de relance d'une partie
		reponseJoueurModeDefenseur="";
		compteurReponseJoueurModeDefenseur=0;
		propositionOrdinateurModeDefenseur="";
		bornesMin=new int[propositionSecreteModeDefenseur.length()];
		bornesMax=new int[propositionSecreteModeDefenseur.length()];
		for (int i=0;i<propositionSecreteModeDefenseur.length();i++) {
			bornesMin[i]=0;
			bornesMax[i]=9;
		}
		this.propositionOrdinateurModeDefenseur();
		this.updateObservateur();
	}

	/**
	 * Méthode relative au mode Défenseur qui permet de récupérer la réponse du joueur.
	 * Suite à la réponse du joueur, l'ordinateur devra faire une proposition.
	 * @param reponseJoueur Réponse du joueur en mode défenseur.
	 */
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		int verifReponseJoueurModeDefenseur=0;
		compteurReponseJoueurModeDefenseur++;
		this.reponseJoueurModeDefenseur=reponseJoueur;		
		this.updateObservateur();
		for(int i=0;i<propositionSecreteModeDefenseur.length();i++) {
			if(reponseJoueurModeDefenseur.charAt(i)=='=') {
				verifReponseJoueurModeDefenseur++;
			}
		}

		if(verifReponseJoueurModeDefenseur!=propositionSecreteModeDefenseur.length()&&compteurReponseJoueurModeDefenseur!=nbEssais) {
			this.propositionOrdinateurModeDefenseur();
			this.updateObservateur();
		}
	}

	/**
	 * Méthode relative au mode Défenseur qui permet de déterminer la proposition de l'ordinateur
	 * en fonction de la réponse du joueur. La première proposition de l'ordinateur est aléatoire
	 * vu que le joueur n'a pas encore répondu. Par la suite, nous adoptons un raisonnement par
	 * dichotomie.
	 */
	public void propositionOrdinateurModeDefenseur() {
		int tabAnalyse[]=new int[this.propositionSecreteModeDefenseur.length()];
		int tabReponse[]=new int[this.propositionSecreteModeDefenseur.length()];

		if (reponseJoueurModeDefenseur.equals("")){
			for (int i=0;i<propositionSecreteModeDefenseur.length();i++) {
				propositionOrdinateurModeDefenseur+=String.valueOf((int)(Math.random()*10));
			}
		}
		else {
			for (int i=0;i<this.propositionSecreteModeDefenseur.length();i++) {
				tabAnalyse[i]=Integer.valueOf(String.valueOf(propositionOrdinateurModeDefenseur.charAt(i)));
				if(reponseJoueurModeDefenseur.charAt(i)=='-') {
					bornesMax[i]=tabAnalyse[i]-1;
					tabReponse[i]=(int)(bornesMin[i]+bornesMax[i])/2;
				}
				else if(reponseJoueurModeDefenseur.charAt(i)=='+') {
					bornesMin[i]=tabAnalyse[i]+1;
					if((bornesMin[i]+bornesMax[i])%2==1)
						tabReponse[i]=(int)(bornesMin[i]+bornesMax[i])/2+1;
					else {
						tabReponse[i]=(int)(bornesMin[i]+bornesMax[i])/2;
					}

				}
				else {
					tabReponse[i]=tabAnalyse[i];
				}	
			}

			propositionOrdinateurModeDefenseur="";

			for(int i=0;i<this.propositionSecreteModeDefenseur.length();i++) {
				propositionOrdinateurModeDefenseur+=String.valueOf(tabReponse[i]);
			}
			reponseJoueurModeDefenseur="";
		}
	}

	/* ***************************************
	 * Méthodes relatives au mode Duel
	 *****************************************/

	/**
	 * Méthode relative au mode Duel qui permet de récupérer la combinaison secrète de l'ordinateur.
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode duel.
	 */
	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		this.propositionSecreteOrdinateurModeDuel=propositionSecrete;
		bornesMin=new int[propositionSecreteOrdinateurModeDuel.length()];
		bornesMax=new int[propositionSecreteOrdinateurModeDuel.length()];
		for (int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
			bornesMin[i]=0;
			bornesMax[i]=9;
		}
	}

	/**
	 * Méthode relative au mode Duel qui permet de récupérer la combinaison secrète du joueur.
	 * @param propositionSecrete Combinaison secrète du joueur en mode duel.
	 */
	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		this.propositionSecreteJoueurModeDuel=propositionSecrete;
	}

	/**
	 * Méthode relative au mode Duel qui permet de récupérer la proposition du joueur.
	 * Suite à la proposition du joueur, l'ordinateur devra répondre et également
	 * faire une proposition.
	 * @param propositionJoueur Proposition du joueur en mode duel.
	 */
	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		int verifReponseOrdinateurModeDuel=0;
		this.propositionJoueurModeDuel=propositionJoueur;
		affichage=this.propositionJoueurModeDuel;
		this.updateObservateur();
		this.analysePropositionJoueurModeDuel();
		affichage=reponseOrdinateurModeDuel;
		this.updateObservateur();

		for(int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
			if(reponseOrdinateurModeDuel.charAt(i)=='=') {
				verifReponseOrdinateurModeDuel++;
			}
		}
		if(verifReponseOrdinateurModeDuel!=propositionSecreteOrdinateurModeDuel.length()) {
			this.propositionOrdinateurModeDuel();
			affichage=propositionOrdinateurModeDuel;
			this.updateObservateur();
		}
	}

	/**
	 * Méthode relative au mode Duel qui permet de récupérer la réponse du joueur.
	 * @param reponseJoueur Réponse du joueur en mode duel.
	 */
	public void setReponseJoueurModeDuel(String reponseJoueur) {
		this.reponseJoueurModeDuel=reponseJoueur;
		affichage=this.reponseJoueurModeDuel;
		this.updateObservateur();
	}

	/**
	 * Méthode relative au mode Duel qui permet d'analyser la proposition du joueur en la comparant
	 * à la combinaison secrète de l'ordinateur.
	 */
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

	/**
	 * Méthode relative au mode Duel qui permet de déterminer la proposition de l'ordinateur
	 * en fonction de la réponse du joueur. La première proposition de l'ordinateur est aléatoire
	 * vu que le joueur n'a pas encore répondu. Par la suite, nous adoptons un raisonnement par
	 * dichotomie.
	 */
	public void propositionOrdinateurModeDuel() {
		int tabAnalyse[]=new int[this.propositionSecreteOrdinateurModeDuel.length()];
		int tabReponse[]=new int[this.propositionSecreteOrdinateurModeDuel.length()];

		if (reponseJoueurModeDuel.equals("")){
			for (int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
				propositionOrdinateurModeDuel+=String.valueOf((int)(Math.random()*10));
			}
		}
		else {
			for (int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
				tabAnalyse[i]=Integer.valueOf(String.valueOf(propositionOrdinateurModeDuel.charAt(i)));
				if(reponseJoueurModeDuel.charAt(i)=='-') {
					bornesMax[i]=tabAnalyse[i]-1;
					tabReponse[i]=(int)(bornesMin[i]+bornesMax[i])/2;

				}
				else if(reponseJoueurModeDuel.charAt(i)=='+') {
					bornesMin[i]=tabAnalyse[i]+1;
					if((bornesMin[i]+bornesMax[i])%2==1)
						tabReponse[i]=(int)(bornesMin[i]+bornesMax[i])/2+1;
					else {
						tabReponse[i]=(int)(bornesMin[i]+bornesMax[i])/2;
					}			
				}
				else {
					tabReponse[i]=tabAnalyse[i];
				}	
			}

			propositionOrdinateurModeDuel="";

			for(int i=0;i<propositionSecreteOrdinateurModeDuel.length();i++) {
				propositionOrdinateurModeDuel+=String.valueOf(tabReponse[i]);
			}
			reponseJoueurModeDuel="";
		}
	}


	/* ********************************************
	 * Méthodes communes à tous les modes de jeu
	 *********************************************/

	/**
	 * Mutateur commun à tous les modes de jeu qui permet de modifier le mode de jeu.
	 * @param modeDeJeu Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel.
	 */
	public void setModeDeJeu(int modeDeJeu) {
		this.modeDeJeu=modeDeJeu;
	}

	/**
	 * Mutateur commun à tous les modes de jeu qui permet de modifier le nombre d'essais.
	 * @param nbEssais Nombre d'essais.
	 */
	public void setNbEssais(int nbEssais) {
		this.nbEssais=nbEssais;
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de récupérer le choix du joueur en fin de partie et
	 * en fonction de cela, faire appel à la méthode adéquate correspondant au choix du joueur.
	 * @param choixFinDePartie Choix du joueur en fin de partie.
	 */
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


	/* ********************************
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