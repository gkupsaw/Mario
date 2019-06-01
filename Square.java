package Mario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Square {

	private Type _type;
	private Pane _parent;
	private Rectangle _body;
	private boolean _isImpacted;
	private boolean _isFree;
	private Timeline _bopUp;

	public Square(Pane parent, double x, double y) {
		_body = new Rectangle(x, y, Constants.BLOCK_LENGTH, Constants.BLOCK_LENGTH);
		_body.setFill(Constants.SKY);
//		_body.setFill(Color.TRANSPARENT);
		_parent = parent;
		_parent.getChildren().add(_body);
		_body.toBack();
		_isFree = true;
		this.setupBop();
	}

	public Rectangle getBody() {
		return _body;
	}

	public boolean isFree() {
		return _isFree;
	}

	public boolean isImpacted() {
		return _isImpacted;
	}

	public Type getType() {
		return _type;
	}

	public void setFree(boolean yn) {
		_isFree = yn;
	}

	public void setImpacted(boolean yn) {
		_isImpacted = yn;
	}

	public void setType(Type type) {
		_type = type;
	}

	public void delete() {
		_parent.getChildren().remove(_body);
		_body.toFront();
	}

	public void setupBop() {
		_bopUp = new Timeline();
		_bopUp.setCycleCount(10);
		_bopUp.getKeyFrames()
				.add(new KeyFrame(Duration.seconds(Constants.DURATION), "Bop", new EventHandler<ActionEvent>() {
					private int _count = 1;

					@Override
					public void handle(ActionEvent event) {
						if (_count <= 5) {
							_body.setY(_body.getY() - 2);
						} else if (_count <= 10) {
							_body.setY(_body.getY() + 2);
						}

						if (_count == 10) {
							_count = 1; // reset count
						} else {
							_count++;
						}
					}

				}));
	}

	public Timeline getBop() {
		_body.toFront();
		return _bopUp;
	}

}