package controller;

import javafx.scene.control.Cell;
import model.MazeModel;
import view.MazeView;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class MazeController {
    public String filename;
    int characterNum;
    public static int panelSize = 25;

    public MazeModel mazeModel ;
    public MazeView mazeView;


    public MazeController(String filename, int characterNum){
        this.filename = filename;
        this.characterNum = characterNum;
        mazeModel = new MazeModel(this);
        mazeView  = new MazeView(this);
        init();
    }

    public void init(){
        mazeModel.cellControllers = new CellController[mazeModel.columns][mazeModel.rows];
        createCellsGrid();
//        mazeModel.init();
    }

    private void createCellsGrid() {
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                mazeModel.cellControllers[i][j] = new CellController(mazeModel.getMap()[i][j]);
//                System.out.println(mazeModel.getMap()[i][j]);
                mazeModel.cellControllers[i][j].cellView.setTranslateX(i * panelSize);
                mazeModel.cellControllers[i][j].cellView.setTranslateY(j * panelSize);
                mazeView.getChildren().add(mazeModel.cellControllers[i][j].cellView);
            }
    }


}
