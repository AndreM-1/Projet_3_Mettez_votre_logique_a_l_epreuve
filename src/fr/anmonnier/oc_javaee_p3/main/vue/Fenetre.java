package fr.anmonnier.oc_javaee_p3.main.vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
			jmParametres=new JMenu("Paramètres"),jmNbCases=new JMenu("Nombre de cases/couleurs"),
			jmNbEssais=new JMenu("Nombre d'essais");
	private JMenuItem jmiModeChallenger=new JMenuItem("Mode Challenger"), jmiModeDefenseur=new JMenuItem("Mode Défenseur"),
			jmiModeDuel=new JMenuItem("Mode Duel"),jmi2ModeChallenger=new JMenuItem("Mode Challenger"), jmi2ModeDefenseur=new JMenuItem("Mode Défenseur"),
			jmi2ModeDuel=new JMenuItem("Mode Duel"),jmiQuitter = new JMenuItem("Quitter"), 
			jmiJeuRecherchePlusMoins = new JMenuItem("Recherche +/-"),jmiMastermind = new JMenuItem("Mastermind");
	private JRadioButtonMenuItem[] jrbmiNbcases=new JRadioButtonMenuItem[7],jrbmiNbEssais=new JRadioButtonMenuItem[3];
	private ButtonGroup bgNbCases=new ButtonGroup(),bgNbEssais=new ButtonGroup();
	private ModeleDonnees model;

	//Valeurs nominales
	private int nbreCases=4, nbEssais=10;

	public Fenetre(ModeleDonnees model) {
		this.setTitle("Mettez votre logique à l'épreuve");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		imageJeu.setPreferredSize(new Dimension(452,480));
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

		for(int i=4;i<=10;i++) {
			jrbmiNbcases[i-4]=new JRadioButtonMenuItem(String.valueOf(i));
			bgNbCases.add(jrbmiNbcases[i-4]);
			jmNbCases.add(jrbmiNbcases[i-4]);
		}
		jrbmiNbcases[0].setSelected(true);

		for (int i=0;i<3;i++) {
			jrbmiNbEssais[i]=new JRadioButtonMenuItem(String.valueOf(10+5*i));
			bgNbEssais.add(jrbmiNbEssais[i]);
			jmNbEssais.add(jrbmiNbEssais[i]);
		}

		jrbmiNbEssais[0].setSelected(true);
		jmFichier.add(jmJeuRecherchePlusMoins);
		jmFichier.add(jmJeuMastermind);
		jmFichier.addSeparator();
		jmFichier.add(jmiQuitter);
		jmParametres.add(jmNbCases);
		jmParametres.add(jmNbEssais);
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
				jpContainer.add(new RecherchePlusMoins(nbreCases,nbEssais,model));
				jpContainer.revalidate();
				/*********************************************************************************************************
				 *Ne pas oublier de réinitialiser le modèle dans le cas où on revient plusieurs fois à la page d'acceuil
				 *********************************************************************************************************/
				initModel();

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

	public void quitterApplication() {
		System.exit(0);
	}

	public void acceuilObservateur() {
		jpContainer.removeAll();
		jpContainer.setBackground(Color.WHITE);
		
		/**********************************************************************
		 * Obligation d'instancier un nouveau JLabel, sinon ça ne marche pas.
		 *********************************************************************/
		this.imageJeu=new JLabel((new ImageIcon("src/fr/anmonnier/oc_javaee_p3/main/resources/MastermindFormatMoyen.jpg")));
		jpContainer.add(imageJeu);
		jpContainer.revalidate();
	}

	public void relancerPartie() {}

}
