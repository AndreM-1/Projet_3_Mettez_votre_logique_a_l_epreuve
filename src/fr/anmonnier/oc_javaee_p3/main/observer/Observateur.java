package fr.anmonnier.oc_javaee_p3.main.observer;

public interface Observateur {
	public void update(String propositionJoueur, String reponse);
	public void updateDuel(String affichage);
	public void quitterApplication();
	public void acceuilObservateur();
	public void relancerPartie();
}
