package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;

public class MastermindControler {
	private ModeleDonneesMastermind modelMastermind;

	public MastermindControler(ModeleDonneesMastermind modelMastermind) {
		this.modelMastermind=modelMastermind;
	}

	/*****************************************
	 * M�thodes relatives au mode Challenger
	 *****************************************/
	
	public void setPropositionSecreteOrdinateurModeChallenger(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteOrdinateurModeChallenger(propositionSecrete);
	}
	
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.modelMastermind.setPropositionJoueurModeChallenger(propositionJoueur);
	}
	
	/*****************************************
	 * M�thodes relatives au mode D�fenseur
	 *****************************************/
	
	public void setPropositionSecreteJoueurModeDefenseur(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteJoueurModeDefenseur(propositionSecrete);
	}
	
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		this.modelMastermind.setReponseJoueurModeDefenseur(reponseJoueur);
	}
	
	/**********************************************
	 * M�thodes commnunes � tous les modes de jeu
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
