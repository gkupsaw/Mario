package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Mushroom extends Item {

	public Mushroom(Pane parent, double x, double y, Type type) {
		super(parent, x, y, type);
		super.getBody().setFill(Constants.MUSHROOM);
		super.getBody().setWidth(super.getBody().getWidth() - 4);
		super.getBody().setHeight(super.getBody().getHeight() - 4);
		super.move(0, -10);
		super.setType(Type.MUSHROOM);
		Line[] lines = super.getCollisionLines();
		for (Line line : lines) {
			parent.getChildren().remove(line);
		}
		super.setupCollisionLines();
		super.setSpeedWeight(3);
	}

	@Override
	public void afterRising() {
		super.afterRising();
		super.getMoveTL().play();
	}

	@Override
	public void flip() {
		super.setDir(super.getDir().opposite());
	}

}