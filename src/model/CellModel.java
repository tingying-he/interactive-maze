package model;

import controller.CellController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import view.MazeView;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class CellModel {


    public CellController cellController;



    public CellModel(CellController cellController){
        this.cellController = cellController;

    }



}

