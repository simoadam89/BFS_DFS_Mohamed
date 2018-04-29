package BFS_DFS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BFS_DFS.fxml"));
        primaryStage.setTitle("BFS und DFS");
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add((getClass().getResource("/BFS_DFS/custom.css")).toExternalForm());
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
