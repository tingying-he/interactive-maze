package view;

import controller.MazeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.MazeModel;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class GamePage extends BorderPane {
    public String filename;
    int characterNum;

    public static HBox toolbarPane;
    public MazeController mazeController;

    public static ImageView axe0View;
    public static ImageView axe1View;
    public static ImageView axe2View;
    public static ImageView tree0View;


    public GamePage(String filename, int characterNum){
        this.filename = filename;
        this.characterNum = characterNum;
        init(filename,characterNum);

    }

    public void init(String filename, int characterNum){
        this.setPrefSize(800,600);
        toolbarPane = new HBox();
        toolbarPane.setPrefSize(800,100);
        toolbarPane.setStyle("-fx-background-color:LIGHTGREY");
        initToolbar(toolbarPane);

        mazeController = new MazeController(filename,characterNum);

        this.setTop(toolbarPane);
        this.setCenter(mazeController.mazeView);

    }

    private void initToolbar(Pane toolbarPane) {

        StackPane axe0Pane = new StackPane();
        Rectangle rec0 = new Rectangle(40,40);
        rec0.setFill(Color.WHITE);
        Image axe0 = new Image("img/tool/axe0.png");
        axe0View = new ImageView(axe0);
        axe0View.setFitWidth(40);
        axe0View.setFitHeight(40);
        axe0Pane.getChildren().addAll(rec0,axe0View);

        StackPane axe1Pane = new StackPane();
        Rectangle rec1 = new Rectangle(40,40);
        rec1.setFill(Color.WHITE);
        Image axe1 = new Image("img/tool/axe1.png", 40, 40, false, false);
        axe1View = new ImageView(axe1);
//        axe1View.setFitWidth(40);
//        axe1View.setFitHeight(40);
        axe1Pane.getChildren().addAll(rec1, axe1View);

        StackPane axe2Pane = new StackPane();
        Rectangle rec2 = new Rectangle(40,40);
        rec2.setFill(Color.WHITE);
        Image axe2 = new Image("img/tool/axe2.png", 40, 40, false, false);
        axe2View = new ImageView(axe2);
        axe2View.setFitWidth(40);
        axe2View.setFitHeight(40);
        axe2Pane.getChildren().addAll(rec2, axe2View);

        StackPane tree0Pane = new StackPane();
        Rectangle rec3 = new Rectangle(40,40);
        rec3.setFill(Color.WHITE);
        Image tree0 = new Image("img/tool/tree0.png");
        tree0View = new ImageView(tree0);
        tree0View.setFitWidth(40);
        tree0View.setFitHeight(40);
        tree0Pane.getChildren().addAll(rec3, tree0View);


        toolbarPane.setPadding(new Insets(20,40,20,40));
        toolbarPane.getChildren().addAll(axe0Pane, axe1Pane, axe2Pane, tree0Pane);
    }



}
