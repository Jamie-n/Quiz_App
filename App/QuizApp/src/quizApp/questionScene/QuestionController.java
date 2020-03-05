package quizApp.questionScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import quizApp.quizz.TriviaQuestion;
import quizApp.quizz.UrlRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML
    private Label questionLabel;

    @FXML
    private Label timerLabel;

    private Integer totalQuestions;
    private Integer questionCategory;
    private String questionDifficulty;
    private int quizScore;
    private int onQuestion = 0;
    private ArrayList<TriviaQuestion> triviaQuestions = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void finishQuiz(ActionEvent actionEvent){

    }

    public void nextQuestion(ActionEvent actionEvent){

    }

    public void correctAnswer(ActionEvent actionEvent){

    }

    public void incorrectAnswer(ActionEvent actionEvent){

    }

    public void questionData(){
        questionLabel.setText(triviaQuestions.get(onQuestion).getQuestion());
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setQuestionCategory(int questionCategory) {
        this.questionCategory = questionCategory;
    }

    public void setQuestionDifficulty(String questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }
}
