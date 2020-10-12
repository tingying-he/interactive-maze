package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        private Rectangle cellContent = new Rectangle(panelSize, panelSize);

        public Cell(int x, int y, int type) {
            if(type == 0) {
                cellContent.setFill(Color.GRAY);
            } else if (type == 1) {
                cellContent.setFill(Color.WHITE);
                if(x == columns-1){
                    endLevelLoc = y;
                }
            }
            getChildren().addAll(cellContent);
            this.setTranslateX(x * panelSize);
            this.setTranslateY(y * panelSize);
        }
    }
}
