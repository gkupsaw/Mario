package Mario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class HighScore {

	private Pane _gamespace;
	private Pane _scorebox;
	private String _hsName;
	private int _highscore;
	private Label _highscoreTxt;
	private Label _hs;
	private KeyCode _lastKey;

	public HighScore(Pane parent, Pane score) {
		_gamespace = parent;
		_scorebox = score;
		this.getHighScore();
		_highscoreTxt = new Label("High Score: " + _highscore + " by " + _hsName);
		_highscoreTxt.setTextFill(Color.SILVER);
		_highscoreTxt.setPadding(new Insets(10, 10, 10, 10));
		_highscoreTxt.setFont(Font.font(Constants.TEXT_FONT, 20));
		_scorebox.getChildren().add(_highscoreTxt);
	}

	public void getHighScore() {
		// gets the high score value from the highscore text file
		try {
			File f1 = new File(Constants.HIGH_SCORE);
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);

			String hs = br.readLine();
			String name = br.readLine();
			if (hs == null || name == null) {
				_highscore = 0;
				_hsName = "NULL";
				this.updateHighScore();
			} else {
				_highscore = Integer.parseInt(hs);
				_hsName = name;
			}

			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateHighScore() {
		// updates the high score value within the highscore text file
		try {
			File f1 = new File(Constants.HIGH_SCORE);

			FileWriter fw = new FileWriter(f1);
			BufferedWriter out = new BufferedWriter(fw);

			out.write(Integer.toString(_highscore));
			out.newLine();
			out.write(_hsName);

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkHS(int score) {
		if (score > _highscore) {
			_highscore = score;
			_highscoreTxt.setText("High Score of " + _highscore + "  by");
			this.askForName();
		}
	}

	private void askForName() {
		// allows the user to input a 4 character name
		_gamespace.addEventHandler(KeyEvent.KEY_PRESSED, new Input());
		_hs = new Label("You Got a New High Score!\nEnter Your (4 Character) Name: \n" + " ");
		_hs.setFont(Font.font("Georgia", 15));
		_hs.setPadding(new Insets(0, 10, 0, 10));
		_hs.setTextFill(Color.DEEPSKYBLUE);
		_hs.setStyle("-fx-border-color:deepskyblue; -fx-background-color: black;");
		_hs.setLayoutX(Constants.GAME_WIDTH / 2 - Constants.GAME_HEIGHT / 2);
		_hs.setLayoutY(Constants.GAME_WIDTH / 2 - Constants.GAME_HEIGHT * 1.55 + 1);
		_hs.setTextAlignment(TextAlignment.CENTER);
		_hs.setOpacity(Constants.FADING_START_OPACITY);
		_gamespace.getChildren().add(_hs);
		this.fadeMessage(_hs);

		_hsName = "";

		Timeline temp = new Timeline();
		KeyFrame input = new KeyFrame(Duration.seconds(0.1), new CheckInput(temp, _hs));
		temp.getKeyFrames().add(input);
		temp.setCycleCount(Animation.INDEFINITE);
		temp.play();
	}

	private void fadeMessage(Labeled msg) {
		// timeline setup for having a message fade into view
		Timeline temp = new Timeline();
		KeyFrame fade = new KeyFrame(Duration.seconds(0.01), new FadeMessage(msg));
		temp.getKeyFrames().add(fade);
		temp.setCycleCount((int) (100 - Constants.FADING_START_OPACITY * 100));
		temp.play();

		temp = null;
	}

	private class FadeMessage implements EventHandler<ActionEvent> {

		/**
		 * Handles graphically fading in a message
		 */

		private Labeled _msg;

		public FadeMessage(Labeled msg) {
			_msg = msg;
		}

		public void handle(ActionEvent e) {
			_msg.setOpacity(_msg.getOpacity() + 0.01); // gradually increases opacity
			e.consume();
		}

	}

	private class CheckInput implements EventHandler<ActionEvent> {

		/**
		 * Handles user input for a 4 character string
		 */

		private Timeline _thisTimeline;
		private Label _hs;

		public CheckInput(Timeline thisTimeline, Label hs) {
			_thisTimeline = thisTimeline;
			_hs = hs;
		}

		@Override
		public void handle(ActionEvent event) {
			if (_lastKey != null) {
				if (_hsName.length() < 4) { // if the name is under 4 characters
					if (_lastKey.toString().length() == 1) {
						_hsName = _hsName + _lastKey.toString();
						_highscoreTxt.setText("High Score: " + _highscore + " by " + _hsName);
						_hs.setText("You Got a New High Score!\nEnter Your (4 Character) Name: \n" + _hsName);
						_lastKey = KeyCode.ENTER; // arbitrary
					}
				} else if (_hsName.length() == 4) { // if it equals 4 characters
					updateHighScore();
					_gamespace.getChildren().remove(_hs);
					_thisTimeline.stop();
					_thisTimeline = null;
				} else if (_hsName.length() > 4) { // if for some reason it exceeds 4 characters
					_hsName = "";
				}
			}

			event.consume();
		}

	}

	private class Input implements EventHandler<KeyEvent> {

		public Input() {
		}

		@Override
		public void handle(KeyEvent event) {
			_lastKey = event.getCode();

			event.consume();
		}
	}

}