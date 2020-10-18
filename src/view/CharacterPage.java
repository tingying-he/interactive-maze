package view;

import controller.PlayerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class CharacterPage extends BorderPane {


    public Text characterTitle;
    public Button selectBtn;
    public Button backBtn;
    public Button changeBtn;

    public int characterNum;



    public ImageView characterImgView;
    public PlayerController playerController;


    HBox btnBox = new HBox();

    public CharacterPage(int characterNum) {
        this.characterNum = characterNum;

        characterTitle = new Text("Choose your character");

        playerController = new PlayerController(characterNum);

        selectBtn = new Button("SELECT");
        selectBtn.setPrefSize(100,40);
        selectBtn.setStyle("-fx-background-color:RED");
        selectBtn.setTextFill(Color.WHITE);
        backBtn = new Button("BACK");
        backBtn.setPrefSize(100,40);
        backBtn.setStyle("-fx-background-color:RED");
        backBtn.setTextFill(Color.WHITE);
        changeBtn = new Button("CHANGE");
        changeBtn.setPrefSize(100,40);
        changeBtn.setStyle("-fx-background-color:RED");
        changeBtn.setTextFill(Color.WHITE);

        btnBox.getChildren().addAll(changeBtn,selectBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(10);

        characterImgView = playerController.playerView.putCharacter(300,300,characterNum);

        this.setTop(characterTitle);
        this.setCenter(characterImgView);
        this.setBottom(btnBox);
        this.setPadding(new Insets(10,10,10,10));

    }
    public void repaint(int characterNum){
        characterImgView = playerController.playerView.putCharacter(300,300,characterNum);
        this.setCenter(characterImgView);
    }



}
