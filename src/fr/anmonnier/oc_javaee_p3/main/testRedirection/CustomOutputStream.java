package fr.anmonnier.oc_javaee_p3.main.testRedirection;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 *  * This class extends from OutputStream to redirect output to a JTextArrea
 *  * @author www.codejava.net  *  
 */
public class CustomOutputStream extends OutputStream {
	private JTextArea textArea;

	public CustomOutputStream(JTextArea textArea) {
		this.textArea = textArea;
	}

	public void write(int b) throws IOException {

		textArea.append(String.valueOf((char) b));
	}
}