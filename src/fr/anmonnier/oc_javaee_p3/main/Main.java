package fr.anmonnier.oc_javaee_p3.main;


import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.vue.Fenetre;

public class Main {
	public static void main(String[] args) {
		ModeleDonnees model=new ModeleDonnees();
		new Fenetre(model);		
	}
}