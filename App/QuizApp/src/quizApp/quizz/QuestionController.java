package quizApp.quizz;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionController {
    private int questions;
    private ArrayList<TriviaQuestion> triviaQuestions;
    private ArrayList<String> allAnswers;
    private UrlRequest urlRequest = new UrlRequest();

    public QuestionController(){
        this.questions = 0;
        this.triviaQuestions = new ArrayList<>();
        this.allAnswers = new ArrayList<>();
    }

    public void setQuestions(int totQuestions, int category, String questionDifficulty){
        questions = totQuestions-1;
        this.triviaQuestions.addAll(urlRequest.getQuestions(totQuestions, category, questionDifficulty));
        for(Object o: getCurrentQuestion().getIncorrectAnswers()){
            this.allAnswers.add(o.toString());
        }
        this.allAnswers.add(getCurrentQuestion().getCorrectAnswer());
        Collections.shuffle(this.allAnswers);
    }

    public ArrayList getAllAnswers(){
        return this.allAnswers;
    }

    public TriviaQuestion getCurrentQuestion(){
        return this.triviaQuestions.get(questions);
    }

    public String getQuestion(){
        return this.triviaQuestions.get(questions).getQuestion();
    }

    public void goToNextQuestion(){
        this.questions--;
    }

    public int getQuestions(){
        return this.questions;
    }


}
