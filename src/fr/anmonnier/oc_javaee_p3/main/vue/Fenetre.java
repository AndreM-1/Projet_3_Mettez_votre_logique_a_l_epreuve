package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel(), sousPanel1 = new JPanel(), sousPanel2 = new JPanel();
	private JLabel choixJeu = new JLabel("Choisissez le jeu auquel participer :");
	private JButton jeuMastermind = new JButton("Mastermind"), jeuPlusMoins = new JButton("Recherche +/-");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier"), aPropos = new JMenu("À propos");
	private JMenuItem jeu1 = new JMenuItem("Recherche +/-"), jeu2 = new JMenuItem("Mastermind"),
			quitter = new JMenuItem("Quitter"), reglesJeux = new JMenuItem("Règles des Jeux");
	private Font policeTitre = new Font("Freestyle Script", Font.BOLD, 40);

	public Fenetre() {
		this.setTitle("Mettez votre logique à l'épreuve");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.initComponent();
		this.initMenu();
		this.setContentPane(container);
		this.setVisible(true);
	}

	public void initComponent() {
		choixJeu.setFont(policeTitre);
		choixJeu.setPreferredSize(new Dimension(880, 60));
		choixJeu.setHorizontalAlignment(JLabel.CENTER);
		choixJeu.setForeground(Color.WHITE);
		jeuPlusMoins.setFont(policeTitre);
		jeuPlusMoins.setPreferredSize(new Dimension(220, 40));
		jeuPlusMoins.setBackground(new Color(139, 69, 19));
		jeuPlusMoins.setForeground(Color.WHITE);
		jeuPlusMoins.setFocusable(false);
		jeuMastermind.setFont(policeTitre);
		jeuMastermind.setPreferredSize(new Dimension(220, 40));
		jeuMastermind.setBackground(new Color(139, 69, 19));
		jeuMastermind.setForeground(Color.WHITE);
		jeuMastermind.setFocusable(false);
		sousPanel1.setBackground(Color.BLACK);
		sousPanel1.setPreferredSize(new Dimension(880, 60));
		sousPanel1.add(jeuPlusMoins);
		sousPanel2.setBackground(Color.BLACK);
		sousPanel2.setPreferredSize(new Dimension(880, 60));
		sousPanel2.add(jeuMastermind);
		container.setBackground(Color.BLACK);
		container.setPreferredSize(new Dimension(900, 600));
		container.add(choixJeu);
		container.add(sousPanel1);
		container.add(sousPanel2);

		// Définition des listeners
		jeuPlusMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.removeAll();
				container.setBackground(Color.WHITE);
				container.add(new JeuRecherchePlusMoins());
				container.revalidate();
			}
		});
	}

	public void initMenu() {

		// Définition des mnémoniques
		fichier.setMnemonic('F');
		aPropos.setMnemonic('o');

		// Définition des accélérateurs
		jeu1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		jeu2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

		// Définition des listeners
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		jeu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.removeAll();
				container.setBackground(Color.WHITE);
				container.add(new JeuRecherchePlusMoins());
				container.revalidate();
			}
		});

		// Construction du menu
		fichier.add(jeu1);
		fichier.add(jeu2);
		fichier.addSeparator();
		fichier.add(quitter);
		aPropos.add(reglesJeux);
		menuBar.add(fichier);
		menuBar.add(aPropos);
		this.setJMenuBar(menuBar);
	}

}
