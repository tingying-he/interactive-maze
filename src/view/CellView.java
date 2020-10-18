package view;

import application.Main;
import controller.CellController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.MazeModel;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class CellView extends Pane {
    //    private Pane cellContent = new Pane();
    CellController cellController;

    public CellView(CellController cellController) {
        this.cellController = cellController;
        init(cellController.type);
    }


    public void init(int type) {

        if (type == 0) {
            Image wallImg = new Image("img/wall.png");
            ImageView wallImgView = new ImageView(wallImg);
            wallImgView.setFitWidth(MazeModel.panelSize);
            wallImgView.setFitHeight(MazeModel.panelSize);
            this.getChildren().add(wallImgView);

        } else if (type == 1) {
            Image whiteImg = new Image("img/white.png");
            ImageView whiteImgView = new ImageView(whiteImg);
            whiteImgView.setFitWidth(MazeModel.panelSize);
            whiteImgView.setFitHeight(MazeModel.panelSize);
            this.getChildren().add(whiteImgView);

        } else if (type == 2) { //
            Image bushImg = new Image("img/bush.png");
            ImageView bushImgView = new ImageView(bushImg);
            bushImgView.setFitWidth(MazeModel.panelSize);
            bushImgView.setFitHeight(MazeModel.panelSize);
            this.getChildren().add(bushImgView);

        }


    }
}
