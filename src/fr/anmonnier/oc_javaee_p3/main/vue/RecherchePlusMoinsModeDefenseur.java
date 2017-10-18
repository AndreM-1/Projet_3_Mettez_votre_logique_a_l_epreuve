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
	private int verificationJftf=0,verificationJftfCombinaisonSecreteJoueur=0,rowIndex=0,columnIndex=0,verifCombinaisonSecrete=0;
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14);
	private JTable jtTableau;
	private ModelTableau modelTableau;
	private ModeleDonnees model;
	private RecherchePlusMoinsControler controler;
	private BoiteDialogueFinDePartie jdFinDePartie;
	private boolean finDePartie=false;
	private String propositionSecreteModeDefenseur="",propositionOrdinateurModeDefenseur="",reponseAttendue="";

	public RecherchePlusMoinsModeDefenseur(int nbCases, int nbEssais,boolean modeDeveloppeurActive,ModeleDonnees model) {
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

		//Mise en place des JFormattedTextField suivant le nombre de cases choisies.
		try {
			switch(this.nbreCases) {
			case 4:
				MaskFormatter formatCombinaisonSecreteJoueur1=new MaskFormatter("####");
				MaskFormatter formatReponseJoueur1=new MaskFormatter("****");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur1);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(50,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur1);
				jftfReponseJoueur.setPreferredSize(new Dimension(50,20));
				break;
			case 5:
				MaskFormatter formatCombinaisonSecreteJoueur2=new MaskFormatter("#####");
				MaskFormatter formatReponseJoueur2=new MaskFormatter("*****");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur2);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(55,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur2);
				jftfReponseJoueur.setPreferredSize(new Dimension(55,20));
				break;
			case 6:
				MaskFormatter formatCombinaisonSecreteJoueur3=new MaskFormatter("######");
				MaskFormatter formatReponseJoueur3=new MaskFormatter("******");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur3);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(60,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur3);
				jftfReponseJoueur.setPreferredSize(new Dimension(80,20));
				break;
			case 7:
				MaskFormatter formatCombinaisonSecreteJoueur4=new MaskFormatter("#######");
				MaskFormatter formatReponseJoueur4=new MaskFormatter("*******");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur4);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(65,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur4);
				jftfReponseJoueur.setPreferredSize(new Dimension(85,20));
				break;
			case 8:
				MaskFormatter formatCombinaisonSecreteJoueur5=new MaskFormatter("########");
				MaskFormatter formatReponseJoueur5=new MaskFormatter("********");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur5);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(70,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur5);
				jftfReponseJoueur.setPreferredSize(new Dimension(90,20));
				break;
			case 9:
				MaskFormatter formatCombinaisonSecreteJoueur6=new MaskFormatter("#########");
				MaskFormatter formatReponseJoueur6=new MaskFormatter("*********");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur6);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(75,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur6);
				jftfReponseJoueur.setPreferredSize(new Dimension(95,20));
				break;
			case 10:
				MaskFormatter formatCombinaisonSecreteJoueur7=new MaskFormatter("##########");
				MaskFormatter formatReponseJoueur7=new MaskFormatter("**********");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur7);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(80,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur7);
				jftfReponseJoueur.setPreferredSize(new Dimension(100,20));
				break;
			default:
				System.out.println("Erreur d'initialisation des JFormattedTextField");
			}

		} catch (ParseException e) {e.printStackTrace();}

		jftfCombinaisonSecreteJoueur.setFont(police);	
		jftfReponseJoueur.setFont(police);	
		jftfReponseJoueur.setEnabled(false);

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

		//La taille du tableau varie suivant le nombre d'essais
		if(this.nbEssais==5)
			jpContainerTableau.setPreferredSize(new Dimension(350,103));
		else if (this.nbEssais==10)
			jpContainerTableau.setPreferredSize(new Dimension(350,183));
		else if (this.nbEssais==15)
			jpContainerTableau.setPreferredSize(new Dimension(350,263));
		else
			jpContainerTableau.setPreferredSize(new Dimension(350,343));

		jpContainerTableau.setLayout(new BorderLayout());
		jpContainerTableau.add(new JScrollPane(jtTableau),BorderLayout.CENTER);
		this.add(jlPremiereInstruction);
		this.add(jftfCombinaisonSecreteJoueur);
		this.add(jbValiderCombinaisonSecreteJoueur);
		this.add(jlDeuxiemeInstruction);
		this.add(jpContainer);
		this.add(jpContainerTableau);

		// Définition des listeners

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
				propositionSecreteModeDefenseur=jftfCombinaisonSecreteJoueur.getText();
				controler.setModeDeJeu(1);
				controler.setNbEssais(nbEssais);
				controler.setPropositionSecreteModeDefenseur(propositionSecreteModeDefenseur);
				jftfReponseJoueur.requestFocusInWindow();
				jftfReponseJoueur.setEnabled(true);
			}
		});

		jbValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reponseAttendue="";

				//On détermine la réponse attendue.
				for (int i=0;i<propositionSecreteModeDefenseur.length();i++) {
					if(propositionOrdinateurModeDefenseur.charAt(i)==propositionSecreteModeDefenseur.charAt(i)) {
						reponseAttendue+=String.valueOf('=');
					}
					else if(propositionOrdinateurModeDefenseur.charAt(i)<propositionSecreteModeDefenseur.charAt(i)) {
						reponseAttendue+=String.valueOf('+');
					}
					else {
						reponseAttendue+=String.valueOf('-');
					}
				}

				/*On contrôle la réponse de l'utilisateur par rapport à la réponse attendue. L'utilisateur doit impérativement
				/transmettre une réponse adéquate à l'ordinateur.*/
				if(!jftfReponseJoueur.getText().equals(reponseAttendue)) {
					JOptionPane.showMessageDialog(null,"Attention : votre réponse est erronée. Veuillez saisir une autre réponse, svp.", 
							"Message d'avertissement", JOptionPane.WARNING_MESSAGE);
					jftfReponseJoueur.setText("");
					jbValider.setEnabled(false);
				}
				else {
					controler.setReponseJoueurModeDefenseur(jftfReponseJoueur.getText());
					jftfReponseJoueur.setText("");
					jbValider.setEnabled(false);
				}

				if(!finDePartie)
					jftfReponseJoueur.requestFocusInWindow();
				else
					finDePartie=false;
			}
		});

		this.model.addObservateur(this);

	}

	public JFormattedTextField getJftfCombinaisonSecreteJoueur() {
		return jftfCombinaisonSecreteJoueur;
	}

	//Implémentation du pattern Observer
	public void update(String propositionJoueur,String reponse) {
		int previousRowIndex=0;
		previousRowIndex=rowIndex;
		this.propositionOrdinateurModeDefenseur=propositionJoueur;
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

	public void updateDuel(String affichage) {}

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
		verifCombinaisonSecrete=0;
		propositionSecreteModeDefenseur="";
		jftfCombinaisonSecreteJoueur.setText("");
		jftfCombinaisonSecreteJoueur.setEnabled(true);
		jftfCombinaisonSecreteJoueur.requestFocusInWindow();
		jbValiderCombinaisonSecreteJoueur.setEnabled(false);
		jftfReponseJoueur.setEnabled(false);
		jbValider.setEnabled(false);
	}

	public void quitterApplication() {}
	public void acceuilObservateur() {}


	public void gestionFinDePartie(String reponse) {
		//Gestion de la fin de la partie
		verifCombinaisonSecrete=0;


		for (int i=0;i<reponse.length();i++) {
			if (reponse.charAt(i)=='=') {
				verifCombinaisonSecrete++;
			}
		}

		//En cas de défaîte
		if(verifCombinaisonSecrete==nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez perdu. L'ordinateur a trouvé la combinaison secrète "+
					"en moins de "+nbEssais+" essais.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	

		}

		//En cas de victoire
		if (rowIndex==nbEssais && verifCombinaisonSecrete!=nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez gagné! L'ordinateur n'a pas trouvé la combinaison secrète "+
					"en moins de "+nbEssais+" essais.", "Fin de Partie",JOptionPane.INFORMATION_MESSAGE);
		}

		//En cas de défaîte ou de victoire
		if(rowIndex==nbEssais||verifCombinaisonSecrete==nbreCases) {
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}

}

