package Mario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Goomba extends Character implements Enemy {

	private Rectangle _body;

	public Goomba(Pane parent, double x, double y) {
		super(parent, x, y);
		super.setCostumes(Constants.GOOMBA_COSTUMES);
		super.setType(Type.GOOMBA);
		super.getBody().setFill(Constants.GOOMBA1);
		super.setEnemy(true);
		_body = super.getBody();
		_body.setRotationAxis(Rotate.Y_AXIS);
	}

	@Override
	public void delete() {
		_body.setHeight(Constants.BLOCK_LENGTH / 2);
		_body.setFill(Constants.GOOMBA_SQUISHED);
		super.move(0, Constants.BLOCK_LENGTH / 2);
		super.setDead(true);
		super.getMoveTL().stop();
		Timeline pause = new Timeline();
		pause.setCycleCount(1);
		pause.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3)));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				delete();
				e.consume();
			}
		});
		pause.play();
	}

	@Override
	public void rogueAction(Mario mario) {
	} // nothing for this yet

}