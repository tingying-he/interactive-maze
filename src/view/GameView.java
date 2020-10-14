package view;

/**
 * Created by Tingying He on 2020/10/14.
 */

import controller.PlayerController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/16:50
 * @Description:
 */
public class GameView extends BorderPane {
    private Pane topPane;
    private Pane centerPane;

    public MazeView myMazeView;

    public int characterNum;

    /** Construct the application */
    public GameView(String filename,int characterNum) {
        //	TODO: Controller

        // Initialize the GUI
        initUI(filename, characterNum);

        // Resize window and make it visible
        setVisible(true);
    }


    public void initUI(String filename,int characterNum) {
        topPane = new Pane();
        topPane.setPrefHeight(80);
        topPane.setStyle("-fx-background-color:LIGHTGREY");

        centerPane = new Pane();
        centerPane.setPrefSize(800, 520);
//		centerPane.setStyle("-fx-background-color:GREEN");

        myMazeView = new MazeView(filename, characterNum);
        PlayerController myPlayerController = new PlayerController(myMazeView);
        myPlayerController.addKeyListener();

        centerPane.getChildren().add(myMazeView);

        myMazeView.setPrefSize(500,500);
        myMazeView.layoutXProperty().bind(centerPane.widthProperty().subtract(myMazeView.widthProperty()).divide(2));
        myMazeView.layoutYProperty().bind(centerPane.heightProperty().subtract(myMazeView.heightProperty()).divide(2));

//		double w = (centerPane.getWidth()/2)-(myMazeView.getWidth()/2);
//		double h = (centerPane.getHeight()/2)-(myMazeView.getHeight()/2);
//		myMazeView.setLayoutX(w);
//		myMazeView.setLayoutY(h);



        this.setTop(topPane);
        this.setCenter(centerPane);

    }
}