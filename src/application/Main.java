package application;

import com.sun.javafx.css.StyleManager;
import com.sun.javafx.logging.PlatformLogger;
import controller.MazeController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MazeModel;
import view.*;


/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/15:50
 * @Description:
 */

public class Main extends Application {
//    public int characterNum;
    public String characterColor = "greenblack";
    public String filename;



    @Override
    public void start(Stage primaryStage) throws Exception{


        GamePage gamePage = new GamePage(filename,characterColor);
        Pane root = new Pane();
        root.getChildren().add(gamePage);
        Scene gameScene = new Scene(root,800,600);

        //router between pages
        CharacterPage characterPage = new CharacterPage();


        LevelPage levelPage = new LevelPage(characterColor);
        Scene levelScene = new Scene(levelPage,800,600);

        Scene chooseCharacterScene = new Scene(characterPage,800,600);

        characterPage.selectBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                characterColor = characterPage.getCharacterColor();
                levelPage.init(characterColor);
                primaryStage.setScene(levelScene);

            }
        });


//        characterPage.changeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                characterNum++;
//                characterNum =  characterNum % 3;
//                System.out.println(characterNum);
//                characterPage.repaint(characterNum);
//                gamePage.init(filename,characterNum);
//            }
//        });


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

//
//        launchPage.helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                primaryStage.setScene(helpScene);
//            }
//        });


        helpPage.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(gameScene);
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
                primaryStage.setScene(chooseCharacterScene);
            }
        });

        levelPage.easyBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filename = "Level 0";
                gamePage.init(filename,characterColor);
                primaryStage.setScene(gameScene);
                gamePage.mazeController.mazeView.helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        primaryStage.setScene(helpScene);
                    }
                });
            }
        });

        levelPage.hardBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filename = "Level 1";
                gamePage.init(filename,characterColor);
                primaryStage.setScene(gameScene);
                gamePage.mazeController.mazeView.helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        primaryStage.setScene(helpScene);
                    }
                });
            }
        });



//        launchScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("Enchanted Woods");

        primaryStage.setScene(launchScene);
        primaryStage.show();

        //stop application on window close
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}