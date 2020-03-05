package quizApp.menuScene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Parent root = FXMLLoader.load(getClass().getResource("../optionScene/OptionScene.fxml"));
        Stage secondStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        secondStage.setScene(new Scene(root));
        secondStage.show();
    }
}
