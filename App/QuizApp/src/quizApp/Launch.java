package quizApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launch extends Application {

    @Override
    public void start(Stage firstStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuScene/MenuScene.fxml"));
        firstStage.getIcons().add(new Image("/quizApp/imgRrc/logoIcon.png"));
        firstStage.setTitle("Quiz");
        firstStage.setScene(new Scene(root));
        firstStage.setResizable(false);
        firstStage.setMaximized(false);
        firstStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
