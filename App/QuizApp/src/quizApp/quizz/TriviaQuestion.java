package quizApp.quizz;

import org.json.JSONArray;

import java.util.ArrayList;

public class TriviaQuestion {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;


    public TriviaQuestion(String category, String type, String difficulty,String question, String correctAnswer, ArrayList incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public TriviaQuestion(String category, String type) {
        this.category = category;
        this.type = type;
    }

    public TriviaQuestion(String category, String type, String difficulty, String questionAsk, String correctAnswer, JSONArray incorrectAnswers) {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return ""+incorrectAnswers;
    }
}
