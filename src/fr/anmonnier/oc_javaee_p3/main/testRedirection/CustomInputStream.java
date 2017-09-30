package fr.anmonnier.oc_javaee_p3.main.testRedirection;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextArea;

public class CustomInputStream extends InputStream {
	private JTextArea textArea;

	public CustomInputStream(JTextArea textArea) {
		this.textArea = textArea;
	}

	public int read() throws IOException {
		// TODO Auto-generated method stub
		return read((textArea.getText()).getBytes());
	}
}
