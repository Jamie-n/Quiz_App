package quizApp.optionScene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import quizApp.API.Categories;
import quizApp.API.UrlRequest;
import quizApp.startQuizScene.StartQuizController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class  OptionController implements Initializable {
    @FXML
    private TextField incrementLabel;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private ListView<Categories> categoryList;

    private UrlRequest urlRequest = new UrlRequest();

    private ObservableList<Categories> category;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyComboBox.getItems().addAll("Easy", "Medium", "Hard");

        try {

            ObservableList<Categories> category = FXCollections.observableArrayList(urlRequest.getCategories());
            categoryList.getItems().addAll(category);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void incrementLabel(ActionEvent actionEvent) {
        try {
            int val = Integer.parseInt(incrementLabel.getText());
            if (val < 50) {
                incrementLabel.setText("" + (val + 1));
            } else if (incrementLabel.getText().equals("Num Only")) {
                incrementLabel.setText("" + 0);
            }

        } catch (NumberFormatException e) {
            incrementLabel.setText("Num Only");
        }
    }

    public void decrementLabel(ActionEvent actionEvent) {
        try {
            int val = Integer.parseInt(incrementLabel.getText());
            if (val > 0) {
                incrementLabel.setText("" + (val - 1));
            } else if (incrementLabel.getText().equals("Num Only")) {
                incrementLabel.setText("" + 0);
            }
        } catch (NumberFormatException e) {
            incrementLabel.setText("Num Only");
        }
    }

    public Integer getQuestionNum() {
        return Integer.parseInt(incrementLabel.getText());
    }

    public Integer getCategory() {
        try {
            return categoryList.getSelectionModel().getSelectedItem().getCategoryID(); //id not index
        }catch (Exception e){
            return null;
        }
    }

    public String getDifficulty() {
        return difficultyComboBox.getSelectionModel().getSelectedItem().toLowerCase();
    }

    public void backToMenu(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../menuScene/MenuScene.fxml"));
        Stage firstStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        firstStage.setScene(new Scene(root));
        firstStage.show();
    }

    public void startQuiz(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../startQuizScene/StartQuizScene.fxml"));
        Parent scene = loader.load();
        StartQuizController startQuizController = loader.getController();


        if (getCategory()==null || getQuestionNum().equals(0) || getDifficulty() == "") {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please make selections", ButtonType.OK);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.show();

        } else {

            startQuizController.setParams(getCategory(), getDifficulty(), getQuestionNum()); //Calls the startup for the quiz start controller

            Stage thirdStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            thirdStage.setScene(new Scene(scene));
            thirdStage.show();
        }
    }


}
