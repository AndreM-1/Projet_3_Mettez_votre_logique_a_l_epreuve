package fr.anmonnier.oc_javaee_p3.main.model;

import javax.swing.table.AbstractTableModel;

/****************************************************************************************************
 * Classe relative au Modèle de données du tableau utilisé dans le cadre du jeu RecherchePlusMoins.
 *  @author André Monnier
 ****************************************************************************************************/
public class ModelTableau extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Données du tableau.
	 */
	private Object[][] data;

	/**
	 * Titre des colonnes du tableau.
	 */
	private String[] title;

	/**
	 * Constructeur de la classe ModelTableau.
	 * @param data Données du tableau.
	 * @param title Titre des colonnes du tableau.
	 */
	public ModelTableau(Object[][] data, String[] title) {
		this.data = data;
		this.title = title;
	}

	/**
	 * Méthode qui retourne le nombre de colonnes.
	 * @return Le nombre de colonnes.
	 */
	public int getColumnCount() {
		return this.title.length;
	}

	/**
	 * Méthode qui retourne le nombre de lignes.
	 * @return Le nombre de lignes.
	 */
	public int getRowCount() {
		return this.data.length;
	}

	/**
	 * Méthode qui retourne la valeur à l'emplacement spécifié.
	 * @param row Ligne.
	 * @param col Colonne.
	 * @return La valeur à l'emplacement spécifié.
	 */
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}

	/**
	 * Méthode qui retourne le titre de la colonne à l'indice spécifié.
	 * @param col Colonne.
	 * @return Le titre de la colonne à l'indice spécifié.
	 */
	public String getColumnName(int col) {
		return this.title[col];
	}

	/**
	 * Méthode qui définit la valeur à l'emplacement spécifié.
	 * @param value Valeur souhaitée.
	 * @param row Ligne.
	 * @param col Colonne.
	 */
	public void setValueAt(Object value, int row, int col) {
		this.data[row][col] = value;
	}

	/**
	 * Méthode permettant de rendre les cellules du tableau éditables ou non.
	 * @param row Ligne.
	 * @param col Colonne.
	 * @return False. Dans le cadre du jeu RecherchePlusMoins, les cellules du
	 * tableau ne seront pas éditables.
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}