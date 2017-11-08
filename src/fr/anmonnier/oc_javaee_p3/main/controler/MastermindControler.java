package fr.anmonnier.oc_javaee_p3.main.controler;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;

/*************************************************************************
 * Pattern MVC - Classe relative au Controler du jeu Mastermind.
 * Dans le cadre de ce programme, le but du Controler est de transférer
 * les données saisies par l'utilisateur dans la vue au modèle de données
 * associé au jeu Mastermind.
 * @author André Monnier
 * @see ModeleDonneesMastermind
 *************************************************************************/
public class MastermindControler {

	/**
	 * Modèle de données relatif au jeu Mastermind.
	 * @see ModeleDonneesMastermind 
	 */
	private ModeleDonneesMastermind modelMastermind;

	/**
	 * Constructeur de la classe MastermindControler. 
	 * @param modelMastermind Modèle de données relatif au jeu Mastermind.
	 * @see ModeleDonneesMastermind
	 */
	public MastermindControler(ModeleDonneesMastermind modelMastermind) {
		this.modelMastermind=modelMastermind;
	}

	/* ***************************************
	 * Méthodes relatives au mode Challenger
	 *****************************************/

	/**
	 * Méthode relative au mode Challenger qui permet de transférer la combinaison secrète de l'ordinateur au modèle.
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode challenger.
	 */
	public void setPropositionSecreteOrdinateurModeChallenger(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteOrdinateurModeChallenger(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Challenger qui permet de transférer la proposition du joueur au modèle.
	 * @param propositionJoueur Proposition du joueur en mode challenger.
	 */
	public void setPropositionJoueurModeChallenger(String propositionJoueur) {
		this.modelMastermind.setPropositionJoueurModeChallenger(propositionJoueur);
	}

	/* ***************************************
	 * Méthodes relatives au mode Défenseur
	 *****************************************/

	/**
	 * Méthode relative au mode Défenseur qui permet de transférer la combinaison secrète du joueur au modèle.
	 * @param propositionSecrete Combinaison secrète du joueur en mode défenseur.
	 */
	public void setPropositionSecreteJoueurModeDefenseur(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteJoueurModeDefenseur(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Défenseur qui permet de transférer la réponse du joueur au modèle.
	 * @param reponseJoueur Réponse du joueur en mode défenseur.
	 */
	public void setReponseJoueurModeDefenseur(String reponseJoueur) {
		this.modelMastermind.setReponseJoueurModeDefenseur(reponseJoueur);
	}

	/* ***************************************
	 * Méthodes relatives au mode Duel
	 *****************************************/

	/**
	 * Méthode relative au mode Duel qui permet de transférer la combinaison secrète de l'ordinateur au modèle.
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode duel.
	 */
	public void setPropositionSecreteOrdinateurModeDuel(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteOrdinateurModeDuel(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la combinaison secrète du joueur au modèle.
	 * @param propositionSecrete Combinaison secrète du joueur en mode duel.
	 */
	public void setPropositionSecreteJoueurModeDuel(String propositionSecrete) {
		this.modelMastermind.setPropositionSecreteJoueurModeDuel(propositionSecrete);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la proposition du joueur au modèle.
	 * @param propositionJoueur Proposition du joueur en mode duel.
	 */
	public void setPropositionJoueurModeDuel(String propositionJoueur) {
		this.modelMastermind.setPropositionJoueurModeDuel(propositionJoueur);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la réponse du joueur au modèle.
	 * @param reponseJoueur Réponse du joueur en mode duel.
	 */
	public void setReponseJoueurModeDuel(String reponseJoueur) {
		this.modelMastermind.setReponseJoueurModeDuel(reponseJoueur);
	}

	/* ********************************************
	 * Méthodes communes à tous les modes de jeu
	 *********************************************/

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le mode de jeu au modèle.
	 * @param modeDeJeu Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel.
	 */
	public void setModeDeJeu(int modeDeJeu) {
		this.modelMastermind.setModeDeJeu(modeDeJeu);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre d'essais au modèle.
	 * @param nbEssais Nombre d'essais.
	 */
	public void setNbEssais(int nbEssais) {
		this.modelMastermind.setNbEssais(nbEssais);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre de cases au modèle.
	 * @param nbreCases Nombre de cases 
	 */
	public void setNbreCases(int nbreCases) {
		this.modelMastermind.setNbreCases(nbreCases);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre de couleurs utilisables au modèle.
	 * @param nbCouleursUtilisables Nombre de couleurs utilisables 
	 */
	public void setNbCouleursUtilisables(int nbCouleursUtilisables) {
		this.modelMastermind.setNbCouleursUtilisables(nbCouleursUtilisables);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le choix du joueur en fin de partie au modèle.
	 * @param choixFinDePartie Choix du joueur en fin de partie.
	 */
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.modelMastermind.setChoixFinDePartie(choixFinDePartie);
	}
}