package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;

public class RecherchePlusMoinsControler {
	private ModeleDonnees model;

	public RecherchePlusMoinsControler(ModeleDonnees model) {
		this.model=model;
	}

	/*****************************************
	 * M�thodes relatives au mode Challenger
	 *****************************************/

	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		model.setPropositionJoueurModeChallenger(propositionJoueur);

	}

	public void setPropositionSecreteModeChallenger(String propositionSecrete) {
		model.setPropositionSecreteModeChallenger(propositionSecrete);
	}

	/*****************************************
	 * M�thodes relatives au mode D�fenseur
	 *****************************************/

	public void setPropositionSecreteModeDefenseur(String propositionSecrete) {
		model.setPropositionSecreteModeDefenseur(propositionSecrete);
	}

	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		model.setReponseJoueurModeDefenseur(reponseJoueur);
	}

	/*****************************************
	 * M�thodes relatives au mode Duel
	 *****************************************/

	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		model.setPropositionSecreteOrdinateurModeDuel(propositionSecrete);
	}

	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		model.setPropositionSecreteJoueurModeDuel(propositionSecrete);
	}

	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		model.setPropositionJoueurModeDuel(propositionJoueur);
	}

	public void setReponseJoueurModeDuel(String reponseJoueur) {
		model.setReponseJoueurModeDuel(reponseJoueur);
	}

	/**********************************************
	 * M�thodes communes � tous les modes de jeu
	 *********************************************/

	public void setModeDeJeu(int modeDeJeu) {
		model.setModeDeJeu(modeDeJeu);
	}

	public void setNbEssais(int nbEssais) {
		model.setNbEssais(nbEssais);
	}

	public void setChoixFinDePartie(String choixFinDePartie) {
		model.setChoixFinDePartie(choixFinDePartie);
	}

}
