package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * Classe permettant de gérer l'affichage du tableau
 * 
 * */
public class LabelRenderer extends JLabel implements TableCellRenderer  {
	private static final long serialVersionUID = 1L;
	private int modeDeJeu=0;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		this.setText((value!=null)?value.toString():"");
		this.setHorizontalAlignment(JLabel.CENTER);
		
		if(modeDeJeu==2) {
			if((row%2==0&&column==0)||(row%2==1&&column==1))
				this.setForeground(Color.GREEN);
			else
				this.setForeground(Color.RED);
		}
		return this;
	}
	
	public void setModeDeJeu(int modeDeJeu) {
		this.modeDeJeu=modeDeJeu;
	}

}
