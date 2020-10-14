package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.MazeModel;

import java.beans.PropertyChangeSupport;


/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:56
 * @Description:
 */
public class PlayerView extends StackPane {
    public int x, y;
    public PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void setPlayerX(int playerX){
        this.x =playerX;
        pcs.firePropertyChange("playerX", x,this.x);
    }
//    public int characterNum;

//    ChooseCharacterView chooseCharacterView = new ChooseCharacterView();
    private ImageView player;


    public PlayerView(int characterNum) {
        player = putCharacter(MazeModel.panelSize,MazeModel.panelSize,characterNum);
        this.getChildren().addAll(player);
    }
    public void init(int characterNum){
        player = putCharacter(MazeModel.panelSize,MazeModel.panelSize,characterNum);
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

    public ImageView putCharacter(int w, int h, int characterNum ){
        Image img = new Image("/img/character"+characterNum+".png");
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(w);
        imgView.setFitHeight(h);

        return imgView;
    }


}
