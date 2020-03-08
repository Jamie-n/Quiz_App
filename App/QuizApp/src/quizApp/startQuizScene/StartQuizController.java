package quizApp.startQuizScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import quizApp.quizz.QuestionController;
import quizApp.quizz.TriviaQuestion;
import quizApp.quizz.UrlRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartQuizController implements Initializable {
    @FXML
    private Label timerLabel, onQuestion, scoreLabel;

    @FXML
    private TextArea questionLabel;

    @FXML
    private Button answerButton1, answerButton2, answerButton3, answerButton4;

    private int totalQuestion;
    private int questionCat;
    private String quesDiff;
    private ArrayList<String> allAns = new ArrayList<>();

    private int quizScore = 0;
    private QuestionController questionController = new QuestionController();
    public ArrayList<TriviaQuestion> questionsArrayList = new ArrayList<TriviaQuestion>();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
    }

    public void setParams(Integer questionCat, String questionDiff, Integer totalQuestion){
        this.setQuestionDifficulty(questionDiff);
        this.setQuestionCategory(questionCat);
        this.setTotalQuestions(totalQuestion);

        UrlRequest getQs = new UrlRequest(); //Makes the URL request based on chosen categories
        questionsArrayList = getQs.getQuestions(totalQuestion,questionCat,questionDiff);

        allAns = questionController.setQuestion(questionsArrayList);

        setAnswers();
        setQuestionLabel();

        quizScore = 0;
    }

    public void finishQuiz(ActionEvent actionEvent) {
    }

    public void checkAnswer(ActionEvent actionEvent) {
        try {
            String text = ((Button) actionEvent.getSource()).getText();
            if (questionController.getCorrectAnswer().equals(text)) {

                this.quizScore += 10;

            } else {

                this.quizScore -= 4;

            }

            questionController.goToNextQuestion();
            System.out.println(questionsArrayList.get(0).getQuestion());
            questionController.getQuestion();
            setAnswers();
            setScoreLabel();
            setQuestionLabel();
            setOnQuestionNumber();
        } catch (IndexOutOfBoundsException e) {

        }

    }

    private void setQuestionLabel() {
        questionLabel.setText(questionController.getQuestion());
    }

    private void setOnQuestionNumber(){
        onQuestion.setText("Question No."+(questionController.getOnQuestion()+1));
    }

    public void setAnswers() {

        answerButton3.setVisible(true);
        answerButton4.setVisible(true);

        if(allAns.size() == 2){ //If there is a True/False Answer
            answerButton1.setText(allAns.get(0));
            answerButton2.setText(allAns.get(1));
            answerButton3.setVisible(false);
            answerButton4.setVisible(false);
        }
        else {
            answerButton1.setText(allAns.get(0)); //Multi choice answer
            answerButton2.setText(allAns.get(1));
            answerButton3.setText(allAns.get(2));
            answerButton4.setText(allAns.get(3));
        }
    }

    public void setScoreLabel(){
        scoreLabel.setText("Total Score: "+quizScore);
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
