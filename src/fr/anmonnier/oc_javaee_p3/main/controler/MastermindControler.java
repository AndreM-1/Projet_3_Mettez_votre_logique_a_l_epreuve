package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;

public class MastermindControler {
	private ModeleDonneesMastermind modelMastermind;

	public MastermindControler(ModeleDonneesMastermind modelMastermind) {
		this.modelMastermind=modelMastermind;
	}

	/*****************************************
	 * Méthodes relatives au mode Challenger
	 *****************************************/
	
	public void setPropositionSecreteOrdinateurModeChallenger(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteOrdinateurModeChallenger(propositionSecrete);
	}
	
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.modelMastermind.setPropositionJoueurModeChallenger(propositionJoueur);
	}
	
	/*****************************************
	 * Méthodes relatives au mode Défenseur
	 *****************************************/
	
	public void setPropositionSecreteJoueurModeDefenseur(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteJoueurModeDefenseur(propositionSecrete);
	}
	
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		this.modelMastermind.setReponseJoueurModeDefenseur(reponseJoueur);
	}
	
	/*****************************************
	 * Méthodes relatives au mode Duel
	 *****************************************/
	
	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteOrdinateurModeDuel(propositionSecrete);
	}
	
	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteJoueurModeDuel(propositionSecrete);
	}
	
	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		this.modelMastermind.setPropositionJoueurModeDuel(propositionJoueur);
	}
	
	public void setReponseJoueurModeDuel(String reponseJoueur) {
		this.modelMastermind.setReponseJoueurModeDuel(reponseJoueur);
	}
	
	/**********************************************
	 * Méthodes communes à tous les modes de jeu
	 *********************************************/

	public void setModeDeJeu(int modeDeJeu) {
		this.modelMastermind.setModeDeJeu(modeDeJeu);
	}

	public void setNbEssais(int nbEssais) {
		this.modelMastermind.setNbEssais(nbEssais);
	}
	
	public void setNbreCases(int nbreCases) {
		this.modelMastermind.setNbreCases(nbreCases);
	}
	
	public void setNbCouleursUtilisables(int nbCouleursUtilisables) {
		this.modelMastermind.setNbCouleursUtilisables(nbCouleursUtilisables);
	}

	public void setChoixFinDePartie(String choixFinDePartie) {
		this.modelMastermind.setChoixFinDePartie(choixFinDePartie);
	}
	
}
