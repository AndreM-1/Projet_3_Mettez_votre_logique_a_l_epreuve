package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Classe relative à la boite de dialogue disponible en fin de partie et qui permet au joueur soit de
 * relancer le même jeu, de retourner à la page d'accueil ou de quitter l'application.
 *  @author André Monnier
 */
public class BoiteDialogueFinDePartie extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Choix du joueur en fin de partie : Le joueur a le choix entre relancer le même jeu,
	 * retourner à la page d'accueil ou quitter l'application.
	 */
	private String choixFinDePartie= "";

	/**
	 * JRadioButton correspondant à un choix.
	 */
	private JRadioButton jrbChoixRejouer=new JRadioButton("Rejouer au même jeu"),
			jrbRetourPageAccueil=new JRadioButton("Lancer un autre jeu    "),
			jrbQuitterApplication=new JRadioButton("Quitter l'application    ");

	/**
	 * Button Group permettant de regrouper les JRadioButton.
	 */
	private ButtonGroup bgChoix=new ButtonGroup();

	/**
	 * JLabel de type informatif.
	 */
	private JLabel jlMessageInformatif = new JLabel("Vous avez le choix entre :");

	/**
	 * JPanel principal de la classe contenant les composants relatifs 
	 * au message informatif et aux choix possibles en fin de partie.
	 */
	private JPanel jpContainer=new JPanel();

	/**
	 * JPanel contenant le JButton de validation.
	 */
	private JPanel jpButton=new JPanel();

	/**
	 * JButton de validation.
	 */
	private JButton jbOk=new JButton("OK");

	/**
	 * Constructeur de la classe BoiteDialogueFinDePartie.
	 * @param parent Composant parent. Cette variable sera null.
	 * @param title Titre de la boite de dialogue.
	 * @param modal Modalité de la boite de dialogue. On optera pour une boite de dialogue modale.
	 */
	public BoiteDialogueFinDePartie(JFrame parent, String title, boolean modal) {
		super(parent,title,modal);
		this.setSize(200,170);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.showDialog(true);
	}

	/**
	 * Méthode permettant de réaliser l'interface graphique de la boite de dialogue.
	 */
	private void initComponent() {

		bgChoix.add(jrbChoixRejouer);
		bgChoix.add(jrbRetourPageAccueil);
		bgChoix.add(jrbQuitterApplication);

		jrbChoixRejouer.setSelected(true);

		jpContainer.add(jlMessageInformatif);
		jpContainer.add(jrbChoixRejouer);
		jpContainer.add(jrbRetourPageAccueil);
		jpContainer.add(jrbQuitterApplication);
		jpButton.add(jbOk);


		this.getContentPane().add(jpContainer,BorderLayout.CENTER);
		this.getContentPane().add(jpButton,BorderLayout.SOUTH);

		//Définition des listeners
		jbOk.addActionListener(new ActionListener() {
			String strChoix="";
			public void actionPerformed(ActionEvent event) {
				if(jrbChoixRejouer.isSelected()) {
					strChoix=jrbChoixRejouer.getText().trim();
				}
				else if(jrbRetourPageAccueil.isSelected()) {
					strChoix=jrbRetourPageAccueil.getText().trim();
				}
				else {
					strChoix=jrbQuitterApplication.getText().trim();
				}
				setChoixFinDePartie(strChoix);
				showDialog(false);	
			}	
		});
	}

	/**
	 * Méthode permettant de rendre visible la boite de dialogue.
	 * @param affichage Variable de type booléenne permettant d'indiquer
	 * si la boite de dialogue doit être visible ou non.
	 */
	private void showDialog(boolean affichage) {
		this.setVisible(affichage);
	}

	/**
	 * Mutateur permettant de modifier le choix effectué par le joueur en fin de partie.
	 * @param choixFinDePartie Choix effectué par le joueur en fin de partie.
	 */
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartie=choixFinDePartie;
	}

	/**
	 * Accesseur permettant de récupérer le choix du joueur en fin de partie.
	 * @return Le choix du joueur en fin de partie
	 */
	public String getChoixFinDePartie() {
		return this.choixFinDePartie;
	}
}