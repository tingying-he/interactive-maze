package controller;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.MazeModel;
import view.MazeView;

import java.security.PublicKey;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tingying He on 2020/10/13.
 */
public class BadGuysController {
    public StackPane badGuyPane = new StackPane();
    Timer timer;
    public int x, y;
    private ImageView badGuy;
    public BadGuysController() {
        Image img = new Image("/img/badguy.png");
        badGuy = new ImageView(img);
        badGuy.setFitWidth(MazeModel.panelSize);
        badGuy.setFitHeight(MazeModel.panelSize);

        badGuyPane.getChildren().addAll(badGuy);
    }
    public void init(int characterNum){

    }

    public void randomMove(){
        if(Math.random()<0.25){
            moveDown();
            System.out.println("bad guy move down");
        }
        else if(Math.random()<0.5){
            moveLeft();
            System.out.println("bad guy move left");
        }
        else if(Math.random()<0.75){
            moveRight();
            System.out.println("bad guy move right");
        }
        else if(Math.random()<1){
            moveUp();
            System.out.println("bad guy move up");
        }
//        moveRight();

    }

    public void moveLeft() {
        if(x > 0 && MazeModel.map[x-1][y] == 1){
            badGuyPane.setTranslateX(this.x * MazeView.panelSize-25);
            x--;
        }
    }

    public void moveRight() {
        if(x < MazeModel.columns-1 && MazeModel.map[x+1][y] == 1){
            badGuyPane.setTranslateX(this.x * MazeView.panelSize+25);
            x++;
        }
    }

    public void moveUp() {
        if(y > 0 && MazeModel.map[x][y-1] == 1){
            badGuyPane.setTranslateY(this.y * MazeView.panelSize - 25);
            y--;
        }
    }

    public void moveDown() {
        if(y < MazeModel.rows-1 && MazeModel.map[x][y+1] == 1){
            badGuyPane.setTranslateY(this.y * MazeView.panelSize + 25);
            y++;
        }
    }

    public void setBadGuyMoveTimer() {
        try {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
//                            remainTimeLabel.setText(gameModel.remainTime + "s");
                        }
                    });
//                    gameModel.spentTime++;
//                    gameModel.remainTime--;
                    randomMove();


                }
            }, 0, 1500);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
