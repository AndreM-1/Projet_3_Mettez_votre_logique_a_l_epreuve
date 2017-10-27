package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.anmonnier.oc_javaee_p3.main.controler.MastermindControler;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;
import fr.anmonnier.oc_javaee_p3.main.observer.ObservateurMastermind;

public class MastermindModeChallenger extends JPanel implements ObservateurMastermind {

	private static final long serialVersionUID = 1L;
	private int nbreCases, nbEssais,ligne=0,colonne=1,nbCouleursUtilisables=6,verifCombinaisonSecrete=0;
	private boolean modeDeveloppeurActive;
	private GridLayout gl,glSolution;
	private JPanel jpContainerGrilleDeJeu=new JPanel(),jpContainerButtonEffacerValider=new JPanel(),jpContainerButtonCouleur=new JPanel(),
			jPanelContainerSolutionCombinaisonSecreteOrdinateur=new JPanel();
	private JPanel[] jpContainerSolution;
	private ImageIcon imgIconMastermindEmplacementVide=new ImageIcon("resources/MastermindEmplacementVide.png"),
			imgIconMastermindEmplacementVideSolution=new ImageIcon("resources/MastermindEmplacementVideSolution.png"),
			imgIconCouleurVerte=new ImageIcon("resources/imgCouleurVerte.png"),imgIconCouleurBleu=new ImageIcon("resources/imgCouleurBleu.png"),
			imgIconCouleurOrange=new ImageIcon("resources/imgCouleurOrange.png"),imgIconCouleurRouge=new ImageIcon("resources/imgCouleurRouge.png"),
			imgIconCouleurJaune=new ImageIcon("resources/imgCouleurJaune.png"),imgIconCouleurViolet=new ImageIcon("resources/imgCouleurViolet.png"),
			imgIconMastermindPionRougeSolution=new ImageIcon("resources/MastermindPionRougeSolution.png"),
			imgIconMastermindPionBlancSolution=new ImageIcon("resources/MastermindPionBlancSolution.png"),
			imgIconMastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur=new ImageIcon("resources/MastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur.png");
	private JLabel [][] tabJLabelGrilleDeJeu;
	private JLabel [] tabJLabelSolution,tabJLabelSolutionCombinaisonSecreteOrdinateur;
	private JLabel jlPremiereInstruction=new JLabel("La combinaison secrète a été générée par l'ordinateur."),jlSolution=new JLabel("Solution :");
	private JButton jbEffacer=new JButton("Effacer la ligne"), jbValider=new JButton("Valider"),
			jbCouleurVerte=new JButton(imgIconCouleurVerte),jbCouleurBleu=new JButton(imgIconCouleurBleu),
			jbCouleurOrange=new JButton(imgIconCouleurOrange),jbCouleurRouge=new JButton(imgIconCouleurRouge),
			jbCouleurJaune=new JButton(imgIconCouleurJaune),jbCouleurViolet=new JButton(imgIconCouleurViolet);
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14),policeSolution=new Font("Segoe UI Semilight",Font.BOLD,14);
	private String combinaisonSecreteOrdinateur="",propositionJoueurModeChallenger="";
	private ModeleDonneesMastermind modelMastermind;
	private MastermindControler controlerMastermind;
	private BoiteDialogueFinDePartie jdFinDePartie;
	private boolean finDePartie=false;


	public MastermindModeChallenger(int nbCases, int nbEssais,boolean modeDeveloppeurActive,ModeleDonneesMastermind modelMastermind) {
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.modeDeveloppeurActive=modeDeveloppeurActive;
		this.modelMastermind=modelMastermind;
		this.controlerMastermind=new MastermindControler(this.modelMastermind);
		this.generationCombinaisonSecreteOrdinateur();
		jlPremiereInstruction.setPreferredSize(new Dimension(900,40));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);

		jbValider.setEnabled(false);
		jpContainerButtonEffacerValider.setPreferredSize(new Dimension(900,40));
		jpContainerButtonEffacerValider.setBackground(Color.WHITE);
		jpContainerButtonEffacerValider.add(jbValider);
		jpContainerButtonEffacerValider.add(jbEffacer);

		jbCouleurBleu.setPreferredSize(new Dimension(27,27));
		jbCouleurJaune.setPreferredSize(new Dimension(27,27));
		jbCouleurOrange.setPreferredSize(new Dimension(27,27));
		jbCouleurRouge.setPreferredSize(new Dimension(27,27));
		jbCouleurVerte.setPreferredSize(new Dimension(27,27));
		jbCouleurViolet.setPreferredSize(new Dimension(27,27));
		jpContainerButtonCouleur.setPreferredSize(new Dimension(900,40));
		jpContainerButtonCouleur.setBackground(Color.WHITE);
		jpContainerButtonCouleur.add(jbCouleurBleu);
		jpContainerButtonCouleur.add(jbCouleurJaune);
		jpContainerButtonCouleur.add(jbCouleurOrange);
		jpContainerButtonCouleur.add(jbCouleurRouge);
		jpContainerButtonCouleur.add(jbCouleurVerte);
		jpContainerButtonCouleur.add(jbCouleurViolet);

		jlSolution.setFont(policeSolution);
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.setPreferredSize(new Dimension(900,40));
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.setBackground(Color.WHITE);
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(jlSolution);
		tabJLabelSolutionCombinaisonSecreteOrdinateur=new JLabel[this.nbreCases];
		if(modeDeveloppeurActive==false) {
			for (int i=0;i<this.nbreCases;i++) {
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel();
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setPreferredSize(new Dimension(27,27));
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur);
				jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(tabJLabelSolutionCombinaisonSecreteOrdinateur[i]);
			}
		}
		else {
			this.affichageSolution();
		}


		this.add(jlPremiereInstruction);
		this.add(jpContainerButtonEffacerValider);
		this.add(jpContainerButtonCouleur);
		this.InitialisationGrilleJeu();
		this.add(jpContainerGrilleDeJeu);

		this.add(jPanelContainerSolutionCombinaisonSecreteOrdinateur);

		this.modelMastermind.addObservateurMastermind(this);


		// Définition des listeners

		jbCouleurBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateGrilleJeu(ligne, colonne,imgIconCouleurBleu,"0");
				colonne++;
			}	
		});

		jbCouleurJaune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateGrilleJeu(ligne, colonne,imgIconCouleurJaune,"1");
				colonne++;
			}	
		});

		jbCouleurOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateGrilleJeu(ligne, colonne,imgIconCouleurOrange,"2");
				colonne++;
			}	
		});

		jbCouleurRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateGrilleJeu(ligne, colonne,imgIconCouleurRouge,"3");
				colonne++;
			}	
		});

		jbCouleurVerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateGrilleJeu(ligne, colonne,imgIconCouleurVerte,"4");
				colonne++;
			}	
		});

		jbCouleurViolet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateGrilleJeu(ligne, colonne,imgIconCouleurViolet,"5");
				colonne++;
			}	
		});

		jbValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlerMastermind.setPropositionJoueurModeChallenger(propositionJoueurModeChallenger);
				if(!finDePartie) {
					propositionJoueurModeChallenger="";
					ligne++;
					colonne=1;
					jbValider.setEnabled(false);
				}
				else
					finDePartie=false;
			}	
		});

		jbEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EffacerLigneGrilleJeu(ligne, colonne, imgIconMastermindEmplacementVide);
				colonne=1;
			}	
		});
	}

	/*****************************************************
	 * Méthode permettant d'initialiser la grille de jeu.
	 * 
	 * ***************************************************/
	private void InitialisationGrilleJeu() {
		gl=new GridLayout(this.nbEssais,this.nbreCases+2);
		jpContainerGrilleDeJeu.setLayout(gl);
		jpContainerGrilleDeJeu.setPreferredSize(new Dimension(30*(this.nbreCases+2),29*this.nbEssais));
		tabJLabelGrilleDeJeu=new JLabel[this.nbEssais][this.nbreCases+1];
		glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2);
		tabJLabelSolution= new JLabel[this.nbreCases];
		jpContainerSolution=new JPanel[this.nbEssais];

		/**
		 * La grille de jeu est un JPanel organisé en GridLayout composé d'un tableau de JLabel et d'un tableau de JPanel organisé 
		 * également en GridLayout.
		 * 
		 */
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<=this.nbreCases;j++) {
				if(j==0) {
					tabJLabelGrilleDeJeu[i][j]=new JLabel(String.valueOf(i+1));
					tabJLabelGrilleDeJeu[i][j].setOpaque(true);
					tabJLabelGrilleDeJeu[i][j].setBackground(Color.LIGHT_GRAY);
					tabJLabelGrilleDeJeu[i][j].setForeground(Color.BLACK);
					tabJLabelGrilleDeJeu[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					tabJLabelGrilleDeJeu[i][j].setHorizontalAlignment(JLabel.CENTER);		
				}
				else {
					tabJLabelGrilleDeJeu[i][j]=new JLabel(imgIconMastermindEmplacementVide);
				}	
			}
		}

		for (int i=0;i<this.nbEssais;i++) {
			jpContainerSolution[i]=new JPanel();
			jpContainerSolution[i].removeAll();
			jpContainerSolution[i].setPreferredSize(new Dimension(30,29));
			jpContainerSolution[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jpContainerSolution[i].setLayout(glSolution);
			for (int k=0;k<this.nbreCases;k++) {
				tabJLabelSolution[k]=new JLabel(imgIconMastermindEmplacementVideSolution);
				jpContainerSolution[i].add(tabJLabelSolution[k]);
			}
			jpContainerSolution[i].revalidate();
			jpContainerSolution[i].repaint();
		}

		//L'organisation en GridLayout impose un remplissage ligne par ligne
		jpContainerGrilleDeJeu.removeAll();
		for (int i=0;i<this.nbEssais;i++) {
			int j=0;
			for (j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeu.add(tabJLabelGrilleDeJeu[i][j]);
			}
			jpContainerGrilleDeJeu.add(jpContainerSolution[i]);
		}
		jpContainerGrilleDeJeu.revalidate();
		jpContainerGrilleDeJeu.repaint();
	}

	/********************************************************
	 * Méthode permettant de mettre à jour la grille de jeu.
	 * 
	 * ******************************************************/
	private void UpdateGrilleJeu(int lig, int col,ImageIcon couleurChoisie, String codeCouleur) {
		if(colonne<=this.nbreCases) {
			tabJLabelGrilleDeJeu[lig][col]=new JLabel(couleurChoisie);
			propositionJoueurModeChallenger+=codeCouleur;
			tabJLabelGrilleDeJeu[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//L'organisation en GridLayout impose un remplissage ligne par ligne
			jpContainerGrilleDeJeu.removeAll();
			for (int i=0;i<this.nbEssais;i++) {
				for (int j=0;j<this.nbreCases+1;j++) {
					jpContainerGrilleDeJeu.add(tabJLabelGrilleDeJeu[i][j]);
				}
				jpContainerGrilleDeJeu.add(jpContainerSolution[i]);
			}
			jpContainerGrilleDeJeu.revalidate();
			jpContainerGrilleDeJeu.repaint();	
			if(colonne==this.nbreCases) {
				jbValider.setEnabled(true);
			}	
		}

		if(colonne>this.nbreCases) {
			JOptionPane.showMessageDialog(null, "La ligne est complète. Vous pouvez soit valider, soit effacer la ligne", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
			colonne=this.nbreCases;
		}
	}

	private void EffacerLigneGrilleJeu(int lig, int col,ImageIcon emplacementVide) {
		for(int i=1;i<col;i++) {
			tabJLabelGrilleDeJeu[lig][i]=new JLabel(emplacementVide);
		}
		propositionJoueurModeChallenger="";
		//L'organisation en GridLayout impose un remplissage ligne par ligne
		jpContainerGrilleDeJeu.removeAll();
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeu.add(tabJLabelGrilleDeJeu[i][j]);
			}
			jpContainerGrilleDeJeu.add(jpContainerSolution[i]);
		}
		jpContainerGrilleDeJeu.revalidate();
		jpContainerGrilleDeJeu.repaint();	
		jbValider.setEnabled(false);

	}

	/*****************************************************************************************************************
	 * Génération de la combinaison secrète par l'ordinateur. On genère une combinaison de chiffres castés en String,
	 * chaque chiffre correspondant à une couleur.
	 ******************************************************************************************************************/
	private void generationCombinaisonSecreteOrdinateur(){
		int nbreAleatoire;
		for (int i=0;i<this.nbreCases;i++) {
			nbreAleatoire=(int)(Math.random()*nbCouleursUtilisables);
			combinaisonSecreteOrdinateur+=String.valueOf(nbreAleatoire);	
		}

		System.out.println("COMBINAISON SECRETE VUE :"+combinaisonSecreteOrdinateur);
		controlerMastermind.setModeDeJeu(0);
		controlerMastermind.setNbEssais(this.nbEssais);
		controlerMastermind.setNbreCases(this.nbreCases);
		controlerMastermind.setPropositionSecreteOrdinateurModeChallenger(combinaisonSecreteOrdinateur);

	}

	//Implémentation du pattern Observer
	public void quitterApplicationMastermind() {}
	public void acceuilObservateurMastermind() {}

	public void updateMastermind(String reponse) {

		/*Pour une ligne donnée, on met à jour le JPanel jpContainerSolution en suivant les étapes habituelles pour un JPanel :
		On supprime les anciens composants, on ajoute les nouveaux, on revalide et on fait appel à la méthode repaint()*/

		jpContainerSolution[ligne].removeAll();

		for (int i=0;i<this.nbreCases;i++) {
			if(reponse.charAt(i)=='R') {
				tabJLabelSolution[i]=new JLabel(imgIconMastermindPionRougeSolution);
				jpContainerSolution[ligne].add(tabJLabelSolution[i]);
			}
			else if(reponse.charAt(i)=='B') {
				tabJLabelSolution[i]=new JLabel(imgIconMastermindPionBlancSolution);
				jpContainerSolution[ligne].add(tabJLabelSolution[i]);
			}
			else {
				tabJLabelSolution[i]=new JLabel(imgIconMastermindEmplacementVideSolution);
				jpContainerSolution[ligne].add(tabJLabelSolution[i]);
			}
		}

		jpContainerSolution[ligne].revalidate();
		jpContainerSolution[ligne].repaint();

		//L'organisation en GridLayout impose un remplissage ligne par ligne
		jpContainerGrilleDeJeu.removeAll();
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeu.add(tabJLabelGrilleDeJeu[i][j]);
			}
			jpContainerGrilleDeJeu.add(jpContainerSolution[i]);
		}
		jpContainerGrilleDeJeu.revalidate();
		jpContainerGrilleDeJeu.repaint();
		this.gestionFinDePartie(reponse);
	}

	/*On réinitialise l'IHM de la grille de jeu et de la solution en bas de page ainsi que toutes les variables
	et on regénère une nouvelle combinaison secrète*/
	public void relancerPartieMastermind() {
		//Réinitialisation de l'IHM : on refait appel à la fonction InitialisationGrilleJeu() et on réinitialise la solution en bas de page
		this.InitialisationGrilleJeu();
		if(modeDeveloppeurActive==false) {
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.removeAll();
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(jlSolution);
			for (int i=0;i<this.nbreCases;i++) {
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel();
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setPreferredSize(new Dimension(27,27));
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur);
				jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(tabJLabelSolutionCombinaisonSecreteOrdinateur[i]);
			}
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.revalidate();
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.repaint();
		}

		ligne=0;
		colonne=1;
		verifCombinaisonSecrete=0;
		jbValider.setEnabled(false);
		propositionJoueurModeChallenger="";
		combinaisonSecreteOrdinateur="";
		this.generationCombinaisonSecreteOrdinateur();
		if(modeDeveloppeurActive==true)
			this.affichageSolution();
	}

	//Gestion de la fin de la partie
	private void gestionFinDePartie(String reponse) {

		verifCombinaisonSecrete=0;
		for (int i=0;i<reponse.length();i++) {
			if(reponse.charAt(i)=='R')
				verifCombinaisonSecrete++;
		}

		//En cas de victoire
		if(verifCombinaisonSecrete==nbreCases) {
			JOptionPane.showMessageDialog(null, "Félicitations!!! Vous avez trouvé la combinaison secrète en moins de "+nbEssais+" essais.", 
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);
			this.affichageSolution();
		}

		//En cas de défaîte
		if (ligne==nbEssais-1 && verifCombinaisonSecrete!=nbreCases) {
			this.affichageSolution();
			JOptionPane.showMessageDialog(null, "Vous avez perdu! Pour information, la combinaison secrète est affichée en bas de la page.", "Fin de Partie",
					JOptionPane.INFORMATION_MESSAGE);
		}

		//En cas de défaîte ou de victoire
		if(ligne==nbEssais-1||verifCombinaisonSecrete==nbreCases) {
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controlerMastermind.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}

	}

	private void affichageSolution() {
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.removeAll();
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(jlSolution);
		for (int i=0;i<nbreCases;i++) {
			switch(combinaisonSecreteOrdinateur.charAt(i)) {
			case '0':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurBleu);
				break;
			case '1':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurJaune);
				break;
			case '2':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurOrange);
				break;
			case '3':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurRouge);
				break;
			case '4':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurVerte);
				break;
			case '5':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurViolet);
				break;
			default :
				System.out.println("Erreur de correspondance entre la combinaison secrète et les couleurs");
			}

			tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(tabJLabelSolutionCombinaisonSecreteOrdinateur[i]);
		}
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.revalidate();
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.repaint();
	}
}