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

public class MastermindModeDuel extends JPanel implements ObservateurMastermind {

	private static final long serialVersionUID = 1L;
	private int nbreCases,nbEssais,nbCouleursUtilisables,ligne=0,colonne=1,colonneCombinaisonSecrete=0,remplissageSolution=0,
			verifCombinaisonSecrete=0;
	private int [] tabReponseJoueur;
	private GridLayout gl,glSolution;
	private JPanel jpContainerGrilleDeJeuGauche=new JPanel(),jpContainerButtonCouleur=new JPanel(),
			jpContainerReponseJoueur=new JPanel(),jPanelContainerSolutionCombinaisonSecreteJoueur=new JPanel(),
			jPanelContainerSolutionCombinaisonSecreteOrdinateur=new JPanel(),jpContainerPartieGauche=new JPanel(),
			jpContainerPartieDroite=new JPanel(),jpContainerGrilleDeJeuDroite=new JPanel();
	private JPanel[] jpContainerSolutionGauche,jpContainerSolutionDroite;
	private ImageIcon imgIconMastermindEmplacementVide=new ImageIcon("resources/MastermindEmplacementVide.png"),
			imgIconMastermindEmplacementVideSolution=new ImageIcon("resources/MastermindEmplacementVideSolution.png"),
			imgIconCouleurVerte=new ImageIcon("resources/imgCouleurVerte.png"),imgIconCouleurBleu=new ImageIcon("resources/imgCouleurBleu.png"),
			imgIconCouleurOrange=new ImageIcon("resources/imgCouleurOrange.png"),imgIconCouleurRouge=new ImageIcon("resources/imgCouleurRouge.png"),
			imgIconCouleurJaune=new ImageIcon("resources/imgCouleurJaune.png"),imgIconCouleurViolet=new ImageIcon("resources/imgCouleurViolet.png"),
			imgIconMastermindPionRougeSolution=new ImageIcon("resources/MastermindPionRougeSolution.png"),
			imgIconMastermindPionBlancSolution=new ImageIcon("resources/MastermindPionBlancSolution.png"),
			imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur=new ImageIcon("resources/MastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur.png"),
			imgIconCouleurGris=new ImageIcon("resources/imgCouleurGris.png"),imgIconCouleurBleuFonce=new ImageIcon("resources/imgCouleurBleuFonce.png"),
			imgIconCouleurNoir=new ImageIcon("resources/imgCouleurNoir.png"),imgIconCouleurMarron=new ImageIcon("resources/imgCouleurMarron.png"),
			imgIconMastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur=new ImageIcon("resources/MastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur.png");
	private JLabel [][] tabJLabelGrilleDeJeuGauche,tabJLabelGrilleDeJeuDroite;
	private JLabel [] tabJLabelSolutionGauche,tabJLabelSolutionCombinaisonSecreteJoueur,tabJLabelSolutionCombinaisonSecreteOrdinateur,
	tabJLabelSolutionDroite;
	private JLabel jlPremiereInstruction= new JLabel("La combinaison secrète a été générée par l'ordinateur."),
			jlDeuxiemeInstruction=new JLabel("Veuillez choisir une combinaison secrète."),
			jlReponseJoueur=new JLabel("Votre réponse :"),jlCombinaisonSecreteJoueur=new JLabel("Combinaison Secrète Joueur :"),
			jlCombinaisonSecreteOrdinateur=new JLabel("Combinaison Secrète Ordinateur :");
	private JButton jbValiderCombinaisonSecreteEtPropositionJoueur=new JButton("Valider"),
			jbEffacerCombinaisonSecreteEtPropositionJoueur=new JButton("Effacer"), 
			jbCouleurVerte=new JButton(imgIconCouleurVerte),jbCouleurBleu=new JButton(imgIconCouleurBleu),
			jbCouleurOrange=new JButton(imgIconCouleurOrange),jbCouleurRouge=new JButton(imgIconCouleurRouge),
			jbCouleurJaune=new JButton(imgIconCouleurJaune),jbCouleurViolet=new JButton(imgIconCouleurViolet),
			jbValiderReponseJoueur=new JButton("Valider"),jbEffacerReponseJoueur=new JButton("Effacer"),
			jbPionRougeSolution=new JButton(imgIconMastermindPionRougeSolution),jbPionBlancSolution=new JButton(imgIconMastermindPionBlancSolution),
			jbCouleurGris=new JButton(imgIconCouleurGris),jbCouleurBleuFonce=new JButton(imgIconCouleurBleuFonce),
			jbCouleurNoir=new JButton(imgIconCouleurNoir),jbCouleurMarron=new JButton(imgIconCouleurMarron);	
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14),policeSolution=new Font("Segoe UI Semilight",Font.BOLD,14);
	private String propositionSecreteJoueurModeDuel="",propositionOrdinateurModeDuel="",reponseJoueurModeDuel="",
			reponseAttendue="",combinaisonSecreteOrdinateur="",propositionJoueurModeDuel="";
	private ModeleDonneesMastermind modelMastermind;
	private MastermindControler controlerMastermind;
	private BoiteDialogueFinDePartie jdFinDePartie;
	private boolean finDePartie=false, combinaisonSecreteJoueurValide=false,miseAJourAffichageModeDuel=false,modeDeveloppeurActive;


	public MastermindModeDuel(int nbCases, int nbEssais,int nbCouleursUtilisables,boolean modeDeveloppeurActive,ModeleDonneesMastermind modelMastermind) {
		this.setPreferredSize(new Dimension(1000,740));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.nbCouleursUtilisables=nbCouleursUtilisables;
		this.modeDeveloppeurActive=modeDeveloppeurActive;
		this.modelMastermind=modelMastermind;
		this.controlerMastermind=new MastermindControler(this.modelMastermind);
		this.generationCombinaisonSecreteOrdinateur();
		//Ce tableau sera utilisé pour enregistrer la réponse du joueur
		tabReponseJoueur=new int[this.nbreCases];
		for (int i=0;i<this.nbreCases;i++) {
			tabReponseJoueur[i]=3;
			reponseJoueurModeDuel+="V";
		}

		jlPremiereInstruction.setPreferredSize(new Dimension(1000,30));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);

		jlDeuxiemeInstruction.setPreferredSize(new Dimension(1000,30));
		jlDeuxiemeInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlDeuxiemeInstruction.setFont(police);

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
		jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(false);
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
			System.out.println("Erreur lors de la mise en place de l'IHM pour les boutons liés aux couleurs");
		}

		jpContainerButtonCouleur.add(jbValiderCombinaisonSecreteEtPropositionJoueur);
		jpContainerButtonCouleur.add(jbEffacerCombinaisonSecreteEtPropositionJoueur);

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

		//Mise en place de l'IHM pour la combinaison secrète de l'ordinateur
		jlCombinaisonSecreteOrdinateur.setFont(policeSolution);
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.setPreferredSize(new Dimension(470,40));
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.setBackground(Color.WHITE);
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(jlCombinaisonSecreteOrdinateur);
		tabJLabelSolutionCombinaisonSecreteOrdinateur=new JLabel[this.nbreCases];
		if(this.modeDeveloppeurActive==false) {
			for (int i=0;i<this.nbreCases;i++) {
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel();
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setPreferredSize(new Dimension(29,29));
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur);
				jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(tabJLabelSolutionCombinaisonSecreteOrdinateur[i]);
			}
		}
		else {
			this.affichageSolution();
		}

		//Mise en place de l'IHM pour la combinaison secrète du joueur
		jlCombinaisonSecreteJoueur.setFont(policeSolution);
		jPanelContainerSolutionCombinaisonSecreteJoueur.setPreferredSize(new Dimension(470,40));
		jPanelContainerSolutionCombinaisonSecreteJoueur.setBackground(Color.WHITE);
		jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlCombinaisonSecreteJoueur);
		tabJLabelSolutionCombinaisonSecreteJoueur=new JLabel[this.nbreCases];
		for (int i=0;i<this.nbreCases;i++) {
			tabJLabelSolutionCombinaisonSecreteJoueur[i]=new JLabel();
			tabJLabelSolutionCombinaisonSecreteJoueur[i].setPreferredSize(new Dimension(29,29));
			tabJLabelSolutionCombinaisonSecreteJoueur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur);
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
		}
		this.InitialisationGrilleJeuDroite();
		this.InitialisationGrilleJeuGauche();

		//On va ajuster la taille des 2 JPanel principaux en fonction de la taille de la grille de jeu
		if (this.nbEssais==5) {
			jpContainerPartieGauche.setPreferredSize(new Dimension(480,220));
			jpContainerPartieDroite.setPreferredSize(new Dimension(480,220));
		}
		else if(this.nbEssais==10) {
			jpContainerPartieGauche.setPreferredSize(new Dimension(480,370));
			jpContainerPartieDroite.setPreferredSize(new Dimension(480,370));
		}
		else {
			jpContainerPartieGauche.setPreferredSize(new Dimension(480,510));
			jpContainerPartieDroite.setPreferredSize(new Dimension(480,510));
		}

		/*Le JPanel de gauche contiendra les propositions du joueur, les réponses de l'ordinateur
		et la combinaison secrète de l'ordinateur.*/
		jpContainerPartieGauche.setBackground(Color.WHITE);
		jpContainerPartieGauche.setBorder(BorderFactory.createTitledBorder("Propositions Joueur"));
		jpContainerPartieGauche.add(jpContainerGrilleDeJeuGauche);
		jpContainerPartieGauche.add(jPanelContainerSolutionCombinaisonSecreteOrdinateur);

		/*Le JPanel de droite contiendra les propositions de l'ordinateur, les réponses du joueur
		et la combinaison secrète du joueur.*/
		jpContainerPartieDroite.setBackground(Color.WHITE);
		jpContainerPartieDroite.setBorder(BorderFactory.createTitledBorder("Propositions Ordinateur"));
		jpContainerPartieDroite.add(jpContainerGrilleDeJeuDroite);
		jpContainerPartieDroite.add(jPanelContainerSolutionCombinaisonSecreteJoueur);

		this.add(jlPremiereInstruction);
		this.add(jlDeuxiemeInstruction);
		this.add(jpContainerButtonCouleur);
		this.add(jpContainerReponseJoueur);
		this.add(jpContainerPartieGauche);
		this.add(jpContainerPartieDroite);

		this.modelMastermind.addObservateurMastermind(this);


		// Définition des listeners

		jbCouleurBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurBleu,"0");
					colonneCombinaisonSecrete++;
				}
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurBleu,"0");
					colonne++;
				}
			}	
		});

		jbCouleurJaune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurJaune,"1");
					colonneCombinaisonSecrete++;
				}	
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurJaune,"1");
					colonne++;
				}
			}	
		});

		jbCouleurOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurOrange,"2");
					colonneCombinaisonSecrete++;
				}	
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurOrange,"2");
					colonne++;
				}
			}	
		});

		jbCouleurRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurRouge,"3");
					colonneCombinaisonSecrete++;
				}	
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurRouge,"3");
					colonne++;
				}
			}	
		});

		jbCouleurVerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurVerte,"4");
					colonneCombinaisonSecrete++;
				}
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurVerte,"4");
					colonne++;
				}
			}	
		});

		jbCouleurViolet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurViolet,"5");
					colonneCombinaisonSecrete++;
				}
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurViolet,"5");
					colonne++;
				}
			}	
		});

		jbCouleurGris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurGris,"6");
					colonneCombinaisonSecrete++;
				}
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurGris,"6");
					colonne++;
				}
			}	
		});

		jbCouleurBleuFonce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurBleuFonce,"7");
					colonneCombinaisonSecrete++;
				}
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurBleuFonce,"7");
					colonne++;
				}
			}	
		});

		jbCouleurMarron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurMarron,"8");
					colonneCombinaisonSecrete++;
				}	
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurMarron,"8");
					colonne++;
				}
			}	
		});

		jbCouleurNoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					UpdateCombinaisonSecrete(colonneCombinaisonSecrete,imgIconCouleurNoir,"9");
					colonneCombinaisonSecrete++;
				}	
				else {
					AffichagePropositionJoueur(ligne, colonne,imgIconCouleurNoir,"9");
					colonne++;
				}
			}	
		});

		jbValiderCombinaisonSecreteEtPropositionJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(false);
					combinaisonSecreteJoueurValide=true;
					JOptionPane.showMessageDialog(null, "C'est à vous de commencer en effectuant une première proposition!", "Proposition Joueur", JOptionPane.INFORMATION_MESSAGE);
					controlerMastermind.setPropositionSecreteJoueurModeDuel(propositionSecreteJoueurModeDuel);	
				}
				else {
					jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(false);
					jbEffacerCombinaisonSecreteEtPropositionJoueur.setEnabled(false);
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
					jbPionRougeSolution.setEnabled(true);
					jbPionBlancSolution.setEnabled(true);
					jbValiderReponseJoueur.setEnabled(true);
					jbEffacerReponseJoueur.setEnabled(true);
					colonne=1;
					controlerMastermind.setPropositionJoueurModeDuel(propositionJoueurModeDuel);
					propositionJoueurModeDuel="";
					if(finDePartie) {
						finDePartie=false;
					}
				}
			}	
		});

		jbEffacerCombinaisonSecreteEtPropositionJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!combinaisonSecreteJoueurValide) {
					EffacerCombinaisonSecrete(colonneCombinaisonSecrete,imgIconMastermindEmplacementVideSolutionCombinaisonSecreteJoueur);
					colonneCombinaisonSecrete=0;
				}	
				else {
					EffacerPropositionJoueur(ligne, colonne, imgIconMastermindEmplacementVide);
					colonne=1;
				}
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
				System.out.println("reponseJoueurModeDuel :"+reponseJoueurModeDuel);
				calculReponseAttendue();
				System.out.println("Reponse attendue :"+reponseAttendue);
				if(!reponseJoueurModeDuel.equals(reponseAttendue)) {
					JOptionPane.showMessageDialog(null,"Attention : votre réponse est erronée. Veuillez saisir une autre réponse, svp.", 
							"Message d'avertissement", JOptionPane.WARNING_MESSAGE);
					effacerReponseJoueur();
				}
				else {
					ligne++;
					gestionFinDePartie(reponseJoueurModeDuel,'J');
					if(!finDePartie) {
						controlerMastermind.setReponseJoueurModeDuel(reponseJoueurModeDuel);
						remplissageSolution=0;
						reponseJoueurModeDuel="";
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
						jbEffacerCombinaisonSecreteEtPropositionJoueur.setEnabled(true);
						jbPionRougeSolution.setEnabled(false);
						jbPionBlancSolution.setEnabled(false);
						jbValiderReponseJoueur.setEnabled(false);
						jbEffacerReponseJoueur.setEnabled(false);
						for (int i=0;i<nbreCases;i++) {
							tabReponseJoueur[i]=3;
							reponseJoueurModeDuel+="V";
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

	/***************************************************************************************************************
	 * Méthode permettant d'initialiser la grille de jeu gauche : propositions du joueur, réponses de l'ordinateur
	 * 
	 * *************************************************************************************************************/
	private void InitialisationGrilleJeuGauche() {
		gl=new GridLayout(this.nbEssais,this.nbreCases+2);
		jpContainerGrilleDeJeuGauche.setLayout(gl);
		jpContainerGrilleDeJeuGauche.setPreferredSize(new Dimension(30*(this.nbreCases+2),29*this.nbEssais));
		jpContainerGrilleDeJeuGauche.setBackground(Color.WHITE);

		tabJLabelGrilleDeJeuGauche=new JLabel[this.nbEssais][this.nbreCases+1];
		if(this.nbreCases==4)
			glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2);
		else if(this.nbreCases==5)
			glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2+1);
		else
			glSolution=new GridLayout(this.nbreCases/2-1,this.nbreCases/2);

		tabJLabelSolutionGauche= new JLabel[this.nbreCases];
		jpContainerSolutionGauche=new JPanel[this.nbEssais];

		/**
		 * La grille de jeu est un JPanel organisé en GridLayout composé d'un tableau de JLabel et d'un tableau de JPanel organisé 
		 * également en GridLayout.
		 * 
		 */
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<=this.nbreCases;j++) {
				if(j==0) {
					tabJLabelGrilleDeJeuGauche[i][j]=new JLabel(String.valueOf(i+1));
					tabJLabelGrilleDeJeuGauche[i][j].setOpaque(true);
					tabJLabelGrilleDeJeuGauche[i][j].setBackground(Color.LIGHT_GRAY);
					tabJLabelGrilleDeJeuGauche[i][j].setForeground(Color.BLACK);
					tabJLabelGrilleDeJeuGauche[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					tabJLabelGrilleDeJeuGauche[i][j].setHorizontalAlignment(JLabel.CENTER);		
				}
				else {
					tabJLabelGrilleDeJeuGauche[i][j]=new JLabel(imgIconMastermindEmplacementVide);
				}	
			}
		}

		for (int i=0;i<this.nbEssais;i++) {
			jpContainerSolutionGauche[i]=new JPanel();
			jpContainerSolutionGauche[i].removeAll();
			jpContainerSolutionGauche[i].setPreferredSize(new Dimension(30,29));
			jpContainerSolutionGauche[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jpContainerSolutionGauche[i].setLayout(glSolution);
			for (int k=0;k<this.nbreCases;k++) {
				tabJLabelSolutionGauche[k]=new JLabel(imgIconMastermindEmplacementVideSolution);
				jpContainerSolutionGauche[i].add(tabJLabelSolutionGauche[k]);
			}
			jpContainerSolutionGauche[i].revalidate();
			jpContainerSolutionGauche[i].repaint();
		}

		//L'organisation en GridLayout impose un remplissage ligne par ligne
		jpContainerGrilleDeJeuGauche.removeAll();
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeuGauche.add(tabJLabelGrilleDeJeuGauche[i][j]);
			}
			jpContainerGrilleDeJeuGauche.add(jpContainerSolutionGauche[i]);
		}

		jpContainerGrilleDeJeuGauche.revalidate();
		jpContainerGrilleDeJeuGauche.repaint();
	}

	/****************************************************************************************************************
	 * Méthode permettant d'initialiser la grille de jeu droite : propositions de l'ordinateur, réponses du joueur
	 * 
	 * **************************************************************************************************************/
	private void InitialisationGrilleJeuDroite() {
		gl=new GridLayout(this.nbEssais,this.nbreCases+2);
		jpContainerGrilleDeJeuDroite.setLayout(gl);
		jpContainerGrilleDeJeuDroite.setPreferredSize(new Dimension(30*(this.nbreCases+2),29*this.nbEssais));
		jpContainerGrilleDeJeuDroite.setBackground(Color.WHITE);

		tabJLabelGrilleDeJeuDroite=new JLabel[this.nbEssais][this.nbreCases+1];
		if(this.nbreCases==4)
			glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2);
		else if(this.nbreCases==5)
			glSolution=new GridLayout(this.nbreCases/2,this.nbreCases/2+1);
		else
			glSolution=new GridLayout(this.nbreCases/2-1,this.nbreCases/2);

		tabJLabelSolutionDroite= new JLabel[this.nbreCases];
		jpContainerSolutionDroite=new JPanel[this.nbEssais];

		/**
		 * La grille de jeu est un JPanel organisé en GridLayout composé d'un tableau de JLabel et d'un tableau de JPanel organisé 
		 * également en GridLayout.
		 * 
		 */
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<=this.nbreCases;j++) {
				if(j==0) {
					tabJLabelGrilleDeJeuDroite[i][j]=new JLabel(String.valueOf(i+1));
					tabJLabelGrilleDeJeuDroite[i][j].setOpaque(true);
					tabJLabelGrilleDeJeuDroite[i][j].setBackground(Color.LIGHT_GRAY);
					tabJLabelGrilleDeJeuDroite[i][j].setForeground(Color.BLACK);
					tabJLabelGrilleDeJeuDroite[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					tabJLabelGrilleDeJeuDroite[i][j].setHorizontalAlignment(JLabel.CENTER);		
				}
				else {
					tabJLabelGrilleDeJeuDroite[i][j]=new JLabel(imgIconMastermindEmplacementVide);
				}	
			}
		}

		for (int i=0;i<this.nbEssais;i++) {
			jpContainerSolutionDroite[i]=new JPanel();
			jpContainerSolutionDroite[i].removeAll();
			jpContainerSolutionDroite[i].setPreferredSize(new Dimension(30,29));
			jpContainerSolutionDroite[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jpContainerSolutionDroite[i].setLayout(glSolution);
			for (int k=0;k<this.nbreCases;k++) {
				tabJLabelSolutionDroite[k]=new JLabel(imgIconMastermindEmplacementVideSolution);
				jpContainerSolutionDroite[i].add(tabJLabelSolutionDroite[k]);
			}
			jpContainerSolutionDroite[i].revalidate();
			jpContainerSolutionDroite[i].repaint();
		}

		//L'organisation en GridLayout impose un remplissage ligne par ligne
		jpContainerGrilleDeJeuDroite.removeAll();
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeuDroite.add(tabJLabelGrilleDeJeuDroite[i][j]);
			}
			jpContainerGrilleDeJeuDroite.add(jpContainerSolutionDroite[i]);
		}

		jpContainerGrilleDeJeuDroite.revalidate();
		jpContainerGrilleDeJeuDroite.repaint();
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
		controlerMastermind.setModeDeJeu(2);
		controlerMastermind.setNbEssais(this.nbEssais);
		controlerMastermind.setNbreCases(this.nbreCases);
		controlerMastermind.setNbCouleursUtilisables(nbCouleursUtilisables);
		controlerMastermind.setPropositionSecreteOrdinateurModeDuel(combinaisonSecreteOrdinateur);
	}


	/**************************************************************
	 * Méthodes permettant de mettre à jour la combinaison secrète.
	 * 
	 **************************************************************/
	private void UpdateCombinaisonSecrete(int col,ImageIcon couleurChoisie, String codeCouleur) {
		if(colonneCombinaisonSecrete<this.nbreCases) {
			propositionSecreteJoueurModeDuel+=codeCouleur;
			tabJLabelSolutionCombinaisonSecreteJoueur[col]=new JLabel(couleurChoisie);
			tabJLabelSolutionCombinaisonSecreteJoueur[col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jPanelContainerSolutionCombinaisonSecreteJoueur.removeAll();
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlCombinaisonSecreteJoueur);
			for (int i=0;i<this.nbreCases;i++) {
				jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
			}
			jPanelContainerSolutionCombinaisonSecreteJoueur.revalidate();
			jPanelContainerSolutionCombinaisonSecreteJoueur.repaint();
			if(colonneCombinaisonSecrete==this.nbreCases-1)
				jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(true);
		}
		else if(colonneCombinaisonSecrete==this.nbreCases) {
			JOptionPane.showMessageDialog(null, "La ligne est complète. Vous pouvez soit valider, soit effacer la ligne", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
			colonneCombinaisonSecrete=this.nbreCases-1;
		}

	}

	private void EffacerCombinaisonSecrete(int col,ImageIcon emplacementVide) {
		for (int i=0;i<col;i++) {
			tabJLabelSolutionCombinaisonSecreteJoueur[i]=new JLabel(emplacementVide);
		}
		jPanelContainerSolutionCombinaisonSecreteJoueur.removeAll();
		jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlCombinaisonSecreteJoueur);
		for (int i=0;i<this.nbreCases;i++) {
			jPanelContainerSolutionCombinaisonSecreteJoueur.add(tabJLabelSolutionCombinaisonSecreteJoueur[i]);
		}
		jPanelContainerSolutionCombinaisonSecreteJoueur.revalidate();
		jPanelContainerSolutionCombinaisonSecreteJoueur.repaint();
		jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(false);
		propositionSecreteJoueurModeDuel="";

	}

	/****************************************************************************************
	 * Méthodes permettant de mettre à jour la grille de jeu suite aux propositions du joueur
	 * 
	 * **************************************************************************************/
	private void AffichagePropositionJoueur(int lig, int col,ImageIcon couleurChoisie, String codeCouleur) {
		if(colonne<=this.nbreCases) {
			tabJLabelGrilleDeJeuGauche[lig][col]=new JLabel(couleurChoisie);
			propositionJoueurModeDuel+=codeCouleur;
			tabJLabelGrilleDeJeuGauche[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//L'organisation en GridLayout impose un remplissage ligne par ligne
			jpContainerGrilleDeJeuGauche.removeAll();
			for (int i=0;i<this.nbEssais;i++) {
				for (int j=0;j<this.nbreCases+1;j++) {
					jpContainerGrilleDeJeuGauche.add(tabJLabelGrilleDeJeuGauche[i][j]);
				}
				jpContainerGrilleDeJeuGauche.add(jpContainerSolutionGauche[i]);
			}
			jpContainerGrilleDeJeuGauche.revalidate();
			jpContainerGrilleDeJeuGauche.repaint();	
			if(colonne==this.nbreCases) {
				jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(true);
			}	
		}

		if(colonne>this.nbreCases) {
			JOptionPane.showMessageDialog(null, "La ligne est complète. Vous pouvez soit valider, soit effacer la ligne", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
			colonne=this.nbreCases;
		}
	}

	private void EffacerPropositionJoueur(int lig, int col,ImageIcon emplacementVide) {
		for(int i=1;i<col;i++) {
			tabJLabelGrilleDeJeuGauche[lig][i]=new JLabel(emplacementVide);
		}
		propositionJoueurModeDuel="";
		//L'organisation en GridLayout impose un remplissage ligne par ligne
		jpContainerGrilleDeJeuGauche.removeAll();
		for (int i=0;i<this.nbEssais;i++) {
			for (int j=0;j<this.nbreCases+1;j++) {
				jpContainerGrilleDeJeuGauche.add(tabJLabelGrilleDeJeuGauche[i][j]);
			}
			jpContainerGrilleDeJeuGauche.add(jpContainerSolutionGauche[i]);
		}
		jpContainerGrilleDeJeuGauche.revalidate();
		jpContainerGrilleDeJeuGauche.repaint();	
		jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(false);

	}

	private void affichageReponseJoueur(ImageIcon couleurChoisieReponse, int codeCouleurReponse) {
		if(remplissageSolution<this.nbreCases) {
			jpContainerSolutionDroite[ligne].removeAll();
			reponseJoueurModeDuel="";
			tabReponseJoueur[remplissageSolution]=codeCouleurReponse;

			for(int i=0;i<remplissageSolution;i++) {
				jpContainerSolutionDroite[ligne].add(tabJLabelSolutionDroite[i]);
			}

			if(codeCouleurReponse==1) {
				tabJLabelSolutionDroite[remplissageSolution]=new JLabel(couleurChoisieReponse);
				jpContainerSolutionDroite[ligne].add(tabJLabelSolutionDroite[remplissageSolution]);
				remplissageSolution++;
			}
			else if(codeCouleurReponse==2) {
				tabJLabelSolutionDroite[remplissageSolution]=new JLabel(imgIconMastermindPionBlancSolution);
				jpContainerSolutionDroite[ligne].add(tabJLabelSolutionDroite[remplissageSolution]);
				remplissageSolution++;
			}

			for (int i=remplissageSolution;i<this.nbreCases;i++) {
				tabJLabelSolutionDroite[i]=new JLabel(imgIconMastermindEmplacementVideSolution);
				jpContainerSolutionDroite[ligne].add(tabJLabelSolutionDroite[i]);
			}
			jpContainerSolutionDroite[ligne].revalidate();
			jpContainerSolutionDroite[ligne].repaint();

			//On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la correspondance avec les couleurs dans 
			//l'ordre suivant : pions rouges (si présents), pions blancs (si présents), et emplacement vide
			Arrays.sort(tabReponseJoueur);

			for (int i=0;i<this.nbreCases;i++) {
				if(tabReponseJoueur[i]==1)
					reponseJoueurModeDuel+="R";
				else if(tabReponseJoueur[i]==2)
					reponseJoueurModeDuel+="B";
				else
					reponseJoueurModeDuel+="V";
			}

		}
		else {
			JOptionPane.showMessageDialog(null, "Vous avez bien renseignés tous les pions. Vous pouvez soit valider votre réponse, soit effacer.", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void effacerReponseJoueur() {
		jpContainerSolutionDroite[ligne].removeAll();
		jpContainerSolutionDroite[ligne].revalidate();
		for (int i=0;i<nbreCases;i++) {
			tabJLabelSolutionDroite[i]=new JLabel(imgIconMastermindEmplacementVideSolution);
			jpContainerSolutionDroite[ligne].add(tabJLabelSolutionDroite[i]);
		}
		jpContainerSolutionDroite[ligne].repaint();
		remplissageSolution=0;
		reponseJoueurModeDuel="";
		for (int i=0;i<nbreCases;i++) {
			tabReponseJoueur[i]=3;
			reponseJoueurModeDuel+="V";
		}
	}

	//Cette méthode permet de déterminer la réponse attendue par le joueur
	private void calculReponseAttendue() {
		//Analyse des boules bien placées (pions rouges) et mal placées (pions blancs). Pour faciliter le traitement, on va dire 
		//que pions rouges équivaut à 1, pions blancs à 2 et emplacement vide à 3.
		int[] tabReponseAttendue=new int[this.nbreCases];
		char[] tabAnalyse=new char[this.nbreCases];
		tabAnalyse=this.propositionSecreteJoueurModeDuel.toCharArray();
		for (int i=0;i<this.nbreCases;i++) {
			tabReponseAttendue[i]=3;
		}
		reponseAttendue="";

		for (int i=0;i<this.nbreCases;i++) {
			if(this.propositionOrdinateurModeDuel.charAt(i)==tabAnalyse[i]) {
				tabReponseAttendue[i]=1;
				tabAnalyse[i]=' ';
			}
		}

		for (int i=0;i<this.nbreCases;i++) {
			for(int j=0;j<this.nbreCases;j++) {
				if(this.propositionOrdinateurModeDuel.charAt(i)==tabAnalyse[j]&&tabReponseAttendue[i]!=1) {
					tabReponseAttendue[i]=2;
					tabAnalyse[j]=' ';
					break;
				}
			}
		}

		//On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :
		//pions rouges (si présents), pions blancs (si présents), et emplacement vide
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

	/*******************************************************************************************************************************
	 * Cette méthode est utilisée pour afficher la combinaison secrète de l'ordinateur dans le cas où le mode développeur est activé.
	 *******************************************************************************************************************************/
	private void affichageSolution() {
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.removeAll();
		jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(jlCombinaisonSecreteOrdinateur);
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
			case '6':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurGris);
				break;	
			case '7':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurBleuFonce);
				break;	
			case '8':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurMarron);
				break;	
			case '9':
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel(imgIconCouleurNoir);
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

	//Gestion de la fin de la partie
	private void gestionFinDePartie(String reponse, char identiteJoueur) {
		verifCombinaisonSecrete=0;
		for (int i=0;i<reponse.length();i++) {
			if(reponse.charAt(i)=='R')
				verifCombinaisonSecrete++;
		}

		//En cas de victoire
		if(verifCombinaisonSecrete==nbreCases&& identiteJoueur=='O') {
			this.affichageSolution();
			JOptionPane.showMessageDialog(null,"Félicitations!!! Vous avez trouvé la combinaison secrète de l'ordinateur en premier. ",
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	
		}

		//En cas de défaîte
		if(verifCombinaisonSecrete==nbreCases&& identiteJoueur=='J') {
			this.affichageSolution();
			JOptionPane.showMessageDialog(null, "Vous avez perdu. L'ordinateur a trouvé en premier votre combinaison secrète. "
					+ "\nPour information, la combinaison secrète de l'ordinateur est affichée.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);
		}

		//En cas de match nul
		if (ligne==nbEssais && verifCombinaisonSecrete!=nbreCases) {
			this.affichageSolution();
			JOptionPane.showMessageDialog(null, "Match nul. Le nombre d'essai maximal a été dépassé.\nPour information, la combinaison secrète de l'ordinateur est "
					+ "affichée.", "Fin de Partie",
					JOptionPane.INFORMATION_MESSAGE);	
		}

		//En cas de défaîte ou de victoire
		if (ligne==nbEssais||verifCombinaisonSecrete==nbreCases) {
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controlerMastermind.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}

	//Implémentation du pattern Observer
	public void quitterApplicationMastermind() {}
	public void acceuilObservateurMastermind() {}

	public void updateMastermind(String affichage) {

		//Dans un cas, on met à jour la grille de jeu gauche avec les réponses de l'ordinateur
		if(!miseAJourAffichageModeDuel) {
			/*Pour une ligne donnée, on met à jour le JPanel jpContainerSolutionGauche en suivant les étapes habituelles pour un JPanel :
			On supprime les anciens composants, on ajoute les nouveaux, on revalide et on fait appel à la méthode repaint()*/
			jpContainerSolutionGauche[ligne].removeAll();
			for (int i=0;i<this.nbreCases;i++) {
				if(affichage.charAt(i)=='R') {
					tabJLabelSolutionGauche[i]=new JLabel(imgIconMastermindPionRougeSolution);
					jpContainerSolutionGauche[ligne].add(tabJLabelSolutionGauche[i]);
				}
				else if(affichage.charAt(i)=='B') {
					tabJLabelSolutionGauche[i]=new JLabel(imgIconMastermindPionBlancSolution);
					jpContainerSolutionGauche[ligne].add(tabJLabelSolutionGauche[i]);
				}
				else {
					tabJLabelSolutionGauche[i]=new JLabel(imgIconMastermindEmplacementVideSolution);
					jpContainerSolutionGauche[ligne].add(tabJLabelSolutionGauche[i]);
				}
			}
			jpContainerSolutionGauche[ligne].revalidate();
			jpContainerSolutionGauche[ligne].repaint();
			miseAJourAffichageModeDuel=true;
			this.gestionFinDePartie(affichage,'O');
		}

		//Dans l'autre cas, on met à jour la grille de jeu droite avec les propositions de l'ordinateur
		else {
			this.propositionOrdinateurModeDuel= affichage;
			/*On convertit la proposition de l'ordinateur en JLabel avec les images adéquates en vue de l'affichage
			 sur la grille de jeu*/
			jpContainerGrilleDeJeuDroite.removeAll();
			for (int i=0;i<nbreCases;i++) {
				switch(affichage.charAt(i)) {
				case '0':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurBleu);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '1':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurJaune);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '2':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurOrange);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '3':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurRouge);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '4':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurVerte);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '5':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurViolet);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '6':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurGris);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '7':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurBleuFonce);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '8':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurMarron);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				case '9':
					tabJLabelGrilleDeJeuDroite[ligne][i+1]=new JLabel(imgIconCouleurNoir);
					tabJLabelGrilleDeJeuDroite[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
				default :
					System.out.println("Erreur de correspondance entre la proposition de l'ordinateur et les couleurs");
				}
			}
			for (int i=0;i<this.nbEssais;i++) {
				for (int j=0;j<this.nbreCases+1;j++) {
					jpContainerGrilleDeJeuDroite.add(tabJLabelGrilleDeJeuDroite[i][j]);
				}
				jpContainerGrilleDeJeuDroite.add(jpContainerSolutionDroite[i]);
			}
			jpContainerGrilleDeJeuDroite.revalidate();
			jpContainerGrilleDeJeuDroite.repaint();
			miseAJourAffichageModeDuel=false;
		}	
	}

	/*On réinitialise l'IHM des grilles de jeu et des combinaisons secrètes en bas de page ainsi que toutes les variables,
	et on regénère une nouvelle combinaison secrète pour l'ordinateur.*/
	public void relancerPartieMastermind() {
		//Réinitialisation de l'IHM des grilles de jeu
		this.InitialisationGrilleJeuGauche();
		this.InitialisationGrilleJeuDroite();

		//Réinitialisation de la combinaison secrète de l'ordinateur lorsque le mode développeur est désactivé
		if(this.modeDeveloppeurActive==false) {
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.removeAll();
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(jlCombinaisonSecreteOrdinateur);
			for (int i=0;i<this.nbreCases;i++) {
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i]=new JLabel();
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setPreferredSize(new Dimension(29,29));
				tabJLabelSolutionCombinaisonSecreteOrdinateur[i].setIcon(imgIconMastermindEmplacementVideSolutionCombinaisonSecreteOrdinateur);
				jPanelContainerSolutionCombinaisonSecreteOrdinateur.add(tabJLabelSolutionCombinaisonSecreteOrdinateur[i]);
			}
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.revalidate();
			jPanelContainerSolutionCombinaisonSecreteOrdinateur.repaint();
		}

		//Réinitialisation de la combinaison secrète du joueur
		jPanelContainerSolutionCombinaisonSecreteJoueur.removeAll();
		jPanelContainerSolutionCombinaisonSecreteJoueur.add(jlCombinaisonSecreteJoueur);
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
		jbValiderCombinaisonSecreteEtPropositionJoueur.setEnabled(false);
		jbEffacerCombinaisonSecreteEtPropositionJoueur.setEnabled(true);
		jbPionRougeSolution.setEnabled(false);
		jbPionBlancSolution.setEnabled(false);
		jbValiderReponseJoueur.setEnabled(false);
		jbEffacerReponseJoueur.setEnabled(false);
		reponseJoueurModeDuel="";
		for (int i=0;i<this.nbreCases;i++) {
			tabReponseJoueur[i]=3;
			reponseJoueurModeDuel+="V";
		}
		ligne=0;
		colonne=1;
		remplissageSolution=0;
		colonneCombinaisonSecrete=0;
		verifCombinaisonSecrete=0;
		reponseAttendue="";
		combinaisonSecreteOrdinateur="";
		propositionSecreteJoueurModeDuel="";
		propositionOrdinateurModeDuel="";
		propositionJoueurModeDuel="";
		combinaisonSecreteJoueurValide=false;
		miseAJourAffichageModeDuel=false;
		this.generationCombinaisonSecreteOrdinateur();
		//Réinitialisation de la combinaison secrète de l'ordinateur lorsque le mode développeur est activé
		if(modeDeveloppeurActive==true)
			this.affichageSolution();
	}
}