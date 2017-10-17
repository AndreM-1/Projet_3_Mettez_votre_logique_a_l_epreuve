package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoiteDialogueParametrage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jpContainer=new JPanel(),jpContainerRecherchePlusMoins=new JPanel(), jpContainerMastermind=new JPanel(),
			jpContainerModeDeveloppeur=new JPanel(),jpContainerButton=new JPanel();
	private JLabel jlNbEssaisRecherchePlusMoins=new JLabel("Nombre d'essais :"),jlNbCasesRecherchePlusMoins=new JLabel("Nombre de cases :"),
			jlNbEssaisMastermind=new JLabel("Nombre d'essais :"),jlNbCasesMastermind=new JLabel("Nombre de cases :"),
			jlModeDeveloppeur=new JLabel("Mode développeur");
	private JComboBox jcbNbEssaisRecherchePlusMoins=new JComboBox(),jcbNbCasesRecherchePlusMoins=new JComboBox(),
			jcbNbEssaisMastermind=new JComboBox(),jcbNbCasesMastermind=new JComboBox();
	private JCheckBox jcbModeDeveloppeur=new JCheckBox();
	private JButton jbOK=new JButton("OK"), jbAnnuler=new JButton("Annuler");
	private Properties prop;
	private InputStream input;
	private String strNbEssais,strNbCases;
	private String [] tabNbEssais, tabNbCases;
	private int choixNombreEssaisFichierConfig=4, choixNombreCasesFichierConfig=7;
	private int nbreCasesRecherchePlusMoins, nbEssaisRecherchePlusMoins,nbreCasesMastermind,nbEssaisMastermind;
			

	public BoiteDialogueParametrage(JFrame parent, String title, boolean modal, int nbEssaisRecherchePlusMoins,
			int nbreCasesRecherchePlusMoins,int nbEssaisMastermind,int nbreCasesMastermind){
		super(parent,title,modal);
		this.nbEssaisRecherchePlusMoins=nbEssaisRecherchePlusMoins;
		this.nbreCasesRecherchePlusMoins=nbreCasesRecherchePlusMoins;
		this.nbEssaisMastermind=nbEssaisMastermind;
		this.nbreCasesMastermind=nbreCasesMastermind;
		this.setSize(450,290);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.showDialog(true);
	}
	
	private void initComponent() {
		
		//Mise en place de l'interface graphique
		jpContainerRecherchePlusMoins.setPreferredSize(new Dimension(440,80));
		jpContainerRecherchePlusMoins.setBorder(BorderFactory.createTitledBorder("RecherchePlusMoins"));
		jpContainerRecherchePlusMoins.add(jlNbEssaisRecherchePlusMoins);
		jpContainerRecherchePlusMoins.add(jcbNbEssaisRecherchePlusMoins);
		jpContainerRecherchePlusMoins.add(jlNbCasesRecherchePlusMoins);
		jpContainerRecherchePlusMoins.add(jcbNbCasesRecherchePlusMoins);
		
		jpContainerMastermind.setPreferredSize(new Dimension(440,80));
		jpContainerMastermind.setBorder(BorderFactory.createTitledBorder("Mastermind"));
		jpContainerMastermind.add(jlNbEssaisMastermind);
		jpContainerMastermind.add(jcbNbEssaisMastermind);
		jpContainerMastermind.add(jlNbCasesMastermind);
		jpContainerMastermind.add(jcbNbCasesMastermind);
		
		jpContainerModeDeveloppeur.setPreferredSize(new Dimension(450,40));
		jpContainerModeDeveloppeur.add(jlModeDeveloppeur);
		jpContainerModeDeveloppeur.add(jcbModeDeveloppeur);
		
		jpContainerButton.setPreferredSize(new Dimension(450,40));
		jpContainerButton.add(jbOK);
		jpContainerButton.add(jbAnnuler);
		
		jpContainer.setPreferredSize(new Dimension(450,300));
		jpContainer.add(jpContainerRecherchePlusMoins);
		jpContainer.add(jpContainerMastermind);
		jpContainer.add(jpContainerModeDeveloppeur);
		jpContainer.add(jpContainerButton);
		
		this.setContentPane(jpContainer);
		
		//Import des données du fichier config.properties

		prop=new Properties();
        input=null;
		
		
		try {
			input=new FileInputStream("src/fr/anmonnier/oc_javaee_p3/main/resources/config.properties");
			prop.load(input);
			strNbEssais=prop.getProperty("param.nbEssais");
			tabNbEssais=strNbEssais.split(",");
			strNbCases=prop.getProperty("param.nbreCases");
			tabNbCases=strNbCases.split(",");
			for (int i=0;i<choixNombreEssaisFichierConfig;i++) {
				jcbNbEssaisRecherchePlusMoins.addItem(tabNbEssais[i]);
				jcbNbEssaisMastermind.addItem(tabNbEssais[i]);
			}
			
			for (int i=0;i<choixNombreCasesFichierConfig;i++) {
				jcbNbCasesRecherchePlusMoins.addItem(tabNbCases[i]);
				jcbNbCasesMastermind.addItem(tabNbCases[i]);
			}
			
			System.out.println(prop.getProperty("param.modeDeveloppeur"));
			
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
		
		jcbNbEssaisRecherchePlusMoins.setSelectedItem(String.valueOf(nbEssaisRecherchePlusMoins));
		jcbNbCasesRecherchePlusMoins.setSelectedItem(String.valueOf(nbreCasesRecherchePlusMoins));
		
		jcbNbEssaisMastermind.setSelectedItem(String.valueOf(nbEssaisMastermind));
		jcbNbCasesMastermind.setSelectedItem(String.valueOf(nbreCasesMastermind));
	
		
		//Définition des listeners
		jbOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Traitement pour le jeu RecherchePlusMoins
				nbreCasesRecherchePlusMoins=Integer.valueOf((String)jcbNbCasesRecherchePlusMoins.getSelectedItem());
				nbEssaisRecherchePlusMoins=Integer.valueOf((String)jcbNbEssaisRecherchePlusMoins.getSelectedItem());

				//Traitement pour le jeu Mastermind
				nbreCasesMastermind=Integer.valueOf((String)jcbNbCasesMastermind.getSelectedItem());
				nbEssaisMastermind=Integer.valueOf((String)jcbNbEssaisMastermind.getSelectedItem());
		
				showDialog(false);
			}
			
		});
		
		jbAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDialog(false);
			}
			
		});
		
	}
	
	private void showDialog(boolean affichage) {
		this.setVisible(affichage);
	}
	
	//Accesseurs
	public int getNbreCasesRecherchePlusMoins() {
		return nbreCasesRecherchePlusMoins;
	}
	
	public int getNbEssaisRecherchePlusMoins() {
		return nbEssaisRecherchePlusMoins;
	}
	
	public int getNbreCasesMastermind() {
		return nbreCasesMastermind;
	}
	
	public int getNbEssaisMastermind() {
		return nbEssaisMastermind;
	}
	
}
