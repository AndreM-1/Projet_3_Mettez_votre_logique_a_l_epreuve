package fr.anmonnier.oc_javaee_p3.main.observer;

public interface ObservableMastermind {
	public void addObservateurMastermind(ObservateurMastermind obs);
	public void updateObservateurMastermind();
	public void delObservateurMastermind();
	public void quitterApplicationObservateurMastermind();
	public void acceuilObservateurMastermind();
	public void relancerPartieObservateurMastermind();
}