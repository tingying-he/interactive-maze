package model;

import controller.PlayerController;

/**
 * Created by Tingying He on 2020/10/17.
 */
public class PlayerModel {
    public PlayerController playerController;

    public PlayerModel(PlayerController playerController){
        this.playerController = playerController;

    }
}
