package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class HelpPage extends AnchorPane {
    public Text helpText;
    public Image helpImg;
    public ImageView helpImgView;
    public Button backBtn;

    public HelpPage(){
        helpText = new Text("How to play this game?");

        helpImg =  new Image("/img/test.gif");
        helpImgView = new ImageView(helpImg);

        backBtn = new Button("BACK");
        backBtn.setPrefSize(100,40);
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-background-color:RED");

//        this.getChildren().addAll(helpText,helpImgView,backBtn);
//        this.setSpacing(15);
//        this.setAlignment(Pos.CENTER);
        Image img = new Image("img/tutorial-bubble.gif");
        ImageView imageView = new ImageView(img);
        this.getChildren().add(imageView);
        imageView.setLayoutX(100);
        imageView.setLayoutY(300);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
    }
}
