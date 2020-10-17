package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.PlayerModel;
import view.PlayerView;

import java.security.PublicKey;

/**
 * Created by Tingying He on 2020/10/17.
 */
public class PlayerController {
    public int characterNum;
    public PlayerModel playerModel;
    public PlayerView playerView;

    public PlayerController(int characterNum){

        playerModel = new PlayerModel(this);
        playerView = new PlayerView(this);
//        addKeyListener();

    }



//    public void keyEventHandler(KeyEvent event) {
//        KeyCode key = event.getCode();
//
//
//        //Player movement
//        if(key == KeyCode.UP){
//            playerView.moveUp();
//        }
//        if(key == KeyCode.DOWN){
//            playerView.moveDown();
//        }
//        if(key == KeyCode.LEFT){
//            playerView.moveLeft();
//        }
//        if(key == KeyCode.RIGHT){
//            playerView.moveRight();
//        }
//    }

}

