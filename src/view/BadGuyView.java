package view;

import application.Main;
import controller.BadGuyController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.MazeModel;

/**
 * Created by Tingying He on 2020/10/17.
 */
public class BadGuyView extends StackPane {
    private ImageView badGuy;
    public BadGuyController badGuyController;

    public BadGuyView(BadGuyController badGuyController,int badguyNum){
        this.badGuyController = badGuyController;

        Image img = new Image("/img/badguy"+badguyNum+".png");
        badGuy = new ImageView(img);
        badGuy.setFitWidth(MazeModel.panelSize);
        badGuy.setFitHeight(MazeModel.panelSize);
        this.getChildren().add(badGuy);
    }

}
