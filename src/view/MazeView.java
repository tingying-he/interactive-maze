package view;

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


    public MazeView(String filename, int characterNum) {
        mazePane = new Pane();
        this.getChildren().add(mazePane);

        mazeModel = new MazeModel(filename);
        setVisible(true);

        player = new PlayerView(characterNum);
        player.setVisible(true);

        setupMaze(mazePane, mazeModel.getMap());

        this.setFocusTraversable(true);
    }

    private void setupMaze(Pane mazePane, int[][] map) {
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
//                System.out.print(x);
//                System.out.print(y);
//                System.out.println(map[x][y]);
                Cell cell = new Cell(x, y, map[x][y]);
                mazePane.getChildren().addAll(cell);

                // Starting point of the player
                if(x == 0 && map[x][y] == 1) {
                    player.setTranslateX(x * panelSize);
                    player.setTranslateY(y * panelSize);
                    player.y = y;
                    player.x = x;
                    mazePane.getChildren().addAll(player);
                }

            }
        }
        player.toFront();
    }

    private class Cell extends StackPane {
//        private Rectangle cellContent = new Rectangle(panelSize, panelSize);
        private Pane cellContent = new Pane();
        int type;
        int x;
        int y;

        public Cell(int x, int y, int type) {
            this.type =type;
            this.x = x;
            this.y = y;
            init(x,y,type);
        }

        public void init(int x,int y,int type){

            if(type == 0) {
                Image wallImg = new Image("img/wall.png");
                ImageView wallImgView = new ImageView(wallImg);
                wallImgView.setFitWidth(panelSize);
                wallImgView.setFitHeight(panelSize);
                cellContent.getChildren().add(wallImgView);
//                mazePane.getChildren().add(wallImgView);
            } else if (type == 1) {
                Image whiteImg = new Image("img/white.png");
                ImageView whiteImgView = new ImageView(whiteImg);
                whiteImgView.setFitWidth(panelSize);
                whiteImgView.setFitHeight(panelSize);
                cellContent.getChildren().add(whiteImgView);
                if(x == columns-1){
                    endLevelLoc = y;
                }
            } else if (type == 2){ //bush
                Image bushImg = new Image("img/bush.png");
                ImageView bushImgView = new ImageView(bushImg);
                bushImgView.setFitWidth(panelSize);
                bushImgView.setFitHeight(panelSize);
                cellContent.getChildren().add(bushImgView);

                cellContent.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 10){
                                System.out.println("10 clicked");
                            }
                        }
                    }
                });
            }
            getChildren().addAll(cellContent);
            this.setTranslateX(x * panelSize);
            this.setTranslateY(y * panelSize);
        }
    }
}
