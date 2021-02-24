package quiz.scenes;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuBar;
import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

//Settiamo pagina iniziale
public class MenuController {
	private Controller mainApp;
	//Opzioni MenuBar
	@FXML private MenuItem miHelp;
	@FXML private MenuItem miExit;

	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	private void showHelp(){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.getDialogPane().getScene().getStylesheets().add("application.css");
		alert.setTitle("Istruzioni");
		alert.setHeaderText(null);
		alert.setContentText("Benvenuti su Quiz Mama! \n" +
				"Il gioco è semplice: si tratta di un quiz per giocatore singolo, egli dovrà rispondere a varie domande relative al cibo." +
				"I quiz possono essere creati dall'utente, oppure si possono fare challenge pre-impostate.");
		alert.showAndWait();
	}

	//Azioni Menubar
	@FXML private void Exit() { System.exit(0); }
	@FXML private void Help() throws Exception{ showHelp(); }
}
