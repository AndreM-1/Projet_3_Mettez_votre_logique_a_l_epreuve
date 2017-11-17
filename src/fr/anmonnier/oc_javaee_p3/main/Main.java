package fr.anmonnier.oc_javaee_p3.main;


import java.util.Scanner;

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
	 * RecherchePlusMoins et Mastermind et également la fenêtre principale. Il est aussi
	 * possible de choisir via la console si le mode développeur sera activé ou non.
	 * @param args Paramètre d'entrée standard de la fonction main.
	 * @see Fenetre
	 */
	public static void main(String[] args) {
		
		//Activation ou non du mode développeur via la console.
		Scanner sc=new Scanner(System.in);
		String strModeDeveloppeurActiveConsole="";
		boolean modeDeveloppeurActiveConsole=false;
		do {
			System.out.println("Souhaitez-vous activer le mode développeur(O pour oui/N pour non) ? :");
			strModeDeveloppeurActiveConsole=sc.nextLine();
		}
		while(!strModeDeveloppeurActiveConsole.equals("O")&&!strModeDeveloppeurActiveConsole.equals("N"));
		
		if(strModeDeveloppeurActiveConsole.equals("O"))
			modeDeveloppeurActiveConsole=true;
		else 
			modeDeveloppeurActiveConsole=false;
		
		//Instanciation des modèles de données relatifs aux jeux RecherchePlusMoins et Mastermind.
		ModeleDonnees model=new ModeleDonnees();
		ModeleDonneesMastermind modelMastermind=new ModeleDonneesMastermind();
		
		//Instanciation de la fenêtre principale.
		new Fenetre(model,modelMastermind,modeDeveloppeurActiveConsole);		
	}
}