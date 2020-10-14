package controller;

import model.MazeModel;
import view.MazeView;

/**
 * Created by Tingying He on 2020/10/14.
 */
public class MazeController {
    String filename;
    int characterNum;

    public MazeModel mazeModel;
    public MazeView mazeView;

    public MazeController(String filename, int characterNum){
        this.filename = filename;
        this.characterNum = characterNum;

        mazeModel  = new MazeModel(this,filename);
        mazeView = new MazeView(this,filename,characterNum);
    }

}
