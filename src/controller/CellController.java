package controller;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.CellModel;
import view.CellView;
import view.GamePage;
import view.MazeView;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class CellController {
    public int type;
    public CellView cellView;
    public CellModel cellModel;

    double orgX;
    double orgY;

    public CellController(int type) {
        this.type = type;

        cellView = new CellView(this);
        cellModel = new CellModel(this);

        init();
    }

    public void init() {
        addDrawListener(this.cellView);
    }

    public void setType(int type) {
        this.type = type;
    }

    public void addDrawListener(CellView cellView) {
        if (type == 1) {
            cellView.setOnMouseEntered((event) -> {
                orgX = event.getSceneX();
                orgY = event.getSceneY();
            });
            cellView.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    double changeInX = mouseEvent.getSceneX() - orgX;
                    double changeInY = mouseEvent.getSceneY() - orgY;
                    Image whiteImg;
                    if (mouseEvent.isShiftDown()
                            && Main.pressedKeys.contains(KeyCode.D)
                            && MazeController.canDraw) {
//                        System.out.println("zhu zhu huahua");
                        cellView.isPath = true;
                        if (changeInX > 0) {
                            System.out.println("moving right");
                            whiteImg = new Image("img/arrowright.png");
                            cellView.whiteImgView.setImage(whiteImg);
                        } else if (changeInX < 0) {
                            System.out.println("moving left");
                            whiteImg = new Image("img/arrowleft.png");
                            cellView.whiteImgView.setImage(whiteImg);
                        }
                        if (changeInY > 0) {
                            System.out.println("moving down");
                            whiteImg = new Image("img/arrowdown.png");
                            cellView.whiteImgView.setImage(whiteImg);
                        } else if (changeInY < 0) {
                            System.out.println("moving up");
                            whiteImg = new Image("img/arrowup.png");
                            cellView.whiteImgView.setImage(whiteImg);
                        }
                    }
                }
            });
        }
    }
}

