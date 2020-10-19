package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class LaunchPage extends VBox {

    public Button startBtn;
//    public Button helpBtn;


    public LaunchPage(){


        //buttons style
        startBtn= new Button("START");
//        helpBtn = new Button("HELP");
        startBtn.setPrefSize(100,35);
        startBtn.setTextFill(Color.web("#0A1D10"));
        startBtn.setStyle("-fx-background-color:#FFE08A");

//        helpBtn.setPrefSize(100,35);
//        helpBtn.setTextFill(Color.web("#0A1D10"));
//        helpBtn.setStyle("-fx-background-color:#FFE08A");

        //page style
        this.setSpacing(15);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(startBtn);

        //background image
        BackgroundImage myBI= new BackgroundImage(new Image("img/lauchpage.png",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

    }

}