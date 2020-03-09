package quizApp.startQuizScene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import quizApp.quizz.QuestionController;
import quizApp.quizz.TriviaQuestion;
import quizApp.quizz.UrlRequest;
import quizApp.resultScene.ResultController;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartQuizController implements Initializable {
    @FXML
    private Label timerLabel, onQuestion, scoreLabel, questionLabel;

    @FXML
    private Button answerButton1, answerButton2, answerButton3, answerButton4, finishButton;

    private int questionCat, totalQuestion, correctQuestions, quizScore = 0, countdownSeconds;
    private final int countdownMaxSecs = 25;
    private String quesDiff;
    private ArrayList<String> allAns = new ArrayList<>();
    private QuestionController questionController = new QuestionController();
    public ArrayList<TriviaQuestion> questionsArrayList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
    }

    public void setParams(Integer questionCat, String questionDiff, Integer totalQuestion) {
        this.setQuestionDifficulty(questionDiff);
        this.setQuestionCategory(questionCat);
        this.setTotalQuestions(totalQuestion);

        UrlRequest getQs = new UrlRequest(); //Makes the URL request based on chosen categories
        questionsArrayList = getQs.getQuestions(totalQuestion, questionCat, questionDiff);

        initialiseAnswers();

        setAnswers();
        setQuestionLabel();

        quizScore = 0;
        countdownSeconds = countdownMaxSecs;

        finishButton.setVisible(false);
        startTimer();
    }

    public void initialiseAnswers() {
        allAns.addAll(questionController.setQuestion(questionsArrayList));
    }

    public void checkAnswer(ActionEvent actionEvent) {

        String text = ((Button) actionEvent.getSource()).getText();
        System.out.println(text);
        if (questionController.getCorrectAnswer().equals(text)) {

            this.quizScore += 10;
            this.correctQuestions++;

        } else {

            this.quizScore -= 4;

        }
        setScoreLabel();
        try {
            questionController.goToNextQuestion();
            System.out.println(questionController.getAllAnswers());
            this.initialiseAnswers();
            this.setAnswers();
            this.setQuestionLabel();
            this.setOnQuestionNumber();
            countdownSeconds = countdownMaxSecs;


        } catch (IndexOutOfBoundsException e) {
            finishButton.setVisible(true);
        }
    }

    public void startTimer() {
        DecimalFormat df = new DecimalFormat("00");
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            countdownSeconds--;
            timerLabel.setText(df.format(countdownSeconds) + "s");
            if (countdownSeconds == 0) {
                try {

                    quizScore -= 4;
                    questionController.goToNextQuestion();
                    initialiseAnswers();
                    setAnswers();
                    setQuestionLabel();
                    setOnQuestionNumber();
                    setScoreLabel();
                    countdownSeconds = countdownMaxSecs;

                } catch (IndexOutOfBoundsException e) {
                    finishButton.setVisible(true);
                    time.stop();
                }
            }
            else if(countdownSeconds <= 10){
                timerLabel.setTextFill(Color.web("#FF6347"));
            }
            else{
                timerLabel.setTextFill(Color.web("#FFFFFF"));
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    public void resultScene(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resultScene/ResultScene.fxml"));
        Parent scene = loader.load();

        ResultController resultController = loader.getController();

        resultController.setParams(getTotalQuestions(), getCorrectQuestions(), getQuizScore(), questionController.getCategory());

        Stage thirdStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thirdStage.setScene(new Scene(scene));
        thirdStage.show();
    }

    public void setAnswers() {

        if (questionController.getAllAnswers().size() == 2) { //If there is a True/False Answer
            answerButton1.setText(questionController.getAllAnswers().get(0));
            answerButton2.setText(questionController.getAllAnswers().get(1));
            answerButton3.setVisible(false);
            answerButton4.setVisible(false);
        } else {
            answerButton3.setVisible(true);
            answerButton4.setVisible(true);
            answerButton1.setText(questionController.getAllAnswers().get(0)); //Multi choice answer
            answerButton2.setText(questionController.getAllAnswers().get(1));
            answerButton3.setText(questionController.getAllAnswers().get(2));
            answerButton4.setText(questionController.getAllAnswers().get(3));
        }
    }

    private void setOnQuestionNumber() {
        onQuestion.setText("Question No." + (questionController.getOnQuestion() + 1));
    }

    private void setQuestionLabel() {
        questionLabel.setText(questionController.getQuestion());
    }

    public void setScoreLabel() {
        scoreLabel.setText("Total Score: " + quizScore);
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

    public int getQuestionCat() {
        return questionCat;
    }

    public void setQuestionCat(int questionCat) {
        this.questionCat = questionCat;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public int getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }

    public int getCountdownSeconds() {
        return countdownSeconds;
    }

    public void setCountdownSeconds(int countdownSeconds) {
        this.countdownSeconds = countdownSeconds;
    }

}
