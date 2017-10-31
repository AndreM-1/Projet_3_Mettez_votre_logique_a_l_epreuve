package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			jlNbCouleursUtilisablesMastermind=new JLabel("Nombre de couleurs utilisables :");
	private JComboBox jcbNbEssaisRecherchePlusMoins=new JComboBox(),jcbNbCasesRecherchePlusMoins=new JComboBox(),
			jcbNbEssaisMastermind=new JComboBox(),jcbNbCasesMastermind=new JComboBox(),
			jcbNbCouleursUtilisablesMastermind=new JComboBox();
	private JCheckBox jcbModeDeveloppeur=new JCheckBox("Mode développeur");
	private JButton jbOK=new JButton("OK"), jbAnnuler=new JButton("Annuler");
	private Properties prop;
	private InputStream input;
	private OutputStream output;
	private String strNbEssaisRecherchePlusMoins="",strNbCasesRecherchePlusMoins="",strNbEssaisMastermind="",
			strNbCasesMastermind="",strNbCouleursUtilisablesMastermind="";
	private String [] tabNbEssaisRecherchePlusMoins, tabNbCasesRecherchePlusMoins,tabNbEssaisMastermind, 
			tabNbCasesMastermind,tabNbCouleursUtilisablesMastermind;
	private int choixNombreEssaisFichierConfigRecherchePlusMoins=4, choixNombreCasesFichierConfigRecherchePlusMoins=7,
			choixNombreCouleursUtilisablesFichierConfig=7;
	private int nbreCasesRecherchePlusMoins, nbEssaisRecherchePlusMoins,nbreCasesMastermind,nbEssaisMastermind,
			nbCouleursUtilisablesMastermind;
	private boolean modeDeveloppeurActive;


	public BoiteDialogueParametrage(JFrame parent, String title, boolean modal, int nbEssaisRecherchePlusMoins,
			int nbreCasesRecherchePlusMoins,int nbEssaisMastermind,int nbreCasesMastermind,int nbCouleursUtilisablesMastermind,
			boolean modeDeveloppeurActive){
		super(parent,title,modal);
		this.nbEssaisRecherchePlusMoins=nbEssaisRecherchePlusMoins;
		this.nbreCasesRecherchePlusMoins=nbreCasesRecherchePlusMoins;
		this.nbEssaisMastermind=nbEssaisMastermind;
		this.nbreCasesMastermind=nbreCasesMastermind;
		this.nbCouleursUtilisablesMastermind=nbCouleursUtilisablesMastermind;
		this.modeDeveloppeurActive=modeDeveloppeurActive;
		this.setSize(600,290);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.showDialog(true);
	}

	private void initComponent() {

		//Mise en place de l'interface graphique
		jpContainerRecherchePlusMoins.setPreferredSize(new Dimension(590,80));
		jpContainerRecherchePlusMoins.setBorder(BorderFactory.createTitledBorder("RecherchePlusMoins"));
		jpContainerRecherchePlusMoins.add(jlNbEssaisRecherchePlusMoins);
		jpContainerRecherchePlusMoins.add(jcbNbEssaisRecherchePlusMoins);
		jpContainerRecherchePlusMoins.add(jlNbCasesRecherchePlusMoins);
		jpContainerRecherchePlusMoins.add(jcbNbCasesRecherchePlusMoins);

		jpContainerMastermind.setPreferredSize(new Dimension(590,80));
		jpContainerMastermind.setBorder(BorderFactory.createTitledBorder("Mastermind"));
		jpContainerMastermind.add(jlNbEssaisMastermind);
		jpContainerMastermind.add(jcbNbEssaisMastermind);
		jpContainerMastermind.add(jlNbCasesMastermind);
		jpContainerMastermind.add(jcbNbCasesMastermind);
		jpContainerMastermind.add(jlNbCouleursUtilisablesMastermind);
		jpContainerMastermind.add(jcbNbCouleursUtilisablesMastermind);

		jpContainerModeDeveloppeur.setPreferredSize(new Dimension(600,40));
		jpContainerModeDeveloppeur.add(jcbModeDeveloppeur);

		jpContainerButton.setPreferredSize(new Dimension(600,40));
		jpContainerButton.add(jbOK);
		jpContainerButton.add(jbAnnuler);

		jpContainer.setPreferredSize(new Dimension(600,300));
		jpContainer.add(jpContainerRecherchePlusMoins);
		jpContainer.add(jpContainerMastermind);
		jpContainer.add(jpContainerModeDeveloppeur);
		jpContainer.add(jpContainerButton);

		this.setContentPane(jpContainer);

		//Import des données du fichier config.properties
		prop=new Properties();
		input=null;

		try {
			input=new FileInputStream("resources/config.properties");
			prop.load(input);
			strNbEssaisRecherchePlusMoins=prop.getProperty("param.nbEssaisRecherchePlusMoins");
			tabNbEssaisRecherchePlusMoins=strNbEssaisRecherchePlusMoins.split(",");
			strNbCasesRecherchePlusMoins=prop.getProperty("param.nbreCasesRecherchePlusMoins");
			tabNbCasesRecherchePlusMoins=strNbCasesRecherchePlusMoins.split(",");

			strNbEssaisMastermind=prop.getProperty("param.nbEssaisMastermind");
			tabNbEssaisMastermind=strNbEssaisMastermind.split(",");
			strNbCasesMastermind=prop.getProperty("param.nbreCasesMastermind");
			tabNbCasesMastermind=strNbCasesMastermind.split(",");
			strNbCouleursUtilisablesMastermind=prop.getProperty("param.nbCouleursUtilisablesMastermind");
			tabNbCouleursUtilisablesMastermind=strNbCouleursUtilisablesMastermind.split(",");
			
			for (int i=0;i<choixNombreEssaisFichierConfigRecherchePlusMoins;i++) {
				jcbNbEssaisRecherchePlusMoins.addItem(tabNbEssaisRecherchePlusMoins[i]);
				if(i<choixNombreEssaisFichierConfigRecherchePlusMoins-1)
					jcbNbEssaisMastermind.addItem(tabNbEssaisMastermind[i]);
			}

			for (int i=0;i<choixNombreCasesFichierConfigRecherchePlusMoins;i++) {
				jcbNbCasesRecherchePlusMoins.addItem(tabNbCasesRecherchePlusMoins[i]);
				if(i<3)
					jcbNbCasesMastermind.addItem(tabNbCasesMastermind[i]);
			}
			
			for (int i=0;i<choixNombreCouleursUtilisablesFichierConfig;i++) {
				jcbNbCouleursUtilisablesMastermind.addItem(tabNbCouleursUtilisablesMastermind[i]);
			}

			jcbNbEssaisRecherchePlusMoins.setSelectedItem(prop.getProperty("param.nbEssaisActifRecherchePlusMoins"));
			jcbNbCasesRecherchePlusMoins.setSelectedItem(prop.getProperty("param.nbreCasesActifRecherchePlusMoins"));	
			jcbNbEssaisMastermind.setSelectedItem(prop.getProperty("param.nbEssaisActifMastermind"));
			jcbNbCasesMastermind.setSelectedItem(prop.getProperty("param.nbreCasesActifMastermind"));
			jcbNbCouleursUtilisablesMastermind.setSelectedItem(prop.getProperty("param.nbCouleursUtilisablesActifMastermind"));

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


		if(modeDeveloppeurActive==false)
			jcbModeDeveloppeur.setSelected(false);
		else
			jcbModeDeveloppeur.setSelected(true);

		//Définition des listeners

		//Lors de la validation, on enregistre les paramètres du joueur dans le fichier config.properties
		jbOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				prop=new Properties();
				input=null;
				output=null;

				try {
					input=new FileInputStream("resources/config.properties");
					prop.load(input);

					//Traitement pour le jeu RecherchePlusMoins
					nbreCasesRecherchePlusMoins=Integer.valueOf((String)jcbNbCasesRecherchePlusMoins.getSelectedItem());
					nbEssaisRecherchePlusMoins=Integer.valueOf((String)jcbNbEssaisRecherchePlusMoins.getSelectedItem());
					prop.setProperty("param.nbreCasesActifRecherchePlusMoins", (String)jcbNbCasesRecherchePlusMoins.getSelectedItem());
					prop.setProperty("param.nbEssaisActifRecherchePlusMoins", (String)jcbNbEssaisRecherchePlusMoins.getSelectedItem());

					//Traitement pour le jeu Mastermind
					nbreCasesMastermind=Integer.valueOf((String)jcbNbCasesMastermind.getSelectedItem());
					nbEssaisMastermind=Integer.valueOf((String)jcbNbEssaisMastermind.getSelectedItem());
					nbCouleursUtilisablesMastermind=Integer.valueOf((String)jcbNbCouleursUtilisablesMastermind.getSelectedItem());
					
					prop.setProperty("param.nbreCasesActifMastermind", (String)jcbNbCasesMastermind.getSelectedItem());
					prop.setProperty("param.nbEssaisActifMastermind", (String)jcbNbEssaisMastermind.getSelectedItem());
					prop.setProperty("param.nbCouleursUtilisablesActifMastermind",(String)jcbNbCouleursUtilisablesMastermind.getSelectedItem());
					output=new FileOutputStream("resources/config.properties");
					prop.store(output, "Fichier de configuration config.properties");

				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					if (input != null) {
						try {
							output.close();
							input.close();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					}

				}

				//Traitement pour l'option Mode Développeur
				if(jcbModeDeveloppeur.isSelected()) {
					modeDeveloppeurActive=true;
				}
				else
					modeDeveloppeurActive=false;

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
	
	public int getNbCouleursUtilisablesMastermind(){
		return nbCouleursUtilisablesMastermind;
	}

	public boolean getModeDeveloppeurActive() {
		return modeDeveloppeurActive;
	}

}
