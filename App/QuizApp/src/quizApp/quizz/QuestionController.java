package quizApp.quizz;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionController {
    private int questions = 0; //Increment each time a new question is had
    private ArrayList<TriviaQuestion> triviaQuestions;
    private ArrayList<String> allAnswers = new ArrayList<>();

    public ArrayList<String> setQuestion(ArrayList<TriviaQuestion> triviaQuestionArrayList){
        allAnswers.clear(); //Purges the array list ready for adding in next set of questions.

        triviaQuestions = triviaQuestionArrayList;
        allAnswers.addAll(getCurrentQuestion().getIncorrectAnswers());
        allAnswers.add(getCurrentQuestion().getCorrectAnswer());
        Collections.shuffle(this.allAnswers);

        return allAnswers;
    }

    public ArrayList<String> getAllAnswers(){
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

    public String getCorrectAnswer(){
        return this.getCurrentQuestion().getCorrectAnswer();
    }

    public int getOnQuestion(){
        return this.questions;
    }

    public String getCategory(){
        return triviaQuestions.get(0).getCategory();
    }

}
