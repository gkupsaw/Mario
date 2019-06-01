package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BackgroundNugget extends Square {

	private Rectangle _body;
	private Pane _parent;

	public BackgroundNugget(Pane parent, double x, double y, double width, double height, ImagePattern fill,
			Type type) { // some are bigger than blocks, width and height are different
		super(parent, x, y);
		_body = super.getBody();
		_body.setWidth(width);
		_body.setHeight(height);
		_body.setFill(fill);
		_parent = parent;
		super.setType(type);
	}

	@Override
	public void delete() {
		_parent.getChildren().remove(_body);
	}

}