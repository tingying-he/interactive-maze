package model;

import controller.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Timer;


/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/17:50
 * @Description:
 */
public class MazeModel{
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 25;
    public CellController[][] cellControllers;
    public BadGuyController badGuyController0 = new BadGuyController(0);
    public BadGuyController badGuyController1 = new BadGuyController(1);
    public PlayerController playerController;
    public KeyController keyController = new KeyController();

    public Timer timer;
    public int remainTime;

//    public int bubbleNum = 0;
    public int starNum;

    public boolean hasKey = false;


    public static int map[][] = new int[columns][rows];

    private MazeController mazeController;

    public MazeModel(MazeController mazeController) {
        this.mazeController = mazeController;
        this.playerController = new PlayerController(mazeController.characterColor);

        loadMaze(mazeController.filename);
        init();
    }

    public void init(){
        remainTime = 180;
        starNum = 0;
        hasKey = false;


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

    public void setMap(int[][] map) {
        this.map = map;
    }

}
