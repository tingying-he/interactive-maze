package view;

<<<<<<< Updated upstream
=======
import controller.BadGuyController;
import controller.BadGuysController;
import controller.MazeController;
import javafx.application.Platform;
>>>>>>> Stashed changes
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.MazeModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:39
 * @Description:
 */
public class MazeView extends Pane {
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 25;
    public static int endLevelLoc;

    public MazeController mazeController;

    public PlayerView player;
<<<<<<< Updated upstream

//    public Cell[][] cells = new Cell[rows][columns];
=======
    public BadGuyController badGuysController0;
    public BadGuyController badGuysController1;

    public Cell[][] cells = new Cell[rows][columns];
    Timer timer;
>>>>>>> Stashed changes


    public MazeView(MazeController mazeController, String filename, int characterNum) {
        this.mazeController = mazeController;

<<<<<<< Updated upstream
        setupMaze(mazePane, mazeModel.getMap());
=======
        setupMaze(mazeController.mazeModel.getMap());
>>>>>>> Stashed changes

        this.setFocusTraversable(true);

    }

    private void setupMaze(int[][] map) {
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
<<<<<<< Updated upstream
//                System.out.print(x);
//                System.out.print(y);
//                System.out.println(map[x][y]);
                Cell cell = new Cell(x, y, map[x][y]);
//                cells[x][y] = cell;
                mazePane.getChildren().addAll(cell);

                // Starting point of the player
                if(x == 0 && map[x][y] == 1) {
                    player.setTranslateX(x * panelSize);
                    player.setTranslateY(y * panelSize);
                    player.y = y;
                    player.x = x;
                    mazePane.getChildren().addAll(player);
                }
=======

                cells[x][y]=new Cell(x, y, map[x][y]);
                this.getChildren().add(cells[x][y]);

                // Starting point of the player
//                if(x == 0 && map[x][y] == 1) {
//                    player.setTranslateX(x * panelSize);
//                    player.setTranslateY(y * panelSize);
//                    player.y = y;
//                    player.x = x;
//                    this.getChildren().addAll(player);
//                }
//
//                if(x == 2 && y == 2){
//                    badGuysController0.badGuyPane.setTranslateX(x * panelSize);
//                    badGuysController0.badGuyPane.setTranslateY(y * panelSize);
//                    badGuysController0.y = y;
//                    badGuysController0.x = x;
////                    mazePane.getChildren().remove(cells[x][y].cellContent);
//                    this.getChildren().addAll(badGuysController0.badGuyPane);
//                }
//
//                if(x == 15 && y == 15){
//                    badGuysController1.badGuyPane.setTranslateX(x * panelSize);
//                    badGuysController1.badGuyPane.setTranslateY(y * panelSize);
//                    badGuysController1.y = y;
//                    badGuysController1.x = x;
////                    mazePane.getChildren().remove(cells[x][y].cellContent);
//                    this.getChildren().addAll(badGuysController1.badGuyPane);
//                }
>>>>>>> Stashed changes

            }
        }

<<<<<<< Updated upstream
        player.toFront();
=======
//        setBadGuyMoveTimer();
//        badGuysController0.badGuyPane.toFront();
//
////        setBadGuyMoveTimer();
//        badGuysController1.badGuyPane.toFront();
//        player.toFront();

>>>>>>> Stashed changes
    }
//    public void setBadGuyMoveTimer() {
//        try {
//            timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            badGuysController0.randomMove();
//                            badGuysController1.randomMove();
//                        }
//                    });
////                    gameModel.spentTime++;
////                    gameModel.remainTime--;
//
//
//
//                }
//            }, 0, 500);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void checkCollision(int playerX, int playerY, int x, int y){
//
//        if (
//                playerX == x && playerY == y
//        ) {
//            System.out.println("catch by bad guy!");
//        }
//
//    }


<<<<<<< Updated upstream
    private class Cell extends StackPane {
//        private Rectangle cellContent = new Rectangle(panelSize, panelSize);
        private Pane cellContent = new Pane();
        int type;
        int x;
        int y;
//        int flag = 0;

        public Cell(int x, int y, int type) {
            this.type =type;
            this.x = x;
            this.y = y;
            init(type);

        }


        public void init(int type){

            if(type == 0) {
                Image wallImg = new Image("img/wall.png");
                ImageView wallImgView = new ImageView(wallImg);
                wallImgView.setFitWidth(panelSize);
                wallImgView.setFitHeight(panelSize);
                cellContent.getChildren().add(wallImgView);
//                mazePane.getChildren().add(wallImgView);
                getChildren().addAll(cellContent);
            } else if (type == 1) {
                Image whiteImg = new Image("img/white.png");
                ImageView whiteImgView = new ImageView(whiteImg);
                whiteImgView.setFitWidth(panelSize);
                whiteImgView.setFitHeight(panelSize);
                cellContent.getChildren().add(whiteImgView);
                if(x == columns-1){
                    endLevelLoc = y;
                }
                getChildren().addAll(cellContent);
            } else if (type == 2){ //
                Image bushImg = new Image("img/bush.png");
                ImageView bushImgView = new ImageView(bushImg);
                bushImgView.setFitWidth(panelSize);
                bushImgView.setFitHeight(panelSize);
                cellContent.getChildren().add(bushImgView);
                getChildren().addAll(cellContent);
                cellContent.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 10){
                                System.out.println("10 clicked");
                                getChildren().remove(cellContent);
                                init(1);
                            }
                        }
                    }
                });
            }



//            getChildren().addAll(cellContent);
            this.setTranslateX(x * panelSize);
            this.setTranslateY(y * panelSize);




        }
=======

>>>>>>> Stashed changes


    }
}
