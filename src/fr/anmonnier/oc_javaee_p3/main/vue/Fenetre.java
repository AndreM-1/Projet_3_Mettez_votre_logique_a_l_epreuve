package fr.anmonnier.oc_javaee_p3.main.vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;


import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

public class Fenetre extends JFrame implements Observateur {


	private static final long serialVersionUID = 1L;
	private JPanel jpContainer = new JPanel();
	private JLabel imageJeu = new JLabel(new ImageIcon("src/fr/anmonnier/oc_javaee_p3/main/resources/MastermindFormatMoyen.jpg"));
	private JMenuBar jmbMenuBar = new JMenuBar();
	private JMenu jmFichier = new JMenu("Fichier"), jmInstructions = new JMenu("Instructions");
	private JMenu jmJeuRecherchePlusMoins = new JMenu("Recherche +/-"), jmJeuMastermind = new JMenu("Mastermind"),
			jmParametres=new JMenu("Paramètres");
	private JMenuItem jmiModeChallenger=new JMenuItem("Mode Challenger"), jmiModeDefenseur=new JMenuItem("Mode Défenseur"),
			jmiModeDuel=new JMenuItem("Mode Duel"),jmi2ModeChallenger=new JMenuItem("Mode Challenger"), 
			jmi2ModeDefenseur=new JMenuItem("Mode Défenseur"),jmi2ModeDuel=new JMenuItem("Mode Duel"),
			jmiQuitter = new JMenuItem("Quitter"), jmiJeuRecherchePlusMoins = new JMenuItem("Recherche +/-"),
			jmiMastermind = new JMenuItem("Mastermind"),jmiParametres=new JMenuItem("Paramètres");
	private ModeleDonnees model;
	private RecherchePlusMoinsModeChallenger jpRecherchePlusMoinsModeChallenger;
	private RecherchePlusMoinsModeDefenseur jpRecherchePlusMoinsModeDefenseur;
	private RecherchePlusMoinsModeDuel jpRecherchePlusMoinsModeDuel;
	private BoiteDialogueParametrage jdParametrage;

	//Valeurs nominales
	private int nbreCasesRecherchePlusMoins=4, nbEssaisRecherchePlusMoins=10,nbreCasesMastermind=4,nbEssaisMastermind=10;
	
	//Valeurs pour le test
	private int nbCasesTest=4, nbEssaisTest=10;

	public Fenetre(ModeleDonnees model) {
		this.setTitle("Mettez votre logique à l'épreuve");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		imageJeu.setPreferredSize(new Dimension(452,480));
		jpContainer.setPreferredSize(new Dimension(900,600));
		jpContainer.add(imageJeu);
		jpContainer.setBackground(Color.WHITE);
		this.setContentPane(jpContainer);
		this.model=model;
		this.model.addObservateur(this);
		this.initMenu();
		this.setVisible(true);
	}


	public void initMenu() {

		// Définition des mnémoniques
		jmFichier.setMnemonic('F');
		jmInstructions.setMnemonic('I');
		jmParametres.setMnemonic('P');

		// Définition des accélérateurs
		jmiQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

		// Construction du menu
		jmJeuRecherchePlusMoins.add(jmiModeChallenger);
		jmJeuRecherchePlusMoins.add(jmiModeDefenseur);
		jmJeuRecherchePlusMoins.add(jmiModeDuel);
		jmJeuMastermind.add(jmi2ModeChallenger);
		jmJeuMastermind.add(jmi2ModeDefenseur);
		jmJeuMastermind.add(jmi2ModeDuel);
		jmFichier.add(jmJeuRecherchePlusMoins);
		jmFichier.add(jmJeuMastermind);
		jmFichier.addSeparator();
		jmFichier.add(jmiQuitter);
		jmParametres.add(jmiParametres);
		jmInstructions.add(jmiJeuRecherchePlusMoins);
		jmInstructions.add(jmiMastermind);
		jmbMenuBar.add(jmFichier);
		jmbMenuBar.add(jmParametres);
		jmbMenuBar.add(jmInstructions);
		this.setJMenuBar(jmbMenuBar);

		// Définition des listeners
		jmiModeChallenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpRecherchePlusMoinsModeChallenger=new RecherchePlusMoinsModeChallenger(nbreCasesRecherchePlusMoins,nbEssaisRecherchePlusMoins,model);
				jpContainer.add(jpRecherchePlusMoinsModeChallenger);
				jpContainer.revalidate();

				/**
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut impérativement placer
				 * cette instruction après le .add
				 */
				jpRecherchePlusMoinsModeChallenger.getJftfPropositionJoueur().requestFocusInWindow();

				/*********************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmiModeDefenseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpRecherchePlusMoinsModeDefenseur=new RecherchePlusMoinsModeDefenseur(nbreCasesRecherchePlusMoins,nbEssaisRecherchePlusMoins,model);
				jpContainer.add(jpRecherchePlusMoinsModeDefenseur);
				jpContainer.revalidate();

				/**
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut impérativement placer
				 * cette instruction après le .add
				 */
				jpRecherchePlusMoinsModeDefenseur.getJftfCombinaisonSecreteJoueur().requestFocusInWindow();

				/*********************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmiModeDuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpRecherchePlusMoinsModeDuel=new RecherchePlusMoinsModeDuel(nbreCasesRecherchePlusMoins,nbEssaisRecherchePlusMoins,model);
				jpContainer.add(jpRecherchePlusMoinsModeDuel);
				jpContainer.revalidate();

				/**
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut impérativement placer
				 * cette instruction après le .add
				 */
				jpRecherchePlusMoinsModeDuel.getJftfCombinaisonSecreteJoueur().requestFocusInWindow();

				/*********************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});
		
		jmiParametres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdParametrage=new BoiteDialogueParametrage(null,"Paramètres des Jeux",true,nbEssaisRecherchePlusMoins,
						nbreCasesRecherchePlusMoins,nbEssaisMastermind,nbreCasesMastermind);
				System.out.println("Nb essais RecherchePlusMoins :"+jdParametrage.getNbEssaisRecherchePlusMoins());
				System.out.println("Nb cases RecherchePlusMoins :"+jdParametrage.getNbreCasesRecherchePlusMoins());
				System.out.println("Nb essais Mastermind :"+jdParametrage.getNbEssaisMastermind());
				System.out.println("Nb cases Mastermind :"+jdParametrage.getNbreCasesMastermind());
				nbEssaisRecherchePlusMoins=jdParametrage.getNbEssaisRecherchePlusMoins();
				nbreCasesRecherchePlusMoins=jdParametrage.getNbreCasesRecherchePlusMoins();
				nbEssaisMastermind=jdParametrage.getNbEssaisMastermind();
				nbreCasesMastermind=jdParametrage.getNbreCasesMastermind();		
			}
			
		});

		jmiJeuRecherchePlusMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strInstructionsJeuRecherchePlusMoins=
						"Le but de ce jeu est de découvrir la combinaison à x chiffres de l'adversaire (le défenseur)."
								+ "\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de "
								+ "\nla combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) "
								+ "\nou si c'est le bon chiffre (=). Un mode duel où attaquant et défenseur jouent tour à tour "
								+ "\nest également disponible";
				JOptionPane.showMessageDialog(null, strInstructionsJeuRecherchePlusMoins, "Instructions Recherche +/-", JOptionPane.INFORMATION_MESSAGE);
			}
		});


		jmiQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void initModel() {
		this.model=new ModeleDonnees();
		this.model.addObservateur(this);	
	}

	//Implémentation du pattern Observer
	public void update(String propositionJoueur, String reponse) {}
	public void updateDuel(String affichage) {}

	public void quitterApplication() {
		System.exit(0);
	}

	public void acceuilObservateur() {
		jpContainer.removeAll();
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(imageJeu);
		jpContainer.revalidate();

		/****************************************************************************************************************************
		 * ATTENTION : Il faut impérativement utiliser la méthode repaint() sinon des composants de l'ancien JPanel resteront visible
		 ****************************************************************************************************************************/
		jpContainer.repaint();
	}

	public void relancerPartie() {}
}
