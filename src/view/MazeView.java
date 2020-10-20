package view;

import application.Main;
import controller.MazeController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.CellModel;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:39
 * @Description:
 */
public class MazeView extends BorderPane {
    public MazeController mazeController;

    public Pane mazePane = new Pane();
    public VBox statusPane = new VBox();

    //remaining time status pane
    private AnchorPane timePane = new AnchorPane();
    public Label remainTimeLabel = new Label(); //show remain time
    private Image clockIconImg = new Image("img/clock.png");
    public ImageView clockIconImgView = new ImageView(clockIconImg);
    public Label clockIntroLabel = new Label("Remaining Time");

    //star status pane
    private AnchorPane starPane = new AnchorPane();
    public Label starNumberLabel = new Label("0");
    Image starIconImg = new Image("img/star.png");
    public ImageView starIconImgView = new ImageView(starIconImg);
    public Label starIntroLabel = new Label("Magic Sticks");


    //key pane
    private AnchorPane keyPane = new AnchorPane();
    public Label keyNumberLabel = new Label("No");
    Image keyIconImg = new Image("img/key.png");
    public ImageView keyIconImgView = new ImageView(keyIconImg);
    public Label keyIntroLabel = new Label("Have Key");

    //btn
    public Button helpBtn = new Button("HELP");
    public Button restartBtn = new Button("RESTART");
    public Button exitBtn = new Button("EXIT");
    public VBox btnPane = new VBox();


    public MazeView(MazeController mazeController) {
        this.mazeController = mazeController;
        this.setFocusTraversable(true);
//        this.setStyle("-fx-background-color:#4D5156");
        this.setStyle("-fx-background-color:#292b2a");

        this.setCenter(mazePane);
        this.setRight(statusPane);
        statusPane.setPadding(new Insets(20,20,20,20));

        //status pane
        statusPane.getChildren().addAll(keyPane,timePane,starPane,btnPane);
        statusPane.setSpacing(3);
        //timePane
        clockIntroLabel.setLayoutX(80);
        clockIntroLabel.setLayoutY(10);
        clockIntroLabel.setTextFill(Color.WHITE);
        //timePane text
        remainTimeLabel.setFont(new Font("Arial", 30));
        remainTimeLabel.setLayoutX(80);
        remainTimeLabel.setLayoutY(30);
        remainTimeLabel.setTextFill(Color.WHITE);
        //timePane animation
        clockIconImgView.setFitHeight(50);
        clockIconImgView.setFitWidth(50);
        clockIconImgView.setX(10);
        clockIconImgView.setY(10);

        timePane.getChildren().addAll(remainTimeLabel,clockIconImgView,clockIntroLabel);

        //starPane for magic stick
        starIntroLabel.setLayoutX(80);
        starIntroLabel.setLayoutY(10);
        starIntroLabel.setTextFill(Color.WHITE);
        //star pane text
        starNumberLabel.setFont(new Font("Arial", 30));
        starNumberLabel.setLayoutX(80);
        starNumberLabel.setLayoutY(30);
        starNumberLabel.setTextFill(Color.WHITE);
        //star pane animation
        starIconImgView.setFitHeight(50);
        starIconImgView.setFitWidth(50);
        starIconImgView.setX(10);
        starIconImgView.setY(10);

        starPane.getChildren().addAll(starIntroLabel,starNumberLabel,starIconImgView);

        //keyPane
        keyIntroLabel.setLayoutX(80);
        keyIntroLabel.setLayoutY(10);
        keyIntroLabel.setTextFill(Color.WHITE);
        //star pane text
        keyNumberLabel.setFont(new Font("Arial", 25));
        keyNumberLabel.setLayoutX(80);
        keyNumberLabel.setLayoutY(30);
        keyNumberLabel.setTextFill(Color.WHITE);
        //star pane animation
        keyIconImgView.setFitHeight(50);
        keyIconImgView.setFitWidth(50);
        keyIconImgView.setX(10);
        keyIconImgView.setY(10);

        keyPane.getChildren().addAll(keyIntroLabel,keyNumberLabel,keyIconImgView);


        //btnPane
        btnPane.getChildren().addAll(helpBtn,restartBtn,exitBtn);
        btnPane.setAlignment(Pos.CENTER);
        btnPane.setSpacing(10);
        btnPane.setPadding(new Insets(160,0,0,0));

        //help button
        helpBtn.setPrefSize(100,35);
        helpBtn.setStyle("-fx-background-color:#FFE08A");
        helpBtn.setTextFill(Color.web("#0A1D10"));
        //restart button
        restartBtn.setPrefSize(100,35);
        restartBtn.setStyle("-fx-background-color:#FFE08A");
        restartBtn.setTextFill(Color.web("#0A1D10"));
        //exit button
        exitBtn.setPrefSize(100,35);
        exitBtn.setStyle("-fx-background-color:#F5EAE4");
        exitBtn.setTextFill(Color.web("#0A1D10"));



    }

}


