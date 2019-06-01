package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Floor extends Square {

	private Rectangle _body;

	public Floor(Pane parent, double x, double y) {
		super(parent, x, y);
		_body = super.getBody();
		_body.setFill(Constants.FLOOR);
		super.setType(Type.FLOOR);
		super.setFree(false);
	}

}