package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.MazeModel;


import java.awt.*;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:56
 * @Description:
 */
public class PlayerView extends StackPane {
    int x, y;

    private Rectangle player = new Rectangle(MazeModel.panelSize, MazeModel.panelSize);


    public PlayerView() {
//        player.setFill(Color.hsb(0.3f,0.3f, 1.0));
        player.setFill(Color.RED);
        this.getChildren().addAll(player);
    }

    public void moveLeft() {
        if(x > 0 && MazeModel.map[x-1][y] == 1){
            this.setTranslateX(this.x * MazeView.panelSize-25);
            x--;
        }
    }

    public void moveRight() {
        if(x < MazeModel.columns-1 && MazeModel.map[x+1][y] == 1){
            this.setTranslateX(this.x * MazeView.panelSize+25);
            x++;
        }
    }

    public void moveUp() {
        if(y > 0 && MazeModel.map[x][y-1] == 1){
            this.setTranslateY(this.y * MazeView.panelSize - 25);
            y--;
        }
    }

    public void moveDown() {
        if(y < MazeModel.rows-1 && MazeModel.map[x][y+1] == 1){
            this.setTranslateY(this.y * MazeView.panelSize + 25);
            y++;
        }
    }
}
