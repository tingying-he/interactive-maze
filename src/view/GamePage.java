package view;

import controller.MazeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class GamePage extends BorderPane {
    public String filename;
    int characterNum;

    Pane toolbarPane;
    public MazeController mazeController;


    public GamePage(String filename, int characterNum){
        this.filename = filename;
        this.characterNum = characterNum;
        init(filename,characterNum);

    }

    public void init(String filename, int characterNum){
        this.setPrefSize(800,600);
        toolbarPane = new Pane();
        toolbarPane.setPrefSize(800,100);
        toolbarPane.setStyle("-fx-background-color:LIGHTGREY");

        mazeController = new MazeController(filename,characterNum);

        this.setTop(toolbarPane);
        this.setCenter(mazeController.mazeView);

    }

}
