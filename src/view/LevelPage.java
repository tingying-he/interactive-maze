package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class LevelPage extends VBox {
    public Button easyBtn = new Button("EASY");
    public Button hardBtn = new Button("HARD");
    public Button backBtn = new Button("Back");

    public LevelPage(){
        easyBtn.setPrefSize(100,40);
        easyBtn.setStyle("-fx-background-color:RED");
        easyBtn.setTextFill(Color.WHITE);
        hardBtn.setPrefSize(100,40);
        hardBtn.setStyle("-fx-background-color:RED");
        hardBtn.setTextFill(Color.WHITE);
        backBtn.setPrefSize(100,40);
        backBtn.setStyle("-fx-background-color:RED");
        backBtn.setTextFill(Color.WHITE);

        this.getChildren().addAll(easyBtn,hardBtn,backBtn);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
    }

}