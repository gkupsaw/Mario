package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Stair extends Square {

	private Rectangle _body;

	public Stair(Pane parent, double x, double y) {
		super(parent, x, y);
		_body = super.getBody();
		_body.setFill(Constants.STAIR);
		super.setType(Type.STAIR);
		super.setFree(false);
	}

}