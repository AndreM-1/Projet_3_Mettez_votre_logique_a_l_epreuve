package fr.anmonnier.oc_javaee_p3.main.vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import fr.anmonnier.oc_javaee_p3.main.model.Joueur;
import fr.anmonnier.oc_javaee_p3.main.observer.Observateur;

public class JeuRecherchePlusMoins extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ATTENTION A BIEN INSTANCIER L'OBJET OBSERVABLE DE CETTE MANIERE
	 */
	private Joueur joueurs=new Joueur();
	private JTextArea jtaTexte = new JTextArea();
	private int nbEssais=10;
	private Font policeTitre = new Font("Freestyle Script", Font.BOLD, 40);
	
	public JeuRecherchePlusMoins() {
		this.setPreferredSize(new Dimension(900, 600));
		this.setBackground(Color.WHITE);
		jtaTexte.setPreferredSize(new Dimension(880,600));
		jtaTexte.addKeyListener(new ClavierListener());
		joueurs.addObservateur(new Observateur() {
			public void update(String texte) {
				jtaTexte.setText(texte);
			}
		});
		
		joueurs.modeChallenger();
		this.add(jtaTexte);
	}
	
	class ClavierListener implements KeyListener{

		private String str="";
		public void keyPressed(KeyEvent arg0) {}
		public void keyTyped(KeyEvent event) {}
		
		public void keyReleased(KeyEvent event) {
			if(!isNumeric(event.getKeyChar())) {
				str=jtaTexte.getText();
				jtaTexte.setText(str.replace(String.valueOf(event.getKeyChar()), ""));
				
			}		
		}

		private boolean isNumeric(char carac) {
			try {
				Integer.parseInt(String.valueOf(carac));
			}
			catch(NumberFormatException e) {
				return false;
			}
			return true;
		}

	
		
	}
}
