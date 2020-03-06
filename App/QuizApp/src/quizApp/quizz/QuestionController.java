package quizApp.quizz;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionController {
    private int questions = 0; //Increment each time a new question is had
    private ArrayList<TriviaQuestion> triviaQuestions;
    private ArrayList<String> allAnswers = new ArrayList<>();

    public ArrayList<String> setQuestions(ArrayList<TriviaQuestion> triviaQuestionArrayList){
        allAnswers.clear(); //Purges the array list ready for adding in next set of questions.

        triviaQuestions = triviaQuestionArrayList;
        allAnswers.addAll(triviaQuestions.get(questions).getIncorrectAnswers());
        allAnswers.add(getCurrentQuestion().getCorrectAnswer());
        Collections.shuffle(this.allAnswers);

        return allAnswers;
    }

    public ArrayList getAllAnswers(){
        return this.allAnswers;
    }

    public void addToAllAnswers(String answer){
        allAnswers.add(answer);
    }

    public TriviaQuestion getCurrentQuestion(){
        return triviaQuestions.get(questions);
    }

    public String getQuestion(){
        return triviaQuestions.get(questions).getQuestion();
    }

    public void goToNextQuestion(){
        this.questions++;
    }

    public int getQuestions(){
        return this.questions;
    }


}
