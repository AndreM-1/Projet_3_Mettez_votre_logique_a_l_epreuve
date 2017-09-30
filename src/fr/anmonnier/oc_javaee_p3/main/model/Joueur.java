package fr.anmonnier.oc_javaee_p3.main.model;

import java.util.ArrayList;
import java.util.Random;

import fr.anmonnier.oc_javaee_p3.main.observer.Observable;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

public class Joueur implements Observable{
	
	private ArrayList <Observateur> listeObservateur=new ArrayList<Observateur>();
	private String texte="";
	private int nbreAleatoire,min=1000,max=9999;
	private int[] tableau;
	private Random rand=new Random();
	
	public void modeChallenger() {
		nbreAleatoire=rand.nextInt(max-min+1)+min;
		texte="Mode Challenger"+"\n"+"(Combinaison secrète : "+nbreAleatoire+")";
		texte+="\n"+"Proposition :";
		conversionTab();
		for (int i=0;i<4;i++) {
			System.out.println(tableau[i]+"\t");
		}
		this.updateObservateur();
	}
	
	public int[] conversionTab() {
		String str=""+nbreAleatoire;
		tableau=new int[str.length()];
		for(int i=0;i<str.length();i++) {
			tableau[i]=Integer.parseInt(""+str.charAt(i));
		}
		return tableau;
	}

	public void addObservateur(Observateur obs) {
		this.listeObservateur.add(obs);
	}

	public void updateObservateur() {
		for (Observateur obs:this.listeObservateur) {
			obs.update(texte);
		}
	}

	public void delObservateur() {
		this.listeObservateur=new ArrayList<Observateur>();
	}

}
