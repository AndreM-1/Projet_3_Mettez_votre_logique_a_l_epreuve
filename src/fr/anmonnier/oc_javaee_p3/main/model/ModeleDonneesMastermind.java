package fr.anmonnier.oc_javaee_p3.main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import fr.anmonnier.oc_javaee_p3.main.observer.ObservableMastermind;
import fr.anmonnier.oc_javaee_p3.main.observer.ObservateurMastermind;

public class ModeleDonneesMastermind implements ObservableMastermind  {
	private ArrayList<ObservateurMastermind> listeObservateurMastermind=new ArrayList<ObservateurMastermind>();
	private LinkedList<String> listePossibilitees;
	private String propositionSecreteOrdinateurModeChallenger="",propositionJoueurModeChallenger="",choixFinDePartieMastermind="",
			reponseOrdinateurModeChallenger="";
	private String propositionSecreteJoueurModeDefenseur="",reponseJoueurModeDefenseur="",propositionOrdinateurModeDefenseur="";
	private String propositionSecreteOrdinateurModeDuel="",propositionSecreteJoueurModeDuel="",propositionJoueurModeDuel="",
			propositionOrdinateurModeDuel="",reponseJoueurModeDuel="",reponseOrdinateurModeDuel="",affichage="";
	private int modeDeJeuMastermind,nbEssaisMastermind,nbreCasesMastermind,nbCouleursUtilisablesMastermind;

	/*****************************************
	 * Méthodes relatives au mode Challenger
	 *****************************************/

	public void setPropositionSecreteOrdinateurModeChallenger(String propositionSecrete) {
		this.propositionSecreteOrdinateurModeChallenger=propositionSecrete;
	}

	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.propositionJoueurModeChallenger=propositionJoueur;
		this.analysePropositionJoueurModeChallenger();
		this.updateObservateurMastermind();
	}

	private void analysePropositionJoueurModeChallenger() {

		//Analyse des boules bien placées (pions rouges) et mal placées (pions blancs). Pour faciliter le traitement, on va dire 
		//que pions rouges équivaut à 1, pions blancs à 2 et emplacement vide à 3.
		int[] tabReponse=new int[this.nbreCasesMastermind];
		char []tabAnalyse=new char[this.nbreCasesMastermind];
		tabAnalyse=propositionSecreteOrdinateurModeChallenger.toCharArray();
		for (int i=0;i<this.nbreCasesMastermind;i++) {
			tabReponse[i]=3;
		}
		reponseOrdinateurModeChallenger="";

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			if(this.propositionJoueurModeChallenger.charAt(i)==tabAnalyse[i]) {
				tabReponse[i]=1;
				tabAnalyse[i]=' ';
			}
		}

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			for(int j=0;j<this.nbreCasesMastermind;j++) {
				if(this.propositionJoueurModeChallenger.charAt(i)==tabAnalyse[j]&&tabReponse[i]!=1) {
					tabReponse[i]=2;
					tabAnalyse[j]=' ';
					break;
				}
			}
		}


		//On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :
		//pions rouges (si présents), pions blancs (si présents), et emplacement vide
		Arrays.sort(tabReponse);

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			if(tabReponse[i]==1)
				reponseOrdinateurModeChallenger+="R";
			else if(tabReponse[i]==2)
				reponseOrdinateurModeChallenger+="B";
			else
				reponseOrdinateurModeChallenger+="V";
		}
	}

	/*****************************************
	 * Méthodes relatives au mode Défenseur
	 *****************************************/
	public void setPropositionSecreteJoueurModeDefenseur(String propositionSecrete) {
		this.propositionSecreteJoueurModeDefenseur=propositionSecrete;
		System.out.println("Combinaison secrète joueur Modèle de données :"+this.propositionSecreteJoueurModeDefenseur);
		this.initListePossibilitees();
		System.out.println(listePossibilitees.size());
		this.propositionOrdinateurModeDefenseur();
		this.updateObservateurMastermind();
	}

	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		this.reponseJoueurModeDefenseur=reponseJoueur;
		this.propositionOrdinateurModeDefenseur();
		this.updateObservateurMastermind();
	}

	private void propositionOrdinateurModeDefenseur() {
		if(reponseJoueurModeDefenseur.equals("")) {
			propositionOrdinateurModeDefenseur=listePossibilitees.getFirst();
			System.out.println(propositionOrdinateurModeDefenseur);
		}
		else {
			System.out.println("Modele de données réponse du joueur :"+reponseJoueurModeDefenseur);
			Iterator<String> itParcoursListe=listePossibilitees.iterator();
			String premierElementListe=this.listePossibilitees.getFirst();
			while(itParcoursListe.hasNext()) {
				String strElementListe=itParcoursListe.next();
				String resultatComparaison="";
				int[] tabComparaison=new int[this.nbreCasesMastermind];
				char []tabAnalyseListe=new char[this.nbreCasesMastermind];
				tabAnalyseListe=strElementListe.toCharArray();
				for (int i=0;i<this.nbreCasesMastermind;i++) {
					tabComparaison[i]=3;
				}

				for (int i=0;i<this.nbreCasesMastermind;i++) {
					if(premierElementListe.charAt(i)==tabAnalyseListe[i]) {
						tabComparaison[i]=1;
						tabAnalyseListe[i]=' ';
					}
				}
				for (int i=0;i<this.nbreCasesMastermind;i++) {
					for(int j=0;j<this.nbreCasesMastermind;j++) {
						if(premierElementListe.charAt(i)==tabAnalyseListe[j]&&tabComparaison[i]!=1) {
							tabComparaison[i]=2;
							tabAnalyseListe[j]=' ';
							break;
						}
					}
				}

				Arrays.sort(tabComparaison);
				for (int i=0;i<this.nbreCasesMastermind;i++) {
					if(tabComparaison[i]==1)
						resultatComparaison+="R";
					else if(tabComparaison[i]==2)
						resultatComparaison+="B";
					else
						resultatComparaison+="V";
				}
				if(!resultatComparaison.equals(reponseJoueurModeDefenseur)) {
					itParcoursListe.remove();
				}

			}
			System.out.println("Taille liste réactualisé :"+listePossibilitees.size());
			System.out.println("Premier élément réactualisé :"+listePossibilitees.getFirst());
			reponseJoueurModeDefenseur="";
			propositionOrdinateurModeDefenseur=listePossibilitees.getFirst();
		}
	}


	/*****************************************
	 * Méthodes relatives au mode Duel
	 *****************************************/
	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		this.propositionSecreteOrdinateurModeDuel=propositionSecrete;
		System.out.println("Combinaison Secrète Ordinateur Modèle de données :"+this.propositionSecreteOrdinateurModeDuel);
	}

	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		this.propositionSecreteJoueurModeDuel=propositionSecrete;
		System.out.println("Proposition Secrète Joueur Modèle de données :"+this.propositionSecreteJoueurModeDuel);
		this.initListePossibilitees();
		System.out.println(listePossibilitees.size());
	}

	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		int verifReponseOrdinateurModeDuel=0;
		this.propositionJoueurModeDuel=propositionJoueur;
		System.out.println("Proposition Joueur Modèle de données :"+this.propositionJoueurModeDuel);
		this.analysePropositionJoueurModeDuel();
		affichage=reponseOrdinateurModeDuel;
		System.out.println("reponseOrdinateurModeDuel :"+affichage);
		this.updateObservateurMastermind();
		
		for(int i=0;i<this.nbreCasesMastermind;i++) {
			if(reponseOrdinateurModeDuel.charAt(i)=='R') {
				verifReponseOrdinateurModeDuel++;
			}
		}
		if(verifReponseOrdinateurModeDuel!=this.nbreCasesMastermind) {
			this.propositionOrdinateurModeDuel();
			affichage=propositionOrdinateurModeDuel;
			this.updateObservateurMastermind();
		}
	}
	
	public void setReponseJoueurModeDuel(String reponseJoueur) {
		this.reponseJoueurModeDuel=reponseJoueur;
	}

	private void analysePropositionJoueurModeDuel() {

		//Analyse des boules bien placées (pions rouges) et mal placées (pions blancs). Pour faciliter le traitement, on va dire 
		//que pions rouges équivaut à 1, pions blancs à 2 et emplacement vide à 3.
		int[] tabReponse=new int[this.nbreCasesMastermind];
		char []tabAnalyse=new char[this.nbreCasesMastermind];
		tabAnalyse=propositionSecreteOrdinateurModeDuel.toCharArray();
		for (int i=0;i<this.nbreCasesMastermind;i++) {
			tabReponse[i]=3;
		}
		reponseOrdinateurModeDuel="";

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			if(this.propositionJoueurModeDuel.charAt(i)==tabAnalyse[i]) {
				tabReponse[i]=1;
				tabAnalyse[i]=' ';
			}
		}

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			for(int j=0;j<this.nbreCasesMastermind;j++) {
				if(this.propositionJoueurModeDuel.charAt(i)==tabAnalyse[j]&&tabReponse[i]!=1) {
					tabReponse[i]=2;
					tabAnalyse[j]=' ';
					break;
				}
			}
		}

		//On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :
		//pions rouges (si présents), pions blancs (si présents), et emplacement vide
		Arrays.sort(tabReponse);

		for (int i=0;i<this.nbreCasesMastermind;i++) {
			if(tabReponse[i]==1)
				reponseOrdinateurModeDuel+="R";
			else if(tabReponse[i]==2)
				reponseOrdinateurModeDuel+="B";
			else
				reponseOrdinateurModeDuel+="V";
		}

	}
	
	public void propositionOrdinateurModeDuel() {
		
		if(reponseJoueurModeDuel.equals("")) {
			propositionOrdinateurModeDuel=listePossibilitees.getFirst();
			System.out.println(propositionOrdinateurModeDuel);
		}
		else {
			System.out.println("Modele de données réponse du joueur :"+reponseJoueurModeDuel);
			Iterator<String> itParcoursListe=listePossibilitees.iterator();
			String premierElementListe=this.listePossibilitees.getFirst();
			while(itParcoursListe.hasNext()) {
				String strElementListe=itParcoursListe.next();
				String resultatComparaison="";
				int[] tabComparaison=new int[this.nbreCasesMastermind];
				char []tabAnalyseListe=new char[this.nbreCasesMastermind];
				tabAnalyseListe=strElementListe.toCharArray();
				for (int i=0;i<this.nbreCasesMastermind;i++) {
					tabComparaison[i]=3;
				}

				for (int i=0;i<this.nbreCasesMastermind;i++) {
					if(premierElementListe.charAt(i)==tabAnalyseListe[i]) {
						tabComparaison[i]=1;
						tabAnalyseListe[i]=' ';
					}
				}
				for (int i=0;i<this.nbreCasesMastermind;i++) {
					for(int j=0;j<this.nbreCasesMastermind;j++) {
						if(premierElementListe.charAt(i)==tabAnalyseListe[j]&&tabComparaison[i]!=1) {
							tabComparaison[i]=2;
							tabAnalyseListe[j]=' ';
							break;
						}
					}
				}

				Arrays.sort(tabComparaison);
				for (int i=0;i<this.nbreCasesMastermind;i++) {
					if(tabComparaison[i]==1)
						resultatComparaison+="R";
					else if(tabComparaison[i]==2)
						resultatComparaison+="B";
					else
						resultatComparaison+="V";
				}
				if(!resultatComparaison.equals(reponseJoueurModeDuel)) {
					itParcoursListe.remove();
				}

			}
			System.out.println("Taille liste réactualisé :"+listePossibilitees.size());
			System.out.println("Premier élément réactualisé :"+listePossibilitees.getFirst());
			reponseJoueurModeDuel="";
			propositionOrdinateurModeDuel=listePossibilitees.getFirst();
		}
	}


	/**********************************************
	 * Méthodes communes à tous les modes de jeu
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

	public void setNbCouleursUtilisables(int nbCouleursUtilisables) {
		this.nbCouleursUtilisablesMastermind=nbCouleursUtilisables;
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
	
	public void initListePossibilitees() {
		/*On crée un objet LinkedList avec l'ensemble des possibilités. Dans le cas où on a 4 cases et 6 couleurs utilisables, 
		 l'objet LinkedList contiendra 1296 éléments. On s'assure bien que cette liste est initialisée à chaque début de partie*/
		listePossibilitees=new LinkedList<String>();
		if(nbreCasesMastermind==4) {
			for(int i=0;i<this.nbCouleursUtilisablesMastermind;i++) {
				for(int j=0;j<this.nbCouleursUtilisablesMastermind;j++) {
					for(int k=0;k<this.nbCouleursUtilisablesMastermind;k++) {
						for(int l=0;l<this.nbCouleursUtilisablesMastermind;l++) {
							listePossibilitees.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(l));
						}

					}
				}
			}
		}
		else if(nbreCasesMastermind==5) {
			for(int i=0;i<this.nbCouleursUtilisablesMastermind;i++) {
				for(int j=0;j<this.nbCouleursUtilisablesMastermind;j++) {
					for(int k=0;k<this.nbCouleursUtilisablesMastermind;k++) {
						for(int l=0;l<this.nbCouleursUtilisablesMastermind;l++) {
							for(int m=0;m<this.nbCouleursUtilisablesMastermind;m++) {
								listePossibilitees.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(l)+String.valueOf(m));
							}
						}

					}
				}
			}
		}
		else {
			for(int i=0;i<this.nbCouleursUtilisablesMastermind;i++) {
				for(int j=0;j<this.nbCouleursUtilisablesMastermind;j++) {
					for(int k=0;k<this.nbCouleursUtilisablesMastermind;k++) {
						for(int l=0;l<this.nbCouleursUtilisablesMastermind;l++) {
							for(int m=0;m<this.nbCouleursUtilisablesMastermind;m++) {
								for(int n=0;n<this.nbCouleursUtilisablesMastermind;n++) {
									listePossibilitees.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(l)+String.valueOf(m)+String.valueOf(n));
								}		
							}
						}
					}
				}
			}
		}
	}

	/**********************************
	 * Mise à jour des observateurs
	 **********************************/


	public void addObservateurMastermind(ObservateurMastermind obs) {
		listeObservateurMastermind.add(obs);
	}


	public void updateObservateurMastermind() {
		for(ObservateurMastermind obs:listeObservateurMastermind) {
			if(modeDeJeuMastermind==0)
				obs.updateMastermind(reponseOrdinateurModeChallenger);
			else if(modeDeJeuMastermind==1)
				obs.updateMastermind(propositionOrdinateurModeDefenseur);
			else
				obs.updateMastermind(affichage);
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
			this.propositionSecreteOrdinateurModeDuel="";
			this.propositionSecreteJoueurModeDuel="";
			this.reponseJoueurModeDuel="";
			this.propositionOrdinateurModeDuel="";
			obs.relancerPartieMastermind();
		}
	}

}
