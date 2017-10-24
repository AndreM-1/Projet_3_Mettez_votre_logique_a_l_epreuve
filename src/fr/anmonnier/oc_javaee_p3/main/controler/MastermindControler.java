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
	
	/**********************************************
	 * Méthodes commnunes à tous les modes de jeu
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

	public void setChoixFinDePartie(String choixFinDePartie) {
		this.modelMastermind.setChoixFinDePartie(choixFinDePartie);
	}
	
}
