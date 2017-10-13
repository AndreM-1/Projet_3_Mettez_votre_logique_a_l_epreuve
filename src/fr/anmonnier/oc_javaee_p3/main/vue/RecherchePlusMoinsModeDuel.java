package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;
import javax.swing.table.AbstractTableModel;

import fr.anmonnier.oc_javaee_p3.main.controler.RecherchePlusMoinsControler;
import fr.anmonnier.oc_javaee_p3.main.model.ModelTableau;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

public class RecherchePlusMoinsModeDuel extends JPanel implements Observateur {

	private static final long serialVersionUID = 1L;
	private JLabel jlPremiereInstruction=new JLabel("La combinaison secr�te a �t� g�n�r�e par l'ordinateur."),
	 jlDeuxiemeInstruction=new JLabel("Veuillez saisir votre combinaison secr�te :"),jlPropositionJoueur=new JLabel("Votre proposition :"),
	 jlReponseJoueur=new JLabel("Votre r�ponse (+,-,=) :");
	private JButton jbValiderCombinaisonSecreteJoueur=new JButton("Valider"),jbValiderPropositionJoueur=new JButton("Valider"),
			jbValiderReponseJoueur=new JButton("Valider");
	private JFormattedTextField jftfCombinaisonSecreteJoueur,jftfPropositionJoueur,jftfReponseJoueur;
	private JPanel jpContainerCombinaisonSecreteJoueur=new JPanel(),jpContainerTableau=new JPanel(),jpContainerPropositionReponseJoueur=new JPanel();
	private int nbreCases, nbEssais;
	private int verificationJftf=0,verificationJftfCombinaisonSecreteJoueur=0,verificationJftfPropositionJoueur=0,
			rowIndex=0,columnIndex=0,min=0,max=10,verifCombinaisonSecrete=0,miseAJourAffichageModeDuel=0;
	private String combinaisonSecreteOrdinateur="";
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14);
	private JTable jtTableau;
	private ModelTableau modelTableau;
	private ModeleDonnees model;
	private RecherchePlusMoinsControler controler;
	private BoiteDialogueFinDePartie jdFinDePartie;
	private LabelRenderer labelRenderer=new LabelRenderer();
	private boolean finDePartie=false;

	public RecherchePlusMoinsModeDuel(int nbCases, int nbEssais,ModeleDonnees model) {
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.model=model;
		controler=new RecherchePlusMoinsControler(model);
		this.generationCombinaisonSecrete();
		jlPremiereInstruction.setPreferredSize(new Dimension(900,40));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);
		jlDeuxiemeInstruction.setPreferredSize(new Dimension(250,40));
		jlDeuxiemeInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlDeuxiemeInstruction.setFont(police);
		jlPropositionJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlPropositionJoueur.setFont(police);
		jlReponseJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlReponseJoueur.setFont(police);
		try {
			MaskFormatter formatCombinaisonSecreteJoueur=new MaskFormatter("####");
			MaskFormatter formatPropositionJoueur=new MaskFormatter("####");
			MaskFormatter formatReponseJoueur=new MaskFormatter("****");	
			jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur);
			jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(50,20));
			jftfCombinaisonSecreteJoueur.setFont(police);	
			jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur);
			jftfPropositionJoueur.setPreferredSize(new Dimension(50,20));
			jftfPropositionJoueur.setFont(police);		
			jftfPropositionJoueur.setEnabled(false);
			jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur);
			jftfReponseJoueur.setPreferredSize(new Dimension(50,20));
			jftfReponseJoueur.setFont(police);		
			jftfReponseJoueur.setEnabled(false);
		} catch (ParseException e) {e.printStackTrace();}

		//Mise en place d'un tableau � partir d'un mod�le de donn�es
		String[] title= {"Proposition","R�ponse"};
		Object[][] data= new Object[2*this.nbEssais][2];
		modelTableau=new ModelTableau(data,title);
		jtTableau=new JTable(modelTableau);
		labelRenderer.setModeDeJeu(2);
		jtTableau.getColumn("Proposition").setCellRenderer(labelRenderer);
		jtTableau.getColumn("R�ponse").setCellRenderer(labelRenderer);
		jpContainerCombinaisonSecreteJoueur.setPreferredSize(new Dimension(900,40));
		jpContainerPropositionReponseJoueur.setPreferredSize(new Dimension(900,40));
		
		
		jbValiderCombinaisonSecreteJoueur.setEnabled(false);
		jbValiderPropositionJoueur.setEnabled(false);
		jbValiderReponseJoueur.setEnabled(false);
		jpContainerTableau.setBackground(Color.WHITE);
		jpContainerTableau.setPreferredSize(new Dimension(350,343));
		jpContainerTableau.setLayout(new BorderLayout());
		jpContainerTableau.add(new JScrollPane(jtTableau),BorderLayout.CENTER);
		
		jpContainerCombinaisonSecreteJoueur.setBackground(Color.WHITE);
		jpContainerCombinaisonSecreteJoueur.add(jlDeuxiemeInstruction);
		jpContainerCombinaisonSecreteJoueur.add(jftfCombinaisonSecreteJoueur);
		jpContainerCombinaisonSecreteJoueur.add(jbValiderCombinaisonSecreteJoueur);
	
		jpContainerPropositionReponseJoueur.setBackground(Color.WHITE);
		jpContainerPropositionReponseJoueur.add(jlPropositionJoueur);
		jpContainerPropositionReponseJoueur.add(jftfPropositionJoueur);
		jpContainerPropositionReponseJoueur.add(jbValiderPropositionJoueur);
		jpContainerPropositionReponseJoueur.add(jlReponseJoueur);
		jpContainerPropositionReponseJoueur.add(jftfReponseJoueur);
		jpContainerPropositionReponseJoueur.add(jbValiderReponseJoueur);
		
		this.add(jlPremiereInstruction);
		this.add(jpContainerCombinaisonSecreteJoueur);
		this.add(jpContainerPropositionReponseJoueur);
		this.add(jpContainerTableau);

		// D�finition des listeners

		//Gestion du positionnement du curseur
		jftfCombinaisonSecreteJoueur.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
				jftfCombinaisonSecreteJoueur.setCaretPosition(0);
			}
			public void focusLost(FocusEvent arg0) {}
		});
		
		jftfPropositionJoueur.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				jftfPropositionJoueur.setCaretPosition(0);
			}
			public void focusLost(FocusEvent arg0) {}
		});

		jftfReponseJoueur.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				jftfReponseJoueur.setCaretPosition(0);
			}
			public void focusLost(FocusEvent arg0) {}
		});


		//Les boutons Valider ne doivent �tre accessibles que lorsque les JFormattedTextField associ�es sont renseign�s
		jftfCombinaisonSecreteJoueur.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent event) {
				for (int i=0;i<nbreCases;i++) {
					if(jftfCombinaisonSecreteJoueur.getText().charAt(i)!=' ') {
						verificationJftfCombinaisonSecreteJoueur++;
					}
				}
				if(verificationJftfCombinaisonSecreteJoueur==nbreCases) {
					jbValiderCombinaisonSecreteJoueur.setEnabled(true); 
					verificationJftfCombinaisonSecreteJoueur=0;
				}
				else {
					jbValiderCombinaisonSecreteJoueur.setEnabled(false);
					verificationJftfCombinaisonSecreteJoueur=0;
				}
			}
		});
		
		jftfPropositionJoueur.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent event) {
				for (int i=0;i<nbreCases;i++) {
					if(jftfPropositionJoueur.getText().charAt(i)!=' ') {
						verificationJftfPropositionJoueur++;
					}
				}
				if(verificationJftfPropositionJoueur==nbreCases) {
					jbValiderPropositionJoueur.setEnabled(true); 
					verificationJftfPropositionJoueur=0;
				}
				else {
					jbValiderPropositionJoueur.setEnabled(false);
					verificationJftfPropositionJoueur=0;
				}
			}
		});
		
		jftfReponseJoueur.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent event) {

				for (int i=0;i<nbreCases;i++) {
					if(jftfReponseJoueur.getText().charAt(i)!='='&&jftfReponseJoueur.getText().charAt(i)!='+'&&
							jftfReponseJoueur.getText().charAt(i)!='-'&&jftfReponseJoueur.getText().charAt(i)!=' ') {
						JOptionPane.showMessageDialog(null, "Veuillez saisir l'un des carat�res suivants : +, - ou =", "Information", JOptionPane.INFORMATION_MESSAGE);
						jftfReponseJoueur.setText("");
					}

					if(jftfReponseJoueur.getText().charAt(i)!=' ') {
						verificationJftf++;
					}
				}
				if(verificationJftf==nbreCases) {
					jbValiderReponseJoueur.setEnabled(true); 
					verificationJftf=0;
				}
				else {
					jbValiderReponseJoueur.setEnabled(false);
					verificationJftf=0;
				}
			}

		});

		//Lors de la validation, les donn�es saisies doivent �tre transmises au contr�leur
		jbValiderCombinaisonSecreteJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jftfCombinaisonSecreteJoueur.setEnabled(false);
				jbValiderCombinaisonSecreteJoueur.setEnabled(false);
				controler.setPropositionSecreteJoueurModeDuel(jftfCombinaisonSecreteJoueur.getText());
				jftfPropositionJoueur.setEnabled(true);
			}

		});
		
		jbValiderPropositionJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jftfPropositionJoueur.setEnabled(false);
				jbValiderPropositionJoueur.setEnabled(false);
				controler.setPropositionJoueurModeDuel(jftfPropositionJoueur.getText());
				jftfPropositionJoueur.setText("");
				if(!finDePartie)
					jftfReponseJoueur.setEnabled(true);
				else
					finDePartie=false;
			}
		});
		
		jbValiderReponseJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.setReponseJoueurModeDuel(jftfReponseJoueur.getText());
				jftfReponseJoueur.setText("");
				jftfReponseJoueur.setEnabled(false);
				jbValiderReponseJoueur.setEnabled(false);
				if(!finDePartie)
					jftfPropositionJoueur.setEnabled(true);
				else
					finDePartie=false;
			}

		});

		this.model.addObservateur(this);
	}

	//Impl�mentation du pattern Observer
	public void update(String propositionJoueur,String reponse) {}
	
	public void updateDuel(String affichage) {
		
		if(miseAJourAffichageModeDuel==0) {
			((AbstractTableModel)jtTableau.getModel()).setValueAt(affichage, rowIndex, columnIndex);
			((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
			columnIndex++;
			miseAJourAffichageModeDuel++;
		}
		else {
			((AbstractTableModel)jtTableau.getModel()).setValueAt(affichage, rowIndex, columnIndex);
			((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
			this.gestionFinDePartie(affichage);
			columnIndex=0;
			miseAJourAffichageModeDuel=0;	
			if(!finDePartie) {
				rowIndex++;
			}
			else {
				rowIndex=0;
			}
			
		}
		
	}
	
	public void relancerPartie() {
		for(int i=0;i<=rowIndex;i++) {
			for (int j=0;j<2;j++) {
				((AbstractTableModel)jtTableau.getModel()).setValueAt("", i, j);
				((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(i, j);
			}
		}
		verificationJftf=0;
		verificationJftfCombinaisonSecreteJoueur=0;
		verificationJftfPropositionJoueur=0;
		rowIndex=0;
		columnIndex=0;
		min=0;
		max=10;
		verifCombinaisonSecrete=0;
		combinaisonSecreteOrdinateur="";
		jftfCombinaisonSecreteJoueur.setText("");
		jftfCombinaisonSecreteJoueur.setEnabled(true);
		jbValiderCombinaisonSecreteJoueur.setEnabled(false);
		jftfPropositionJoueur.setEnabled(false);
		jftfPropositionJoueur.setText("");
		jbValiderPropositionJoueur.setEnabled(false);
		jftfReponseJoueur.setEnabled(false);
		jftfReponseJoueur.setText("");
		jbValiderReponseJoueur.setEnabled(false);
		this.generationCombinaisonSecrete();
	}

	public void quitterApplication() {}
	public void acceuilObservateur() {}


	//G�n�ration de la combinaison secr�tre par l'ordinateur
	public void generationCombinaisonSecrete(){
		int nbreAleatoire;
		for (int i=0;i<this.nbreCases;i++) {
			nbreAleatoire=(int)(Math.random()*(max-min));
			combinaisonSecreteOrdinateur+=String.valueOf(nbreAleatoire);	
		}
		controler.setModeDeJeu(2);
		controler.setPropositionSecreteOrdinateurModeDuel(combinaisonSecreteOrdinateur);
	}
	
	public void gestionFinDePartie(String affichage) {
		//Gestion de la fin de la partie
		verifCombinaisonSecrete=0;

		for (int i=0;i<affichage.length();i++) {
			if (affichage.charAt(i)=='=') {
				verifCombinaisonSecrete++;
			}
		}

		//En cas de victoire
		if(verifCombinaisonSecrete==nbreCases && rowIndex%2==0) {
			JOptionPane.showMessageDialog(null, "F�licitations!!! Vous avez trouv� la combinaison secr�te de l'ordinateur. ",
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	
		}
		
		//En cas de d�fa�te
		if(verifCombinaisonSecrete==nbreCases && rowIndex%2==1) {
			JOptionPane.showMessageDialog(null, "Vous avez perdu! L'ordinateur a trouv� en premier votre combinaison secr�te."
					+"\n Pour information, la combinaison secr�te de l'ordinateur �tait : "+combinaisonSecreteOrdinateur,
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	

		}
		
		//En cas de match nul
		if(verifCombinaisonSecrete!=nbreCases&&rowIndex==2*nbEssais-1) {
			JOptionPane.showMessageDialog(null, "Match nul. Le nombre d'essai maximal a �t� d�pass�.",
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}

		//En cas de d�fa�te ou de victoire
		if(verifCombinaisonSecrete==nbreCases) {
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}

}

