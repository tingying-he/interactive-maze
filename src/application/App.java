package application;

import controller.PlayerController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.MazeModel;
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




	/** Construct the application */
	public App() {
		//	TODO: Controller

		// Initialize the GUI
		initUI();

		// Resize window and make it visible
		setVisible(true);
	}


	private void initUI() {
		topPane = new Pane();
		centerPane = new Pane();

		myMazeView = new MazeView();
		PlayerController myPlayerController = new PlayerController(myMazeView);
		myPlayerController.addKeyListener();

		centerPane.getChildren().add(myMazeView);

		this.setTop(topPane);
		this.setCenter(centerPane);
	}
}
