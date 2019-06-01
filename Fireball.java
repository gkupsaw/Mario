package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Fireball extends Character implements Offensives {

	public Fireball(Pane parent, double x, double y) { // i and j are essentially x and y in this class
		super(parent, x, y);
		Rectangle body = super.getBody();
		body.setWidth(body.getWidth() / 3);
		body.setHeight(body.getHeight() / 3);
		body.setFill(Color.RED);
		body.setX(x);
		body.setY(y);
		super.replaceCollisionLine(0,
				new Line(body.getX(), body.getY() + body.getHeight(), body.getX(), body.getY() + body.getHeight() / 2));
		super.replaceCollisionLine(1, new Line(body.getX() + body.getWidth(), body.getY() + body.getHeight(),
				body.getX() + body.getWidth(), body.getY() + body.getHeight() / 2));
		super.replaceCollisionLine(2,
				new Line(body.getX(), body.getY() + body.getHeight() / 2, body.getX(), body.getY()));
		super.replaceCollisionLine(3, new Line(body.getX() + body.getWidth(), body.getY() + body.getHeight() / 2,
				body.getX() + body.getWidth(), body.getY()));
		super.move(Constants.BLOCK_LENGTH / 3, Constants.BLOCK_LENGTH / 3);
		super.setNPC(false);
		super.getMoveTL().play();
	}

	@Override
	public void onHittingGround() {
		super.onHittingGround();
		super.getJumpTL().play();
	}

}