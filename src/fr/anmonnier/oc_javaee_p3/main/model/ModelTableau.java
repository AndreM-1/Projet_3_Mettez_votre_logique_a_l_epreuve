package fr.anmonnier.oc_javaee_p3.main.model;

import javax.swing.table.AbstractTableModel;

public class ModelTableau extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] data;
	private String[] title;

	// Constructeur
	public ModelTableau(Object[][] data, String[] title) {
		this.data = data;
		this.title = title;
	}

	// Retourne le nombre de colonnes
	public int getColumnCount() {
		return this.title.length;
	}

	// Retourne le nombre de lignes
	public int getRowCount() {
		return this.data.length;
	}

	// Retourne la valeur � l'emplacement sp�cifi�
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}
	
	//Retourne le titre de la colonne � l'indice sp�cifi�
	
	public String getColumnName(int col) {
	  return this.title[col];
	}
	
	
	 //D�finit la valeur � l'emplacement sp�cifi�
    public void setValueAt(Object value, int row, int col) {
        this.data[row][col] = value;
    }

    public boolean isCellEditable(int row, int col) {
		return false;
	}
	
}
