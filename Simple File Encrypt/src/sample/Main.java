package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 560.0D, 200.0D);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple File Encrypt");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
