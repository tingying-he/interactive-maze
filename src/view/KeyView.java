package view;

import controller.BadGuyController;
import controller.KeyController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.MazeModel;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class KeyView extends StackPane {
    private ImageView key;
    public KeyController keyController;

    public KeyView(KeyController keyController){
        this.keyController = keyController;

        Image img = new Image("img/key.png");
        key = new ImageView(img);
        key.setFitWidth(MazeModel.panelSize);
        key.setFitHeight(MazeModel.panelSize);
        this.getChildren().add(key);
    }
}
