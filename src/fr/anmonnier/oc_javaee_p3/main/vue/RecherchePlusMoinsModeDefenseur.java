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

public class RecherchePlusMoinsModeDefenseur extends JPanel implements Observateur {

	private static final long serialVersionUID = 1L;
	private JLabel jlPremiereInstruction=new JLabel("Veuillez saisir la combinaison secrète :");
	private JLabel jlDeuxiemeInstruction,jlReponseJoueur=new JLabel("Votre réponse (+,-,=) :");
	private JButton jbValider=new JButton("Valider"), jbValiderCombinaisonSecreteJoueur=new JButton("Valider");
	private JFormattedTextField jftfReponseJoueur, jftfCombinaisonSecreteJoueur;
	private JPanel jpContainer=new JPanel(),jpContainerTableau=new JPanel();
	private int nbreCases, nbEssais;
	private int verificationJftf=0,verificationJftfCombinaisonSecreteJoueur=0,rowIndex=0,columnIndex=0,min=0,max=10,
			verifCombinaisonSecrete=0;
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14);
	private JTable jtTableau;
	private ModelTableau modelTableau;
	private ModeleDonnees model;
	private RecherchePlusMoinsControler controler;
	private BoiteDialogueFinDePartie jdFinDePartie;

	public RecherchePlusMoinsModeDefenseur(int nbCases, int nbEssais,ModeleDonnees model) {
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.model=model;
		controler=new RecherchePlusMoinsControler(model);
		jlDeuxiemeInstruction=new JLabel("L'ordinateur a "+this.nbEssais+" essais pour trouver la combinaison secrète de "+this.nbreCases+" cases.");
		jlPremiereInstruction.setPreferredSize(new Dimension(230,40));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);
		jlDeuxiemeInstruction.setPreferredSize(new Dimension(900,40));
		jlDeuxiemeInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlDeuxiemeInstruction.setFont(police);
		jlReponseJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlReponseJoueur.setFont(police);
		try {
			MaskFormatter formatCombinaisonSecreteJoueur=new MaskFormatter("####");
			MaskFormatter formatReponseJoueur=new MaskFormatter("****");	
			jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur);
			jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(50,20));
			jftfCombinaisonSecreteJoueur.setFont(police);	
			jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur);
			jftfReponseJoueur.setPreferredSize(new Dimension(50,20));
			jftfReponseJoueur.setFont(police);		
			jftfReponseJoueur.setEnabled(false);
		} catch (ParseException e) {e.printStackTrace();}

		//Mise en place d'un tableau à partir d'un modèle de données
		String[] title= {"Proposition de l'ordinateur","Réponse"};
		Object[][] data= new Object[this.nbEssais][2];
		modelTableau=new ModelTableau(data,title);
		jtTableau=new JTable(modelTableau);
		jtTableau.getColumn("Proposition de l'ordinateur").setCellRenderer(new LabelRenderer());
		jtTableau.getColumn("Réponse").setCellRenderer(new LabelRenderer());
		jpContainer.setPreferredSize(new Dimension(900,40));
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(jlReponseJoueur);
		jpContainer.add(jftfReponseJoueur);
		jbValider.setEnabled(false);
		jbValiderCombinaisonSecreteJoueur.setEnabled(false);
		jpContainer.add(jbValider);
		jpContainerTableau.setBackground(Color.WHITE);
		jpContainerTableau.setPreferredSize(new Dimension(350,183));
		jpContainerTableau.setLayout(new BorderLayout());
		jpContainerTableau.add(new JScrollPane(jtTableau),BorderLayout.CENTER);
		this.add(jlPremiereInstruction);
		this.add(jftfCombinaisonSecreteJoueur);
		this.add(jbValiderCombinaisonSecreteJoueur);
		this.add(jlDeuxiemeInstruction);
		this.add(jpContainer);
		this.add(jpContainerTableau);

		// Définition des listeners

		//Gestion du positionnement du curseur
		jftfCombinaisonSecreteJoueur.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
				jftfCombinaisonSecreteJoueur.setCaretPosition(0);
			}
			public void focusLost(FocusEvent arg0) {}
		});


		jftfReponseJoueur.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				jftfReponseJoueur.setCaretPosition(0);
			}
			public void focusLost(FocusEvent arg0) {}
		});


		//Les boutons Valider ne doivent être accessibles que lorsque tous les champs des JFormattedTextField sont renseignés
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


		jftfReponseJoueur.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent event) {

				for (int i=0;i<nbreCases;i++) {
					if(jftfReponseJoueur.getText().charAt(i)!='='&&jftfReponseJoueur.getText().charAt(i)!='+'&&
							jftfReponseJoueur.getText().charAt(i)!='-'&&jftfReponseJoueur.getText().charAt(i)!=' ') {
						JOptionPane.showMessageDialog(null, "Veuillez saisir l'un des caratères suivants : +, - ou =", "Information", JOptionPane.INFORMATION_MESSAGE);
						jftfReponseJoueur.setText("");
					}

					if(jftfReponseJoueur.getText().charAt(i)!=' ') {
						verificationJftf++;
					}
				}
				if(verificationJftf==nbreCases) {
					jbValider.setEnabled(true); 
					verificationJftf=0;
				}
				else {
					jbValider.setEnabled(false);
					verificationJftf=0;
				}
			}

		});

		//Lors de la validation, les données saisies doivent être transmises au contrôleur
		jbValiderCombinaisonSecreteJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jftfCombinaisonSecreteJoueur.setEnabled(false);
				jbValiderCombinaisonSecreteJoueur.setEnabled(false);
				controler.setModeDeJeu(1);
				controler.setPropositionSecreteModeDefenseur(jftfCombinaisonSecreteJoueur.getText());
				jftfReponseJoueur.setEnabled(true);

			}

		});

		jbValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.setReponseJoueurModeDefenseur(jftfReponseJoueur.getText());
				jftfReponseJoueur.setText("");
				jbValider.setEnabled(false);
			}

		});

		this.model.addObservateur(this);

	}

	//Implémentation du pattern Observer
	public void update(String propositionJoueur,String reponse) {
		int previousRowIndex=0;
		previousRowIndex=rowIndex;
		((AbstractTableModel)jtTableau.getModel()).setValueAt(propositionJoueur,rowIndex,columnIndex);
		((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);

		if(reponse.equals("")) {
			rowIndex=previousRowIndex;
		}
		else {
			columnIndex++;
			((AbstractTableModel)jtTableau.getModel()).setValueAt(reponse,rowIndex,columnIndex);
			((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
			rowIndex++;
		}

		columnIndex=0;
		this.gestionFinDePartie(reponse);
	}

	public void relancerPartie() {
		for(int i=0;i<rowIndex;i++) {
			for (int j=0;j<2;j++) {
				((AbstractTableModel)jtTableau.getModel()).setValueAt("", i, j);
				((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(i, j);
			}
		}
		verificationJftf=0;
		verificationJftfCombinaisonSecreteJoueur=0;
		rowIndex=0;
		columnIndex=0;
		min=0;
		max=10;
		verifCombinaisonSecrete=0;
		jftfCombinaisonSecreteJoueur.setText("");
		jftfCombinaisonSecreteJoueur.setEnabled(true);
		jbValiderCombinaisonSecreteJoueur.setEnabled(false);
		jftfReponseJoueur.setEnabled(false);
		jbValider.setEnabled(false);
	}

	public void quitterApplication() {}
	public void acceuilObservateur() {}


	public void gestionFinDePartie(String reponse) {
		//Gestion de la fin de la partie
		verifCombinaisonSecrete=0;

		//En cas de victoire
		for (int i=0;i<reponse.length();i++) {
			if (reponse.charAt(i)=='=') {
				verifCombinaisonSecrete++;
			}
		}

		if(verifCombinaisonSecrete==nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez perdu. L'ordinateur a trouvé la combinaison secrète "+
					"en moins de "+nbEssais+" essais.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	

		}

		//En cas de défaîte
		if (rowIndex==nbEssais && verifCombinaisonSecrete!=nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez gagné! L'ordinateur n'a pas trouvé la combinaison secrète "+
					"en moins de "+nbEssais+" essais.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);
		}

		//En cas de défaîte ou de victoire
		if(rowIndex==nbEssais||verifCombinaisonSecrete==nbreCases) {
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}

}

