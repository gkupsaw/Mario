package Mario;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Item extends Character {

	private Timeline _rise;
	private Rectangle _body;
	private Type _type;
	private Pane _parent;

	public Item(Pane parent, double x, double y, Type type) {
		super(parent, x, y);
		_body = super.getBody();
		_body.setFill(Color.TRANSPARENT);
		_type = type;
		_parent = parent;
		super.setDir(Direction.RIGHT);
		_rise = new Timeline();
		if (type != Type.NONE) {
			this.setupRise();
		}
	}

	public Timeline getRiseTL() {
		return _rise;
	}

	@Override
	public void pauseAll() {
		super.pauseAll();
		_rise.pause();
	}

	@Override
	public void playAll() {
		super.playAll();
		if (_rise.getStatus() != Status.STOPPED) {
			_rise.play();
		}
	}

	public void afterRising() {
		super.getAnimate().stop();
		_body.toFront();
	}

	private void setupRise() {
		KeyFrame rise = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
			private int _count;

			public void handle(ActionEvent e) {
				if ((_count == 6 && _type == Type.MUSHROOM) || _count == 7) {
					_count = 0;
					_rise.stop();
					afterRising();
				}
				if (_count % 2 == 1) {
					if (_type == Type.COIN) {
						_body.setFill(Constants.COIN1);
					}
					move(0, -1 * Constants.BLOCK_LENGTH / 9);
				} else if (_count % 2 == 0) {
					if (_type == Type.COIN) {
						_body.setFill(Constants.COIN2);
					}
					move(0, -1 * Constants.BLOCK_LENGTH / 9);
				}
				_count++;
				e.consume();
			}
		});
		_rise.getKeyFrames().add(rise);
		_rise.setCycleCount(Animation.INDEFINITE);
		_rise.play();
	}

}