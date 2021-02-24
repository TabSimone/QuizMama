package quiz.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

//Java per importare domande da quiz, con MAX di 7 answer

public final class FileImport {
	//Fa fileChooser, per scegliere utente il file
	public static void importQuiz(MQuiz quiz, String c) {
		if(c=="other") {  //Se ho loadato quiz
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Importa Quiz");
			fileChooser.setInitialFileName("QuizFile.txt");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
			File selectedFile = fileChooser.showOpenDialog(null);
			if (selectedFile != null) {
				try {
					readFileToQuiz(selectedFile, quiz);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else {  //Challenge ergo no filechooser, setto path
			try {
				File myObj = new File(c);
				Scanner myReader = new Scanner(myObj);
				String string;
				List<String> list = new LinkedList<String>();
				while (myReader.hasNext()) {  //Leggo file e addo domande
					string = myReader.nextLine();
					if ((string.isEmpty()) && (!list.isEmpty())) {
						quiz.addAQuiz(list);
						list.clear();
					} else if ((!string.isEmpty()) && (!string.substring(0, 1).equals("/"))) {
						list.add(string);
					}
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	//Leggo file e scrivo in list contenuto
	private static void readFileToQuiz(File f, MQuiz quiz) {
		try{
			if (f.exists()) { //F=file
			FileInputStream file = new FileInputStream(f);
			Scanner read = new Scanner(file);
			String string;
			List<String> list = new LinkedList<String>();
			while (read.hasNext()) {  //Leggo File
				string = read.nextLine();
				if ((string.isEmpty()) && (!list.isEmpty())) {
					quiz.addAQuiz(list);
					list.clear();
				} else if ((!string.isEmpty()) && (!string.substring(0, 1).equals("/"))) {
						list.add(string);
				}
			}
			quiz.addAQuiz(list);
			read.close();}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}