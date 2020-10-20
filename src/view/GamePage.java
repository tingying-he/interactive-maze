package view;

import controller.MazeController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class GamePage extends BorderPane {
    public String filename;
    String characterColor;

    public static HBox toolbarPane;
    public MazeController mazeController;

    public static ImageView axe0View;
    public static ImageView axe1View;
    public static ImageView axe2View;
    public static ImageView penView;
    public static Label starNum;

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
        toolbarPane = new HBox();
        toolbarPane.setPrefSize(800,100);
//        toolbarPane.setStyle("-fx-background-color:#4D5156");
        toolbarPane.setStyle("-fx-background-color:#292b2a");

        initToolbar(toolbarPane);

        mazeController = new MazeController(filename,characterColor);

        this.setTop(toolbarPane);
        this.setCenter(mazeController.mazeView);

    }

    private void initToolbar(Pane toolbarPane) {

        StackPane axe0Pane = new StackPane();
        Rectangle rec0 = new Rectangle(56,56);
        rec0.setFill(Color.web("#4D5156"));
        rec0.setStroke(Color.BLACK);
        Image axe0 = new Image("img/axe0.png", 50, 50, false, false);
        axe0View = new ImageView(axe0);
        axe0View.setFitWidth(40);
        axe0View.setFitHeight(40);
        axe0Pane.getChildren().addAll(rec0,axe0View);

        StackPane axe1Pane = new StackPane();
        Rectangle rec1 = new Rectangle(56,56);
        rec1.setFill(Color.web("#4D5156"));
        rec1.setStroke(Color.BLACK);
        Image axe1 = new Image("img/axe1.png", 50, 50, false, false);
        axe1View = new ImageView(axe1);
        axe1View.setFitWidth(40);
        axe1View.setFitHeight(40);
        axe1Pane.getChildren().addAll(rec1, axe1View);

        StackPane axe2Pane = new StackPane();
        Rectangle rec2 = new Rectangle(56,56);
        rec2.setFill(Color.web("#4D5156"));
        rec2.setStroke(Color.BLACK);
        Image axe2 = new Image("img/axe2.png", 40, 40, false, false);
        axe2View = new ImageView(axe2);
        axe2View.setFitWidth(40);
        axe2View.setFitHeight(40);
        axe2Pane.getChildren().addAll(rec2, axe2View);

        Rectangle rec4 = new Rectangle(56,56);
        rec4.setFill(Color.web("#292b2a"));
//        rec4.setStroke(Color.BLACK);

        StackPane penPane = new StackPane();
        Rectangle rec3 = new Rectangle(56,56);
        rec3.setFill(Color.web("#4D5156"));
        rec3.setStroke(Color.BLACK);
        Image pen = new Image("img/star.png", 40, 40, false, false);
        penView = new ImageView(pen);
        penView.setFitWidth(40);
        penView.setFitHeight(40);

        penView.setOpacity(0.3);
        starNum = new Label("0 ");
        starNum.setFont(new Font("Arial", 22));
        starNum.setTextFill(Color.WHITE);
//        pane.getChildren().add(starNum);
        penPane.getChildren().addAll(rec3, penView, starNum);
        StackPane.setAlignment(starNum, Pos.TOP_RIGHT);

        toolbarPane.setPadding(new Insets(20,20,20,40));
        toolbarPane.getChildren().addAll(axe0Pane, axe1Pane, axe2Pane, rec4, penPane);
    }



}
