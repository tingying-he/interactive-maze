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
    public String characterColor;
    public PlayerModel playerModel;
    public PlayerView playerView;

    public PlayerController(String characterColor){
        this.characterColor = characterColor;

        playerModel = new PlayerModel(this);
        playerView = new PlayerView(this);

    }

}

