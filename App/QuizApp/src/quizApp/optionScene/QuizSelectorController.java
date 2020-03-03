package quizApp.optionScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class QuizSelectorController {
    @FXML
    private Label incrementLabel;

    public void incrementLabel(ActionEvent actionEvent){
        int val = Integer.parseInt(incrementLabel.getText());
        if (val<25) {
            incrementLabel.setText("" + (val + 1));
        }
    }

    public void decrementLabel(ActionEvent actionEvent){
        int val = Integer.parseInt(incrementLabel.getText());
        if (val>0) {
            incrementLabel.setText("" + (val - 1));
        }
    }

    public void backToMenu(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../menuScene/MenuScene.fxml"));
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
