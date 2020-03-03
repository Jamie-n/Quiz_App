package quizApp.menuScene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class MainMenuController {
    @FXML
    private Button exitButton;

    public void closeWindow(ActionEvent actionEvent) {
        Stage window = (Stage)exitButton.getScene().getWindow();
        window.close();
    }

    public void nextScene(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../optionScene/QuizSelect.fxml"));
        Stage secondStage =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        secondStage.setTitle("Quiz");
        secondStage.getIcons().add(new Image("/quizApp/imgRrc/logoIcon.png"));
        secondStage.setScene(new Scene(root));
        secondStage.show();
    }
}
