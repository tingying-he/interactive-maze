package view;

//import com.sun.tools.javadoc.Start;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Tingying He on 2020/10/4.
 */
public class LaunchView extends VBox {

    public Button startBtn;
    public Button helpBtn;


    public LaunchView(){


        //buttons
        startBtn= new Button("START");
        helpBtn = new Button("HELP");
        startBtn.setPrefSize(100,40);
        startBtn.setTextFill(Color.WHITE);
        startBtn.setStyle("-fx-background-color:RED");

        helpBtn.setPrefSize(100,40);
        helpBtn.setTextFill(Color.WHITE);
        helpBtn.setStyle("-fx-background-color:RED");

        this.setSpacing(15);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(startBtn,helpBtn);

    }

}