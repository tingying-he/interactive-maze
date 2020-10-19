package view;

import controller.PlayerController;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class LevelPage extends BorderPane {
    public Button easyBtn = new Button("EASY");
    public Button hardBtn = new Button("HARD");
    public Button backBtn = new Button("Back");
    public Label textLabel = new Label("Different difficulty of maze layout");
    public VBox btnBox = new VBox();

    public String characterColor;


    public LevelPage(String characterColor){
        this.characterColor = characterColor;

        //text
        textLabel.setTextFill(Color.web("#F5EAE4"));
        textLabel.setFont(new Font("Arial", 20));
        textLabel.setPadding(new Insets(0,0,75,0));

        //button
        easyBtn.setPrefSize(100,35);
        easyBtn.setStyle("-fx-background-color:#FFE08A");
        easyBtn.setTextFill(Color.web("#0A1D10"));
        hardBtn.setPrefSize(100,35);
        hardBtn.setStyle("-fx-background-color:#FFE08A");
        hardBtn.setTextFill(Color.web("#0A1D10"));
        backBtn.setPrefSize(100,35);
        backBtn.setStyle("-fx-background-color:#F5EAE4");
        backBtn.setTextFill(Color.web("#0A1D10"));

        btnBox.getChildren().addAll(textLabel,easyBtn,hardBtn,backBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setPadding(new Insets(80,0,0,0));
        btnBox.setSpacing(15);

        this.setCenter(btnBox);

        //put the character into this page
        init(characterColor);

        //background
        BackgroundImage myBI= new BackgroundImage(new Image("img/levelpage.png",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

        this.setPadding(new Insets(0,0,40,0));
    }

    //put the character into this page
    public void init(String characterColor){
        //character imageview
        Image img = new Image("/img/character"+characterColor+".png");
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(140);
        imgView.setFitHeight(140);
        this.setBottom(imgView);

        //character animation
        TranslateTransition tt = new TranslateTransition(Duration.millis(6000), imgView);
        tt.setToX(650);
        tt.setToY(0);
        tt.setAutoReverse(true);
        tt.setCycleCount(Timeline.INDEFINITE);
        tt.play();


    }

}