package fr.anmonnier.oc_javaee_p3.main.observer;

/*******************************************
 * Pattern Observer - Interface Observable. 
 * @author André Monnier
 *******************************************/
public interface Observable {

	/**
	 * Méthode permettant d'ajouter des observateurs à une liste d'observateur.
	 * @param obs Un objet de type Observateur. Dans le cadre du programme, une 
	 * classe qui implémente l'interface Observateur.
	 */
	public void addObservateur(Observateur obs);

	/**
	 * Méthode permettant de mettre à jour les observateurs.
	 */
	public void updateObservateur();

	/**
	 * Méthode permettant de réinitialiser la liste des observateurs.
	 */
	public void delObservateur();

	/**
	 * Méthode permettant de quitter l'application.
	 */
	public void quitterApplicationObservateur();

	/**
	 * Méthode permettant de retourner à la page d'accueil.
	 */
	public void acceuilObservateur();

	/**
	 * Méthode permettant de relancer le même jeu.
	 */
	public void relancerPartieObservateur();
}
