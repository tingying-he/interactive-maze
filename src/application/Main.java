package application;

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
    int characterNum = 0;
    String filename;

    @Override
    public void start(Stage primaryStage) throws Exception{

        App app = new App(filename,characterNum) ;
        Pane root = new Pane();
        root.getChildren().add(app);
        Scene GameScene = new Scene(root,800,600);

        //router between pages
        ChooseCharacterView chooseCharacterView = new ChooseCharacterView(characterNum);
        ChooseLevelView levelView = new ChooseLevelView();

        Scene levelScene = new Scene(levelView,800,600);

        Scene chooseCharacterScene = new Scene(chooseCharacterView,800,600);

        chooseCharacterView.selectBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(levelScene);
            }
        });

        chooseCharacterView.changeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                characterNum++;
                characterNum =  characterNum % 3;
                System.out.println(characterNum);
                chooseCharacterView.repaint(characterNum);
                app.myMazeView.player.init(characterNum);
            }
        });


        HelpView helpView = new HelpView();
        Scene helpScene = new Scene(helpView,800,600);


        LaunchView launchView = new LaunchView();
        Scene launchScene = new Scene(launchView,800,600);

        launchView.startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(chooseCharacterScene);
            }
        });


        launchView.helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(helpScene);
            }
        });


        helpView.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(launchScene);
            }
        });


        chooseCharacterView.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(launchScene);
            }
        });

        levelView.backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(launchScene);
            }
        });

        levelView.easyBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filename = "Level 0";
                app.initUI(filename,characterNum);
                primaryStage.setScene(GameScene);
            }
        });

        levelView.hardBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filename = "Level 1";
                app.initUI(filename,characterNum);
                primaryStage.setScene(GameScene);
            }
        });


        primaryStage.setTitle("Model - View - Controller");

        primaryStage.setScene(launchScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
