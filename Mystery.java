package Mario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Mystery extends Square implements Pulsating {

	private Rectangle _body;
	private Timeline _hit;
	private Type _itemType;

	public Mystery(Pane parent, double x, double y, Type type) {
		super(parent, x, y);
		_body = super.getBody();
		_body.setFill(Constants.MYSTERY);
		_body.toFront();
		_itemType = type;
		this.setupTimelines();
		super.setFree(false);
		super.setType(type);
	}

	@Override
	public void delete() {
		if (!super.isImpacted()) {
			_hit.play();
			super.getBop().play();
		}
		super.setImpacted(true);
	}

	@Override
	public ImagePattern[] getCostumeSet() {
		return Constants.MYSTERY_SET;
	}

	public Type getItemType() {
		return _itemType;
	}

	public void pulsate(int costume) {
		_body.setFill(Constants.MYSTERY_SET[costume]);
	}

	public void setupTimelines() {
		_hit = new Timeline(); // a 0.16s delay between fill change
		_hit.setCycleCount(1);
		KeyFrame jump = new KeyFrame(Duration.seconds(Constants.DURATION), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_body.setFill(Constants.HIT);
			}

		});
		_hit.getKeyFrames().add(jump);
	}

}