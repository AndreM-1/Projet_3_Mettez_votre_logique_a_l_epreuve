package fr.anmonnier.oc_javaee_p3.main.vue;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/****************************************************************************************************
 * Classe permettant de gérer l'affichage du tableau utilisé dans le cadre du jeu RecherchePlusMoins
 * @author André Monnier
 ****************************************************************************************************/
public class LabelRenderer extends JLabel implements TableCellRenderer  {
	private static final long serialVersionUID = 1L;

	/**
	 * Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel.
	 */
	private int modeDeJeu=0;

	/**
	 * Redéfinition de la méthode de l'interface TableCellRenderer permettant de gérer l'affichage du tableau :
	 * la couleur verte sera associée au joueur et la couleur rouge à l'ordinateur.
	 */
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

	/**
	 * Mutateur permettant de modifier le mode de Jeu
	 * @param modeDeJeu Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel.
	 */
	public void setModeDeJeu(int modeDeJeu) {
		this.modeDeJeu=modeDeJeu;
	}
}