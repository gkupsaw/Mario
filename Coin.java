package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Coin extends Item {

	public Coin(Pane parent, double x, double y, Type type) {
		super(parent, x, y, type);
		Rectangle body = super.getBody();
		body.setFill(Constants.COIN1);
		body.setWidth(Constants.BLOCK_LENGTH / 4);
		body.setHeight(Constants.BLOCK_LENGTH / 2);
		super.replaceCollisionLine(0,
				new Line(body.getX(), body.getY() + body.getHeight(), body.getX(), body.getY() + body.getHeight() / 2));
		super.replaceCollisionLine(1, new Line(body.getX() + body.getWidth(), body.getY() + body.getHeight(),
				body.getX() + body.getWidth(), body.getY() + body.getHeight() / 2));
		super.replaceCollisionLine(2,
				new Line(body.getX(), body.getY() + body.getHeight() / 2, body.getX(), body.getY()));
		super.replaceCollisionLine(3, new Line(body.getX() + body.getWidth(), body.getY() + body.getHeight() / 2,
				body.getX() + body.getWidth(), body.getY()));
		super.move(3 * Constants.BLOCK_LENGTH / 8, -1 * Constants.BLOCK_LENGTH / 2);
		super.setType(Type.COIN);
	}

	@Override
	public void afterRising() {
		super.afterRising();
		super.delete();
	}

}