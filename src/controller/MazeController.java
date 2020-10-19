package controller;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
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
    public int characterNum;


    public MazeModel mazeModel ;
    public MazeView mazeView;
    public Timer badguyTimer;
    public Timer keyTimer;

    private RotateTransition treeRotateTransition;
    private ArrayList<ImageView> targetList0 = new ArrayList<>();
    private ArrayList<ImageView> targetList1 = new ArrayList<>();
    private ArrayList<ImageView> targetList2 = new ArrayList<>();

    private ArrayList<RotateTransition> targetRList0 = new ArrayList<>();
    private ArrayList<RotateTransition> targetRList1 = new ArrayList<>();
    private ArrayList<RotateTransition> targetRList2 = new ArrayList<>();

    public MazeController(String filename, int characterNum){
        this.filename = filename;
        this.characterNum = characterNum;
        mazeView = new MazeView(this);
        mazeModel = new MazeModel(this);
        mazeView  = new MazeView(this);
        init(filename,characterNum);
    }

    public void init(String filename, int characterNum){

        mazeModel.cellControllers = new CellController[mazeModel.rows][mazeModel.columns];
        createCellsGrid(filename);
        setBadguy();
        setPlayer(characterNum);
        setKey();
        addKeyListener();
        addMouseEventListener();
    }

    public void createCellsGrid(String filename) {
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                mazeModel.loadMaze(filename);
                mazeModel.cellControllers[i][j] = new CellController(mazeModel.getMap()[i][j]);
                mazeModel.cellControllers[i][j].cellView.setTranslateX(i * MazeModel.panelSize);
                mazeModel.cellControllers[i][j].cellView.setTranslateY(j * MazeModel.panelSize);
                mazeView.getChildren().add(mazeModel.cellControllers[i][j].cellView);

                if(mazeModel.getMap()[i][j] == 3) {
                    ImageView target = mazeModel.cellControllers[i][j].cellView.fenceImgView;
                    targetList0.add(target);
                    setDrop(target, mazeModel.getMap()[i][j]);
                }
                if(mazeModel.getMap()[i][j] == 4) {
                    ImageView target = mazeModel.cellControllers[i][j].cellView.fenceImgView;
                    targetList1.add(target);
                    setDrop(target, mazeModel.getMap()[i][j]);
                }
                if(mazeModel.getMap()[i][j] == 5) {
                    ImageView target = mazeModel.cellControllers[i][j].cellView.fenceImgView;
                    targetList2.add(target);
                    setDrop(target, mazeModel.getMap()[i][j]);
                }
            }
        setDrag(targetList0, targetList1, targetList2);
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
        mazeView.getChildren().addAll( mazeModel.badGuyController0.badGuyView,mazeModel.badGuyController1.badGuyView);
        setBadGuyMoveTimer();
    }

    public void setPlayer(int characterNum){
        for (int i = 0; i < mazeModel.rows; i++)
            for (int j = 0; j < mazeModel.columns; j++) {
                if(i == 0  && mazeModel.map[i][j] == 1){
                    mazeModel.playerController.playerView.init(characterNum);
                    mazeModel.playerController.playerView.setTranslateX(i *  MazeModel.panelSize);
                    mazeModel.playerController.playerView.setTranslateY(j *  MazeModel.panelSize);
                    mazeModel.playerController.playerView.x = i;
                    mazeModel.playerController.playerView.y = j;
                }
            }
        mazeModel.playerController.playerView.toFront();
        mazeView.getChildren().addAll( mazeModel.playerController.playerView);
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
                }
            }
        mazeView.getChildren().addAll(mazeModel.keyController.keyView);
        setKeyMoveTimer();
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
            lose();
        }
        if( mazeModel.playerController.playerView.x ==  mazeModel.badGuyController1.x
                &&  mazeModel.playerController.playerView.y ==  mazeModel.badGuyController1.y){
            System.out.println("Catch by bad guy!");
            badguyTimer.cancel();
            keyTimer.cancel();
            lose();
        }

    }

    public void checkGetKey(){
        if(!mazeModel.hasKey) {
            if (mazeModel.playerController.playerView.x == mazeModel.keyController.x
                    && mazeModel.playerController.playerView.y == mazeModel.keyController.y) {
                mazeModel.hasKey = true;
                System.out.println("you get key");
                mazeModel.keyController.keyView.setVisible(false);
            }
        }
    }

    public void lose(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Restart");
        alert.setTitle("GAME OVER");
        alert.setHeaderText("GAME OVER");
        alert.setContentText("you are caught by a bad guy!");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                restart();
            }
        });
    }

    public void restart(){
        mazeView.getChildren().remove(mazeModel.playerController.playerView);
        mazeView.getChildren().remove(mazeModel.badGuyController0.badGuyView);
        mazeView.getChildren().remove(mazeModel.badGuyController1.badGuyView);
        mazeView.getChildren().remove(mazeModel.keyController.keyView);
        mazeModel.hasKey = false;
        init(filename,characterNum);
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
    public void setDrop(ImageView target, int type) {
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                int targetType = -1;
                if (event.getGestureSource() == GamePage.axe0View) {
                    targetType = 3;
                } else if (event.getGestureSource() == GamePage.axe1View) {
                    targetType = 4;
                } else if (event.getGestureSource() == GamePage.axe2View) {
                    targetType = 5;
                }
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasImage() &&
                        targetType == type) {
                    event.acceptTransferModes(TransferMode.MOVE);
                };
            }
        });
        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                int targetType = -1;
                if (event.getGestureSource() == GamePage.axe0View) {
                    targetType = 3;
                } else if (event.getGestureSource() == GamePage.axe1View) {
                    targetType = 4;
                } else if (event.getGestureSource() == GamePage.axe2View) {
                    targetType = 5;
                }
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasImage() &&
                        targetType == type) {
//                    target.setImage(new Image("img/tool/tree1.png"));
                    treeRotateTransition = new RotateTransition(Duration.millis(100), target);
                    treeRotateTransition.setFromAngle(-20);
                    treeRotateTransition.setToAngle(20);
                    treeRotateTransition.setCycleCount(Timeline.INDEFINITE);
                    treeRotateTransition.setAutoReverse(true);
                    treeRotateTransition.play();
                }
            }
        });
        target.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
//                target.setImage(new Image("img/tool/tree0.png"));
                treeRotateTransition.setFromAngle(0);
                treeRotateTransition.setToAngle(0);
                treeRotateTransition.stop();
            }
        });
        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
//                    target.setImage(new Image("img/tool/tree1.png"));
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), target);
                    fadeTransition.setFromValue(1);
                    fadeTransition.setToValue(0);
                    fadeTransition.play();
                    success = true;
                    System.out.println("drag dropped");
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
            }
        });

    }

}

