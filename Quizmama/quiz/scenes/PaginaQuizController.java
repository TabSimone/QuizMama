package quiz.scenes;

import javafx.scene.image.Image;
import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;


//Pagina con domande quiz

public class PaginaQuizController
{
	private Controller mainApp;
	private RadioButton[] rB;
	@FXML private Label question;
	@FXML private RadioButton rbA;
	@FXML private RadioButton rbB;
	@FXML private RadioButton rbC;
	@FXML private RadioButton rbD;
	@FXML private RadioButton rbE;
	@FXML private RadioButton rbF;
	@FXML private ToggleGroup rbGroup;
	@FXML private ProgressBar pB;
	@FXML private Button bNext;

	public PaginaQuizController() {
		question = new Label("");
		rbGroup = new ToggleGroup();
		pB = new ProgressBar(0);
	}

	//Inizializza la pagina dei quiz con domande
	@FXML
	private void initialize() {
		rB = new RadioButton[6];
		rB[0] = rbA; rB[1] = rbB; rB[2] = rbC;
		rB[3] = rbD; rB[4] = rbE; rB[5] = rbF;
		rbA.setToggleGroup(rbGroup); rbB.setToggleGroup(rbGroup);
		rbC.setToggleGroup(rbGroup); rbD.setToggleGroup(rbGroup);
		rbE.setToggleGroup(rbGroup); rbF.setToggleGroup(rbGroup);
	}

	//Chiamiamo controller
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	//Diamo comportamenti a toggle
	@FXML
	private void keyPressed(KeyEvent ke){
		switch (ke.getText()) {
			case "1": rb(rbA); break;
			case "2": rb(rbB); break;
			case "3": rb(rbC); break;
			case "4": rb(rbD); break;
			case "5": rb(rbE); break;
			case "6": rb(rbF); break;
			default:
				switch (ke.getCode().toString()) {
					case "ENTER": getNextQuiz(); break;
					case "RIGHT_ARROW": getNextQuiz(); break;
					default: break;
				}
		}
	}
	
	//Per click botton
	private void rb(RadioButton pressed){
		if (pressed.isVisible()){
			pressed.setSelected(true);
			bNext.setDisable(false);
		}
	}

	@FXML private void rbClicked()	{ bNext.setDisable(false);	}
	@FXML private void bNext()		{ getNextQuiz();			}

	private void getNextQuiz() {
		String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
		mainApp.qProceed(picked);
	}

	//Per togliere bottun non necessari
	public void qDisable(String question, String[] allAnswers){
		this.question.setText(question + "?");
		this.question.setVisible(true);
		this.bNext.setDisable(true);
		for (int i = 0; i < 6; i++){
			if (i >= allAnswers.length) {
				//Non necessario
				rB[i].setText("");
				rB[i].setSelected(false);
				rB[i].setVisible(false);
			} else {
				//Necessario
				rB[i].setText(allAnswers[i]);
				rB[i].setSelected(false);
				rB[i].setVisible(true);
			}
		}
	}
	
	//Per aggiornare progresso
	public void setProgress(double d){
		pB.setProgress(d);
	}
}
