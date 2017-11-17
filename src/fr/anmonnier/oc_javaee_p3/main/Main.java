package fr.anmonnier.oc_javaee_p3.main;


import java.util.Scanner;

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
	 * RecherchePlusMoins et Mastermind et �galement la fen�tre principale. Il est aussi
	 * possible de choisir via la console si le mode d�veloppeur sera activ� ou non.
	 * @param args Param�tre d'entr�e standard de la fonction main.
	 * @see Fenetre
	 */
	public static void main(String[] args) {
		
		//Activation ou non du mode d�veloppeur via la console.
		Scanner sc=new Scanner(System.in);
		String strModeDeveloppeurActiveConsole="";
		boolean modeDeveloppeurActiveConsole=false;
		do {
			System.out.println("Souhaitez-vous activer le mode d�veloppeur(O pour oui/N pour non) ? :");
			strModeDeveloppeurActiveConsole=sc.nextLine();
		}
		while(!strModeDeveloppeurActiveConsole.equals("O")&&!strModeDeveloppeurActiveConsole.equals("N"));
		
		if(strModeDeveloppeurActiveConsole.equals("O"))
			modeDeveloppeurActiveConsole=true;
		else 
			modeDeveloppeurActiveConsole=false;
		
		//Instanciation des mod�les de donn�es relatifs aux jeux RecherchePlusMoins et Mastermind.
		ModeleDonnees model=new ModeleDonnees();
		ModeleDonneesMastermind modelMastermind=new ModeleDonneesMastermind();
		
		//Instanciation de la fen�tre principale.
		new Fenetre(model,modelMastermind,modeDeveloppeurActiveConsole);		
	}
}