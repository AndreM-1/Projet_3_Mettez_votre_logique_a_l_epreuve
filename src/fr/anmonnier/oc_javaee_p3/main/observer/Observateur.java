package fr.anmonnier.oc_javaee_p3.main.observer;

/********************************************
 * Pattern Observer - Interface Observateur.
 * @author André Monnier
 ********************************************/
public interface Observateur {
	
	/**
	 * Méthode permettant de mettre à jour le tableau pour le jeu
	 * RecherchePlusMoins en mode challenger et défenseur.
	 * @param propositionJoueur Proposition du joueur en mode challenger/Proposition de l'ordinateur en mode défenseur.
	 * @param reponse Réponse de l'ordinateur en mode challenger/Réponse du joueur en mode défenseur.
	 */
	public void update(String propositionJoueur, String reponse);
	
	/**
	 * Méthode permettant de mettre à jour le tableau pour le jeu RecherchePlusMoins en 
	 * mode duel en fonction des propositions et des réponses du joueur et de l'ordinateur.
	 * @param affichage Cette variable peut correspondre aux propositions/réponses du joueur
	 * et de l'ordinateur.
	 */
	public void updateDuel(String affichage);
	
	/**
	 * Méthode permettant de quitter l'application.
	 */
	public void quitterApplication();
	
	/**
	 * Méthode permettant de retourner à la page d'accueil.
	 */
	public void acceuilObservateur();
	
	/**
	 * Méthode permettant de relancer le même jeu.
	 */
	public void relancerPartie();
}
