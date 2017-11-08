package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;

/*************************************************************************
 * Pattern MVC - Classe relative au Controler du jeu RecherchePlusMoins.
 * Dans le cadre de ce programme, le but du Controler est de transférer
 * les données saisies par l'utilisateur dans la vue au modèle de données
 * associé au jeu RecherchePlusMoins.
 * @author André Monnier
 * @see ModeleDonnees
 *************************************************************************/
public class RecherchePlusMoinsControler {

	/**
	 * Modèle de données relatif au jeu RecherchePlusMoins.
	 * @see ModeleDonnees
	 */
	private ModeleDonnees model;

	/**
	 * Constructeur de la classe RecherchePlusMoinsControler.
	 * @param model Modèle de données relatif au jeu RecherchePlusMoins.
	 * @see ModeleDonnees
	 */
	public RecherchePlusMoinsControler(ModeleDonnees model) {
		this.model=model;
	}

	/* **************************************
	 * Méthodes relatives au mode Challenger
	 ****************************************/

	/**
	 * Méthode relative au mode Challenger qui permet de transférer la proposition du joueur au modèle.
	 * @param propositionJoueur Proposition du joueur en mode challenger.
	 */
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		model.setPropositionJoueurModeChallenger(propositionJoueur);

	}

	/**
	 * Méthode relative au mode Challenger qui permet de transférer la combinaison secrète de l'ordinateur au modèle.
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode challenger.
	 */
	public void setPropositionSecreteModeChallenger(String propositionSecrete) {
		model.setPropositionSecreteModeChallenger(propositionSecrete);
	}

	/* *************************************
	 * Méthodes relatives au mode Défenseur
	 ***************************************/

	/**
	 * Méthode relative au mode Défenseur qui permet de transférer la combinaison secrète du joueur au modèle.
	 * @param propositionSecrete Combinaison secrète du joueur en mode défenseur.
	 */
	public void setPropositionSecreteModeDefenseur(String propositionSecrete) {
		model.setPropositionSecreteModeDefenseur(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Défenseur qui permet de transférer la réponse du joueur au modèle.
	 * @param reponseJoueur Réponse du joueur en mode défenseur.
	 */
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		model.setReponseJoueurModeDefenseur(reponseJoueur);
	}

	/* ***************************************
	 * Méthodes relatives au mode Duel
	 *****************************************/

	/**
	 * Méthode relative au mode Duel qui permet de transférer la combinaison secrète de l'ordinateur au modèle.
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode duel.
	 */
	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		model.setPropositionSecreteOrdinateurModeDuel(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la combinaison secrète du joueur au modèle.
	 * @param propositionSecrete Combinaison secrète du joueur en mode duel.
	 */
	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		model.setPropositionSecreteJoueurModeDuel(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la proposition du joueur au modèle.
	 * @param propositionJoueur Proposition du joueur en mode duel.
	 */
	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		model.setPropositionJoueurModeDuel(propositionJoueur);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la réponse du joueur au modèle.
	 * @param reponseJoueur Réponse du joueur en mode duel.
	 */
	public void setReponseJoueurModeDuel(String reponseJoueur) {
		model.setReponseJoueurModeDuel(reponseJoueur);
	}

	/* ********************************************
	 * Méthodes communes à tous les modes de jeu
	 *********************************************/

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le mode de jeu au modèle. 
	 * @param modeDeJeu Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel. 
	 */
	public void setModeDeJeu(int modeDeJeu) {
		model.setModeDeJeu(modeDeJeu);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre d'essais au modèle.
	 * @param nbEssais Nombre d'essais.
	 */
	public void setNbEssais(int nbEssais) {
		model.setNbEssais(nbEssais);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le choix du joueur en fin de partie au modèle.
	 * @param choixFinDePartie Choix du joueur en fin de partie.
	 */
	public void setChoixFinDePartie(String choixFinDePartie) {
		model.setChoixFinDePartie(choixFinDePartie);
	}
}