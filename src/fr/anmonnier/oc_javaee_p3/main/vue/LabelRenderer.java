package fr.anmonnier.oc_javaee_p3.main.vue;

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

	public Component getTableCellRendererComponent(JTable arg0, Object value, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		this.setText((value!=null)?value.toString():"");
		this.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

}
