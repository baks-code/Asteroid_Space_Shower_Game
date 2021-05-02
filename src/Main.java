import ui.GamePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX Application Entry Point
 * @author Bakang Motlhankana
 *
 */
public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GamePane gamepane = new GamePane();
		Scene scene = new Scene(gamepane, 800, 700);
		primaryStage.setTitle("Asteroid Space Shower");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
