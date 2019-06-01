package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Flower extends Item {

	public Flower(Pane parent, double x, double y, Type type) {
		super(parent, x, y, type);
		super.getBody().setFill(Constants.FLOWER);
		super.setType(Type.FLOWER);
		super.move(0, -1 * Constants.BLOCK_LENGTH / 18);
	}

}