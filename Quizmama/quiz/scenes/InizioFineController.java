package quiz.scenes;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import quiz.classes.AQuiz;
import quiz.classes.FileImport;
import quiz.classes.MQuiz;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class InizioFineController {
	private ArrayList<Integer> values = new ArrayList<Integer>();  //Array per risultati
	private Controller mainApp;		//Per reference a Controller
	public String other = "other";
	public String pathE = "quiz//easy.txt";
	public String pathH = "quiz//hell.txt";
	public String pathF = "quiz//fish.txt";
	public String pathM = "quiz//meat.txt";
	@FXML private Label headline;
	@FXML private Label result;
	@FXML private Button bPoints;    //Per punti
	@FXML private Button bRestart;	//Per restartare
	@FXML private Button bLoad;		//Per loadare quiz
	//Bottoni challenge
	@FXML private Button bEasy;
	@FXML private Button bFish;
	@FXML private Button bMeat;
	@FXML private Button bHell;



	//Costruttore
	public InizioFineController() {
		headline = new Label();
		result = new Label();
	}

	public void setMainApp(Controller mainApp) { this.mainApp = mainApp; }

	//Per comportamenti dei bottoni
	@FXML private void bPoints() throws Exception	{ rankQuiz(); }
	@FXML private void bRestart() throws Exception	{ mainApp.restartQuiz(); }
	@FXML private void bLoad() throws Exception		{ mainApp.loadQuiz(other); }
	@FXML private void bEasy() throws Exception {mainApp.loadQuiz(pathE); }
	@FXML private void bHell() throws Exception {mainApp.loadQuiz(pathH); }
	@FXML private void bFish() throws Exception {mainApp.loadQuiz(pathF); }
	@FXML private void bMeat() throws Exception {mainApp.loadQuiz(pathM); }

	//Metodo per mostrare risultati da ultima partita
	public void showResult(int score, int max){
		bRestart.setVisible(true);
		headline.setText("Risultati:");
		result.setText("Hai totalizzato " + score + " punti su " + max + ".");
		//Rendiamo visibili challenge adeguate
		if(score==0){
			bEasy.setVisible(true);
		}else if(score==max){
			bHell.setVisible(true);
		}
		//Aggiungiamo valore a file
		try(FileWriter fw = new FileWriter("quiz\\Board.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)) {
			out.println(" "+score+" "+max);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Mostra storico
	public void rankQuiz() {
		//Leggo storico da file
		try {
				File myObj = new File("quiz\\Board.txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextInt()) {
					int data = myReader.nextInt();
					values.add(data);
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		result.setText("Punteggi ultime 3 partite: \n" +values.get(values.size()-2)+ "/" +values.get(values.size()-1)+ "\n"
				+values.get(values.size()-4)+ "/" +values.get(values.size()-3)+ "\n"
				+values.get(values.size()-6)+ "/" +values.get(values.size()-5) + "\n"
		);
		//Leggo ultimi valorei da file
	}
}