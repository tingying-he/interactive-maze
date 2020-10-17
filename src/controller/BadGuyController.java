package controller;

import application.Main;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.BadGuyModel;
import model.MazeModel;
import view.BadGuyView;
import view.MazeView;

import java.security.PublicKey;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tingying He on 2020/10/13.
 */
public class BadGuyController {
    public BadGuyModel badGuyModel;
    public BadGuyView badGuyView;
    Timer timer;
    public int x, y;
    public int badguyNum;


    public BadGuyController(int badguyNum) {
        this.badguyNum = badguyNum;
        badGuyModel = new BadGuyModel(this);
        badGuyView = new BadGuyView(this,badguyNum);
    }

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
            badGuyView.setTranslateX(this.x * Main.panelSize-25);
            x--;
        }
    }

    public void moveRight() {
        if(x < MazeModel.columns-1 && MazeModel.map[x+1][y] == 1){
            badGuyView.setTranslateX(this.x * Main.panelSize+25);
            x++;
        }
    }

    public void moveUp() {
        if(y > 0 && MazeModel.map[x][y-1] == 1){
            badGuyView.setTranslateY(this.y * Main.panelSize - 25);
            y--;
        }
    }

    public void moveDown() {
        if(y < MazeModel.rows-1 && MazeModel.map[x][y+1] == 1){
            badGuyView.setTranslateY(this.y * Main.panelSize + 25);
            y++;
        }
    }




    public void checkCollision(int playerX, int playerY, int x, int y){

        if (
                playerX == x && playerY == y
        ) {
            System.out.println("catch by bad guy!");
        }

    }
}