package controller;

//import com.sun.deploy.security.AbstractBrowserAuthenticator;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import view.MazeView;

import javafx.scene.input.KeyEvent;

import javafx.event.EventHandler;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:55
 * @Description:
 */
public class PlayerController {
    MazeView myMazeView;

    public PlayerController(MazeView mazeView) {
        this.myMazeView = mazeView;
//        System.out.println("wwwww");

    }

    public void addKeyListener() {


        myMazeView.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode().toString()) {
                    case "UP":
                        myMazeView.player.moveUp();
                        System.out.println("UP");
                        break;
                    case "DOWN":
                        myMazeView.player.moveDown();
                        System.out.println("down");
                        break;
                    case "LEFT":
                        myMazeView.player.moveLeft();
                        System.out.println("left");
                        break;
                    case "RIGHT":
                        myMazeView.player.moveRight();
                        System.out.println("right");
                        break;
                }

                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("Enter Pressed");
                }
            }
        });
    }


    public void keyEventHandler(KeyEvent event) {
        KeyCode key = event.getCode();


        //Player movement
        if(key == KeyCode.UP){
            myMazeView.player.moveUp();
        }
        if(key == KeyCode.DOWN){
            myMazeView.player.moveDown();
        }
        if(key == KeyCode.LEFT){
            myMazeView.player.moveLeft();
        }
        if(key == KeyCode.RIGHT){
            myMazeView.player.moveRight();
        }
    }

}
