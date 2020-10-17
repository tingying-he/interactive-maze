package controller;

import javafx.scene.control.Cell;
import model.MazeModel;
import view.MazeView;

import java.util.Timer;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class MazeController {
    public String filename;
    int characterNum;
    public int panelSize;

    public MazeModel mazeModel ;
    public MazeView mazeView;
    public Timer timer;


    public MazeController(String filename, int characterNum, int panelSize){
        this.filename = filename;
        this.characterNum = characterNum;
        this.panelSize = panelSize;
        mazeModel = new MazeModel(this);
        mazeView  = new MazeView(this);
        init();
    }

    public void init(){
        mazeModel.cellControllers = new CellController[mazeModel.rows][mazeModel.columns];
        createCellsGrid();
//        mazeModel.init();
        setBadguy();
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

    public void setBadguy(){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 4  && j == 4){
                    mazeModel.badGuyController0.badGuyView.setTranslateX(i *  panelSize);
                    mazeModel.badGuyController0.badGuyView.setTranslateY(j *  panelSize);
                    mazeModel.badGuyController0.x = i;
                    mazeModel.badGuyController0.y = j;
                    mazeModel.badGuyController0.badGuyView.toFront();
                }
                if(i == 16 && j == 16){
                    mazeModel.badGuyController1.badGuyView.setTranslateX(i *  panelSize);
                    mazeModel.badGuyController1.badGuyView.setTranslateY(j *  panelSize);
                    mazeModel.badGuyController1.x = i;
                    mazeModel.badGuyController1.y = j;
                    mazeModel.badGuyController1.badGuyView.toFront();
                }
            }
        mazeView.getChildren().addAll( mazeModel.badGuyController0.badGuyView,mazeModel.badGuyController1.badGuyView);
        mazeModel.badGuyController0.setBadGuyMoveTimer();
        mazeModel.badGuyController1.setBadGuyMoveTimer();
    }


}
