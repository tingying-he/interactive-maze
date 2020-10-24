package controller;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.MazeModel;
import view.GamePage;
import view.MazeView;

import java.util.ArrayList;
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
    public Timer autoTimer;

    private RotateTransition treeRotateTransition;
    private ArrayList<ImageView> targetList0 = new ArrayList<>();
    private ArrayList<ImageView> targetList1 = new ArrayList<>();
    private ArrayList<ImageView> targetList2 = new ArrayList<>();

    private ArrayList<RotateTransition> targetRList0 = new ArrayList<>();
    private ArrayList<RotateTransition> targetRList1 = new ArrayList<>();
    private ArrayList<RotateTransition> targetRList2 = new ArrayList<>();

    public static boolean canDraw = false;
    public static Timer myTimer;
    public int time = 8 ;

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

        setPen();
//        setAutoNavigate();

//        mazeView.starNumberLabel.setText(Integer.toString(mazeModel.starNum));
        GamePage.starNum.setText(Integer.toString(mazeModel.starNum) + " ");

        mazeView.keyNumberLabel.setText("No");
    }

    /**
     * Create the maze pane
     */
    public void createCellsGrid(String filename) {
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                mazeModel.loadMaze(filename);
                mazeModel.cellControllers[i][j] = new CellController(mazeModel.getMap()[i][j]);
                mazeModel.cellControllers[i][j].cellView.setTranslateX(i * MazeModel.panelSize);
                mazeModel.cellControllers[i][j].cellView.setTranslateY(j * MazeModel.panelSize);

                mazeView.mazePane.getChildren().add(mazeModel.cellControllers[i][j].cellView);

                if(mazeModel.getMap()[i][j] == 3) {
                    CellController target = mazeModel.cellControllers[i][j];
                    ImageView targetImg = target.cellView.fenceImgView;
                    targetList0.add(targetImg);
                    setDrop(target, i, j);
                }
                if(mazeModel.getMap()[i][j] == 4) {
                    CellController target = mazeModel.cellControllers[i][j];
                    ImageView targetImg = target.cellView.fenceImgView;
                    targetList1.add(targetImg);
                    setDrop(target, i, j);
                }
                if(mazeModel.getMap()[i][j] == 5) {
                    CellController target = mazeModel.cellControllers[i][j];
                    ImageView targetImg = target.cellView.fenceImgView;
                    targetList2.add(targetImg);
                    setDrop(target, i, j);
                }
            }
        setDrag(targetList0, targetList1, targetList2);
    }
    /**
     * Put bad guy into maze pane
     */
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

    /**
     * Put player into maze pane
     */
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

    /**
     * Put key into maze pane
     */
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

    /**
     * navigating the player
     */
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

    /**
     * combined clicks to remove the bush
     */
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
//                                                mazeView.starNumberLabel.setText(mazeModel.starNum + "");
                                                if (mazeModel.starNum > 0) {
                                                    GamePage.penView.setOpacity(1);
                                                }
                                                GamePage.starNum.setText(mazeModel.starNum + " ");
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

    /**
     * bad guy automatically moves every 0.5s
     */
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

    /**
     * key automatically moves every 0.5s
     */
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

    /**
     * check if the player got caught everytime player or bad guys move
     */
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

    /**
     * check if the player got the key
     */
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

    /**
     * lose the game
     */
    public void lose(String whyLose){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Restart");
        alert.setTitle("GAME OVER");
        alert.setHeaderText("GAME OVER");
        alert.setContentText(whyLose);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                restart();
                setBadGuyMoveTimer();
                setKeyMoveTimer();
            }
        });
    }

    /**
     * restart the game
     */
    public void restart(){
        mazeView.mazePane.getChildren().remove(mazeModel.playerController.playerView);
        mazeView.mazePane.getChildren().remove(mazeModel.badGuyController0.badGuyView);
        mazeView.mazePane.getChildren().remove(mazeModel.badGuyController1.badGuyView);
        mazeView.mazePane.getChildren().remove(mazeModel.keyController.keyView);
        mazeModel.remainTime = 120;
        init(filename,characterColor);
    }

    /**
     * check if the player win the game
     */
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

    /**
     * Timer tool
     */
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

                        mazeView.remainTimeLabel.setTextFill(Color.RED);

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

    /**
     * setDrag: all trees match the axe's color will shake slightly
     */
    public void setDrag(ArrayList targetList0, ArrayList targetList1, ArrayList targetList2) {
        GamePage.axe0View.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = GamePage.axe0View.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putImage(GamePage.axe0View.getImage());
                db.setContent(content);

                for(int i=0; i<targetList0.size(); i++) {
                    RotateTransition treeRotate0 = new RotateTransition(Duration.millis(100), (ImageView) targetList0.get(i));
                    targetRList0.add(treeRotate0);
                    treeRotate0.setFromAngle(-5);
                    treeRotate0.setToAngle(5);
                    treeRotate0.setCycleCount(Timeline.INDEFINITE);
                    treeRotate0.setAutoReverse(true);
                    treeRotate0.play();
                }
            }
        });
        GamePage.axe0View.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                for(int i=0; i<targetRList0.size(); i++) {
                    RotateTransition rotate = (RotateTransition) targetRList0.get(i);
                    rotate.setFromAngle(0);
                    rotate.setToAngle(0);
                    rotate.stop();
                }
                if (event.getTransferMode() == TransferMode.MOVE) {
                    System.out.println("drag doneeeeee");
                }
            }
        });

        GamePage.axe1View.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = GamePage.axe1View.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putImage(GamePage.axe1View.getImage());
                db.setContent(content);

                for(int i=0; i<targetList1.size(); i++) {
                    RotateTransition treeRotate0 = new RotateTransition(Duration.millis(100), (ImageView) targetList1.get(i));
                    targetRList1.add(treeRotate0);
                    treeRotate0.setFromAngle(-5);
                    treeRotate0.setToAngle(5);
                    treeRotate0.setCycleCount(Timeline.INDEFINITE);
                    treeRotate0.setAutoReverse(true);
                    treeRotate0.play();
                }
            }
        });
        GamePage.axe1View.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                for(int i=0; i<targetRList1.size(); i++) {
                    RotateTransition rotate = (RotateTransition) targetRList1.get(i);
                    rotate.setFromAngle(0);
                    rotate.setToAngle(0);
                    rotate.stop();
                }
                if (event.getTransferMode() == TransferMode.MOVE) {
                }
            }
        });

        GamePage.axe2View.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = GamePage.axe2View.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putImage(GamePage.axe2View.getImage());
                db.setContent(content);

                for(int i=0; i<targetList2.size(); i++) {
                    RotateTransition treeRotate0 = new RotateTransition(Duration.millis(100), (ImageView) targetList2.get(i));
                    targetRList2.add(treeRotate0);
                    treeRotate0.setFromAngle(-5);
                    treeRotate0.setToAngle(5);
                    treeRotate0.setCycleCount(Timeline.INDEFINITE);
                    treeRotate0.setAutoReverse(true);
                    treeRotate0.play();
                }
            }
        });
        GamePage.axe2View.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                for(int i=0; i<targetRList2.size(); i++) {
                    RotateTransition rotate = (RotateTransition) targetRList2.get(i);
                    rotate.setFromAngle(0);
                    rotate.setToAngle(0);
                    rotate.stop();
                }
                if (event.getTransferMode() == TransferMode.MOVE) {
                }
            }
        });

    }

    /**
     * setDrop: only trees that match the axe color will
     * 1) allow drop
     * 2) shake strongly
     */
    public void setDrop(CellController targetCell, int i, int j) {
        final ImageView[] target = {targetCell.cellView.fenceImgView};
        int type = mazeModel.getMap()[i][j];


        target[0].setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                int targetType = -1;
                if (event.getGestureSource() == GamePage.axe0View) {
                    targetType = 3;
                } else if (event.getGestureSource() == GamePage.axe1View) {
                    targetType = 4;
                } else if (event.getGestureSource() == GamePage.axe2View) {
                    targetType = 5;
                }
                if (event.getGestureSource() != target[0] &&
                        event.getDragboard().hasImage() &&
                        targetType == type) {
                    event.acceptTransferModes(TransferMode.MOVE);
                };
            }
        });
        target[0].setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                int targetType = -1;
                if (event.getGestureSource() == GamePage.axe0View) {
                    targetType = 3;
                } else if (event.getGestureSource() == GamePage.axe1View) {
                    targetType = 4;
                } else if (event.getGestureSource() == GamePage.axe2View) {
                    targetType = 5;
                }
                if (event.getGestureSource() != target[0] &&
                        event.getDragboard().hasImage() &&
                        targetType == type) {
                    treeRotateTransition = new RotateTransition(Duration.millis(100), target[0]);
                    treeRotateTransition.setFromAngle(-20);
                    treeRotateTransition.setToAngle(20);
                    treeRotateTransition.setCycleCount(Timeline.INDEFINITE);
                    treeRotateTransition.setAutoReverse(true);
                    treeRotateTransition.play();
                }
            }
        });
        target[0].setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                treeRotateTransition.setFromAngle(0);
                treeRotateTransition.setToAngle(0);
                treeRotateTransition.stop();
            }
        });
        target[0].setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), target[0]);
                    fadeTransition.setFromValue(1);
                    fadeTransition.setToValue(0);
                    fadeTransition.play();
                    success = true;
                    System.out.println("drag dropped");
                    fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            targetCell.type = 1;
                            Image whiteImg = new Image("img/white.png");
                            target[0].setImage(whiteImg);
                            ImageView tmp = new ImageView(whiteImg);
                            targetCell.cellView.getChildren().add(tmp);

                            int[][] map = mazeModel.getMap();
                            map[i][j] = 1;
                            mazeModel.setMap(map);

                            System.out.println("drag dropped");
                        }
                    });
                }
                event.setDropCompleted(success);
            }
        });
    }

    /**
     * setPen: change the cursor view and allow drawing an auto path
     */
    public void setPen() {
        GamePage.penView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//        mazeView.starIconImgView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mazeModel.starNum > 0 && mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    System.out.println("click pen");
                    Image pen = new Image("img/star.png", 40, 40, false, false);
                    Main.gameScene.setCursor(new ImageCursor(pen));

                    canDraw = true;
                    mazeModel.starNum--;
//                    mazeView.starNumberLabel.setText(Integer.toString(mazeModel.starNum));
                    GamePage.starNum.setText(Integer.toString(mazeModel.starNum));
                    if (mazeModel.starNum == 0) {
                        GamePage.penView.setOpacity(0.3);
                    }
                    setTimerStart();
                }
            }
        });
    }

    public void setTimerStart() {
        if (canDraw == true) {
            myTimer = new Timer();
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (canDraw == true && time > 0) {
                        Platform.runLater(() -> {
                            time--;
                            System.out.println("time is " + time);
                        });
                    } else if (canDraw == true && time == 0) {
                        setAutoNavigate();
                        canDraw = false;
                        time = 8;
                        Main.gameScene.setCursor(Cursor.DEFAULT);
                        System.out.println("timer endssss");
                        myTimer.cancel();
                    }
                    System.gc();
                }
            }, 0, 1000);
        }

    }


    public void setAutoNavigate() {
        try {
            autoTimer = new Timer();
            autoTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("detect path");
                            detectPath();
                        }
                    });
                }
            }, 0, 900);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detectPath() {
        int playerX = mazeModel.playerController.playerView.x;
        int playerY = mazeModel.playerController.playerView.y;


        if (playerX > 0 && playerX < MazeModel.columns-1 && playerY>0 && playerY < MazeModel.rows-1
                && !mazeModel.cellControllers[playerX-1][playerY].cellView.isPath
                && !mazeModel.cellControllers[playerX+1][playerY].cellView.isPath
                && !mazeModel.cellControllers[playerX][playerY-1].cellView.isPath
                && !mazeModel.cellControllers[playerX][playerY+1].cellView.isPath) {
            System.out.println("cancel detect");
            autoTimer.cancel();
        }
        if (playerX > 0
                && mazeModel.cellControllers[playerX-1][playerY].type == 1
                && mazeModel.cellControllers[playerX-1][playerY].cellView.isPath) {
            System.out.println("left");
            mazeModel.cellControllers[playerX][playerY].type = 0;
            mazeModel.cellControllers[playerX-1][playerY].cellView.isPath = false;
            mazeModel.playerController.playerView.moveLeft();

        }
        if (playerX < MazeModel.columns-1
                && mazeModel.cellControllers[playerX+1][playerY].type == 1
                && mazeModel.cellControllers[playerX+1][playerY].cellView.isPath) {
            System.out.println("right");
            mazeModel.cellControllers[playerX][playerY].type = 0;
            mazeModel.cellControllers[playerX+1][playerY].cellView.isPath = false;
            mazeModel.playerController.playerView.moveRight();

        }
        if (playerY>0
                && mazeModel.cellControllers[playerX][playerY-1].type == 1
                && mazeModel.cellControllers[playerX][playerY-1].cellView.isPath) {
            System.out.println("up");
            mazeModel.cellControllers[playerX][playerY].type = 0;
            mazeModel.cellControllers[playerX][playerY-1].cellView.isPath = false;
            mazeModel.playerController.playerView.moveUp();

        }
        if (playerY < MazeModel.rows-1
                && mazeModel.cellControllers[playerX][playerY+1].type == 1
                && mazeModel.cellControllers[playerX][playerY+1].cellView.isPath) {
            System.out.println("down");
            mazeModel.cellControllers[playerX][playerY].type = 0;
            mazeModel.cellControllers[playerX][playerY+1].cellView.isPath = false;
            mazeModel.playerController.playerView.moveDown();

        }
    }
}

