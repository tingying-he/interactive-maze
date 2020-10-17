package view;

import application.Main;
import controller.PlayerController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.MazeModel;



/**
 * Created by Tingying He on 2020/10/17.
 */
public class PlayerView extends StackPane {
    public PlayerController playerController;

    public int x, y;
    private ImageView player;

    public PlayerView(PlayerController playerController){
        this.playerController = playerController;
        init(playerController.characterNum);
    }
    public void init(int characterNum){
        player = putCharacter(Main.panelSize,Main.panelSize,characterNum);
        this.getChildren().addAll(player);
    }

    public void moveLeft() {
        if(x > 0 && MazeModel.map[x-1][y] == 1){
            this.setTranslateX(this.x * Main.panelSize-25);
            x--;
        }
    }

    public void moveRight() {
        if(x < MazeModel.columns-1 && MazeModel.map[x+1][y] == 1){
            this.setTranslateX(this.x * Main.panelSize+25);
            x++;
        }
    }

    public void moveUp() {
        if(y > 0 && MazeModel.map[x][y-1] == 1){
            this.setTranslateY(this.y * Main.panelSize - 25);
            y--;
        }
    }

    public void moveDown() {
        if(y < MazeModel.rows-1 && MazeModel.map[x][y+1] == 1){
            this.setTranslateY(this.y * Main.panelSize + 25);
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
