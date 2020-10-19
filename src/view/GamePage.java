package view;

import controller.MazeController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class GamePage extends BorderPane {
    public String filename;
    String characterColor;

    Pane toolbarPane;
    public MazeController mazeController;

    public VBox statusPane = new VBox();
    public Label remainTimeLabel = new Label();





    public GamePage(String filename, String characterColor){
        this.filename = filename;
        this.characterColor = characterColor;
        this.statusPane.getChildren().add(remainTimeLabel);
        init(filename,characterColor);

    }

    public void init(String filename, String characterColor){
        this.setPrefSize(800,600);
        toolbarPane = new Pane();
        toolbarPane.setPrefSize(800,100);
        toolbarPane.setStyle("-fx-background-color:LIGHTGREY");

        mazeController = new MazeController(filename,characterColor);

        this.setTop(toolbarPane);
        this.setCenter(mazeController.mazeView);

    }



}
