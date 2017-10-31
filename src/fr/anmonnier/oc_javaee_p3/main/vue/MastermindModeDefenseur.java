package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.anmonnier.oc_javaee_p3.main.controler.MastermindControler;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;
import fr.anmonnier.oc_javaee_p3.main.observer.ObservateurMastermind;

public class MastermindModeDefenseur extends JPanel implements ObservateurMastermind {

	private static final long serialVersionUID = 1L;
	private int nbreCases, nbEssais,nbCouleursUtilisables,ligne=0,colonne=1,colonneCombinaisonSecrete=0,remplissageSolution=0,
			verifCombinaisonSecrete=0;
	private int [] tabReponseJoueur;
	private GridLayout gl,glSolution;
	private JPanel jpContainerGrilleDeJeu=new JPanel(),jpContainerButtonCouleur=new JPanel(),
			jpContainerReponseJoueur=new JPanel(),jPanelContainerSolutionCombinaisonSecreteJoueur=new JPanel();
	private JPanel[] jpContainerSolution;
	private ImageIcon imgIconMastermindEmplacementVide=new ImageIcon("resources/MastermindEmplacementVide.png"),
			imgIconMastermindEmplacementVideSolution=new ImageIcon("resources/MastermindEmplacementVideSolution.png"),
			imgIconCouleurVerte=new ImageIcon("resources/imgCouleurVerte.png"),imgIconCouleurBleu=new ImageIcon("resources/imgCouleurBleu.png"),
			imgIconCouleurOrange=new ImageIcon("resources/imgCouleurOrange.png"),imgIconCouleurRouge=new ImageIcon("resources/imgCouleurRouge.png"),
			imgIconCouleurJaune=new ImageIcon("resources/imgCouleurJaune.png"),imgIconCouleurViolet=new ImageIcon("resources/imgCouleurViolet.png"),
			imgIconMastermindPionRougeSolution=new ImageIcon("resources/MastermindPionRougeSolution.png"),
			imgIconMastermindPionBlancSolution=new ImageIcon("resources/MastermindPionBlancSolution.png"),
			imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur=new ImageIcon("resources/MastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur.png"),
			imgIconCouleurGris=new ImageIcon("resources/imgCouleurGris.png"),imgIconCouleurBleuFonce=new ImageIcon("resources/imgCouleurBleuFonce.png"),
			imgIconCouleurNoir=new ImageIcon("resources/imgCouleurNoir.png"),imgIconCouleurMarron=new ImageIcon("resources/imgCouleurMarron.png");
	private JLabel [][] tabJLabelGrilleDeJeu;
	private JLabel [] tabJLabelSolution,tabJLabelSolutionCombinaisonSecreteJoueur;
	private JLabel jlPremiereInstruction=new JLabel("Veuillez choisir une combinaison secr�te."),jlReponseJoueur=new JLabel("Votre r�ponse :"),
			jlSolution=new JLabel("Combinaison Secr�te :");
	private JButton jbEffacerCombinaisonSecrete=new JButton("Effacer"), jbValiderCombinaisonSecrete=new JButton("Valider"),
			jbCouleurVerte=new JButton(imgIconCouleurVerte),jbCouleurBleu=new JButton(imgIconCouleurBleu),
			jbCouleurOrange=new JButton(imgIconCouleurOrange),jbCouleurRouge=new JButton(imgIconCouleurRouge),
			jbCouleurJaune=new JButton(imgIconCouleurJaune),jbCouleurViolet=new JButton(imgIconCouleurViolet),
			jbValiderReponseJoueur=new JButton("Valider"),jbEffacerReponseJoueur=new JButton("Effacer"),
			jbPionRougeSolution=new JButton(imgIconMastermindPionRougeSolution),jbPionBlancSolution=new JButton(imgIconMastermindPionBlancSolution),
			jbCouleurGris=new JButton(imgIconCouleurGris),jbCouleurBleuFonce=new JButton(imgIconCouleurBleuFonce),
			jbCouleurNoir=new JButton(imgIconCouleurNoir),jbCouleurMarron=new JButton(imgIconCouleurMarron);	
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14),policeSolution=new Font("Segoe UI Semilight",Font.BOLD,14);
	private String propositionSecreteJoueurModeDefenseur="",propositionOrdinateurModeDefenseur="",
			reponseJoueurModeDefenseur="",reponseAttendue="";
	private ModeleDonneesMastermind modelMastermind;
	private MastermindControler controlerMastermind;
	private BoiteDialogueFinDePartie jdFinDePartie;
	private boolean finDePartie=false;


	public MastermindModeDefenseur(int nbCases, int nbEssais,int nbCouleursUtilisables,boolean modeDeveloppeurActive,ModeleDonneesMastermind modelMastermind) {
		this.setPreferredSize(new Dimension(1000,740));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.nbCouleursUtilisables=nbCouleursUtilisables;
		this.modelMastermind=modelMastermind;
		this.controlerMastermind=new MastermindControler(this.modelMastermind);

		//Ce tableau sera utilis� pour enregistrer la r�ponse du joueur
		tabReponseJoueur=new int[this.nbreCases];
		for (int i=0;i<this.nbreCases;i++) {
			tabReponseJoueur[i]=3;
			reponseJoueurModeDefenseur+="V";
		}

		jlPremiereInstruction.setPreferredSize(new Dimension(1000,40));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);

		jbCouleurBleu.setPreferredSize(new Dimension(29,29));
		jbCouleurJaune.setPreferredSize(new Dimension(29,29));
		jbCouleurOrange.setPreferredSize(new Dimension(29,29));
		jbCouleurRouge.setPreferredSize(new Dimension(29,29));
		jbCouleurVerte.setPreferredSize(new Dimension(29,29));
		jbCouleurViolet.setPreferredSize(new Dimension(29,29));
		jbCouleurGris.setPreferredSize(new Dimension(29,29));
		jbCouleurBleuFonce.setPreferredSize(new Dimension(29,29));
		jbCouleurMarron.setPreferredSize(new Dimension(29,29));
		jbCouleurNoir.setPreferredSize(new Dimension(29,29));
		jbValiderCombinaisonSecrete.setEnabled(false);
		jpContainerButtonCouleur.setPreferredSize(new Dimension(1000,40));
		jpContainerButtonCouleur.setBackground(Color.WHITE);
		
		
		switch(this.nbCouleursUtilisables) {
		case 4 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			break;
		case 5 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			jpContainerButtonCouleur.add(jbCouleurVerte);
			break;
		case 6 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			jpContainerButtonCouleur.add(jbCouleurVerte);
			jpContainerButtonCouleur.add(jbCouleurViolet);
			break;
		case 7 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			jpContainerButtonCouleur.add(jbCouleurVerte);
			jpContainerButtonCouleur.add(jbCouleurViolet);
			jpContainerButtonCouleur.add(jbCouleurGris);
			break;
		case 8 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			jpContainerButtonCouleur.add(jbCouleurVerte);
			jpContainerButtonCouleur.add(jbCouleurViolet);
			jpContainerButtonCouleur.add(jbCouleurGris);
			jpContainerButtonCouleur.add(jbCouleurBleuFonce);
			break;
		case 9 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			jpContainerButtonCouleur.add(jbCouleurVerte);
			jpContainerButtonCouleur.add(jbCouleurViolet);
			jpContainerButtonCouleur.add(jbCouleurGris);
			jpContainerButtonCouleur.add(jbCouleurBleuFonce);
			jpContainerButtonCouleur.add(jbCouleurMarron);
			break;
		case 10 :
			jpContainerButtonCouleur.add(jbCouleurBleu);
			jpContainerButtonCouleur.add(jbCouleurJaune);
			jpContainerButtonCouleur.add(jbCouleurOrange);
			jpContainerButtonCouleur.add(jbCouleurRouge);
			jpContainerButtonCouleur.add(jbCouleurVerte);
			jpContainerButtonCouleur.add(jbCouleurViolet);
			jpContainerButtonCouleur.add(jbCouleurGris);
			jpContainerButtonCouleur.add(jbCouleurBleuFonce);
			jpContainerButtonCouleur.add(jbCouleurMarron);
			jpContainerButtonCouleur.add(jbCouleurNoir);
			break;
		default :
			System.out.println("Erreur lors de la mise en place de l'IHM pour les boutons li�s aux couleurs");
		}
		
		jpContainerButtonCouleur.add(jbValiderCombinaisonSecrete);
		jpContainerButtonCouleur.add(jbEffacerCombinaisonSecrete);

		jlReponseJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlReponseJoueur.setFont(police);
		jbPionRougeSolution.setPreferredSize(new Dimension(14,14));
		jbPionRougeSolution.setEnabled(false);
		jbPionBlancSolution.setPreferredSize(new Dimension(14,14));
		jbPionBlancSolution.setEnabled(false);
		jbValiderReponseJoueur.setEnabled(false);
		jbEffacerReponseJoueur.setEnabled(false);
		jpContainerReponseJoueur.setPreferredSize(new Dimension(1000,40));
		jpContainerReponseJoueur.setBackground(Color.WHITE);
		jpContainerReponseJoueur.add(jlReponseJoueur);
		jpContainerReponseJoueur.add(jbPionRougeSolution);
		jpContainerReponseJoueur.add(jbPionBlancSolution);
		jpContainerReponseJoueur.add(jbValiderReponseJoueur);
		jpContainerReponseJoueur.add(jbEffacerReponseJoueur);

		jlSolution.setFont(policeSolution);
		jPanelContainerSolutionCombinaisonSecreteJoueur.setPreferredSize(new Dimension(1000,40));
		jPanelContainerSolutionCombinaisonSecreteJoueur.setBackground(Color.WHITE);
		jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlSolution);
		tabJLabelSolutionCombinaisonSecreteJoueur=new JLabel[this.nbreCases];
		for (int i=0;i<this.nbreCases;i++) {
			tabJLabelSolutionCombinaisonSecreteJoueur[i]=new JLabel();
			tabJLabelSolutionCombinaisonSecreteJoueur[i].setPreferredSize(new Dimension(29,29));
			tabJLabelSolutionCombinaisonSecreteJoueur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur);
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
		}

		this.add(jlPremiereInstruction);
		this.add(jpContainerButtonCouleur);
		this.add(jpContainerReponseJoueur);
		this.InitialisationGrilleJeu();
		this.add(jpContainerGrilleDeJeu);
		this.add(jPanelContainerSolutionCombinaisonSecreteJoueur);

		this.modelMastermind.addObservateurMastermind(this);


		// D�finition des listeners

		jbCouleurBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurBleu,"0");
				colonneCombinaisonSecrete++;
			}	
		});

		jbCouleurJaune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurJaune,"1");
				colonneCombinaisonSecrete++;
			}	
		});

		jbCouleurOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurOrange,"2");
				colonneCombinaisonSecrete++;
			}	
		});

		jbCouleurRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurRouge,"3");
				colonneCombinaisonSecrete++;
			}	
		});

		jbCouleurVerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurVerte,"4");
				colonneCombinaisonSecrete++;
			}	
		});

		jbCouleurViolet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurViolet,"5");
				colonneCombinaisonSecrete++;
			}	
		});
		
		jbCouleurGris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurGris,"6");
				colonneCombinaisonSecrete++;
			}	
		});
		
		jbCouleurBleuFonce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurBleuFonce,"7");
				colonneCombinaisonSecrete++;
			}	
		});
		
		jbCouleurMarron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurMarron,"8");
				colonneCombinaisonSecrete++;
			}	
		});
		
		jbCouleurNoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurNoir,"9");
				colonneCombinaisonSecrete++;
			}	
		});
		
		jbValiderCombinaisonSecrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jbCouleurBleu.setEnabled(false);
				jbCouleurJaune.setEnabled(false);
				jbCouleurOrange.setEnabled(false);
				jbCouleurRouge.setEnabled(false);
				jbCouleurVerte.setEnabled(false);
				jbCouleurViolet.setEnabled(false);
				jbCouleurGris.setEnabled(false);
				jbCouleurBleuFonce.setEnabled(false);
				jbCouleurMarron.setEnabled(false);
				jbCouleurNoir.setEnabled(false);
				jbValiderCombinaisonSecrete.setEnabled(false);
				jbEffacerCombinaisonSecrete.setEnabled(false);
				jbPionRougeSolution.setEnabled(true);
				jbPionBlancSolution.setEnabled(true);
				jbValiderReponseJoueur.setEnabled(true);
				jbEffacerReponseJoueur.setEnabled(true);
				controlerMastermind.setNbEssais(nbEssais);
				controlerMastermind.setNbreCases(nbreCases);
				controlerMastermind.setNbCouleursUtilisables(nbCouleursUtilisables);
				controlerMastermind.setModeDeJeu(1);
				controlerMastermind.setPropositionSecreteJoueurModeDefenseur(propositionSecreteJoueurModeDefenseur);	
			}	
		});

		jbEffacerCombinaisonSecrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EffacerCombinaisonSecrete(colonneCombinaisonSecrete,imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur);
				colonneCombinaisonSecrete=0;
			}	
		});


		jbPionRougeSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageReponseJoueur(imgIconMastermindPionRougeSolution,1);
			}	
		});

		jbPionBlancSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageReponseJoueur(imgIconMastermindPionBlancSolution,2);
			}	
		});

		jbValiderReponseJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("reponseJoueurModeDefenseur :"+reponseJoueurModeDefenseur);
				calculReponseAttendue();
				System.out.println("Reponse attendue :"+reponseAttendue);
				if(!reponseJoueurModeDefenseur.equals(reponseAttendue)) {
					JOptionPane.showMessageDialog(null,"Attention : votre r�ponse est erron�e. Veuillez saisir une autre r�ponse, svp.", 
							"Message d'avertissement", JOptionPane.WARNING_MESSAGE);
					effacerReponseJoueur();
				}
				else {
					ligne++;
					gestionFinDePartie(reponseJoueurModeDefenseur);
					if(!finDePartie) {
						controlerMastermind.setReponseJoueurModeDefenseur(reponseJoueurModeDefenseur);
						remplissageSolution=0;
						reponseJoueurModeDefenseur="";
						for (int i=0;i<nbreCases;i++) {
							tabReponseJoueur[i]=3;
							reponseJoueurModeDefenseur+="V";
						}
					}
					else
						finDePartie=false;
				}
			}	
		});

		jbEffacerReponseJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				effacerReponseJoueur();
			}	
		});

	}

	/*****************************************************
	 * M�thode permettant d'initialiser la grille de jeu.
	 * 
	 * ***************************************************/
	private void InitialisationGrilleJeu() {
		gl=new GridLayout(this.nbEssais,this.nbreCases+2);
		jpContainerGrilleDeJeu.setLayout(gl);
		jpContainerGrilleDeJeu.setPreferredSize(new Dimension(30*(this.nbreCases+2),29*this.nbEssais));
		tabJLabelGrilleDeJeu=new JLabel[this.nbEssais][this.nbreCases+1];
		if(this.nbreCases==4)
			glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2);
		else if(this.nbreCases==5)
			glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2+1);
		else
			glSolution=new GridLayout(this.nbreCases/2-1,this.nbreCases/2);

		tabJLabelSolution= new JLabel[this.nbreCases];
		jpContainerSolution=new JPanel[this.nbEssais];

		/**
		 * La grille de jeu est un JPanel organis� en GridLayout compos� d'un tableau de JLabel et d'un tableau de JPanel organis� 
		 * �galement en GridLayout.
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
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeu.add(tabJLabelGrilleDeJeu[i][j]);
			}
			jpContainerGrilleDeJeu.add(jpContainerSolution[i]);
		}
		jpContainerGrilleDeJeu.revalidate();
		jpContainerGrilleDeJeu.repaint();
	}

	/**************************************************************
	 * M�thode permettant de mettre � jour la combinaison secr�te.
	 * 
	 **************************************************************/
	private void UpdateCombinaisonSecrete(int col,ImageIcon couleurChoisie, String codeCouleur) {
		if(colonneCombinaisonSecrete<this.nbreCases) {
			propositionSecreteJoueurModeDefenseur+=codeCouleur;
			tabJLabelSolutionCombinaisonSecreteJoueur[col]=new JLabel(couleurChoisie);
			tabJLabelSolutionCombinaisonSecreteJoueur[col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jPanelContainerSolutionCombinaisonSecreteJoueur.removeAll();
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlSolution);
			for (int i=0;i<this.nbreCases;i++) {
				jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
			}
			jPanelContainerSolutionCombinaisonSecreteJoueur.revalidate();
			jPanelContainerSolutionCombinaisonSecreteJoueur.repaint();
			if(colonneCombinaisonSecrete==this.nbreCases-1)
				jbValiderCombinaisonSecrete.setEnabled(true);
		}
		else if(colonneCombinaisonSecrete==this.nbreCases) {
			JOptionPane.showMessageDialog(null, "La ligne est compl�te. Vous pouvez soit valider, soit effacer la ligne", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
			colonneCombinaisonSecrete=this.nbreCases-1;
		}

	}

	private void EffacerCombinaisonSecrete(int col,ImageIcon emplacementVide) {
		for (int i=0;i<col;i++) {
			tabJLabelSolutionCombinaisonSecreteJoueur[i]=new JLabel(emplacementVide);
		}
		jPanelContainerSolutionCombinaisonSecreteJoueur.removeAll();
		jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlSolution);
		for (int i=0;i<this.nbreCases;i++) {
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
		}
		jPanelContainerSolutionCombinaisonSecreteJoueur.revalidate();
		jPanelContainerSolutionCombinaisonSecreteJoueur.repaint();
		jbValiderCombinaisonSecrete.setEnabled(false);
		propositionSecreteJoueurModeDefenseur="";

	}

	//Impl�mentation du pattern Observer
	public void quitterApplicationMastermind() {}
	public void acceuilObservateurMastermind() {}

	public void updateMastermind(String propositionOrdinateur) {
		this.propositionOrdinateurModeDefenseur=propositionOrdinateur;
		/*On convertit la proposition de l'ordinateur en JLabel avec les images ad�quates en vue de l'affichage
		 sur la grille de jeu*/
		jpContainerGrilleDeJeu.removeAll();
		for (int i=0;i<nbreCases;i++) {
			switch(propositionOrdinateur.charAt(i)) {
			case '0':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurBleu);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '1':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurJaune);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '2':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurOrange);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '3':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurRouge);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '4':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurVerte);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '5':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurViolet);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '6':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurGris);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '7':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurBleuFonce);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '8':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurMarron);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			case '9':
				tabJLabelGrilleDeJeu[ligne][i+1]=new JLabel(imgIconCouleurNoir);
				tabJLabelGrilleDeJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				break;
			default :
				System.out.println("Erreur de correspondance entre la proposition de l'ordinateur et les couleurs");
			}
		}
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeu.add(tabJLabelGrilleDeJeu[i][j]);
			}
			jpContainerGrilleDeJeu.add(jpContainerSolution[i]);
		}
		jpContainerGrilleDeJeu.revalidate();
		jpContainerGrilleDeJeu.repaint();
	}

	//On r�initialise l'IHM de la grille de jeu et de la combinaison secr�te en bas de page ainsi que toutes les variables.
	public void relancerPartieMastermind() {
		this.InitialisationGrilleJeu();
		jPanelContainerSolutionCombinaisonSecreteJoueur.removeAll();
		jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlSolution);
		for (int i=0;i<this.nbreCases;i++) {
			tabJLabelSolutionCombinaisonSecreteJoueur[i]=new JLabel();
			tabJLabelSolutionCombinaisonSecreteJoueur[i].setPreferredSize(new Dimension(29,29));
			tabJLabelSolutionCombinaisonSecreteJoueur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur);
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
		}
		jPanelContainerSolutionCombinaisonSecreteJoueur.revalidate();
		jPanelContainerSolutionCombinaisonSecreteJoueur.repaint();
		jbCouleurBleu.setEnabled(true);
		jbCouleurJaune.setEnabled(true);
		jbCouleurOrange.setEnabled(true);
		jbCouleurRouge.setEnabled(true);
		jbCouleurVerte.setEnabled(true);
		jbCouleurViolet.setEnabled(true);
		jbCouleurGris.setEnabled(true);
		jbCouleurBleuFonce.setEnabled(true);
		jbCouleurMarron.setEnabled(true);
		jbCouleurNoir.setEnabled(true);
		jbValiderCombinaisonSecrete.setEnabled(false);
		jbEffacerCombinaisonSecrete.setEnabled(true);
		jbPionRougeSolution.setEnabled(false);
		jbPionBlancSolution.setEnabled(false);
		jbValiderReponseJoueur.setEnabled(false);
		jbEffacerReponseJoueur.setEnabled(false);
		reponseJoueurModeDefenseur="";
		for (int i=0;i<this.nbreCases;i++) {
			tabReponseJoueur[i]=3;
			reponseJoueurModeDefenseur+="V";
		}
		ligne=0;
		remplissageSolution=0;
		colonneCombinaisonSecrete=0;
		verifCombinaisonSecrete=0;
		reponseAttendue="";
		propositionSecreteJoueurModeDefenseur="";
		propositionOrdinateurModeDefenseur="";
	}

	//Gestion de la fin de la partie
	private void gestionFinDePartie(String reponse) {
		verifCombinaisonSecrete=0;
		for (int i=0;i<reponse.length();i++) {
			if(reponse.charAt(i)=='R')
				verifCombinaisonSecrete++;
		}

		//En cas de d�fa�te
		if(verifCombinaisonSecrete==nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez perdu. L'ordinateur a trouv� la combinaison secr�te "+
					"en moins de "+nbEssais+" essais.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);
		}

		//En cas de victoire
		if (ligne==nbEssais && verifCombinaisonSecrete!=nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez gagn�! L'ordinateur n'a pas trouv� la combinaison secr�te "+
					"en moins de "+nbEssais+" essais.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);
		}

		//En cas de d�fa�te ou de victoire
		if (ligne==nbEssais||verifCombinaisonSecrete==nbreCases) {
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controlerMastermind.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}

	}

	private void effacerReponseJoueur() {
		jpContainerSolution[ligne].removeAll();
		jpContainerSolution[ligne].revalidate();
		for (int i=0;i<nbreCases;i++) {
			tabJLabelSolution[i]=new JLabel(imgIconMastermindEmplacementVideSolution);
			jpContainerSolution[ligne].add(tabJLabelSolution[i]);
		}
		jpContainerSolution[ligne].repaint();
		remplissageSolution=0;
		reponseJoueurModeDefenseur="";
		for (int i=0;i<nbreCases;i++) {
			tabReponseJoueur[i]=3;
			reponseJoueurModeDefenseur+="V";
		}
	}

	private void affichageReponseJoueur(ImageIcon couleurChoisieReponse, int codeCouleurReponse) {
		if(remplissageSolution<this.nbreCases) {
			jpContainerSolution[ligne].removeAll();
			reponseJoueurModeDefenseur="";
			tabReponseJoueur[remplissageSolution]=codeCouleurReponse;

			for(int i=0;i<remplissageSolution;i++) {
				jpContainerSolution[ligne].add(tabJLabelSolution[i]);
			}

			if(codeCouleurReponse==1) {
				tabJLabelSolution[remplissageSolution]=new JLabel(couleurChoisieReponse);
				jpContainerSolution[ligne].add(tabJLabelSolution[remplissageSolution]);
				remplissageSolution++;
			}
			else if(codeCouleurReponse==2) {
				tabJLabelSolution[remplissageSolution]=new JLabel(imgIconMastermindPionBlancSolution);
				jpContainerSolution[ligne].add(tabJLabelSolution[remplissageSolution]);
				remplissageSolution++;
			}

			for (int i=remplissageSolution;i<this.nbreCases;i++) {
				tabJLabelSolution[i]=new JLabel(imgIconMastermindEmplacementVideSolution);
				jpContainerSolution[ligne].add(tabJLabelSolution[i]);
			}
			jpContainerSolution[ligne].revalidate();
			jpContainerSolution[ligne].repaint();

			//On r�ordonne le tableau d'entiers dans l'ordre num�rique puis on effectue la correspondance avec les couleurs dans 
			//l'ordre suivant : pions rouges (si pr�sents), pions blancs (si pr�sents), et emplacement vide
			Arrays.sort(tabReponseJoueur);

			for (int i=0;i<this.nbreCases;i++) {
				if(tabReponseJoueur[i]==1)
					reponseJoueurModeDefenseur+="R";
				else if(tabReponseJoueur[i]==2)
					reponseJoueurModeDefenseur+="B";
				else
					reponseJoueurModeDefenseur+="V";
			}

		}
		else {
			JOptionPane.showMessageDialog(null, "Vous avez bien renseign�s tous les pions. Vous pouvez soit valider votre r�ponse, soit effacer.", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	//Cette m�thode permet de d�terminer la r�ponse attendue par le joueur
	private void calculReponseAttendue() {
		//Analyse des boules bien plac�es (pions rouges) et mal plac�es (pions blancs). Pour faciliter le traitement, on va dire 
		//que pions rouges �quivaut � 1, pions blancs � 2 et emplacement vide � 3.
		int[] tabReponseAttendue=new int[this.nbreCases];
		char[] tabAnalyse=new char[this.nbreCases];
		tabAnalyse=this.propositionSecreteJoueurModeDefenseur.toCharArray();
		for (int i=0;i<this.nbreCases;i++) {
			tabReponseAttendue[i]=3;
		}
		reponseAttendue="";

		for (int i=0;i<this.nbreCases;i++) {
			if(this.propositionOrdinateurModeDefenseur.charAt(i)==tabAnalyse[i]) {
				tabReponseAttendue[i]=1;
				tabAnalyse[i]=' ';
			}
		}

		for (int i=0;i<this.nbreCases;i++) {
			for(int j=0;j<this.nbreCases;j++) {
				if(this.propositionOrdinateurModeDefenseur.charAt(i)==tabAnalyse[j]&&tabReponseAttendue[i]!=1) {
					tabReponseAttendue[i]=2;
					tabAnalyse[j]=' ';
					break;
				}
			}
		}

		//On r�ordonne le tableau d'entiers dans l'ordre num�rique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :
		//pions rouges (si pr�sents), pions blancs (si pr�sents), et emplacement vide
		Arrays.sort(tabReponseAttendue);

		for (int i=0;i<this.nbreCases;i++) {
			if(tabReponseAttendue[i]==1)
				reponseAttendue+="R";
			else if(tabReponseAttendue[i]==2)
				reponseAttendue+="B";
			else
				reponseAttendue+="V";
		}
	}


}
