package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;

public class RecherchePlusMoinsControler {
	private ModeleDonnees model;
	
	public  RecherchePlusMoinsControler(ModeleDonnees model) {
		this.model=model;
	}
	
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		model.setPropositionJoueurModeChallenger(propositionJoueur);
		
	}
	
	public void setPropositionSecreteModeChallenger(String propositionSecrete) {
		model.setPropositionSecreteModeChallenger(propositionSecrete);
	}
	
	
	public void setPropositionSecreteModeDefenseur(String propositionSecrete) {
		model.setPropositionSecreteModeDefenseur(propositionSecrete);
	}
	
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		model.setReponseJoueurModeDefenseur(reponseJoueur);
	}
	
	public void setModeDeJeu(int modeDeJeu) {
		model.setModeDeJeu(modeDeJeu);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		model.setChoixFinDePartie(choixFinDePartie);
	}

}
