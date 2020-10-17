package view;

import controller.MazeController;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:39
 * @Description:
 */
public class MazeView extends StackPane {


//    public Pane mazePane;


    public MazeController mazeController;


    public MazeView(MazeController mazeController) {
        this.mazeController = mazeController;
//        setVisible(true);
        this.setFocusTraversable(true);

    }
}


