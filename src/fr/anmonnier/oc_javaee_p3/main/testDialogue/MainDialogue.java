package fr.anmonnier.oc_javaee_p3.main.testDialogue;

public class MainDialogue {
	static Programme1 prog1 = new Programme1();
	static Programme2 prog2 = new Programme2();

	public static void main(String[] args) {

		prog1.setNbEssais(10);
		prog2.setNbEssais(10);
		
		// C'est bien dans cette classe qu'il faut définir les observateurs!!!
		prog1.addObservateur(prog2);
		prog2.addObservateur(prog1);
		prog1.Dialogue();

	}

}
