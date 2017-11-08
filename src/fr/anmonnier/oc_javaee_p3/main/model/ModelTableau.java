package fr.anmonnier.oc_javaee_p3.main.model;

import javax.swing.table.AbstractTableModel;

/****************************************************************************************************
 * Classe relative au Mod�le de donn�es du tableau utilis� dans le cadre du jeu RecherchePlusMoins.
 *  @author Andr� Monnier
 ****************************************************************************************************/
public class ModelTableau extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Donn�es du tableau.
	 */
	private Object[][] data;

	/**
	 * Titre des colonnes du tableau.
	 */
	private String[] title;

	/**
	 * Constructeur de la classe ModelTableau.
	 * @param data Donn�es du tableau.
	 * @param title Titre des colonnes du tableau.
	 */
	public ModelTableau(Object[][] data, String[] title) {
		this.data = data;
		this.title = title;
	}

	/**
	 * M�thode qui retourne le nombre de colonnes.
	 * @return Le nombre de colonnes.
	 */
	public int getColumnCount() {
		return this.title.length;
	}

	/**
	 * M�thode qui retourne le nombre de lignes.
	 * @return Le nombre de lignes.
	 */
	public int getRowCount() {
		return this.data.length;
	}

	/**
	 * M�thode qui retourne la valeur � l'emplacement sp�cifi�.
	 * @param row Ligne.
	 * @param col Colonne.
	 * @return La valeur � l'emplacement sp�cifi�.
	 */
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}

	/**
	 * M�thode qui retourne le titre de la colonne � l'indice sp�cifi�.
	 * @param col Colonne.
	 * @return Le titre de la colonne � l'indice sp�cifi�.
	 */
	public String getColumnName(int col) {
		return this.title[col];
	}

	/**
	 * M�thode qui d�finit la valeur � l'emplacement sp�cifi�.
	 * @param value Valeur souhait�e.
	 * @param row Ligne.
	 * @param col Colonne.
	 */
	public void setValueAt(Object value, int row, int col) {
		this.data[row][col] = value;
	}

	/**
	 * M�thode permettant de rendre les cellules du tableau �ditables ou non.
	 * @param row Ligne.
	 * @param col Colonne.
	 * @return False. Dans le cadre du jeu RecherchePlusMoins, les cellules du
	 * tableau ne seront pas �ditables.
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}