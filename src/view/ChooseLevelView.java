package view;

//import com.sun.tools.javadoc.Start;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/**
 * Created by Tingying He on 2020/10/4.
 */
public class ChooseLevelView extends VBox {
    public Button easyBtn = new Button("EASY");
    public Button hardBtn = new Button("HARD");
    public Button backBtn = new Button("Back");

    public ChooseLevelView(){
        easyBtn.setPrefSize(100,40);
        easyBtn.setStyle("-fx-background-color:RED");
        easyBtn.setTextFill(Color.WHITE);
        hardBtn.setPrefSize(100,40);
        hardBtn.setStyle("-fx-background-color:RED");
        hardBtn.setTextFill(Color.WHITE);
        backBtn.setPrefSize(100,40);
        backBtn.setStyle("-fx-background-color:RED");
        backBtn.setTextFill(Color.WHITE);

        this.getChildren().addAll(easyBtn,hardBtn,backBtn);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
    }

}
