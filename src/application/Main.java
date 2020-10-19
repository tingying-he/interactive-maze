package application;

import com.sun.javafx.logging.PlatformLogger;
import controller.MazeController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.MazeModel;
import view.*;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/15:50
 * @Description:
 */

public class Main extends Application {
    public int characterNum;
    public String filename;

    @Override
    public void start(Stage primaryStage) throws Exception{

        GamePage gamePage = new GamePage(filename,characterNum);
        Pane root = new Pane();
        root.getChildren().add(gamePage);
        Scene gameScene = new Scene(root,800,600);

        //router between pages
        CharacterPage characterPage = new CharacterPage(characterNum);
        LevelPage levelPage = new LevelPage();

        Scene levelScene = new Scene(levelPage,800,600);

        Scene chooseCharacterScene = new Scene(characterPage,800,600);

        characterPage.selectBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(levelScene);
            }
        });

        characterPage.changeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                characterNum++;
                characterNum =  characterNum % 3;
                System.out.println(characterNum);
                characterPage.repaint(characterNum);
                gamePage.init(filename,characterNum);
            }
        });


        HelpPage helpPage = new HelpPage();
        Scene helpScene = new Scene(helpPage,800,600);


        LaunchPage launchPage = new LaunchPage();
        Scene launchScene = new Scene(launchPage,800,600);

        launchPage.startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(chooseCharacterScene);
            }
        });


        launchPage.helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(helpScene);
            }
        });


        helpPage.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(launchScene);
            }
        });


        characterPage.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(launchScene);
            }
        });

        levelPage.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(launchScene);
            }
        });

        levelPage.easyBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filename = "Level 0";
                gamePage.init(filename,characterNum);
                primaryStage.setScene(gameScene);
            }
        });

        levelPage.hardBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filename = "Level 1";
                gamePage.init(filename,characterNum);
                primaryStage.setScene(gameScene);
            }
        });


        primaryStage.setTitle("Model - View - Controller");

        primaryStage.setScene(launchScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}