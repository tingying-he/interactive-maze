package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Cell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.MazeModel;
import view.MazeView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class MazeController {
    public String filename;
    int characterNum;
    public int panelSize;

    public MazeModel mazeModel ;
    public MazeView mazeView;
    public Timer timer;


    public MazeController(String filename, int characterNum, int panelSize){
        this.filename = filename;
        this.characterNum = characterNum;
        this.panelSize = panelSize;
        mazeModel = new MazeModel(this);
        mazeView  = new MazeView(this);
        init();
    }

    public void init(){
        mazeModel.cellControllers = new CellController[mazeModel.rows][mazeModel.columns];
        createCellsGrid();
        setBadguy();
        setPlayer();
        addKeyListener();
        addMouseEventListener();

    }

    private void createCellsGrid() {
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                mazeModel.cellControllers[i][j] = new CellController(mazeModel.getMap()[i][j]);
                mazeModel.cellControllers[i][j].cellView.setTranslateX(i * panelSize);
                mazeModel.cellControllers[i][j].cellView.setTranslateY(j * panelSize);

                mazeView.getChildren().add(mazeModel.cellControllers[i][j].cellView);

            }
    }

    public void setBadguy(){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 4  && j == 4){
                    mazeModel.badGuyController0.badGuyView.setTranslateX(i *  panelSize);
                    mazeModel.badGuyController0.badGuyView.setTranslateY(j *  panelSize);
                    mazeModel.badGuyController0.x = i;
                    mazeModel.badGuyController0.y = j;
                    mazeModel.badGuyController0.badGuyView.toFront();
                }
                if(i == 16 && j == 16){
                    mazeModel.badGuyController1.badGuyView.setTranslateX(i *  panelSize);
                    mazeModel.badGuyController1.badGuyView.setTranslateY(j *  panelSize);
                    mazeModel.badGuyController1.x = i;
                    mazeModel.badGuyController1.y = j;
                    mazeModel.badGuyController1.badGuyView.toFront();
                }
            }
        mazeView.getChildren().addAll( mazeModel.badGuyController0.badGuyView,mazeModel.badGuyController1.badGuyView);
        setBadGuyMoveTimer();
    }

    public void setPlayer(){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 0  && mazeModel.map[i][j] == 1){
                    mazeModel.playerController.playerView.setTranslateX(i *  panelSize);
                    mazeModel.playerController.playerView.setTranslateY(j *  panelSize);
                    mazeModel.playerController.playerView.x = i;
                    mazeModel.playerController.playerView.y = j;
                }
            }
        mazeModel.playerController.playerView.toFront();
        mazeView.getChildren().addAll( mazeModel.playerController.playerView);
    }

    public void addKeyListener() {
        mazeView.setOnMouseClicked(event -> mazeView.requestFocus());
        mazeView.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode().toString()) {
                    case "UP":
                        mazeModel.playerController.playerView.moveUp();
                        checkCollision();
                        System.out.println("UP");
                        break;
                    case "DOWN":
                        mazeModel.playerController.playerView.moveDown();
                        checkCollision();
                        System.out.println("down");
                        break;
                    case "LEFT":
                        mazeModel.playerController.playerView.moveLeft();
                        checkCollision();
                        System.out.println("left");
                        break;
                    case "RIGHT":
                        mazeModel.playerController.playerView.moveRight();
                        checkCollision();
                        System.out.println("right");
                        break;
                }

                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("Enter Pressed");
                }
            }
        });
    }

    public void addMouseEventListener(){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                int a = i;
                int b = j;
                if (mazeModel.getMap()[i][j] == 2) {
                    mazeModel.cellControllers[i][j].cellView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                if (mouseEvent.getClickCount() == 5) {
                                    System.out.println("5 clicked");

                                    int[][] map = mazeModel.getMap();
                                    map[a][b] = 1;
                                    mazeModel.setMap(map);
                                    mazeModel.cellControllers[a][b].cellView.init(mazeModel.getMap()[a][b]);
                                }
                            }
                        }
                    });
                }
            }}

    public void setBadGuyMoveTimer(){
        try {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mazeModel.badGuyController0.randomMove();
                            mazeModel.badGuyController1.randomMove();
                            checkCollision();
                        }
                    });


                }
            }, 0, 500);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkCollision(){
        if( mazeModel.playerController.playerView.x ==  mazeModel.badGuyController0.x
                &&  mazeModel.playerController.playerView.y ==  mazeModel.badGuyController0.y){
            System.out.println("Catch by bad guy!");
            lose();
        }
        if( mazeModel.playerController.playerView.x ==  mazeModel.badGuyController1.x
                &&  mazeModel.playerController.playerView.y ==  mazeModel.badGuyController1.y){
            System.out.println("Catch by bad guy!");
            lose();
        }

    }

    public void lose(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("GAME OVER");
        alert.setContentText("you are caught by a bad guy!");
        alert.show();
    }

}

