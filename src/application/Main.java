package application;

import controller.MazeController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MazeModel;
import view.*;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/15:50
 * @Description:
 */
public class Main extends Application {
    int characterNum = 0;
//    String filename;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MazeController mazeController = new MazeController("Level 0",characterNum);

        primaryStage.setTitle("Model - View - Controller");

        primaryStage.setScene(new Scene(mazeController.mazeView, 800, 680));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
