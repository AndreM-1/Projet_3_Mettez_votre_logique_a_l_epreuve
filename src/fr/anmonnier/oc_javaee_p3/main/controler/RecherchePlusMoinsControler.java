package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;

public class RecherchePlusMoinsControler {
	private ModeleDonnees model;
	
	public  RecherchePlusMoinsControler(ModeleDonnees model) {
		this.model=model;
	}
	
	public void setPropositionJoueur(String propositionJoueur) {
		model.setPropositionJoueur(propositionJoueur);
		
	}
	
	public void setPropositionSecrete(String propositionSecrete) {
		model.setPropositionSecrete(propositionSecrete);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		model.setChoixFinDePartie(choixFinDePartie);
	}

}
