package quiz;

import java.io.IOException;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import quiz.classes.MQuiz;
import quiz.scenes.InizioFineController;
import quiz.scenes.MenuController;
import quiz.scenes.PaginaQuizController;

//Controller del quiz
//Main

public class Controller extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private MQuiz model;
	private PaginaQuizController view;
	private InizioFineController load;
	private MenuController menu;
	private int currentIndex;
	private String cont;

	//Settiamo stage
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Quiz mama");  //Nome stage
		iniziale();
		full();

	}

	//Inizializzo pagina inziale
	private void iniziale() {
		//Try per loader.load per essere dichiarato
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("scenes/Menu.fxml"));  //Chiamo Menu da fxml
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			MenuController viewController = loader.getController();
			viewController.setMainApp(this);
			menu = viewController;
			primaryStage.show();
			music();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	//*Finito di inizializzare pagina iniziale*//
	//Metodo per musica
	MediaPlayer mediaPlayer;
	public void music(){
		String s="music1.mp3";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
	}


	//Fulliamo pagina inziale Menu
	private void full() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("scenes/InizioFine.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			rootLayout.setCenter(loadView);
			InizioFineController viewController = loader.getController();
			viewController.setMainApp(this);
			load = viewController;
		} catch (IOException e) {
			e.getMessage();
		}
	}

	//Restarta quiz
	public void restartQuiz() {
		model.startGame();
		aPaginaQuiz();
	}

	//Load Quiz
	public void loadQuiz(String expression) throws Exception{
		//Comportamento a seconda se load o challenge
		//expression=path
		this.cont=expression;
		if(cont=="other") {
			quiz.classes.MQuiz.changer(cont);
			this.model = new MQuiz();
			aPaginaQuiz();
		} else {
			quiz.classes.MQuiz.changer(cont);
			this.model = new MQuiz();
			aPaginaQuiz();
		}
	}

	//Inizializza la paginaQuiz, pagina che si vede quando iniziamo gioco
	public void aPaginaQuiz() {
		currentIndex = 0;
		if (model.getGameSize() > 0) {
			//Try per il load
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Controller.class.getResource("scenes/PaginaQuiz.fxml"));  //Carichiamo da fxml
				AnchorPane quizView = (AnchorPane) loader.load();
				rootLayout.setCenter(quizView);
				PaginaQuizController viewController = loader.getController();
				viewController.setMainApp(this);
				view = viewController;
				qProgress(currentIndex);
			} catch (IOException e) {
				e.getMessage();
			}
		}
	}

	//Metodo per progresso
	private void qProgress(int index){
		view.setProgress((double) (1 + currentIndex + model.getScore()) / (double) model.getTotalScore());
		if (index < model.getGameSize())
			view.qDisable(model.getQuestion(index), model.getAllA(index));
	}

	//Metodo per procedere quiz
	public void qProceed(String pick){
		if (pick.equals(model.getCorrect(currentIndex)))
			model.remove(currentIndex);
		else
			currentIndex = currentIndex + 1;
		if (currentIndex < model.getGameSize()){
			qProgress((currentIndex));
		} else {
			risultati();
		}
	}

	//Metodo che ritorna risultati
	private void risultati(){
		full();
		load.showResult(model.getScore(), model.getTotalScore());
	}





}
