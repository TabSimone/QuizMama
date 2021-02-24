package quiz.classes;

import quiz.Controller;
import java.util.LinkedList;
import java.util.List;

//Crea Quiz attraverso AQuiz

public class MQuiz {
	private List<AQuiz> quiz;	// Quiz da file
	private List<AQuiz> game;
	static String c;

	public static void changer(String cont){
		c=cont;
	}

	//Creo quiz e chiamo FileImport per leggere file
	public MQuiz() throws Exception{
		quiz = new LinkedList<AQuiz>();
		FileImport.importQuiz(this, c);
		startGame();
	}

	public void startGame(){
		//Creo game per copia per restart
		game = (List<AQuiz>) ((LinkedList<AQuiz>) quiz).clone();
	}
	
	//Builda lista AQuiz
	public void addAQuiz(List<String> list){
		quiz.add(new AQuiz(list));
	}

	//Getter per Question
	public String getQuestion(int index){
		return game.get(index).getQuestion();
	}

	//Getter per Correct A
	public String getCorrect(int index){
		return game.get(index).getCorrect();
	}

	//Getter per array con possibili A
	public String[] getAllA(int index){
		return game.get(index).getAllA();
	}

	//Getter per size
	public int getGameSize(){
		return game.size();
	}

	//Getter per score tota
	public int getTotalScore(){
		return quiz.size();
	}
	
	//Getter per score ora
	public int getScore(){
		return quiz.size() - game.size();
	}
	
	//Rimuove answer in caso di risposta corretta
	public void remove(int index){
		if (index < game.size())
			game.remove(index);
	}

}
