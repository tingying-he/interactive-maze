package model;

import controller.MazeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import view.PlayerView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/17:50
 * @Description:
 */
public class MazeModel extends Pane{
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 25;

    public static int map[][] = new int[columns][rows];
    public String filename;

    public MazeModel(MazeController mazeController, String filename) {
        this.filename = filename;

        loadMaze(filename);
    }

    public void loadMaze(String filename) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();

            }
            String mapStr = sb.toString();

            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
//                        System.out.print(mapChar);
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;//
                        System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        }catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }

    }

    public int[][] getMap() {
        return this.map;
    }
}
