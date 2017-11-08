package fr.anmonnier.oc_javaee_p3.main.observer;

/******************************************************
 * Pattern Observer - Interface ObservateurMastermind.
 * @author André Monnier
 ******************************************************/
public interface ObservateurMastermind {

	/**
	 * Méthode permettant de mettre à jour la ou les grilles de jeu
	 * du Mastermind en fonction des propositions et des réponses de
	 * l'ordinateur suivant le mode de jeu choisi.
	 * @param reponse Cette variable peut correspondre aux propositions et
	 * aux réponses de l'ordinateur suivant le mode de jeu choisi.
	 */
	public void updateMastermind(String reponse);

	/**
	 * Méthode permettant de quitter l'application.
	 */
	public void quitterApplicationMastermind();

	/**
	 * Méthode permettant de retourner à la page d'accueil.
	 */
	public void acceuilObservateurMastermind();

	/**
	 * Méthode permettant de relancer le même jeu.
	 */
	public void relancerPartieMastermind();
}
