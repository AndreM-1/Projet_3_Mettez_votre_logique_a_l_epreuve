package fr.anmonnier.oc_javaee_p3.main.vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;
import fr.anmonnier.oc_javaee_p3.main.observer.ObservateurMastermind;

/************************************************************************************************************
 * La classe Fenetre est la fenêtre principale de l'application. Elle correspond à la page d'accueil du jeu.
 * Elle est composée d'une barre de menu permettant d'accéder aux jeux RecherchePlusMoins et Mastermind
 * dans les modes Challenger, Défenseur et Duel, mais également aux paramètres de jeu et aux instructions.
 * Elle implémente les interfaces Observateur et ObservateurMastermind.
 * @author André Monnier
 * @see Observateur
 * @see ObservateurMastermind
 ************************************************************************************************************/
public class Fenetre extends JFrame implements Observateur,ObservateurMastermind {

	private static final long serialVersionUID = 1L;
	
	/**
	 * JPanel principal de la classe.
	 */
	private JPanel jpContainer = new JPanel();

	/**
	 * Image de la page d'accueil.
	 */
	private JLabel imageJeu = new JLabel(new ImageIcon("resources/Mastermind.jpg"));

	/**
	 * Barre de menu.
	 */
	private JMenuBar jmbMenuBar = new JMenuBar();

	/**
	 * Elément de la barre de menu.
	 */
	private JMenu jmFichier = new JMenu("Fichier"), jmInstructions = new JMenu("Instructions"),
			jmJeuRecherchePlusMoins = new JMenu("Recherche +/-"), jmJeuMastermind = new JMenu("Mastermind"),
			jmParametres=new JMenu("Paramètres");
	/**
	 * Champ permettant d'accéder à la fonctionnalité correspondante de l'application.
	 */
	private JMenuItem jmiModeChallenger=new JMenuItem("Mode Challenger"), jmiModeDefenseur=new JMenuItem("Mode Défenseur"),
			jmiModeDuel=new JMenuItem("Mode Duel"),jmi2ModeChallenger=new JMenuItem("Mode Challenger"), 
			jmi2ModeDefenseur=new JMenuItem("Mode Défenseur"),jmi2ModeDuel=new JMenuItem("Mode Duel"),
			jmiQuitter = new JMenuItem("Quitter"), jmiJeuRecherchePlusMoins = new JMenuItem("Recherche +/-"),
			jmiMastermind = new JMenuItem("Mastermind"),jmiParametres=new JMenuItem("Paramètres");
	/**
	 * Modéle de données relatif au jeu RecherchePlusMoins.
	 * @see ModeleDonnees
	 */
	private ModeleDonnees model;
	
	/**
	 * Modéle de données relatif au jeu Mastermind.
	 * @see ModeleDonneesMastermind
	 */
	private ModeleDonneesMastermind modelMastermind;
	
	/**
	 * Objet lié au jeu correspondant.
	 * @see RecherchePlusMoinsModeChallenger
	 */
	private RecherchePlusMoinsModeChallenger jpRecherchePlusMoinsModeChallenger;
	
	/**
	 * Objet lié au jeu correspondant.
	 * @see RecherchePlusMoinsModeDefenseur
	 */
	private RecherchePlusMoinsModeDefenseur jpRecherchePlusMoinsModeDefenseur;
	
	/**
	 * Objet lié au jeu correspondant.
	 * @see RecherchePlusMoinsModeDuel
	 */
	private RecherchePlusMoinsModeDuel jpRecherchePlusMoinsModeDuel;
	
	/**
	 * Objet lié au jeu correspondant.
	 * @see MastermindModeChallenger
	 */
	private MastermindModeChallenger jpMastermindModeChallenger;
	
	/**
	 * Objet lié au jeu correspondant.
	 * @see MastermindModeDefenseur
	 */
	private MastermindModeDefenseur jpMastermindModeDefenseur;
	
	/**
	 * Objet lié au jeu correspondant.
	 * @see MastermindModeDuel
	 */
	private MastermindModeDuel jpMastermindModeDuel;
	
	/**
	 * Boite de dialogue permettant de changer les paramètres du jeu.
	 * @see BoiteDialogueParametrage
	 */
	private BoiteDialogueParametrage jdParametrage;
	
	/**
	 * Flux d'entrée permettant de lire le fichier resources/config.properties.
	 */
	private InputStream input;
	
	/**
	 * Objet permettant de charger le fichier resources/config.properties.
	 */
	private Properties prop;
	
	/**
	 * Variable permettant la gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER=LogManager.getLogger();

	/**
	 * Paramètre du jeu RecherchePlusMoins.
	 */
	private int nbreCasesRecherchePlusMoins=4, nbEssaisRecherchePlusMoins=10;
	
	/**
	 * Paramètre du jeu Mastermind.
	 */
	private int nbreCasesMastermind=4,nbEssaisMastermind=10,nbCouleursUtilisablesMastermind=6;
	
	/**
	 * Paramètre relatif aux jeux RecherchePlusMoins et Mastermind. Par défaut, le mode développeur est désactivé.
	 */
	private boolean modeDeveloppeurActive=false;

	/**
	 * Constructeur de la classe Fenetre.
	 * @param model Modèle de données correspondant au jeu RecherchePlusMoins.
	 * @param modelMastermind Modèle de données correspondant au jeu Mastermind.
	 * @param modeDeveloppeurActiveConsole Paramètre de type booléen indiquant si le mode développeur est activé ou non. 
	 * @see ModeleDonnees
	 * @see ModeleDonneesMastermind
	 */
	public Fenetre(ModeleDonnees model,ModeleDonneesMastermind modelMastermind, boolean modeDeveloppeurActiveConsole) {      
		LOGGER.trace("Instanciation de la fenêtre principale");
		this.setTitle("Mettez votre logique à l'épreuve");
		this.setSize(1000, 740);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("resources/MastermindFormatIcone.png").getImage());
		imageJeu.setPreferredSize(new Dimension(600,637));
		jpContainer.setPreferredSize(new Dimension(1000,740));
		jpContainer.add(imageJeu);
		jpContainer.setBackground(Color.WHITE);
		this.setContentPane(jpContainer);
		this.model=model;
		this.model.addObservateur(this);
		this.modelMastermind=modelMastermind;
		this.modelMastermind.addObservateurMastermind(this);
		this.modeDeveloppeurActive=modeDeveloppeurActiveConsole;
		LOGGER.trace("Initialisation des modèles de données");

		//On récupère les données enregistrées dans le fichier config.properties
		prop=new Properties();
		input=null;

		try {
			input=new FileInputStream("resources/config.properties");
			prop.load(input);
			nbEssaisRecherchePlusMoins=Integer.valueOf(prop.getProperty("param.nbEssaisActifRecherchePlusMoins"));
			nbreCasesRecherchePlusMoins=Integer.valueOf(prop.getProperty("param.nbreCasesActifRecherchePlusMoins"));
			nbEssaisMastermind=Integer.valueOf(prop.getProperty("param.nbEssaisActifMastermind"));
			nbreCasesMastermind=Integer.valueOf(prop.getProperty("param.nbreCasesActifMastermind"));
			nbCouleursUtilisablesMastermind=Integer.valueOf(prop.getProperty("param.nbCouleursUtilisablesActifMastermind"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(input!=null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}

		this.initMenu();
		this.setVisible(true);
	}

	/**
	 * Méthode permettant d'initialiser le menu de la fenêtre principale. 
	 * Un listener a été ajouté à chaque élément du menu.
	 * */
	public void initMenu() {
		LOGGER.trace("Initialisation du menu");

		// Définition des mnémoniques
		jmFichier.setMnemonic('F');
		jmInstructions.setMnemonic('I');
		jmParametres.setMnemonic('P');

		// Définition des accélérateurs
		jmiQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		jmiParametres.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

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
				jpRecherchePlusMoinsModeChallenger=new RecherchePlusMoinsModeChallenger(nbreCasesRecherchePlusMoins,
						nbEssaisRecherchePlusMoins,modeDeveloppeurActive,model);
				jpContainer.add(jpRecherchePlusMoinsModeChallenger);
				jpContainer.revalidate();
				jpContainer.repaint();
				jmParametres.setEnabled(false);

				/*
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut impérativement placer
				 * cette instruction après le .add
				 */
				jpRecherchePlusMoinsModeChallenger.getJftfPropositionJoueur().requestFocusInWindow();

				/* *******************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmiModeDefenseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpRecherchePlusMoinsModeDefenseur=new RecherchePlusMoinsModeDefenseur(nbreCasesRecherchePlusMoins,
						nbEssaisRecherchePlusMoins,modeDeveloppeurActive,model);
				jpContainer.add(jpRecherchePlusMoinsModeDefenseur);
				jpContainer.revalidate();
				jpContainer.repaint();

				jmParametres.setEnabled(false);

				/*
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut impérativement placer
				 * cette instruction après le .add
				 */
				jpRecherchePlusMoinsModeDefenseur.getJftfCombinaisonSecreteJoueur().requestFocusInWindow();

				/* *******************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmiModeDuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpRecherchePlusMoinsModeDuel=new RecherchePlusMoinsModeDuel(nbreCasesRecherchePlusMoins,
						nbEssaisRecherchePlusMoins,modeDeveloppeurActive,model);
				jpContainer.add(jpRecherchePlusMoinsModeDuel);
				jpContainer.revalidate();
				jpContainer.repaint();
				jmParametres.setEnabled(false);

				/*
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut impérativement placer
				 * cette instruction après le .add
				 */
				jpRecherchePlusMoinsModeDuel.getJftfCombinaisonSecreteJoueur().requestFocusInWindow();

				/* *******************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmi2ModeChallenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpMastermindModeChallenger=new MastermindModeChallenger(nbreCasesMastermind,nbEssaisMastermind,
						nbCouleursUtilisablesMastermind,modeDeveloppeurActive,modelMastermind);
				jpContainer.add(jpMastermindModeChallenger);
				jpContainer.revalidate();
				jpContainer.repaint();
				jmParametres.setEnabled(false);

				/* *******************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmi2ModeDefenseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpMastermindModeDefenseur=new MastermindModeDefenseur(nbreCasesMastermind,nbEssaisMastermind,
						nbCouleursUtilisablesMastermind,modeDeveloppeurActive,modelMastermind);
				jpContainer.add(jpMastermindModeDefenseur);
				jpContainer.revalidate();
				jpContainer.repaint();
				jmParametres.setEnabled(false);

				/* *******************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});

		jmi2ModeDuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpMastermindModeDuel=new MastermindModeDuel(nbreCasesMastermind,nbEssaisMastermind,
						nbCouleursUtilisablesMastermind,modeDeveloppeurActive,modelMastermind);
				jpContainer.add(jpMastermindModeDuel);
				jpContainer.revalidate();
				jpContainer.repaint();
				jmParametres.setEnabled(false);

				/* *******************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});


		jmiParametres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdParametrage=new BoiteDialogueParametrage(null,"Paramètres des Jeux",true,nbEssaisRecherchePlusMoins,
						nbreCasesRecherchePlusMoins,nbEssaisMastermind,nbreCasesMastermind,nbCouleursUtilisablesMastermind,
						modeDeveloppeurActive);
				nbEssaisRecherchePlusMoins=jdParametrage.getNbEssaisRecherchePlusMoins();
				nbreCasesRecherchePlusMoins=jdParametrage.getNbreCasesRecherchePlusMoins();
				nbEssaisMastermind=jdParametrage.getNbEssaisMastermind();
				nbreCasesMastermind=jdParametrage.getNbreCasesMastermind();		
				modeDeveloppeurActive=jdParametrage.getModeDeveloppeurActive();
				nbCouleursUtilisablesMastermind=jdParametrage.getNbCouleursUtilisablesMastermind();
				LOGGER.debug("Menu Paramètres - Nb essais RecherchePlusMoins :"+nbEssaisRecherchePlusMoins);
				LOGGER.debug("Menu Paramètres - Nb cases RecherchePlusMoins :"+nbreCasesRecherchePlusMoins);
				LOGGER.debug("Menu Paramètres - Nb essais Mastermind :"+nbEssaisMastermind);
				LOGGER.debug("Menu Paramètres - Nb cases Mastermind :"+nbreCasesMastermind);
				LOGGER.debug("Menu Paramètres - Nb couleurs utilisables Mastermind :"+nbCouleursUtilisablesMastermind);
				LOGGER.debug("Menu Paramètres - Etat du mode développeur :"+modeDeveloppeurActive);
				
			}
		});

		jmiJeuRecherchePlusMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strInstructionsJeuRecherchePlusMoins=
						"Le but de ce jeu est de découvrir la combinaison à x chiffres de l'adversaire (le défenseur)."
								+ "\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de "
								+ "\nla combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) "
								+ "\nou si c'est le bon chiffre (=). Un mode duel où attaquant et défenseur jouent tour à tour "
								+ "\nest également disponible.";
				JOptionPane.showMessageDialog(null, strInstructionsJeuRecherchePlusMoins, "Instructions Recherche +/-", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		jmiMastermind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strInstructionsJeuMastermind=
						"Le but de ce jeu est de découvrir la combinaison à x couleurs de l'adversaire (le défenseur)."
								+ "\nPour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque proposition"
								+ "\nle nombre de couleurs de la proposition qui apparaissent à la bonne place (à l'aide de pions rouges)"
								+ "\net à la mauvaise place (à l'aide de pions blancs) dans la combinaison secrète.Un mode duel où "
								+ "\nattaquant et défenseur jouent tour à tour est également disponible."; 				
				JOptionPane.showMessageDialog(null, strInstructionsJeuMastermind, "Instructions Mastermind", JOptionPane.INFORMATION_MESSAGE);
			}
		});


		jmiQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOGGER.trace("Fin de l'application");
				System.exit(0);
			}
		});
	}

	/**
	 * Méthode qui permet de réinitialiser les modèles de données relatifs aux jeux RecherchePlusMoins et Mastermind.
	 */
	private void initModel() {
		this.model=new ModeleDonnees();
		this.model.addObservateur(this);	
		this.modelMastermind=new ModeleDonneesMastermind();
		this.modelMastermind.addObservateurMastermind(this);
		LOGGER.trace("Réinitialisation des modèles de données");
	}

	/* ******************************************************************
	 * Implémentation du pattern Observer pour le jeu RecherchePlusMoins
	 * ******************************************************************/

	/**
	 * Pattern Observer - Méthode non utilisée dans la classe Fenêtre.
	 */
	public void update(String propositionJoueur, String reponse) {}

	/**
	 * Pattern Observer - Méthode non utilisée dans la classe Fenêtre.
	 */
	public void updateDuel(String affichage) {}

	/**
	 * Pattern Observer - Méthode permettant de quitter l'application.
	 */
	public void quitterApplication() {
		LOGGER.trace("Fin de l'application");
		System.exit(0);
	}

	/**
	 * Pattern Observer - Méthode permettant de revenir à la page d'accueil.
	 */
	public void acceuilObservateur() {
		jmParametres.setEnabled(true);
		jpContainer.removeAll();
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(imageJeu);
		jpContainer.revalidate();

		/* **************************************************************************************************************************
		 * ATTENTION : Il faut impérativement utiliser la méthode repaint() sinon des composants de l'ancien JPanel resteront visible
		 ****************************************************************************************************************************/
		jpContainer.repaint();
		LOGGER.trace("Retour à l'accueil");
	}

	/**
	 * Pattern Observer - Méthode non utilisée dans la classe Fenêtre.
	 */
	public void relancerPartie() {}

	/* **********************************************************
	 * Implémentation du pattern Observer pour le jeu Mastermind
	 ************************************************************/

	/**
	 * Pattern Observer - Méthode non utilisée dans la classe Fenêtre.
	 */
	public void updateMastermind(String reponse) {}

	/**
	 * Pattern Observer - Méthode permettant de quitter l'application.
	 */
	public void quitterApplicationMastermind() {
		LOGGER.trace("Fin de l'application");
		System.exit(0);
	}

	/**
	 * Pattern Observer - Méthode permettant de revenir à la page d'accueil.
	 */
	public void acceuilObservateurMastermind() {
		jmParametres.setEnabled(true);
		jpContainer.removeAll();
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(imageJeu);
		jpContainer.revalidate();

		/* **************************************************************************************************************************
		 * ATTENTION : Il faut impérativement utiliser la méthode repaint() sinon des composants de l'ancien JPanel resteront visible
		 ****************************************************************************************************************************/
		jpContainer.repaint();
		LOGGER.trace("Retour à l'accueil");
	}

	/**
	 * Pattern Observer - Méthode non utilisée dans la classe Fenêtre.
	 */
	public void relancerPartieMastermind() {}
}
