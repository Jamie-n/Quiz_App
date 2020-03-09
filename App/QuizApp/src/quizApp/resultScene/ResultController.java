package quizApp.resultScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultController {

    @FXML
    private Label gradeLabel, chosenCategoryLabel, totalQuestionsLabel, overallScoreLabel;

    @FXML
    private Button exitButton, restartQuiz;

    private int totalQuestions, correctQuestions, totalScore;
    private String category;

    public void setParams(int totalQuestions, int correctQuestions, int totalScore, String category){
        this.totalQuestions = totalQuestions;
        this.correctQuestions = correctQuestions;
        this.category = category;
        this.totalScore = totalScore;

        setLabels();
    }

    public String getGradeLabel() {
        return gradeLabel.getText();
    }

    public void setGradeLabel(String gradeLabel) {
        this.gradeLabel.setText(gradeLabel);
    }

    public String getChosenCategoryLabel() {
        return chosenCategoryLabel.getText();
    }

    public void setChosenCategoryLabel(String chosenCategoryLabel) {
        this.chosenCategoryLabel.setText(chosenCategoryLabel);
    }

    public String getTotalQuestionsLabel() {
        return totalQuestionsLabel.getText();
    }

    public void setTotalQuestionsLabel(String totalQuestionsLabel) {
        this.totalQuestionsLabel.setText(totalQuestionsLabel);
    }

    public String getOverallScoreLabel() {
        return overallScoreLabel.getText();
    }

    public void setOverallScoreLabel(String overallScoreLabel) {
        this.overallScoreLabel.setText(overallScoreLabel);
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }

    public Button getRestartQuiz() {
        return restartQuiz;
    }

    public void setRestartQuiz(Button restartQuiz) {
        this.restartQuiz = restartQuiz;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String calcGrade(int score){
        int totalScore = totalQuestions*10;

        if(score>totalScore*0.85){
            return "S";
        }
        else if (score>totalScore*0.75){
            return "A";
        }
        else if(score>totalScore*0.55){
            return "B";
        }
        else if(score>totalScore*0.40){
            return "C";
        }
        else if(score>totalScore*0.35){
            return "D";
        }
        else {
            return "F";
        }
    }

    public void setLabels(){
        setChosenCategoryLabel(this.category);
        setTotalQuestionsLabel("Correct Answers: "+this.correctQuestions+"/"+this.totalQuestions);
        setGradeLabel("Grade: "+calcGrade(this.totalScore));
        setOverallScoreLabel("Overall Score: "+this.totalScore+"/"+this.totalQuestions*10);
    }

    public void exitQuiz(ActionEvent actionEvent){
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.close();

    }

    public void returnToOptionScene(ActionEvent actionEvent) throws Exception{
        Parent scene = FXMLLoader.load(getClass().getResource("../optionScene/OptionScene.fxml"));
        Stage thirdStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thirdStage.setScene(new Scene(scene));
        thirdStage.show();
    }
}
