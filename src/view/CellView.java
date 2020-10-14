package view;

import controller.CellController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Created by Tingying He on 2020/10/14.
 */
private class CellView extends StackPane {
    private Pane cellContent = new Pane();
    CellController cellController;

    public CellView(CellController cellController) {
        this.cellController = cellController;
        init(cellController.cellModel.type);


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
