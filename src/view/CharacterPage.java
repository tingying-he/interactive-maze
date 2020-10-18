package view;

import controller.PlayerController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class CharacterPage extends BorderPane {


//    public Text characterTitle;
//    public Button selectBtn;
//    public Button backBtn;
//    public Button changeBtn;
//
//    public int characterNum;
//
//
//
//    public ImageView characterImgView;
//    public PlayerController playerController;
//
//
//    HBox btnBox = new HBox();
//
//    public CharacterPage(int characterNum) {
//        this.characterNum = characterNum;
//
//        characterTitle = new Text("Choose your character");
//
//        playerController = new PlayerController(characterNum);
//
//        selectBtn = new Button("SELECT");
//        selectBtn.setPrefSize(100,40);
//        selectBtn.setStyle("-fx-background-color:RED");
//        selectBtn.setTextFill(Color.WHITE);
//        backBtn = new Button("BACK");
//        backBtn.setPrefSize(100,40);
//        backBtn.setStyle("-fx-background-color:RED");
//        backBtn.setTextFill(Color.WHITE);
//        changeBtn = new Button("CHANGE");
//        changeBtn.setPrefSize(100,40);
//        changeBtn.setStyle("-fx-background-color:RED");
//        changeBtn.setTextFill(Color.WHITE);
//
//        btnBox.getChildren().addAll(changeBtn,selectBtn, backBtn);
//        btnBox.setAlignment(Pos.CENTER);
//        btnBox.setSpacing(10);
//

//
//        this.setTop(characterTitle);
//        this.setCenter(characterImgView);
//        this.setBottom(btnBox);
//        this.setPadding(new Insets(10,10,10,10));
//
//    }


    public String characterColor = "yellowblack";
    public String eyeColor = "yellow";
    public String bodyColor = "black";
        public Button selectBtn;
    public Button backBtn;
    public HBox btnBox = new HBox();

    public GridPane settingPane = new GridPane();

    public ImageView characterImgView;
    public PlayerController playerController;

    public CharacterPage(){
        this.setLeft(settingPane);
        repaint(eyeColor,bodyColor);

        selectBtn = new Button("SELECT");
        selectBtn.setPrefSize(100,40);
        selectBtn.setStyle("-fx-background-color:RED");
        selectBtn.setTextFill(Color.WHITE);
        backBtn = new Button("BACK");
        backBtn.setPrefSize(100,40);
        backBtn.setStyle("-fx-background-color:RED");
        backBtn.setTextFill(Color.WHITE);
                btnBox.getChildren().addAll(selectBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(10);
        this.setBottom(btnBox);


        Image eye0Img = new Image("img/eye0.png");
        ImageView eye0ImgView = new ImageView(eye0Img);
        eye0ImgView.setFitHeight(100);
        eye0ImgView.setFitWidth(100);
        eye0ImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eyeColor = "yellow";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });


        Image eye1Img = new Image("img/eye1.png");
        ImageView eye1ImgView = new ImageView(eye1Img);
        eye1ImgView.setFitHeight(100);
        eye1ImgView.setFitWidth(100);
        eye1ImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eyeColor = "green";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });

        Image body0Img = new Image("img/body0.png");
        ImageView body0ImgView = new ImageView(body0Img);
        body0ImgView.setFitHeight(100);
        body0ImgView.setFitWidth(100);
        body0ImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bodyColor = "black";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });


        Image body1Img = new Image("img/body1.png");
        ImageView body1ImgView = new ImageView(body1Img);
        body1ImgView.setFitHeight(100);
        body1ImgView.setFitWidth(100);
        body1ImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bodyColor = "white";
                repaint(eyeColor,bodyColor);
                setCharacterColor(eyeColor,bodyColor);
            }
        });

        settingPane.add(eye0ImgView,0,0,1,1);
        settingPane.add(eye1ImgView,0,1,1,1);
        settingPane.add(body0ImgView,1,0,1,1);
        settingPane.add(body1ImgView,1,1,1,1);

    }

    public void repaint(String eyeColor,String bodyColor){
        Image characterImg = new Image("img/character"+eyeColor+bodyColor+".png");
        ImageView characterImgView = new ImageView(characterImg);
        characterImgView.setFitHeight(300);
        characterImgView.setFitWidth(300);

        this.setCenter(characterImgView);
    }

    public void setCharacterColor(String eyeColor, String bodyColor){
        this.characterColor = eyeColor + bodyColor;
    }
    public String getCharacterColor(){
        return characterColor;
    }


}
