package view;

import controller.PlayerController;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;




/**
 * Created by Tingying He on 2020/10/18.
 */
public class CharacterPage extends BorderPane {


    public String characterColor = "greenblack";
    public String eyeColor = "green";
    public String bodyColor = "black";

    //button pane
    public Button selectBtn;
    public Button backBtn;
    public HBox btnBox = new HBox();

    //eye color pane
//    public GridPane settingPane = new GridPane();
    public VBox eyePane = new VBox();

    //body color pane
    public VBox bodyPane = new VBox();

//    public ImageView characterImgView;
//    public PlayerController playerController;




    public CharacterPage(){
        this.setRight(eyePane);
        this.setLeft(bodyPane);
        this.setBottom(btnBox);
        repaint(eyeColor,bodyColor);

        selectBtn = new Button("SELECT");
        selectBtn.setPrefSize(100,40);
        selectBtn.setStyle("-fx-background-color:#FFBF00");
        selectBtn.setTextFill(Color.BLACK);
        backBtn = new Button("BACK");
        backBtn.setPrefSize(100,40);
        backBtn.setStyle("-fx-background-color:#FFBF00");
        backBtn.setTextFill(Color.BLACK);
        btnBox.getChildren().addAll(selectBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(10);


        Image eyeyellowImg = new Image("img/eyeyellow.png");
        ImageView eyeyellowImgView = new ImageView(eyeyellowImg);
        eyeyellowImgView.setFitHeight(100);
        eyeyellowImgView.setFitWidth(100);
        eyeyellowImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eyeColor = "yellow";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });


        Image eyegreenImg = new Image("img/eyegreen.png");
        ImageView eyegreenImgView = new ImageView(eyegreenImg);
        eyegreenImgView.setFitHeight(100);
        eyegreenImgView.setFitWidth(100);
        eyegreenImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eyeColor = "green";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });

        Image eyeblueImg = new Image("img/eyeblue.png");
        ImageView eyeblueImgView = new ImageView(eyeblueImg);
        eyeblueImgView.setFitHeight(100);
        eyeblueImgView.setFitWidth(100);
        eyeblueImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eyeColor = "blue";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });

        eyePane.getChildren().addAll(eyeyellowImgView,eyegreenImgView,eyeblueImgView);
        eyePane.setSpacing(10);
        eyePane.setPadding(new Insets(50,0,0,0));

        Image bodywhiteImg = new Image("img/bodywhite.png");
        ImageView bodywhiteImgView = new ImageView(bodywhiteImg);
        bodywhiteImgView.setFitHeight(100);
        bodywhiteImgView.setFitWidth(100);
        bodywhiteImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bodyColor = "white";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });


        Image bodyblackImg = new Image("img/bodyblack.png");
        ImageView bodyblackImgView = new ImageView(bodyblackImg);
        bodyblackImgView.setFitHeight(100);
        bodyblackImgView.setFitWidth(100);
        bodyblackImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bodyColor = "black";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });

        Image bodyorangeImg = new Image("img/bodyorange.png");
        ImageView bodyorangeImgView = new ImageView(bodyorangeImg);
        bodyorangeImgView.setFitHeight(100);
        bodyorangeImgView.setFitWidth(100);
        bodyorangeImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bodyColor = "orange";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });

        bodyPane.getChildren().addAll(bodywhiteImgView,bodyblackImgView,bodyorangeImgView);
        bodyPane.setSpacing(20);
        bodyPane.setPadding(new Insets(50,0,0,0));
        this.setPadding(new Insets(80,100,30,100));

        BackgroundImage myBI= new BackgroundImage(new Image("img/characterpage.png",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

    }

    public void repaint(String eyeColor,String bodyColor){
        Image characterImg = new Image("img/character"+eyeColor+bodyColor+".png");
        ImageView characterImgView = new ImageView(characterImg);
        characterImgView.setFitHeight(240);
        characterImgView.setFitWidth(240);


        this.setCenter(characterImgView);

    }

    public void setCharacterColor(String eyeColor, String bodyColor){
        this.characterColor = eyeColor + bodyColor;
    }
    public String getCharacterColor(){
        return characterColor;
    }



}
