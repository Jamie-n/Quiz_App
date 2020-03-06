package quizApp.quizz;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

public class TriviaQuestion {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correctAnswer;
    private JSONArray incorrectAnswers;
    private ArrayList allAnswers;


    public TriviaQuestion(String category, String type, String difficulty, String question, String correctAnswer, JSONArray incorrectAnswers, ArrayList allAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.allAnswers = allAnswers;

        Collections.shuffle(allAnswers);
    }

    public TriviaQuestion(String category, String type) {
        this.category = category;
        this.type = type;
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
        return question.replaceAll("(&quot;)","'").replaceAll("&#[0-9]*;s","");

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

    public JSONArray getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(JSONArray incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getAllAnswers(int index) {
        return allAnswers.get(index).toString();
    }
}
