package quizApp.startQuizScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import quizApp.quizz.QuestionController;
import quizApp.quizz.TriviaQuestion;
import quizApp.quizz.UrlRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartQuizController implements Initializable {
    @FXML
    private Label questionLabel, timerLabel;

    @FXML
    private Button answerButton1, answerButton2, answerButton3, answerButton4;

    private int totalQuestion;
    private int questionCat;
    private String quesDiff;
    private ArrayList<String> allAns = new ArrayList<>();

    private int quizScore = 0;
    private QuestionController questionController = new QuestionController();
    public ArrayList<TriviaQuestion> questionArrayList = new ArrayList<TriviaQuestion>();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
    }

    public void setParams(Integer questionCat, String questionDiff, Integer totalQuestion){
        this.setQuestionDifficulty(questionDiff);
        this.setQuestionCategory(questionCat);
        this.setTotalQuestions(totalQuestion);

        UrlRequest getQs = new UrlRequest(); //Makes the URL request based on chosen categories
        questionArrayList = getQs.getQuestions(totalQuestion,questionCat,questionDiff);
        System.out.println(questionArrayList.get(0).getQuestion());

        allAns = questionController.setQuestions(questionArrayList);

        setAnswers();
        setQuestionLabel();
    }

    public void finishQuiz(ActionEvent actionEvent) {
    }

    public void correctAnswer(ActionEvent actionEvent) {
        this.quizScore += 10;
    }

    public void incorrectAnswer(ActionEvent actionEvent) {
        this.quizScore -= 4;
    }

    private void setQuestionLabel() {
        String formatting = questionController.getQuestion().replaceAll("(&quot;)", "'");
        questionLabel.setText(formatting);
    }

    private void goNext() {
    }

    public void setAnswers() {
        if(allAns.size() == 1){ //If there is a True/False Answer
            answerButton1.setText(allAns.get(0));
            answerButton2.setText(allAns.get(1));
            answerButton3.setVisible(false);
            answerButton4.setVisible(false);
        }
        answerButton1.setText(allAns.get(0)); //Multi choice answer
        answerButton2.setText(allAns.get(1));
        answerButton3.setText(allAns.get(2));
        answerButton4.setText(allAns.get(3));
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestion = totalQuestions;

    }

    public int getTotalQuestions() {
        return this.totalQuestion;
    }

    public void setQuestionCategory(Integer questionCategory) {
        this.questionCat = questionCategory;
    }

    public int getQuestionCategory() {
        return questionCat;
    }

    public void setQuestionDifficulty(String questionDifficulty) {
        this.quesDiff = questionDifficulty;
    }

    public String getQuestionDifficulty() {
        return this.quesDiff;
    }





}
