package controller;

import model.BadGuyModel;
import model.KeyModel;
import model.MazeModel;
import view.BadGuyView;
import view.KeyView;

import java.util.Timer;

/**
 * Created by Tingying He on 2020/10/18.
 */
public class KeyController {
    public KeyModel keyModel;
    public KeyView keyView;
    public int x, y;


    public KeyController() {
        keyModel = new KeyModel(this);
        keyView = new KeyView(this);
    }
    /**
     * Make key randomly move to four directions
     */
    public void randomMove(){
        if(Math.random()<0.25){
            moveDown();
        }
        else if(Math.random()<0.5){
            moveLeft();
        }
        else if(Math.random()<0.75){
            moveRight();
        }
        else if(Math.random()<1){
            moveUp();
        }

    }

    public void moveLeft() {
        if(x > 0 && MazeModel.map[x-1][y] == 1){
            keyView.setTranslateX(this.x * MazeModel.panelSize-25);
            x--;
        }
    }

    public void moveRight() {
        if(x < MazeModel.columns-1 && MazeModel.map[x+1][y] == 1){
            keyView.setTranslateX(this.x * MazeModel.panelSize+25);
            x++;
        }
    }

    public void moveUp() {
        if(y > 0 && MazeModel.map[x][y-1] == 1){
            keyView.setTranslateY(this.y * MazeModel.panelSize - 25);
            y--;
        }
    }

    public void moveDown() {
        if(y < MazeModel.rows-1 && MazeModel.map[x][y+1] == 1){
            keyView.setTranslateY(this.y * MazeModel.panelSize + 25);
            y++;
        }
    }
}

