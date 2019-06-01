package Mario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Mario extends Character {

	private Rectangle _body;
	private ImagePattern _jumpSprite;
	private Timeline _changeForm;
	private Timeline _blinking;
	private Timeline _goBackToStandard;
	private boolean _isBig;
	private boolean _isFirey;
	private boolean _isBlinking;
	private ImagePattern[] _newSet;
	private ImagePattern _newJump;

	public Mario(Pane parent, int x, int y) {
		super(parent, x, y);
		_body = super.getBody();
		_body.setFill(Constants.MARIO1);
		_body.setRotationAxis(Rotate.Y_AXIS);
		_jumpSprite = Constants.JUMP;
		super.replaceCollisionLine(0, new Line(_body.getX() + 6, _body.getY() + _body.getHeight(), _body.getX() + 6,
				_body.getY() + _body.getHeight() / 2));
		super.replaceCollisionLine(1, new Line(_body.getX() + _body.getWidth() - 6, _body.getY() + _body.getHeight(),
				_body.getX() + _body.getWidth() - 6, _body.getY() + _body.getHeight() / 2));
		super.replaceCollisionLine(2,
				new Line(_body.getX() + 6, _body.getY() + _body.getHeight() / 2, _body.getX() + 6, _body.getY()));
		super.replaceCollisionLine(3, new Line(_body.getX() + _body.getWidth() - 6,
				_body.getY() + _body.getHeight() / 2, _body.getX() + _body.getWidth() - 6, _body.getY()));
		super.setCostumes(Constants.MARIO);
		super.setNPC(false);
		super.setType(Type.MARIO);
		super.getAnimate().stop();
	}

	@Override
	public void setupTLs() {
		super.setupTLs();

		_changeForm = new Timeline();
		_changeForm.setCycleCount(7); // must be odd

		_blinking = new Timeline();
		_blinking.setCycleCount(12); // must be even

		_goBackToStandard = new Timeline();
		_goBackToStandard.setCycleCount(1);
		_goBackToStandard.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (getCostNum() == 0) {
					_body.setFill(getSet()[0]);
				} else {
					_body.setFill(getSet()[getCostNum() - 1]);
				}
				if (getAnimate().getStatus() == Status.PAUSED) {
					getAnimate().play();
				}
			}

		}));

	}

	@Override
	public void fall() {
		super.fall();
		_body.setFill(_jumpSprite);
	}

	@Override
	public void onHittingGround() {
		super.onHittingGround();
		_body.setFill(super.getSet()[0]);
	}

	@Override
	public void move(double xDisplacement, double yDisplacement) {
		double newX = super.getBody().getX() + xDisplacement;
		if (newX >= Constants.MIN_X && newX <= Constants.MAX_X) {
			super.move(xDisplacement, yDisplacement);
		}
	}

	public void growOrShrink() {
		super.getAnimate().stop();
		super.getMoveTL().stop();
		if (!_isBig) {
			_newSet = Constants.BIG_MARIO;
			_newJump = Constants.BIG_JUMP;
		} else {
			_newSet = Constants.MARIO;
			_newJump = Constants.JUMP;
		}
		_blinking.stop();
		_body.setFill(super.getSet()[0]);
		_changeForm.play();
	}

	public void die() {
		if (_isBig) {
			this.growOrShrink();
			_isFirey = false;
		} else {
			super.getMoveTL().stop();
			super.getJumpTL().stop();
			super.setVelocity(-200);
			super.setDead(true);
			super.getBody().setFill(Constants.DEAD);
			_body.setY(_body.getY() - _body.getY() % Constants.BLOCK_LENGTH); // uhhhhhhhhhhhhh
			super.getJumpTL().play();
		}
	}

	public void transform() {
		super.getAnimate().pause();
		super.getMoveTL().stop();
		_isFirey = true;
		_newSet = Constants.FIRE_MARIO;
		_newJump = Constants.FIRE_JUMP;
		_blinking.stop();
		_body.setFill(super.getSet()[0]);
		_changeForm.play();
	}

	public void invincible() {

	}

	public void adjustCollisionLines() {
		super.replaceCollisionLine(0, new Line(_body.getX() + 6, _body.getY() + _body.getHeight(), _body.getX() + 6,
				_body.getY() + _body.getHeight() / 2));
		super.replaceCollisionLine(1, new Line(_body.getX() + _body.getWidth() - 6, _body.getY() + _body.getHeight(),
				_body.getX() + _body.getWidth() - 6, _body.getY() + _body.getHeight() / 2));
		super.replaceCollisionLine(2,
				new Line(_body.getX() + 6, _body.getY() + _body.getHeight() / 2, _body.getX() + 6, _body.getY()));
		super.replaceCollisionLine(3, new Line(_body.getX() + _body.getWidth() - 6,
				_body.getY() + _body.getHeight() / 2, _body.getX() + _body.getWidth() - 6, _body.getY()));
	}

	/** used for transformations */
	public void updateSprite(ImagePattern[] set, ImagePattern jump) {
		if (super.getJumpTL().getStatus() == Status.STOPPED) {
			if (super.getCostNum() == 0) {
				_body.setFill(set[0]);
			} else {
				_body.setFill(set[super.getCostNum() - 1]);
			}
		} else {
			_body.setFill(jump);
		}
	}

	public void changeHeight(boolean grow) {
		double newY = 0;
		if (grow) {
			_body.setHeight(2 * Constants.BLOCK_LENGTH);
			newY = _body.getY() - Constants.BLOCK_LENGTH;
		} else {
			_body.setHeight(Constants.BLOCK_LENGTH);
			newY = _body.getY() + Constants.BLOCK_LENGTH;
		}
		if (super.getJumpTL().getStatus() == Status.STOPPED) {
			_body.setY(newY);
		}
	}

	public boolean isBlinking() {
		return _isBlinking;
	}

	public boolean isBig() {
		return _isBig;
	}

	public boolean isFirey() {
		return _isFirey;
	}

	public Timeline getChangeForm() {
		return _changeForm;
	}

	public Timeline getBlink() {
		return _blinking;
	}

	public Timeline getReturnToStandard() {
		return _goBackToStandard;
	}

	public ImagePattern getJumpSprite() {
		return _jumpSprite;
	}

	public ImagePattern[] getNewSet() {
		return _newSet;
	}

	public ImagePattern getNewJump() {
		return _newJump;
	}

	public void setFirey(boolean yn) {
		_isFirey = yn;
	}

	public void setBlinking(boolean yn) {
		_isBlinking = yn;
	}

	public void setBig(boolean yn) {
		_isBig = yn;
	}

	public void setJumpSprite(ImagePattern sprite) {
		_jumpSprite = sprite;
	}

}