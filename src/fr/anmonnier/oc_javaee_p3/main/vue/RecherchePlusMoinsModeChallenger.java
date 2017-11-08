package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.AbstractTableModel;

import fr.anmonnier.oc_javaee_p3.main.controler.RecherchePlusMoinsControler;
import fr.anmonnier.oc_javaee_p3.main.model.ModelTableau;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

/****************************************************************
 * Classe relative au jeu RecherchePlusMoins en mode challenger.
 * Cette classe implémente l'interface Observateur.
 * @author André Monnier
 * @see Observateur
 ****************************************************************/
public class RecherchePlusMoinsModeChallenger extends JPanel implements Observateur {

	private static final long serialVersionUID = 1L;

	/**
	 * Un JLabel indiquant que la combinaison secrète a été générée par l'ordinateur.
	 */
	private JLabel jlPremiereInstruction=new JLabel("La combinaison secrète a été générée par l'ordinateur.");

	/**
	 * Un JLabel informant le joueur du nombre d'essais qu'il a pour trouver la combinaison secrète, ainsi que le nombre 
	 * de cases de la combinaison secrète.
	 */
	private JLabel jlDeuxiemeInstruction;

	/**
	 * Un JLabel indiquant que le joueur doit faire sa proposition.
	 */
	private JLabel jlPropositionJoueur=new JLabel("Votre proposition :");

	/**
	 * Un JButton permettant de valider la proposition du joueur.
	 */
	private JButton jbValider=new JButton("Valider");

	/**
	 * Un JFormattedTextField correspondant à la proposition du joueur.
	 */
	private JFormattedTextField jftfPropositionJoueur;

	/**
	 * Un JPanel contenant les composants relatifs à la proposition du joueur.
	 */
	private JPanel jpContainer=new JPanel();

	/**
	 * Un JPanel contenant le tableau où sont affichées les propositions du joueur et les réponses de l'ordinateur.
	 */
	private JPanel jpContainerTableau=new JPanel();

	/**
	 * Paramètre du jeu RecherchePlusMoins.
	 */
	private int nbreCases, nbEssais;

	/**
	 * Variable permettant d'effectuer des contrôles.
	 */
	private int verificationJftf=0,rowIndex=0,columnIndex=0,min=0,max=10,verifCombinaisonSecrete=0;

	/**
	 * Combinaison secrète générée par l'ordinateur.
	 */
	private String combinaisonSecrete="";

	/**
	 * Police d'écriture : nom de la police, style et taille.
	 */
	private Font police=new Font("Segoe UI Semilight",Font.PLAIN,14);

	/**
	 * Tableau où sont affichées les propositions du joueur et les réponses de l'ordinateur.
	 */
	private JTable jtTableau;

	/**
	 * Modèle de données lié au tableau.
	 * @see ModelTableau
	 */
	private ModelTableau modelTableau;

	/**
	 * Modèle de données relatif au jeu RecherchePlusMoins.
	 * @see ModeleDonnees
	 */
	private ModeleDonnees model;

	/**
	 * Controler relatif au jeu RecherchePlusMoins.
	 * @see RecherchePlusMoinsControler
	 */
	private RecherchePlusMoinsControler controler;

	/**
	 * Boite de dialogue permettant d'effectuer un choix en fin de partie.
	 * @see BoiteDialogueFinDePartie
	 */
	private BoiteDialogueFinDePartie jdFinDePartie;

	/**
	 * Paramètre de type booléen indiquant si le mode développeur est activé ou non.
	 */
	private boolean modeDeveloppeurActive;

	/**
	 * Variable permettant la gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER=LogManager.getLogger(); 

	/**
	 * Constructeur de la classe RecherchePlusMoinsModeChallenger.
	 * @param nbCases Nombre de cases du jeu RecherchePlusMoins.
	 * @param nbEssais Nombre d'essais du jeu RecherchePlusMoins.
	 * @param modeDeveloppeurActive Paramètre de type booléen indiquant si le mode développeur est activé ou non.
	 * @param model Modèle de données correspondant au jeu RecherchePlusMoins.
	 * @see ModeleDonnees
	 */
	public RecherchePlusMoinsModeChallenger(int nbCases, int nbEssais,boolean modeDeveloppeurActive,ModeleDonnees model) {
		LOGGER.trace("Instanciation du jeu RecherchePlusMoins en mode Challenger");
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
				LOGGER.error("Jeu RecherchePlusMoins en mode Challenger - Erreur d'initialisation pour le JFormattedTextField");
			}

		} catch (ParseException e) {
			LOGGER.error("Jeu RecherchePlusMoins en mode Challenger -"+e.getMessage());
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

	/**
	 * Accesseur permettant de retourner un objet de type JFormattedTextField correspondant à la proposition du joueur
	 * @return Un objet de type JFormattedTextField correspondant à la proposition du joueur.
	 */
	public JFormattedTextField getJftfPropositionJoueur() {
		return jftfPropositionJoueur;
	}

	/* ***********************************
	 * Implémentation du pattern Observer	
	 *************************************/

	/**
	 * Pattern Observer - Méthode permettant de mettre à jour le tableau en fonction de 
	 * la proposition du joueur et de la réponse de l'ordinateur.
	 * @param propositionJoueur Proposition du joueur.
	 * @param reponse Réponse de l'ordinateur.
	 */
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

	/**
	 * Pattern Observer - Méthode non utilisée dans cette classe. 
	 */
	public void updateDuel(String affichage) {}

	/**
	 * Pattern Observer - Méthode permettant de relancer le jeu RecherchePlusMoins en mode challenger.
	 */
	public void relancerPartie() {
		LOGGER.trace("Jeu RecherchePlusMoins en mode Challenger - Partie relancée");
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

	/**
	 * Pattern Observer - Méthode non utilisée dans cette classe. 
	 */
	public void quitterApplication() {}

	/**
	 * Pattern Observer - Méthode non utilisée dans cette classe. 
	 */
	public void acceuilObservateur() {}

	/**
	 * Génération de la combinaison secrète par l'ordinateur.
	 */
	public void generationCombinaisonSecrete(){
		int nbreAleatoire;
		for (int i=0;i<this.nbreCases;i++) {
			nbreAleatoire=(int)(Math.random()*(max-min));
			combinaisonSecrete+=String.valueOf(nbreAleatoire);	
		}
		LOGGER.debug("Jeu RecherchePlusMoins en mode Challenger - Génération de la combinaison secrète:"+combinaisonSecrete);
		controler.setModeDeJeu(0);
		controler.setPropositionSecreteModeChallenger(combinaisonSecrete);
	}

	/**
	 * Gestion de la fin de la partie en fonction de la réponse de l'ordinateur.
	 * @param reponse Réponse de l'ordinateur.
	 */
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
			LOGGER.trace("Jeu RecherchePlusMoins en mode Challenger - Fin de partie");
			jdFinDePartie =new BoiteDialogueFinDePartie(null,"Fin de Partie",true);
			controler.setChoixFinDePartie(jdFinDePartie.getChoixFinDePartie());
		}
	}
}
