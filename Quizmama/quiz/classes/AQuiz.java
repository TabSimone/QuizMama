package quiz.classes;

import java.util.List;

public class AQuiz {
	private final static int maxQ = 6;  //Per domande
	private String question;
	private String correct;
	private String[] allA;	//Per risposte

	// Costruttore per comporre AQuiz da lista stringhe
	//Primo nella lista question
	//Secondo nella lista correct answer
	public AQuiz(List<String> list){
		question = list.get(0);
		correct = list.get(1);
		if (list.size()-1 > maxQ)
			allA = new String[maxQ];
		else
			allA = new String[list.size()-1];
		//Sara random disposizione domande
		int correctIndex = (int) (Math.random() * allA.length);
		int listIndex = 2;
		for (int i=0; i < allA.length; i++){
			if (i == correctIndex){
				allA[i] = correct;
			} else {
			allA[i] = list.get(listIndex);
			listIndex = listIndex + 1;
			}
		}
	}

	//Getter per question, correct, all answer
	public String getQuestion() { return new String (question); }
	public String getCorrect() { return new String (correct); }
	public String[] getAllA() { return allA.clone(); }

}
