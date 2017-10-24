package fr.anmonnier.oc_javaee_p3.main.observer;

public interface ObservateurMastermind {
	public void updateMastermind(String reponse);
	public void quitterApplicationMastermind();
	public void acceuilObservateurMastermind();
	public void relancerPartieMastermind();
}
