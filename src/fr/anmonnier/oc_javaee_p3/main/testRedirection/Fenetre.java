package fr.anmonnier.oc_javaee_p3.main.testRedirection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;



public class Fenetre extends JFrame {
	static JTextArea textArea =new JTextArea(50,10);
	static PrintStream printStream= new PrintStream(new CustomOutputStream(textArea));
	static InputStream entree= new CustomInputStream(textArea);
	
	public Fenetre() {
		this.setTitle("Test redirection");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//textArea.setEditable(false);
		this.add(textArea);
		this.setVisible(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		new Fenetre();
		System.setOut(printStream);
		System.setErr(printStream);
		System.setIn(entree);
		System.out.println("Veuillez saisir un mot :");
		String str=sc.nextLine();
		System.out.println("Vous avez saisi le mot :"+str);
	}

}
