package quizApp.quizz;

import org.json.JSONArray;

import java.util.ArrayList;

public class Questions {
    private UrlRequest urlRequest = new UrlRequest();
    private ArrayList<TriviaQuestion> questions;
    private int onQuestion = 0;
    private int totalQuestions;

    public Questions(Integer totalQuestions, Integer questionCategory, String questionDifficulty){
        this.questions = new ArrayList<>();
        this.totalQuestions = totalQuestions;

        this.questions.addAll(urlRequest.getQuestions(totalQuestions, questionCategory, questionDifficulty));
    }

    public void getNext(){
        if (onQuestion<=totalQuestions) {
            this.onQuestion += 1;
        }
    }

    public String getQuestion(){
        return questions.get(onQuestion).getQuestion();
    }

    public String getCorrectAnswer(){
        return questions.get(onQuestion).getCorrectAnswer();
    }

    public JSONArray getAllAnswers(){
        questions.get(onQuestion).getIncorrectAnswers().;
    }
}
