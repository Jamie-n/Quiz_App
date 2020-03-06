package quizApp.startQuizScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import quizApp.quizz.QuestionController;

import java.net.URL;
import java.util.ResourceBundle;

public class StartQuizController implements Initializable{
    @FXML
    private Label questionLabel, timerLabel;

    @FXML
    private Button answerButton1, answerButton2, answerButton3, answerButton4;

    @FXML
    private Label totalQuestions, questionCategory, questionDifficulty;

    private int quizScore = 0;
    private QuestionController questionController;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        questionController = new QuestionController();
        questionController.setQuestions(getTotalQuestions(), getQuestionCategory(), getQuestionDifficulty());

        setAnswers();
        setQuestionLabel();
    }

    public void finishQuiz(ActionEvent actionEvent){
    }

    public void correctAnswer(ActionEvent actionEvent){
        this.quizScore+=10;
    }

    public void incorrectAnswer(ActionEvent actionEvent){
        this.quizScore-=4;
    }

    private void setQuestionLabel(){
        questionLabel.setText(questionController.getQuestion());
    }

    private void goNext(){
    }

    private void setAnswers(){
        answerButton1.setText(questionController.getAllAnswers().get(0).toString());
        answerButton2.setText(questionController.getAllAnswers().get(1).toString());
        answerButton3.setText(questionController.getAllAnswers().get(2).toString());
        answerButton4.setText(questionController.getAllAnswers().get(3).toString());
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions.setText(String.valueOf(totalQuestions));
    }

    public int getTotalQuestions(){
        return Integer.parseInt(this.totalQuestions.getText());
    }

    public void setQuestionCategory(Integer questionCategory) {
        this.questionCategory.setText(String.valueOf(questionCategory));
    }

    public int getQuestionCategory(){
        return Integer.parseInt(this.questionCategory.getText());
    }

    public void setQuestionDifficulty(String questionDifficulty) {
        this.questionDifficulty.setText(questionDifficulty);
    }

    public String getQuestionDifficulty(){
        return this.questionDifficulty.getText();
    }


}
