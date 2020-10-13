package view;

import controller.BadGuysController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
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

    public MazeModel mazeModel;
    public Pane mazePane;

    public PlayerView player;
    public BadGuysController badGuysController0;
    public BadGuysController badGuysController1;

    public Cell[][] cells = new Cell[rows][columns];


    public MazeView(String filename, int characterNum) {
        mazePane = new Pane();
        this.getChildren().add(mazePane);

        mazeModel = new MazeModel(filename);
        setVisible(true);

        player = new PlayerView(characterNum);
        player.setVisible(true);

        badGuysController0 = new BadGuysController(0);
        badGuysController0.badGuyPane.setVisible(true);

        badGuysController1 = new BadGuysController(1);
        badGuysController1.badGuyPane.setVisible(true);

        setupMaze(mazePane, mazeModel.getMap());

        this.setFocusTraversable(true);

    }

    private void setupMaze(Pane mazePane, int[][] map) {
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
//                System.out.print(x);
//                System.out.print(y);
//                System.out.println(map[x][y]);
//                Cell cell = new Cell(x, y, map[x][y]);
//                mazePane.getChildren().addAll(cell);

                cells[x][y]=new Cell(x, y, map[x][y]);
                mazePane.getChildren().add(cells[x][y]);

                // Starting point of the player
                if(x == 0 && map[x][y] == 1) {
                    player.setTranslateX(x * panelSize);
                    player.setTranslateY(y * panelSize);
                    player.y = y;
                    player.x = x;
                    mazePane.getChildren().addAll(player);
                }

                if(x == 2 && y == 2){
                    badGuysController0.badGuyPane.setTranslateX(x * panelSize);
                    badGuysController0.badGuyPane.setTranslateY(y * panelSize);
                    badGuysController0.y = y;
                    badGuysController0.x = x;
//                    mazePane.getChildren().remove(cells[x][y].cellContent);
                    mazePane.getChildren().addAll(badGuysController0.badGuyPane);
                }

                if(x == 15 && y == 15){
                    badGuysController1.badGuyPane.setTranslateX(x * panelSize);
                    badGuysController1.badGuyPane.setTranslateY(y * panelSize);
                    badGuysController1.y = y;
                    badGuysController1.x = x;
//                    mazePane.getChildren().remove(cells[x][y].cellContent);
                    mazePane.getChildren().addAll(badGuysController1.badGuyPane);
                }



            }
        }

        badGuysController0.setBadGuyMoveTimer(player.x,player.y);
        badGuysController0.badGuyPane.toFront();

        badGuysController1.setBadGuyMoveTimer(player.x,player.y);
        badGuysController1.badGuyPane.toFront();
        player.toFront();

    }



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

                                int[][] map = mazeModel.getMap();
                                map[x][y] =1;
                                mazeModel.setMap(map);
                                mazePane.getChildren().remove(cells[x][y]);
//                                init(1);
                                cells[x][y] = new MazeView.Cell(x,y,1);
                                mazePane.getChildren().add(cells[x][y]);
                            }
                        }
                    }
                });
            }



//            getChildren().addAll(cellContent);
            this.setTranslateX(x * panelSize);
            this.setTranslateY(y * panelSize);



        }


    }


}
