package fr.anmonnier.oc_javaee_p3.main;


import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;
import fr.anmonnier.oc_javaee_p3.main.vue.Fenetre;

/*******************************
 * La classe Main du programme.
 * @author André Monnier
********************************/
public class Main {
	/**
	 * La méthode main qui permet d'instancier les modèles de données relatifs aux jeux
	 * RecherchePlusMoins et Mastermind et également la fenêtre principale.
	 * @param args Paramètre d'entrée standard de la fonction main.
	 * @see Fenetre
	 */
	public static void main(String[] args) {
		ModeleDonnees model=new ModeleDonnees();
		ModeleDonneesMastermind modelMastermind=new ModeleDonneesMastermind();
		new Fenetre(model,modelMastermind);		
	}
}