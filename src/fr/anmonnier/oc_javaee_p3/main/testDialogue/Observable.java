package fr.anmonnier.oc_javaee_p3.main.testDialogue;

public interface Observable {
	public void addObservateur(Observateur obs);

	public void updateObservateur();

	public void delObservateur();
}