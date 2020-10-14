package controller;

import javafx.scene.control.Cell;
import model.CellModel;
import view.CellView;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class CellController {
    CellView cellView = new CellView(this);
    CellModel cellModel = new CellModel(this);

}
