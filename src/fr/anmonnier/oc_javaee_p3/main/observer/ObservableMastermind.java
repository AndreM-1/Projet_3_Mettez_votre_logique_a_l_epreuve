package fr.anmonnier.oc_javaee_p3.main.observer;

/*****************************************************
 * Pattern Observer - Interface ObservableMastermind.
 * @author André Monnier
 *****************************************************/
public interface ObservableMastermind {

	/**
	 * Méthode permettant d'ajouter des observateurs à une liste d'observateur.
	 * @param obs Un objet de type ObservateurMastermind. Dans le cadre du programme, une 
	 * classe qui implémente l'interface ObservateurMastermind.
	 */
	public void addObservateurMastermind(ObservateurMastermind obs);

	/**
	 * Méthode permettant de mettre à jour les observateurs.
	 */
	public void updateObservateurMastermind();

	/**
	 * Méthode permettant de réinitialiser la liste des observateurs.
	 */
	public void delObservateurMastermind();

	/**
	 * Méthode permettant de quitter l'application.
	 */
	public void quitterApplicationObservateurMastermind();

	/**
	 * Méthode permettant de retourner à la page d'accueil.
	 */
	public void acceuilObservateurMastermind();

	/**
	 * Méthode permettant de relancer le même jeu.
	 */
	public void relancerPartieObservateurMastermind();
}