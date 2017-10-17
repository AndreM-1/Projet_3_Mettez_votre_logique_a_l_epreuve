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
	private JLabel jlPremiereInstruction=new JLabel("La combinaison secrète a été générée par l'ordinateur."),
			jlDeuxiemeInstruction=new JLabel("Veuillez saisir votre combinaison secrète :"),jlPropositionJoueur=new JLabel("Votre proposition :"),
			jlReponseJoueur=new JLabel("Votre réponse (+,-,=) :");
	private JButton jbValiderCombinaisonSecreteJoueur=new JButton("Valider"),jbValiderPropositionJoueur=new JButton("Valider"),
			jbValiderReponseJoueur=new JButton("Valider");
	private JFormattedTextField jftfCombinaisonSecreteJoueur,jftfPropositionJoueur,jftfReponseJoueur;
	private JPanel jpContainerCombinaisonSecreteJoueur=new JPanel(),jpContainerTableau=new JPanel(),jpContainerPropositionReponseJoueur=new JPanel();
	private int nbreCases, nbEssais;
	private int verificationJftf=0,verificationJftfCombinaisonSecreteJoueur=0,verificationJftfPropositionJoueur=0,
			rowIndex=0,columnIndex=0,min=0,max=10,verifCombinaisonSecrete=0,miseAJourAffichageModeDuel=0;
	private String combinaisonSecreteOrdinateur="",reponseAttendue="",propositionSecreteJoueurModeDuel="",propositionOrdinateurModeDuel="";
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

		//Mise en place des JFormattedTextField suivant le nombre de cases choisies.
		try {	
			switch(this.nbreCases) {
			case 4 :
				MaskFormatter formatCombinaisonSecreteJoueur1=new MaskFormatter("####");
				MaskFormatter formatPropositionJoueur1=new MaskFormatter("####");
				MaskFormatter formatReponseJoueur1=new MaskFormatter("****");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur1);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(50,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur1);
				jftfPropositionJoueur.setPreferredSize(new Dimension(50,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur1);
				jftfReponseJoueur.setPreferredSize(new Dimension(50,20));
				break;	
			case 5 :
				MaskFormatter formatCombinaisonSecreteJoueur2=new MaskFormatter("#####");
				MaskFormatter formatPropositionJoueur2=new MaskFormatter("#####");
				MaskFormatter formatReponseJoueur2=new MaskFormatter("*****");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur2);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(55,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur2);
				jftfPropositionJoueur.setPreferredSize(new Dimension(55,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur2);
				jftfReponseJoueur.setPreferredSize(new Dimension(55,20));
				break;
			case 6 :
				MaskFormatter formatCombinaisonSecreteJoueur3=new MaskFormatter("######");
				MaskFormatter formatPropositionJoueur3=new MaskFormatter("######");
				MaskFormatter formatReponseJoueur3=new MaskFormatter("******");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur3);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(60,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur3);
				jftfPropositionJoueur.setPreferredSize(new Dimension(60,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur3);
				jftfReponseJoueur.setPreferredSize(new Dimension(60,20));
				break;
			case 7 :
				MaskFormatter formatCombinaisonSecreteJoueur4=new MaskFormatter("#######");
				MaskFormatter formatPropositionJoueur4=new MaskFormatter("#######");
				MaskFormatter formatReponseJoueur4=new MaskFormatter("*******");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur4);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(65,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur4);
				jftfPropositionJoueur.setPreferredSize(new Dimension(65,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur4);
				jftfReponseJoueur.setPreferredSize(new Dimension(65,20));
				break;
			case 8 :
				MaskFormatter formatCombinaisonSecreteJoueur5=new MaskFormatter("########");
				MaskFormatter formatPropositionJoueur5=new MaskFormatter("########");
				MaskFormatter formatReponseJoueur5=new MaskFormatter("********");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur5);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(70,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur5);
				jftfPropositionJoueur.setPreferredSize(new Dimension(70,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur5);
				jftfReponseJoueur.setPreferredSize(new Dimension(70,20));
				break;
			case 9 :
				MaskFormatter formatCombinaisonSecreteJoueur6=new MaskFormatter("#########");
				MaskFormatter formatPropositionJoueur6=new MaskFormatter("#########");
				MaskFormatter formatReponseJoueur6=new MaskFormatter("*********");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur6);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(75,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur6);
				jftfPropositionJoueur.setPreferredSize(new Dimension(75,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur6);
				jftfReponseJoueur.setPreferredSize(new Dimension(75,20));
				break;
			case 10 :
				MaskFormatter formatCombinaisonSecreteJoueur7=new MaskFormatter("##########");
				MaskFormatter formatPropositionJoueur7=new MaskFormatter("##########");
				MaskFormatter formatReponseJoueur7=new MaskFormatter("**********");	
				jftfCombinaisonSecreteJoueur=new JFormattedTextField(formatCombinaisonSecreteJoueur7);
				jftfCombinaisonSecreteJoueur.setPreferredSize(new Dimension(80,20));
				jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur7);
				jftfPropositionJoueur.setPreferredSize(new Dimension(80,20));
				jftfReponseJoueur=new JFormattedTextField(formatReponseJoueur7);
				jftfReponseJoueur.setPreferredSize(new Dimension(80,20));
				break;
			default:
				System.out.println("Erreur d'initialisation des JFormattedTextField");
			}	
		} catch (ParseException e) {e.printStackTrace();}

		jftfCombinaisonSecreteJoueur.setFont(police);
		jftfPropositionJoueur.setFont(police);	
		jftfPropositionJoueur.setEnabled(false);
		jftfReponseJoueur.setFont(police);		
		jftfReponseJoueur.setEnabled(false);

		//Mise en place d'un tableau à partir d'un modèle de données
		String[] title= {"Proposition","Réponse"};
		Object[][] data= new Object[2*this.nbEssais][2];
		modelTableau=new ModelTableau(data,title);
		jtTableau=new JTable(modelTableau);
		labelRenderer.setModeDeJeu(2);
		jtTableau.getColumn("Proposition").setCellRenderer(labelRenderer);
		jtTableau.getColumn("Réponse").setCellRenderer(labelRenderer);
		jpContainerCombinaisonSecreteJoueur.setPreferredSize(new Dimension(900,40));
		jpContainerPropositionReponseJoueur.setPreferredSize(new Dimension(900,40));

		jbValiderCombinaisonSecreteJoueur.setEnabled(false);
		jbValiderPropositionJoueur.setEnabled(false);
		jbValiderReponseJoueur.setEnabled(false);
		jpContainerTableau.setBackground(Color.WHITE);
		
		//La taille du tableau varie suivant le nombre d'essais
		if(this.nbEssais==5)
			jpContainerTableau.setPreferredSize(new Dimension(350,183));
		else if (this.nbEssais==10)
			jpContainerTableau.setPreferredSize(new Dimension(350,343));
		else if (this.nbEssais==15)
			jpContainerTableau.setPreferredSize(new Dimension(350,343));
		else
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

		// Définition des listeners

		//Les boutons Valider ne doivent être accessibles que lorsque les JFormattedTextField associées sont renseignés
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
						JOptionPane.showMessageDialog(null, "Veuillez saisir l'un des caratères suivants : +, - ou =", "Information", JOptionPane.INFORMATION_MESSAGE);
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

		//Lors de la validation, les données saisies doivent être transmises au contrôleur
		jbValiderCombinaisonSecreteJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jftfCombinaisonSecreteJoueur.setEnabled(false);
				jbValiderCombinaisonSecreteJoueur.setEnabled(false);
				propositionSecreteJoueurModeDuel=jftfCombinaisonSecreteJoueur.getText();
				controler.setPropositionSecreteJoueurModeDuel(propositionSecreteJoueurModeDuel);
				jftfPropositionJoueur.setEnabled(true);
				jftfPropositionJoueur.requestFocusInWindow();
			}

		});

		jbValiderPropositionJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jftfPropositionJoueur.setEnabled(false);
				jbValiderPropositionJoueur.setEnabled(false);
				controler.setPropositionJoueurModeDuel(jftfPropositionJoueur.getText());
				jftfPropositionJoueur.setText("");
				if(!finDePartie) {
					jftfReponseJoueur.setEnabled(true);
					jftfReponseJoueur.requestFocusInWindow();
				}	
				else
					finDePartie=false;
			}
		});

		jbValiderReponseJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean saisieJoueur=true;
				reponseAttendue="";

				//On détermine la réponse attendue.
				for (int i=0;i<combinaisonSecreteOrdinateur.length();i++) {
					if(propositionOrdinateurModeDuel.charAt(i)==propositionSecreteJoueurModeDuel.charAt(i)) {
						reponseAttendue+=String.valueOf('=');
					}
					else if(propositionOrdinateurModeDuel.charAt(i)<propositionSecreteJoueurModeDuel.charAt(i)) {
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
					jbValiderReponseJoueur.setEnabled(false);
					saisieJoueur=false;
				}
				else {
					controler.setReponseJoueurModeDuel(jftfReponseJoueur.getText());
					jftfReponseJoueur.setText("");
					jftfReponseJoueur.setEnabled(false);
					jbValiderReponseJoueur.setEnabled(false);
				}

				if(!finDePartie&&saisieJoueur==true) {
					jftfPropositionJoueur.setEnabled(true);
					jftfPropositionJoueur.requestFocusInWindow();
				}		
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
	public void update(String propositionJoueur,String reponse) {}

	public void updateDuel(String affichage) {

		if(miseAJourAffichageModeDuel==0) {
			((AbstractTableModel)jtTableau.getModel()).setValueAt(affichage, rowIndex, columnIndex);
			((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
			columnIndex++;
			miseAJourAffichageModeDuel++;
			if(rowIndex%2==1)
				propositionOrdinateurModeDuel=affichage;
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
		jftfCombinaisonSecreteJoueur.requestFocusInWindow();
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


	//Génération de la combinaison secrètre par l'ordinateur
	public void generationCombinaisonSecrete(){
		int nbreAleatoire;
		for (int i=0;i<this.nbreCases;i++) {
			nbreAleatoire=(int)(Math.random()*(max-min));
			combinaisonSecreteOrdinateur+=String.valueOf(nbreAleatoire);	
		}
		controler.setModeDeJeu(2);
		controler.setNbEssais(nbEssais);
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
			JOptionPane.showMessageDialog(null, "Félicitations!!! Vous avez trouvé la combinaison secrète de l'ordinateur. ",
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	
		}

		//En cas de défaîte
		if(verifCombinaisonSecrete==nbreCases && rowIndex%2==1) {
			JOptionPane.showMessageDialog(null, "Vous avez perdu! L'ordinateur a trouvé en premier votre combinaison secrète."
					+"\n Pour information, la combinaison secrète de l'ordinateur était : "+combinaisonSecreteOrdinateur,
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	

		}

		//En cas de match nul
		if(verifCombinaisonSecrete!=nbreCases&&rowIndex==2*nbEssais-1) {
			JOptionPane.showMessageDialog(null, "Match nul. Le nombre d'essai maximal a été dépassé.",
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}

		//En cas de défaîte ou de victoire
		if(verifCombinaisonSecrete==nbreCases) {
			finDePartie=true;
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}

}


