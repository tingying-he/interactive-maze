package view;

import controller.MazeController;
import javafx.scene.layout.Pane;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/20:39
 * @Description:
 */
public class MazeView extends Pane {


//    public Pane mazePane;


    public MazeController mazeController;


    public MazeView(MazeController mazeController) {
        this.mazeController = mazeController;
//        setVisible(true);
//        this.setFocusTraversable(true);

    }
}


