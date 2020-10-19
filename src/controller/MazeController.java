package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.MazeModel;
import view.MazeView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class MazeController {
    public String filename;
    public String characterColor;


    public MazeModel mazeModel ;
    public MazeView mazeView;
    public Timer badguyTimer;
    public Timer keyTimer;


    public MazeController(String filename, String characterColor){
        this.filename = filename;
        this.characterColor = characterColor;
        mazeView = new MazeView(this);
        mazeModel = new MazeModel(this);
        mazeView  = new MazeView(this);
        setTimer();
        init(filename,characterColor);
        setBadGuyMoveTimer();
        setKeyMoveTimer();
        mazeView.restartBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            restart();
        });
        mazeView.exitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public void init(String filename, String characterColor){
        //remove all magic sticks and clocks in the mazepane(when restart)
        mazeView.mazePane.getChildren().clear();

        //create cells
        mazeModel.cellControllers = new CellController[mazeModel.rows][mazeModel.columns];
        createCellsGrid(filename);
        mazeModel.init();
        setBadguy();
        setPlayer(characterColor);
        setKey();
        addKeyListener();
        addMouseEventListener();


        mazeView.starNumberLabel.setText(Integer.toString(mazeModel.starNum));
        mazeView.keyNumberLabel.setText("No");
    }

    public void createCellsGrid(String filename) {
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                mazeModel.loadMaze(filename);
                mazeModel.cellControllers[i][j] = new CellController(mazeModel.getMap()[i][j]);
                mazeModel.cellControllers[i][j].cellView.setTranslateX(i * MazeModel.panelSize);
                mazeModel.cellControllers[i][j].cellView.setTranslateY(j * MazeModel.panelSize);

                mazeView.mazePane.getChildren().add(mazeModel.cellControllers[i][j].cellView);

            }
    }

    public void setBadguy(){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 4  && j == 4){
                    mazeModel.badGuyController0.badGuyView.setTranslateX(i *  MazeModel.panelSize);
                    mazeModel.badGuyController0.badGuyView.setTranslateY(j *  MazeModel.panelSize);
                    mazeModel.badGuyController0.x = i;
                    mazeModel.badGuyController0.y = j;
                    mazeModel.badGuyController0.badGuyView.toFront();
                }
                if(i == 16 && j == 16){
                    mazeModel.badGuyController1.badGuyView.setTranslateX(i *  MazeModel.panelSize);
                    mazeModel.badGuyController1.badGuyView.setTranslateY(j *  MazeModel.panelSize);
                    mazeModel.badGuyController1.x = i;
                    mazeModel.badGuyController1.y = j;
                    mazeModel.badGuyController1.badGuyView.toFront();
                }
            }
        mazeView.mazePane.getChildren().addAll( mazeModel.badGuyController0.badGuyView,mazeModel.badGuyController1.badGuyView);

    }

    public void setPlayer(String characterColor){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 0  && mazeModel.map[i][j] == 1){
                    mazeModel.playerController.playerView.init(characterColor);
                    mazeModel.playerController.playerView.setTranslateX(i *  MazeModel.panelSize);
                    mazeModel.playerController.playerView.setTranslateY(j *  MazeModel.panelSize);
                    mazeModel.playerController.playerView.x = i;
                    mazeModel.playerController.playerView.y = j;
                }
            }
        mazeModel.playerController.playerView.toFront();
        mazeView.mazePane.getChildren().addAll( mazeModel.playerController.playerView);
    }

    public void setKey(){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 8  && j == 8){
                    mazeModel.keyController.keyView.setTranslateX(i *  MazeModel.panelSize);
                    mazeModel.keyController.keyView.setTranslateY(j *  MazeModel.panelSize);
                    mazeModel.keyController.x = i;
                    mazeModel.keyController.y = j;
                    mazeModel.keyController.keyView.toFront();
                    mazeModel.keyController.keyView.setVisible(true);
                }
            }
        mazeView.mazePane.getChildren().addAll(mazeModel.keyController.keyView);

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
                        checkGetKey();
                        checkWin();
//                        System.out.println("UP");
                        break;
                    case "DOWN":
                        mazeModel.playerController.playerView.moveDown();
                        checkCollision();
                        checkGetKey();
                        checkWin();
//                        System.out.println("down");
                        break;
                    case "LEFT":
                        mazeModel.playerController.playerView.moveLeft();
                        checkCollision();
                        checkGetKey();
                        checkWin();
//                        System.out.println("left");
                        break;
                    case "RIGHT":
                        mazeModel.playerController.playerView.moveRight();
                        checkCollision();
                        checkGetKey();
                        checkWin();
//                        System.out.println("right");
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
                    //bubble scale animation
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1500), mazeModel.cellControllers[i][j].cellView);
                    scaleTransition.setFromX(0.8);
                    scaleTransition.setFromY(0.8);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.setCycleCount(Timeline.INDEFINITE);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
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

                                    //bubble burst animation
                                    Image burstImg = new Image("img/burst.png");
                                    ImageView burstImgView = new ImageView(burstImg);
                                    burstImgView.setFitWidth(MazeModel.panelSize*1.4);
                                    burstImgView.setFitHeight(MazeModel.panelSize*1.4);
                                    burstImgView.setTranslateX(a * MazeModel.panelSize - MazeModel.panelSize *0.2 );
                                    burstImgView.setTranslateY(b * MazeModel.panelSize- MazeModel.panelSize *0.2);
                                    mazeView.getChildren().add(burstImgView);
                                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), burstImgView);
                                    fadeTransition.setFromValue(1);
                                    fadeTransition.setToValue(0);
                                    fadeTransition.play();

                                    if(Math.random()<0.33) {
                                        Image starImg = new Image("img/star.png");
                                        ImageView starImgView = new ImageView(starImg);
                                        starImgView.setFitWidth(MazeModel.panelSize);
                                        starImgView.setFitHeight(MazeModel.panelSize);
                                        starImgView.setTranslateX(0);
                                        starImgView.setTranslateY(0);
//                                        mazeView.getChildren().add(starImgView);
                                        mazeModel.cellControllers[a][b].cellView.getChildren().add(starImgView);

                                        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(1000), starImgView);
                                        fadeTransition1.setFromValue(0);
                                        fadeTransition1.setToValue(1);
                                        fadeTransition1.play();
                                        starImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent mouseEvent) {
                                                mazeModel.starNum++;
                                                mazeView.starNumberLabel.setText(mazeModel.starNum + "");
                                                starImgView.setVisible(false);
                                            }
                                        });
                                    }else{
                                        Image clockImg = new Image("img/clock.png");
                                        ImageView clockImgView = new ImageView(clockImg);
                                        clockImgView.setFitWidth(MazeModel.panelSize);
                                        clockImgView.setFitHeight(MazeModel.panelSize);
                                        clockImgView.setTranslateX(0);
                                        clockImgView.setTranslateY(0);
                                        mazeModel.cellControllers[a][b].cellView.getChildren().add(clockImgView);

                                        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(1000),  clockImgView);
                                        fadeTransition1.setFromValue(0);
                                        fadeTransition1.setToValue(1);
                                        fadeTransition1.play();

                                        clockImgView.setOnDragDetected(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent mouseEvent) {
                                                mazeModel.remainTime= mazeModel.remainTime + 15;
                                                mazeView.remainTimeLabel.setText(mazeModel.remainTime+"s");
                                                clockImgView.setVisible(false);
                                            }
                                        });
                                    }


                                }
                            }
                        }
                    });


                }
            }}

    public void setBadGuyMoveTimer(){
        try {
            badguyTimer = new Timer();
            badguyTimer.schedule(new TimerTask() {
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

    public void setKeyMoveTimer(){
        try {
            keyTimer = new Timer();
            keyTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mazeModel.keyController.randomMove();
                            checkGetKey();
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
            badguyTimer.cancel();
            keyTimer.cancel();
            lose("You are caught by a ghost!");
        }
        if( mazeModel.playerController.playerView.x ==  mazeModel.badGuyController1.x
                &&  mazeModel.playerController.playerView.y ==  mazeModel.badGuyController1.y){
            System.out.println("Catch by bad guy!");
            badguyTimer.cancel();
            keyTimer.cancel();
            lose("You are caught by a ghost!");
        }

    }

    public void checkGetKey(){
        if(!mazeModel.hasKey) {
            if (mazeModel.playerController.playerView.x == mazeModel.keyController.x
                    && mazeModel.playerController.playerView.y == mazeModel.keyController.y) {
                mazeModel.hasKey = true;
                System.out.println("you get key");
                mazeModel.keyController.keyView.setVisible(false);
                mazeView.keyNumberLabel.setText("Yes!");
            }
        }
    }

    public void lose(String whyLose){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Restart");
        alert.setTitle("GAME OVER");
        alert.setHeaderText("GAME OVER");
        alert.setContentText(whyLose);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                restart();
            }
        });
    }

    public void restart(){
        mazeView.mazePane.getChildren().remove(mazeModel.playerController.playerView);
        mazeView.mazePane.getChildren().remove(mazeModel.badGuyController0.badGuyView);
        mazeView.mazePane.getChildren().remove(mazeModel.badGuyController1.badGuyView);
        mazeView.mazePane.getChildren().remove(mazeModel.keyController.keyView);
        mazeModel.remainTime = 120;
        init(filename,characterColor);
    }

    public void checkWin(){

        if(mazeModel.playerController.playerView.x == MazeModel.columns - 1){
            if(mazeModel.hasKey) {
                System.out.println("win!");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Restart");
                alert.setTitle("YOU WIN");
                alert.setHeaderText("YOU WIN");
                alert.setContentText("You go out of the maze!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        restart();
                    }
                });
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No key");
                alert.setHeaderText("No Key");
                alert.setContentText("You cannot go out of maze without a key!Try to get it!");
                alert.show();
            }
        }
    }

    public void setTimer() {
        try {
            mazeModel.timer = new Timer();
            mazeModel.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mazeModel.remainTime--;
                            mazeView.remainTimeLabel.setText(mazeModel.remainTime + "s");

                        }
                    });




                    if(mazeModel.remainTime < 30){

                        mazeView.remainTimeLabel.setTextFill(Color.YELLOW);

                    }else{
                        mazeView.remainTimeLabel.setTextFill(Color.WHITE);
                    }
                    if (mazeModel.remainTime == 0) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                lose("Time is up!");
                            }
                        });
                    }
                }
            }, 0, 1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

