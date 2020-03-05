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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import quizApp.quizz.Categories;
import quizApp.quizz.UrlRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizSelectorController implements Initializable {
    @FXML
    private Label incrementLabel;

    @FXML
    private ComboBox<String> difficultyCmbox;

    @FXML
    private ListView categoryList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UrlRequest urlRequest = new UrlRequest();
        difficultyCmbox.getItems().addAll("Easy", "Medium", "Hard");

        try {

            ObservableList<Categories> category = FXCollections.observableArrayList(urlRequest.getCategories());
            categoryList.getItems().addAll(category);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void incrementLabel(ActionEvent actionEvent) {
        int val = Integer.parseInt(incrementLabel.getText());
        if (val < 50) {
            incrementLabel.setText("" + (val + 1));
        }
    }

    public void decrementLabel(ActionEvent actionEvent) {
        int val = Integer.parseInt(incrementLabel.getText());
        if (val > 0) {
            incrementLabel.setText("" + (val - 1));
        }
    }

    public int getDifficulty() {
        return difficultyCmbox.getSelectionModel().getSelectedIndex();
    }

    public int getQuestionNum() {
        return Integer.parseInt(incrementLabel.getText());
    }

    public void backToMenu(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../menuScene/MenuScene.fxml"));
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void startQuiz(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../questionScene/QuestionScene.fxml"));
        Stage thirdStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thirdStage.setScene(new Scene(root));
        thirdStage.show();
    }


}
