package Mario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Brick extends Square {

	private Rectangle _body;
	private Timeline _hit;

	public Brick(Pane parent, double x, double y) {
		super(parent, x, y);
		_body = super.getBody();
		_body.setFill(Constants.BRICK);
		_body.toFront();
		super.setType(Type.BRICK);
		super.setFree(false);
		this.setupHitProcedure();
	}

	public void setupHitProcedure() {
		_hit = new Timeline();
		_hit.setCycleCount(2);
		KeyFrame jump = new KeyFrame(Duration.seconds(0.04), new EventHandler<ActionEvent>() {
			private int _count;

			@Override
			public void handle(ActionEvent event) {
				if (_count == 0) {
					_body.setFill(Constants.BRICK_DEBRIS);
					_count++;
				} else {
					_body.setFill(Constants.SKY);
					setFree(true);
					_count = 0;
				}
			}

		});
		_hit.getKeyFrames().add(jump);
	}

	public void delete() {
		_hit.play();
	}

}