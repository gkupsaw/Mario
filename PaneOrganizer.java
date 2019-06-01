package Mario;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * This is the PaneOrganizer class. Here, the background is set up and different
 * panes are instantiated and organized within the root BorderPane.
 */

public class PaneOrganizer {

	private BorderPane _root;
	private Pane _gamespace;
	private Rectangle _bg;

	public PaneOrganizer() {
		_root = new BorderPane();

		this.setupGamespace();
		this.setupBG();
//		this.setupTitleBox();

		Pane visualize = new Pane();
		_root.setLeft(visualize);

		new Game(_gamespace, _bg, visualize);
	}

	public BorderPane getRoot() {
		// returns the root BorderPane that contains virtually everything
		return _root;
	}

	private void setupBG() {
		// sets up the background of the root pane
		_bg = new Rectangle(0, 0, Constants.BG_WIDTH, Constants.GAME_HEIGHT);
		_bg.setFill(Constants.SKY);
//		_bg.setFill(Color.TRANSPARENT);
		_gamespace.getChildren().add(_bg);
		_bg.toBack();
	}

	private void setupGamespace() {
		// sets up the pane in which Tetris will take place
		_gamespace = new Pane();
		_gamespace.setFocusTraversable(true);
		_root.setCenter(_gamespace);
	}

	private void setupTitleBox() {
		// sets up the pane that will hold the title
		VBox titlebox = new VBox(15);
		titlebox.setAlignment(Pos.CENTER);
		Label logo = new Label("M   A   R   I   O");
		logo.setFont(Font.font(Constants.TEXT_FONT, 20));
		logo.setPadding(new Insets(10, 10, 10, 10));
		logo.setTextFill(Color.RED);
		titlebox.getChildren().add(logo);

		_root.setTop(titlebox);
	}

}