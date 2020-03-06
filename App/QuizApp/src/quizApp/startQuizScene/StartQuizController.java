package quizApp.startQuizScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import quizApp.quizz.TriviaQuestion;
import quizApp.quizz.UrlRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartQuizController {
    @FXML
    private Label questionLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Button answerButton1;

    @FXML
    private Button answerButton2;

    @FXML
    private Button answerButton3;

    @FXML
    private Button answerButton4;

    private Integer totalQuestions;
    private Integer questionCategory;
    private String questionDifficulty;
    private int onQuestion = 0;
    private int quizScore = 0;
    private ArrayList<TriviaQuestion> triviaQuestions = new ArrayList<>();

    @FXML
    public void initialize(){
        this.triviaQuestions.addAll(new UrlRequest().getQuestions(totalQuestions, questionCategory, questionDifficulty));
        setQuestionLabel();
        setAnswers();

    }

    public void finishQuiz(ActionEvent actionEvent){
        if(onQuestion>=totalQuestions){

        }
    }

    public void correctAnswer(ActionEvent actionEvent){
        quizScore+=10;
    }

    public void incorrectAnswer(ActionEvent actionEvent){
        quizScore-=4;
    }

    private void setQuestionLabel(){
        questionLabel.setText(triviaQuestions.get(onQuestion).getQuestion());
    }

    private void goNext(){
        if (onQuestion<=totalQuestions){
            onQuestion++;
        }
    }

    private void setAnswers(){
        answerButton1.setText(triviaQuestions.get(onQuestion).getAllAnswers(0));
        answerButton2.setText(triviaQuestions.get(onQuestion).getAllAnswers(1));
        answerButton3.setText(triviaQuestions.get(onQuestion).getAllAnswers(2));
        answerButton4.setText(triviaQuestions.get(onQuestion).getAllAnswers(3));
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
