package Mario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the Application class! The stage is set and shown here, and the
 * PaneOrganizer is instantiated.
 */

public class App extends Application {

	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("MARIO");
		stage.show();
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}