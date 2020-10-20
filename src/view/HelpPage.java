package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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

//        helpImg =  new Image("/img/test.gif");
//        helpImgView = new ImageView(helpImg);

        backBtn = new Button("MAZE");
//        backBtn.setPrefSize(100,40);
//        backBtn.setTextFill(Color.WHITE);
//        backBtn.setStyle("-fx-background-color:RED");
        backBtn.setLayoutX(350);
        backBtn.setLayoutY(550);
        backBtn.setPrefSize(100,35);
        backBtn.setTextFill(Color.web("#0A1D10"));
        backBtn.setStyle("-fx-background-color:#FFE08A");

        this.getChildren().addAll(backBtn);
//        this.setSpacing(15);
//        this.setAlignment(Pos.CENTER);
        Image img = new Image("img/help1.png");
        ImageView imageView = new ImageView(img);
        this.getChildren().add(imageView);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitHeight(240);
        imageView.setFitWidth(800);

        Image img2 = new Image("img/help2.png");
        ImageView imageView2 = new ImageView(img2);
        this.getChildren().add(imageView2);
        imageView2.setLayoutX(135);
        imageView2.setLayoutY(240);
        imageView2.setFitHeight(280);
        imageView2.setFitWidth(300);

        Image img3 = new Image("img/help3.png");
        ImageView imageView3= new ImageView(img3);
        this.getChildren().add(imageView3);
        imageView3.setLayoutX(452);
        imageView3.setLayoutY(355);
        imageView3.setFitHeight(165);
        imageView3.setFitWidth(315);

        Image bubbleImg = new Image("img/tutorial-bubble.gif");
        ImageView bubbleImgView = new ImageView(bubbleImg);
        bubbleImgView.setTranslateX(1);
        bubbleImgView.setTranslateY(248);
        bubbleImgView.setFitHeight(70);
        bubbleImgView.setFitWidth(130);
        this.getChildren().add(bubbleImgView);

        Image axeImg = new Image("img/tutorial-axe.gif");
        ImageView axeImgView = new ImageView(axeImg);
        axeImgView.setTranslateX(1);
        axeImgView.setTranslateY(416);
        axeImgView.setFitHeight(70);
        axeImgView.setFitWidth(130);
        this.getChildren().add(axeImgView);


        Image starImg = new Image("img/tutorial-star.gif");
        ImageView starImgView = new ImageView(starImg);
        starImgView.setLayoutX(452);
        starImgView.setLayoutY(242);
        starImgView.setFitHeight(110);
        starImgView.setFitWidth(200);
        this.getChildren().add(starImgView);




        this.setStyle("-fx-background-color:#373737");
    }
}
