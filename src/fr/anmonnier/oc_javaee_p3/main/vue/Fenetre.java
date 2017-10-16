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
			jmParametres=new JMenu("Param�tres"),jmNbCases=new JMenu("Nombre de cases/couleurs"),
			jmNbEssais=new JMenu("Nombre d'essais");
	private JMenuItem jmiModeChallenger=new JMenuItem("Mode Challenger"), jmiModeDefenseur=new JMenuItem("Mode D�fenseur"),
			jmiModeDuel=new JMenuItem("Mode Duel"),jmi2ModeChallenger=new JMenuItem("Mode Challenger"), jmi2ModeDefenseur=new JMenuItem("Mode D�fenseur"),
			jmi2ModeDuel=new JMenuItem("Mode Duel"),jmiQuitter = new JMenuItem("Quitter"), 
			jmiJeuRecherchePlusMoins = new JMenuItem("Recherche +/-"),jmiMastermind = new JMenuItem("Mastermind");
	private JRadioButtonMenuItem[] jrbmiNbcases=new JRadioButtonMenuItem[7],jrbmiNbEssais=new JRadioButtonMenuItem[4];
	private ButtonGroup bgNbCases=new ButtonGroup(),bgNbEssais=new ButtonGroup();
	private ModeleDonnees model;
	private RecherchePlusMoinsModeChallenger jpRecherchePlusMoinsModeChallenger;
	private RecherchePlusMoinsModeDefenseur jpRecherchePlusMoinsModeDefenseur;
	private RecherchePlusMoinsModeDuel jpRecherchePlusMoinsModeDuel;

	//Valeurs nominales
	private int nbreCases=4, nbEssais=10;

	public Fenetre(ModeleDonnees model) {
		this.setTitle("Mettez votre logique � l'�preuve");
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

		// D�finition des mn�moniques
		jmFichier.setMnemonic('F');
		jmInstructions.setMnemonic('I');
		jmParametres.setMnemonic('P');

		// D�finition des acc�l�rateurs
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

		for (int i=0;i<4;i++) {
			jrbmiNbEssais[i]=new JRadioButtonMenuItem(String.valueOf(5+5*i));
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


		// D�finition des listeners
		jmiModeChallenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpContainer.removeAll();
				jpContainer.setBackground(Color.WHITE);
				jpRecherchePlusMoinsModeChallenger=new RecherchePlusMoinsModeChallenger(nbreCases,nbEssais,model);
				jpContainer.add(jpRecherchePlusMoinsModeChallenger);
				jpContainer.revalidate();
				
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
				jpRecherchePlusMoinsModeDefenseur=new RecherchePlusMoinsModeDefenseur(nbreCases,nbEssais,model);
				jpContainer.add(jpRecherchePlusMoinsModeDefenseur);
				jpContainer.revalidate();
				
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
				jpRecherchePlusMoinsModeDuel=new RecherchePlusMoinsModeDuel(nbreCases,nbEssais,model);
				jpContainer.add(jpRecherchePlusMoinsModeDuel);
				jpContainer.revalidate();
				
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
		
		jmiJeuRecherchePlusMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strInstructionsJeuRecherchePlusMoins=
						"Le but de ce jeu est de d�couvrir la combinaison � x chiffres de l'adversaire (le d�fenseur)."
						+ "\nPour ce faire, l'attaquant fait une proposition. Le d�fenseur indique pour chaque chiffre de "
						+ "\nla combinaison propos�e si le chiffre de sa combinaison est plus grand (+), plus petit (-) "
						+ "\nou si c'est le bon chiffre (=). Un mode duel o� attaquant et d�fenseur jouent tour � tour "
						+ "\nest �galement disponible";
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

	//Impl�mentation du pattern Observer
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
		 * ATTENTION : Il faut imp�rativement utiliser la m�thode repaint() sinon des composants de l'ancien JPanel resteront visible
		 ****************************************************************************************************************************/
		jpContainer.repaint();
	}

	public void relancerPartie() {}
}
