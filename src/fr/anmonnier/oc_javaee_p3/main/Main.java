package fr.anmonnier.oc_javaee_p3.main;


import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;
import fr.anmonnier.oc_javaee_p3.main.vue.Fenetre;

/*******************************
 * La classe Main du programme.
 * @author Andr� Monnier
********************************/
public class Main {
	/**
	 * La m�thode main qui permet d'instancier les mod�les de donn�es relatifs aux jeux
	 * RecherchePlusMoins et Mastermind et �galement la fen�tre principale.
	 * @param args Param�tre d'entr�e standard de la fonction main.
	 * @see Fenetre
	 */
	public static void main(String[] args) {
		ModeleDonnees model=new ModeleDonnees();
		ModeleDonneesMastermind modelMastermind=new ModeleDonneesMastermind();
		new Fenetre(model,modelMastermind);		
	}
}