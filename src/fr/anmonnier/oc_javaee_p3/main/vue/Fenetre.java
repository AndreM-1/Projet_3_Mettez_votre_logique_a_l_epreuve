package fr.anmonnier.oc_javaee_p3.main.vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
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
import org.apache.logging.log4j.core.LoggerContext;

import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonnees;
import fr.anmonnier.oc_javaee_p3.main.model.ModeleDonneesMastermind;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;
import fr.anmonnier.oc_javaee_p3.main.observer.ObservateurMastermind;

public class Fenetre extends JFrame implements Observateur,ObservateurMastermind {

	private static final long serialVersionUID = 1L;
	private JPanel jpContainer = new JPanel();
	private JLabel imageJeu = new JLabel(new ImageIcon("resources/Mastermind.jpg"));
	private JMenuBar jmbMenuBar = new JMenuBar();
	private JMenu jmFichier = new JMenu("Fichier"), jmInstructions = new JMenu("Instructions");
	private JMenu jmJeuRecherchePlusMoins = new JMenu("Recherche +/-"), jmJeuMastermind = new JMenu("Mastermind"),
			jmParametres=new JMenu("Param�tres");
	private JMenuItem jmiModeChallenger=new JMenuItem("Mode Challenger"), jmiModeDefenseur=new JMenuItem("Mode D�fenseur"),
			jmiModeDuel=new JMenuItem("Mode Duel"),jmi2ModeChallenger=new JMenuItem("Mode Challenger"), 
			jmi2ModeDefenseur=new JMenuItem("Mode D�fenseur"),jmi2ModeDuel=new JMenuItem("Mode Duel"),
			jmiQuitter = new JMenuItem("Quitter"), jmiJeuRecherchePlusMoins = new JMenuItem("Recherche +/-"),
			jmiMastermind = new JMenuItem("Mastermind"),jmiParametres=new JMenuItem("Param�tres");
	private ModeleDonnees model;
	private ModeleDonneesMastermind modelMastermind;
	private RecherchePlusMoinsModeChallenger jpRecherchePlusMoinsModeChallenger;
	private RecherchePlusMoinsModeDefenseur jpRecherchePlusMoinsModeDefenseur;
	private RecherchePlusMoinsModeDuel jpRecherchePlusMoinsModeDuel;
	private MastermindModeChallenger jpMastermindModeChallenger;
	private MastermindModeDefenseur jpMastermindModeDefenseur;
	private MastermindModeDuel jpMastermindModeDuel;
	private BoiteDialogueParametrage jdParametrage;
	private InputStream input;
	private Properties prop;
	private Logger logger=(Logger)LogManager.getLogger(Fenetre.class);
	private LoggerContext context=(org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
	private File file = new File("resources/log4j2.xml");  

	//Valeurs nominales
	private int nbreCasesRecherchePlusMoins=4, nbEssaisRecherchePlusMoins=10,nbreCasesMastermind=4,nbEssaisMastermind=10,
			nbCouleursUtilisablesMastermind=6;
	private boolean modeDeveloppeurActive=false;

	public Fenetre(ModeleDonnees model,ModeleDonneesMastermind modelMastermind) {      
		context.setConfigLocation(file.toURI());
		logger.trace("Instanciation de la fen�tre principale");
		this.setTitle("Mettez votre logique � l'�preuve");
		this.setSize(1000, 740);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("resources/MastermindFormatIcone.png").getImage());
		imageJeu.setPreferredSize(new Dimension(600,637));
		jpContainer.setPreferredSize(new Dimension(1000,800));
		jpContainer.add(imageJeu);
		jpContainer.setBackground(Color.WHITE);
		this.setContentPane(jpContainer);
		this.model=model;
		this.model.addObservateur(this);
		this.modelMastermind=modelMastermind;
		this.modelMastermind.addObservateurMastermind(this);
		logger.trace("Initialisation des mod�les de donn�es");

		//On r�cup�re les donn�es enregistr�es dans le fichier config.properties
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


	public void initMenu() {
		logger.trace("Initialisation du menu");

		// D�finition des mn�moniques
		jmFichier.setMnemonic('F');
		jmInstructions.setMnemonic('I');
		jmParametres.setMnemonic('P');

		// D�finition des acc�l�rateurs
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

		// D�finition des listeners
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

				/**
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut imp�rativement placer
				 * cette instruction apr�s le .add
				 */
				jpRecherchePlusMoinsModeChallenger.getJftfPropositionJoueur().requestFocusInWindow();

				/*********************************************************************************************************
				 *Ne pas oublier de r�initialiser le mod�le dans le cas o� on revient plusieurs fois � la page d'acceuil
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

				/**
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut imp�rativement placer
				 * cette instruction apr�s le .add
				 */
				jpRecherchePlusMoinsModeDefenseur.getJftfCombinaisonSecreteJoueur().requestFocusInWindow();

				/*********************************************************************************************************
				 *Ne pas oublier de r�initialiser le mod�le dans le cas o� on revient plusieurs fois � la page d'acceuil
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

				/**
				 * Cette instruction permet de placer le curseur sur le JFormattedTextField voulu. Il faut imp�rativement placer
				 * cette instruction apr�s le .add
				 */
				jpRecherchePlusMoinsModeDuel.getJftfCombinaisonSecreteJoueur().requestFocusInWindow();

				/*********************************************************************************************************
				 *Ne pas oublier de r�initialiser le mod�le dans le cas o� on revient plusieurs fois � la page d'acceuil
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

				/*********************************************************************************************************
				 *Ne pas oublier de r�initialiser le mod�le dans le cas o� on revient plusieurs fois � la page d'acceuil
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

				/*********************************************************************************************************
				 *Ne pas oublier de r�initialiser le mod�le dans le cas o� on revient plusieurs fois � la page d'acceuil
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

				/*********************************************************************************************************
				 *Ne pas oublier de r�initialiser le mod�le dans le cas o� on revient plusieurs fois � la page d'acceuil
				 *********************************************************************************************************/
				initModel();

			}
		});


		jmiParametres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdParametrage=new BoiteDialogueParametrage(null,"Param�tres des Jeux",true,nbEssaisRecherchePlusMoins,
						nbreCasesRecherchePlusMoins,nbEssaisMastermind,nbreCasesMastermind,nbCouleursUtilisablesMastermind,
						modeDeveloppeurActive);
				nbEssaisRecherchePlusMoins=jdParametrage.getNbEssaisRecherchePlusMoins();
				nbreCasesRecherchePlusMoins=jdParametrage.getNbreCasesRecherchePlusMoins();
				nbEssaisMastermind=jdParametrage.getNbEssaisMastermind();
				nbreCasesMastermind=jdParametrage.getNbreCasesMastermind();		
				modeDeveloppeurActive=jdParametrage.getModeDeveloppeurActive();
				nbCouleursUtilisablesMastermind=jdParametrage.getNbCouleursUtilisablesMastermind();
				logger.debug("Menu Param�tres - Nb essais RecherchePlusMoins :"+nbEssaisRecherchePlusMoins);
				logger.debug("Menu Param�tres - Nb cases RecherchePlusMoins :"+nbreCasesRecherchePlusMoins);
				logger.debug("Menu Param�tres - Nb essais Mastermind :"+nbEssaisMastermind);
				logger.debug("Menu Param�tres - Nb cases Mastermind :"+nbreCasesMastermind);
				logger.debug("Menu Param�tres - Etat du mode d�veloppeur :"+modeDeveloppeurActive);
				logger.debug("Menu Param�tres - Etat du mode d�veloppeur :"+nbCouleursUtilisablesMastermind);
			}
		});

		jmiJeuRecherchePlusMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strInstructionsJeuRecherchePlusMoins=
						"Le but de ce jeu est de d�couvrir la combinaison � x chiffres de l'adversaire (le d�fenseur)."
								+ "\nPour ce faire, l'attaquant fait une proposition. Le d�fenseur indique pour chaque chiffre de "
								+ "\nla combinaison propos�e si le chiffre de sa combinaison est plus grand (+), plus petit (-) "
								+ "\nou si c'est le bon chiffre (=). Un mode duel o� attaquant et d�fenseur jouent tour � tour "
								+ "\nest �galement disponible.";
				JOptionPane.showMessageDialog(null, strInstructionsJeuRecherchePlusMoins, "Instructions Recherche +/-", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		jmiMastermind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strInstructionsJeuMastermind=
						"Le but de ce jeu est de d�couvrir la combinaison � x couleurs de l'adversaire (le d�fenseur)."
								+ "\nPour ce faire, l'attaquant fait une proposition. Le d�fenseur indique pour chaque proposition"
								+ "\nle nombre de couleurs de la proposition qui apparaissent � la bonne place (� l'aide de pions rouges)"
								+ "\net � la mauvaise place (� l'aide de pions blancs) dans la combinaison secr�te.Un mode duel o� "
								+ "\nattaquant et d�fenseur jouent tour � tour est �galement disponible."; 				
				JOptionPane.showMessageDialog(null, strInstructionsJeuMastermind, "Instructions Mastermind", JOptionPane.INFORMATION_MESSAGE);
			}
		});


		jmiQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.trace("Fin de l'application");
				LogManager.shutdown();
				System.exit(0);
			}
		});
	}

	private void initModel() {
		this.model=new ModeleDonnees();
		this.model.addObservateur(this);	
		this.modelMastermind=new ModeleDonneesMastermind();
		this.modelMastermind.addObservateurMastermind(this);
		logger.trace("R�initialisation des mod�les de donn�es");
	}

	//Impl�mentation du pattern Observer pour le jeu RecherchePlusMoins
	public void update(String propositionJoueur, String reponse) {}
	public void updateDuel(String affichage) {}

	public void quitterApplication() {
		logger.trace("Fin de l'application");
		LogManager.shutdown();
		System.exit(0);
	}

	public void acceuilObservateur() {
		jmParametres.setEnabled(true);
		jpContainer.removeAll();
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(imageJeu);
		jpContainer.revalidate();

		/****************************************************************************************************************************
		 * ATTENTION : Il faut imp�rativement utiliser la m�thode repaint() sinon des composants de l'ancien JPanel resteront visible
		 ****************************************************************************************************************************/
		jpContainer.repaint();
		logger.trace("Retour � l'accueil");
	}

	public void relancerPartie() {}

	//Impl�mentation du pattern Observer pour le jeu Mastermind
	public void updateMastermind(String reponse) {}


	public void quitterApplicationMastermind() {
		logger.trace("Fin de l'application");
		LogManager.shutdown();
		System.exit(0);
	}

	public void acceuilObservateurMastermind() {
		jmParametres.setEnabled(true);
		jpContainer.removeAll();
		jpContainer.setBackground(Color.WHITE);
		jpContainer.add(imageJeu);
		jpContainer.revalidate();

		/****************************************************************************************************************************
		 * ATTENTION : Il faut imp�rativement utiliser la m�thode repaint() sinon des composants de l'ancien JPanel resteront visible
		 ****************************************************************************************************************************/
		jpContainer.repaint();
		logger.trace("Retour � l'accueil");
	}

	public void relancerPartieMastermind() {}
}
