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

public class RecherchePlusMoinsModeChallenger extends JPanel implements Observateur {

	private static final long serialVersionUID = 1L;
	private JLabel jlPremiereInstruction=new JLabel("La combinaison secrète a été générée par l'ordinateur.");
	private JLabel jlDeuxiemeInstruction,jlPropositionJoueur=new JLabel("Votre proposition :");
	private JButton jbValider=new JButton("Valider");
	private JFormattedTextField jftfPropositionJoueur;
	private JPanel jpContainer=new JPanel(),jpContainerTableau=new JPanel();
	private int nbreCases, nbEssais;
	private int verificationJftf=0,rowIndex=0,columnIndex=0,min=0,max=10,verifCombinaisonSecrete=0;
	private String combinaisonSecrete="";
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14);
	private JTable jtTableau;
	private ModelTableau modelTableau;
	private ModeleDonnees model;
	private RecherchePlusMoinsControler controler;
	private BoiteDialogueFinDePartie jdFinDePartie;

	public RecherchePlusMoinsModeChallenger(int nbCases, int nbEssais,ModeleDonnees model) {
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.model=model;
		controler=new RecherchePlusMoinsControler(model);
		this.generationCombinaisonSecrete();
		jlDeuxiemeInstruction=new JLabel("Vous avez "+this.nbEssais+" essais pour trouver la combinaison secrète de "+this.nbreCases+" cases.");
		jlPremiereInstruction.setPreferredSize(new Dimension(900,40));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);
		jlDeuxiemeInstruction.setPreferredSize(new Dimension(900,40));
		jlDeuxiemeInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlDeuxiemeInstruction.setFont(police);
		jlPropositionJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlPropositionJoueur.setFont(police);
		try {
			MaskFormatter formatPropositionJoueur=new MaskFormatter("####");
			jftfPropositionJoueur=new JFormattedTextField(formatPropositionJoueur);
			jftfPropositionJoueur.setPreferredSize(new Dimension(50,20));
			jftfPropositionJoueur.setFont(police);	
		} catch (ParseException e) {e.printStackTrace();}

		//Mise en place d'un tableau à partir d'un modèle de données
		String[] title= {"Proposition du joueur","Réponse"};
		Object[][] data= new Object[this.nbEssais][2];
		modelTableau=new ModelTableau(data,title);
		jtTableau=new JTable(modelTableau);
		jtTableau.getColumn("Proposition du joueur").setCellRenderer(new LabelRenderer());
		jtTableau.getColumn("Réponse").setCellRenderer(new LabelRenderer());
		jpContainer.setPreferredSize(new Dimension(900,40));
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(jlPropositionJoueur);
		jpContainer.add(jftfPropositionJoueur);
		jbValider.setEnabled(false);
		jpContainer.add(jbValider);
		jpContainerTableau.setBackground(Color.WHITE);
		jpContainerTableau.setPreferredSize(new Dimension(350,183));
		jpContainerTableau.setLayout(new BorderLayout());
		jpContainerTableau.add(new JScrollPane(jtTableau),BorderLayout.CENTER);
		this.add(jlPremiereInstruction);
		this.add(jlDeuxiemeInstruction);
		this.add(jpContainer);
		this.add(jpContainerTableau);

		// Définition des listeners

		//Gestion du positionnement du curseur
		jftfPropositionJoueur.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				jftfPropositionJoueur.setCaretPosition(0);
			}
			public void focusLost(FocusEvent arg0) {}
		});
				
		//Le bouton Valider ne doit être accessible que lorsque tous les chiffres sont renseignés
		jftfPropositionJoueur.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent event) {
				for (int i=0;i<nbreCases;i++) {
					if(jftfPropositionJoueur.getText().charAt(i)!=' ') {
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
		jbValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.setPropositionJoueurModeChallenger(jftfPropositionJoueur.getText());
				jftfPropositionJoueur.setText("");
				jbValider.setEnabled(false);
			}

		});

		this.model.addObservateur(this);

	}

	//Implémentation du pattern Observer
	public void update(String propositionJoueur,String reponse) {
		((AbstractTableModel)jtTableau.getModel()).setValueAt(propositionJoueur,rowIndex,columnIndex);
		((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
		columnIndex++;
		((AbstractTableModel)jtTableau.getModel()).setValueAt(reponse,rowIndex,columnIndex);
		((AbstractTableModel)jtTableau.getModel()).fireTableCellUpdated(rowIndex, columnIndex);
		rowIndex++;
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
		rowIndex=0;
		columnIndex=0;
		min=0;
		max=10;
		verifCombinaisonSecrete=0;
		combinaisonSecrete="";
		this.generationCombinaisonSecrete();
	}
	
	public void quitterApplication() {}
	public void acceuilObservateur() {}
	
	//Génération de la combinaison secrète par l'ordinateur
	public void generationCombinaisonSecrete(){
		int nbreAleatoire;
		for (int i=0;i<this.nbreCases;i++) {
			nbreAleatoire=(int)(Math.random()*(max-min));
			combinaisonSecrete+=String.valueOf(nbreAleatoire);	
		}
		controler.setModeDeJeu(0);
		controler.setPropositionSecreteModeChallenger(combinaisonSecrete);
		System.out.println("COMBINAISON SECRETE :"+combinaisonSecrete);
		

	}
    
	//Gestion de la fin de la partie
	public void gestionFinDePartie(String reponse) {
		
		verifCombinaisonSecrete=0;
		
		for (int i=0;i<reponse.length();i++) {
			if (reponse.charAt(i)=='=') {
				verifCombinaisonSecrete++;
			}
		}
		
		//En cas de victoire
		if(verifCombinaisonSecrete==nbreCases) {
			JOptionPane.showMessageDialog(null, "Félicitations!!! Vous avez trouvé la combinaison secrète en moins de "+nbEssais+" essais.", 
					"Fin de Partie",JOptionPane.INFORMATION_MESSAGE);	
			
		}
		
		//En cas de défaîte
		if (rowIndex==nbEssais && verifCombinaisonSecrete!=nbreCases) {
			JOptionPane.showMessageDialog(null, "Vous avez perdu! La combinaison secrète était : "+combinaisonSecrete, "Fin de Partie",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		//En cas de défaîte ou de victoire
		if(rowIndex==nbEssais||verifCombinaisonSecrete==nbreCases) {
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}
	
}
