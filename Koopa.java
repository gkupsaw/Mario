package Mario;

import javafx.animation.Animation.Status;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Koopa extends Character implements Enemy {

	private Rectangle _body;

	public Koopa(Pane parent, double x, double y) {
		super(parent, x, y);
		super.setCostumes(Constants.KOOPA_COSTUMES);
		super.setType(Type.KOOPA);
		super.getBody().setFill(Constants.KOOPA1);
		super.setEnemy(true);
		_body = super.getBody();
		_body.toFront();
		_body.setRotationAxis(Rotate.Y_AXIS);
	}

	@Override
	public void overTurn() {
		super.overTurn();
		if (!super.isRogue()) {
			this.goInShell();
		}
	}

	public void goInShell() {
		_body.setFill(Constants.KOOPA_SHELL);
		_body.setHeight(4 * _body.getHeight() / 5); // makes it less tall
		_body.setY(_body.getY() + _body.getHeight() / 5); // adjusts for new height
		super.getCollisionLines()[0].setStartY(super.getCollisionLines()[0].getStartY() - 2); // adjust for blank space
																								// in shell image
		super.getCollisionLines()[1].setStartY(super.getCollisionLines()[1].getStartY() - 2);
		super.getCollisionLines()[2].setEndY(super.getCollisionLines()[2].getEndY() + _body.getHeight() / 5);
		super.getCollisionLines()[3].setEndY(super.getCollisionLines()[3].getEndY() + _body.getHeight() / 5);
		super.move(0, 1);
		super.setRogue(true);
		super.getMoveTL().stop();
		super.getAnimate().stop();
		super.setSpeedWeight(super.getSpeedWeight() * 5);
	}

	@Override
	public void rogueAction(Mario mario) {
		if (!super.isRogue()) { // if it's hit and isn't in its shell
			this.goInShell();
		} else if (super.getMoveTL().getStatus() == Status.RUNNING) { // if it's in its shell and moving
			super.getMoveTL().stop();
		} else {
//			this.detNewDirection(mario);
			super.getMoveTL().play();
		}
	}

}