package application;

import controller.PlayerController;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.MazeModel;
import view.ChooseCharacterView;
import view.MazeView;

/**
 * @Auther: Anqi Yang
 * @Date: 2020/10/03/16:50
 * @Description:
 */
public class App extends BorderPane {
	private Pane topPane;
	private Pane centerPane;

	public MazeView myMazeView;

	public int characterNum;





	/** Construct the application */
	public App(String filename,int characterNum) {
		//	TODO: Controller

		// Initialize the GUI
		initUI(filename, characterNum);

		// Resize window and make it visible
		setVisible(true);
	}


	public void initUI(String filename,int characterNum) {
		topPane = new Pane();
		centerPane = new Pane();

		myMazeView = new MazeView(filename, characterNum);
		PlayerController myPlayerController = new PlayerController(myMazeView);
		myPlayerController.addKeyListener();

		centerPane.getChildren().add(myMazeView);

		this.setTop(topPane);
		this.setCenter(centerPane);







	}
}
