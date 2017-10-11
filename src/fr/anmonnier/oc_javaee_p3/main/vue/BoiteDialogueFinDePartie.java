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

public class BoiteDialogueFinDePartie extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private String choixFinDePartie= "";
	private JRadioButton jrbChoixRejouer=new JRadioButton("Rejouer au même jeu"),
			jrbRetourPageAccueil=new JRadioButton("Lancer un autre jeu    "),
			jrbQuitterApplication=new JRadioButton("Quitter l'application    ");
	private ButtonGroup bgChoix=new ButtonGroup();
	private JLabel jlMessageInformatif = new JLabel("Vous avez le choix entre :");
	private JPanel jpContainer=new JPanel(),jpButton=new JPanel();
	private JButton jbOk=new JButton("OK");


	public BoiteDialogueFinDePartie(JFrame parent, String title, boolean modal) {
		super(parent,title,modal);
		this.setSize(200,170);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.showDialog(true);
		
	}

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
	
	private void showDialog(boolean affichage) {
		this.setVisible(affichage);
	}
	
	public void setChoixFinDePartie(String choixFinDePartie) {
		this.choixFinDePartie=choixFinDePartie;
	}
	
	public String getChoixFinDePartie() {
		return this.choixFinDePartie;
	}
	
	

}
