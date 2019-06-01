package Mario;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Character {

	private double _v;
	private double _initV;
	private double _speedWeight = 1;
	private boolean _isDead;
	private boolean _isNpc;
	private boolean _isEnemy;
	private boolean _isRogue;
	private int _costNum = 1;
	private Timeline _moveTL;
	private Timeline _jumpTL;
	private Timeline _animate;
	private Direction _dir;
	private Rectangle _body;
	private Type _type;
	private Pane _parent;
	private ImagePattern[] _costSet;
	private Line[] _collisionLines;

	public Character(Pane parent, double x, double y) {
		_dir = Direction.LEFT;
		_body = new Rectangle(x, y, Constants.BLOCK_LENGTH, Constants.BLOCK_LENGTH);
		_body.setRotationAxis(Rotate.Y_AXIS);
		_type = Type.NONE;
		_parent = parent;
		_parent.getChildren().add(_body);
		_initV = Constants.INIT_V;
		_isNpc = true; // generally a character is an npc
		this.setupTLs();
		this.setupCollisionLines();
		this.move(0, -1);
	}

	public void setupTLs() {
		_moveTL = new Timeline();
		_moveTL.setCycleCount(Animation.INDEFINITE);

		_jumpTL = new Timeline();
		_jumpTL.setCycleCount(Animation.INDEFINITE);

		_animate = new Timeline();
		_animate.setCycleCount(Animation.INDEFINITE);
		KeyFrame animate = new KeyFrame(Duration.seconds(0.1), "Animate", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (isDead()) {
					_animate.stop();
				} else {
					switchCostume();
				}
			}
		});
		_animate.getKeyFrames().add(animate);
		_animate.play();
	}

	public void setupCollisionLines() {
		Line bottomL = new Line(_body.getX(), _body.getY() + _body.getHeight(), _body.getX(),
				_body.getY() + _body.getHeight() / 2);
		bottomL.setStroke(Color.RED);
		bottomL.toFront(); // bottom left
		Line bottomR = new Line(_body.getX() + _body.getWidth(), _body.getY() + _body.getHeight(),
				_body.getX() + _body.getWidth(), _body.getY() + _body.getHeight() / 2);
		bottomR.setStroke(Color.GREEN);
		bottomR.toFront(); // bottom right
		Line topL = new Line(_body.getX(), _body.getY() + _body.getHeight() / 2, _body.getX(), _body.getY());
		topL.setStroke(Color.BLUE);
		topL.toFront(); // top left
		Line topR = new Line(_body.getX() + _body.getWidth(), _body.getY() + _body.getHeight() / 2,
				_body.getX() + _body.getWidth(), _body.getY());
		topR.setStroke(Color.YELLOW);
		topR.toFront(); // top right
		_parent.getChildren().addAll(bottomL, bottomR, topL, topR);
		_collisionLines = new Line[] { bottomL, bottomR, topL, topR };
	}

	public void replaceCollisionLine(int index, Line newLine) {
		switch (index) {
		case 0:
			_parent.getChildren().remove(_collisionLines[0]);
			_collisionLines[0] = newLine;
			newLine.setStroke(Color.RED);
			_parent.getChildren().add(_collisionLines[0]);
			break;
		case 1:
			_parent.getChildren().remove(_collisionLines[1]);
			_collisionLines[1] = newLine;
			newLine.setStroke(Color.GREEN);
			_parent.getChildren().add(_collisionLines[1]);
			break;
		case 2:
			_parent.getChildren().remove(_collisionLines[2]);
			_collisionLines[2] = newLine;
			newLine.setStroke(Color.BLUE);
			_parent.getChildren().add(_collisionLines[2]);
			break;
		case 3:
			_parent.getChildren().remove(_collisionLines[3]);
			_collisionLines[3] = newLine;
			newLine.setStroke(Color.YELLOW);
			_parent.getChildren().add(_collisionLines[3]);
			break;
		default:
			break;
		}
	}

	public Rectangle getBody() {
		return _body;
	}

	public Line getFoot() {
		return new Line(_collisionLines[0].getStartX(), _collisionLines[0].getStartY() + 1,
				_collisionLines[1].getStartX(), _collisionLines[1].getStartY() + 1);
	}

	public Line getHead() {
		return new Line(_collisionLines[2].getEndX(), _collisionLines[2].getEndY(), _collisionLines[3].getEndX(),
				_collisionLines[3].getEndY());
	}

	/**
	 * indexed as : bottom left, bottom right, top left, top right
	 */
	public Line[] getCollisionLines() {
		return _collisionLines;
	}

	public Timeline getMoveTL() {
		return _moveTL;
	}

	public Timeline getJumpTL() {
		return _jumpTL;
	}

	public boolean isRogue() {
		return _isRogue;
	}

	public boolean isNPC() {
		return _isNpc;
	}

	public boolean isEnemy() {
		return _isEnemy;
	}

	public boolean isDead() {
		return _isDead;
	}

	public double getSpeedWeight() {
		return _speedWeight;
	}

	public double getVelocity() {
		return _v;
	}

	public double getInitV() {
		return _initV;
	}

	public Direction getDir() {
		return _dir;
	}

	public Type getType() {
		return _type;
	}

	public Timeline getAnimate() {
		return _animate;
	}

	public ImagePattern[] getSet() {
		return _costSet;
	}

	public int getCostNum() {
		return _costNum;
	}

	public void setRogue(boolean yn) {
		_isRogue = yn;
	}

	public void setNPC(boolean yn) {
		_isNpc = yn;
	}

	public void setEnemy(boolean yn) {
		_isEnemy = yn;
	}

	public void setDead(boolean b) {
		_isDead = b;
	}

	public void setSpeedWeight(double weight) {
		_speedWeight = weight;
	}

	public void setVelocity(double v) {
		_v = v;
	}

	public void updateVelocity() {
		_v = _v + Constants.GRAVITY * Constants.DURATION;
	}

	public void setDir(Direction dir) {
		_dir = dir;
	}

	public void setType(Type type) {
		_type = type;
	}

	public void setCostNum(int index) {
		_costNum = index;
	}

	public void setInitV(double v) {
		_initV = v;
	}

	public void delete() {
		_parent.getChildren().remove(_body);
		for (Line line : _collisionLines) {
			_parent.getChildren().remove(line);
		}
	}

	public void fall() {
		_v = 0;
		_animate.pause();
		_jumpTL.play();
	}

	public void flip() {
		_dir = _dir.opposite();
		if (!_isRogue) {
			_body.setRotate(180 + _body.getRotate());
		}
	}

	public void move(double xDisplacement, double yDisplacement) {
		_body.setX(_body.getX() + xDisplacement);
		_body.setY(_body.getY() + yDisplacement);
		for (Line line : _collisionLines) {
			line.setStartX(line.getStartX() + xDisplacement);
			line.setEndX(line.getEndX() + xDisplacement);
			line.setStartY(line.getStartY() + yDisplacement);
			line.setEndY(line.getEndY() + yDisplacement);
		}
	}

	public void startingSprite() {
		_body.setFill(_costSet[0]);
		_costNum = 0;
	}

	public void setCostumes(ImagePattern[] cost) {
		_costSet = cost;
	}

	public void switchCostume() {
		if (_costSet != null && _jumpTL.getStatus() != Status.RUNNING) { // will be null for mushroom, star, and flower
			if (_costNum == _costSet.length) {
				_costNum = 1;
			}
			_body.setFill(_costSet[_costNum]);
			_costNum++;
		}
	}

	public void onHittingGround() {
		_v = _initV;
		_jumpTL.stop();
		if (_animate.getStatus() == Status.PAUSED) {
//			this.switchCostume();
			_animate.play();
		}
	}

	public void deathAnimation() {
		_isDead = true;
		this.pauseAll();
		_body.setRotationAxis(Rotate.X_AXIS);
		_body.setRotate(180 + _body.getRotate());
		_dir = Direction.RIGHT;
		_v = -200;
		_jumpTL.play();
		_moveTL.play();
	}

	public void overTurn() {
		_body.setRotationAxis(Rotate.X_AXIS);
		_body.setRotate(_body.getRotate() + 180);
		_v = -200;
		_jumpTL.play();
	}

	public void pauseAll() {
		_jumpTL.pause();
		_moveTL.pause();
		_animate.pause();
	}

	public void playAll() {
		if (_moveTL.getStatus() != Status.STOPPED && _type != Type.MARIO
		/* && !_isRogue */) {
			_moveTL.play();
		}
		if (_jumpTL.getStatus() != Status.STOPPED) {
			_jumpTL.play();
		}
		if (_animate.getStatus() != Status.STOPPED) {
			_animate.play();
		}
	}

}