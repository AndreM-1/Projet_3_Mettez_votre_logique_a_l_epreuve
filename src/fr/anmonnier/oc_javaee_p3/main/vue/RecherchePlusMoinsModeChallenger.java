package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

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
	private boolean modeDeveloppeurActive;
	private Logger logger=(Logger)LogManager.getLogger(RecherchePlusMoinsModeChallenger.class);
	private LoggerContext context=(org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
	private File file = new File("resources/log4j2.xml"); 

	public RecherchePlusMoinsModeChallenger(int nbCases, int nbEssais,boolean modeDeveloppeurActive,ModeleDonnees model) {
		context.setConfigLocation(file.toURI());
		logger.trace("Instanciation du jeu RecherchePlusMoins en mode Challenger");
		this.setPreferredSize(new Dimension(1000,740));
		this.setBackground(Color.WHITE);
		this.nbreCases=nbCases;
		this.nbEssais=nbEssais;
		this.modeDeveloppeurActive=modeDeveloppeurActive;
		this.model=model;
		controler=new RecherchePlusMoinsControler(model);
		this.generationCombinaisonSecrete();
		if(this.modeDeveloppeurActive==true) {
			jlPremiereInstruction.setText("La combinaison secrète a été générée par l'ordinateur (Solution : "+combinaisonSecrete+")");
		}

		jlPremiereInstruction.setPreferredSize(new Dimension(1000,40));
		jlPremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlPremiereInstruction.setFont(police);
		jlDeuxiemeInstruction=new JLabel("Vous avez "+this.nbEssais+" essais pour trouver la combinaison secrète de "+this.nbreCases+" cases.");
		jlDeuxiemeInstruction.setPreferredSize(new Dimension(1000,40));
		jlDeuxiemeInstruction.setHorizontalAlignment(JLabel.CENTER);
		jlDeuxiemeInstruction.setFont(police);
		jlPropositionJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlPropositionJoueur.setFont(police);

		//Mise en place du JFormattedTextField suivant le nombre de cases choisies.
		try {
			switch (this.nbreCases) {
			case 4:
				MaskFormatter formatPropositionJoueur1 = new MaskFormatter("####");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur1);
				jftfPropositionJoueur.setPreferredSize(new Dimension(50, 20));
				break;
			case 5:
				MaskFormatter formatPropositionJoueur2 = new MaskFormatter("#####");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur2);
				jftfPropositionJoueur.setPreferredSize(new Dimension(55, 20));
				break;
			case 6:
				MaskFormatter formatPropositionJoueur3 = new MaskFormatter("######");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur3);
				jftfPropositionJoueur.setPreferredSize(new Dimension(60, 20));
				break;
			case 7:
				MaskFormatter formatPropositionJoueur4 = new MaskFormatter("#######");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur4);
				jftfPropositionJoueur.setPreferredSize(new Dimension(65, 20));
				break;
			case 8:
				MaskFormatter formatPropositionJoueur5 = new MaskFormatter("########");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur5);
				jftfPropositionJoueur.setPreferredSize(new Dimension(70, 20));
				break;
			case 9:
				MaskFormatter formatPropositionJoueur6 = new MaskFormatter("#########");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur6);
				jftfPropositionJoueur.setPreferredSize(new Dimension(75, 20));
				break;
			case 10:
				MaskFormatter formatPropositionJoueur7 = new MaskFormatter("##########");
				jftfPropositionJoueur = new JFormattedTextField(formatPropositionJoueur7);
				jftfPropositionJoueur.setPreferredSize(new Dimension(80, 20));
				break;
			default:
				logger.error("Jeu RecherchePlusMoins en mode Challenger - Erreur d'initialisation pour le JFormattedTextField");
			}

		} catch (ParseException e) {
			logger.error("Jeu RecherchePlusMoins en mode Challenger -"+e.getMessage());
		}

		jftfPropositionJoueur.setFont(police);

		//Mise en place d'un tableau à partir d'un modèle de données
		String[] title= {"Proposition du joueur","Réponse"};
		Object[][] data= new Object[this.nbEssais][2];
		modelTableau=new ModelTableau(data,title);
		jtTableau=new JTable(modelTableau);
		jtTableau.getColumn("Proposition du joueur").setCellRenderer(new LabelRenderer());
		jtTableau.getColumn("Réponse").setCellRenderer(new LabelRenderer());
		jpContainer.setPreferredSize(new Dimension(1000,40));
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(jlPropositionJoueur);
		jpContainer.add(jftfPropositionJoueur);
		jbValider.setEnabled(false);
		jpContainer.add(jbValider);
		jpContainerTableau.setBackground(Color.WHITE);

		//La taille du tableau varie suivant le nombre d'essais
		if(this.nbEssais==5)
			jpContainerTableau.setPreferredSize(new Dimension(350,103));
		else if(this.nbEssais==10)
			jpContainerTableau.setPreferredSize(new Dimension(350,183));
		else if(this.nbEssais==15)
			jpContainerTableau.setPreferredSize(new Dimension(350,263));
		else
			jpContainerTableau.setPreferredSize(new Dimension(350,343));

		jpContainerTableau.setLayout(new BorderLayout());
		jpContainerTableau.add(new JScrollPane(jtTableau),BorderLayout.CENTER);
		this.add(jlPremiereInstruction);
		this.add(jlDeuxiemeInstruction);
		this.add(jpContainer);
		this.add(jpContainerTableau);

		// Définition des listeners

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
				jftfPropositionJoueur.requestFocusInWindow();
				jbValider.setEnabled(false);
			}

		});

		this.model.addObservateur(this);

	}

	public JFormattedTextField getJftfPropositionJoueur() {
		return jftfPropositionJoueur;
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
		logger.trace("Jeu RecherchePlusMoins en mode Challenger - Partie relancée");
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
		if(this.modeDeveloppeurActive==true) {
			jlPremiereInstruction.setText("La combinaison secrète a été générée par l'ordinateur (Solution : "+combinaisonSecrete+")");
		}
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
		logger.debug("Jeu RecherchePlusMoins en mode Challenger - Génération de la combinaison secrète:"+combinaisonSecrete);
		controler.setModeDeJeu(0);
		controler.setPropositionSecreteModeChallenger(combinaisonSecrete);
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
			logger.trace("Jeu RecherchePlusMoins en mode Challenger - Fin de partie");
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}

}
