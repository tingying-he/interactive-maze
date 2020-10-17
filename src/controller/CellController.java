package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.CellModel;
import view.CellView;
import view.MazeView;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class CellController {
    public int type;
    public CellView cellView;
    public CellModel cellModel;

    public CellController(int type){
        this.type = type;

        cellView = new CellView(this);
        cellModel = new CellModel(this);
    }



    public void setType(int type){
        this.type = type;
    }

}

