package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Pipe extends Square {

	private Rectangle _body;

	public Pipe(Pane parent, double x, double y, Type type) {
		super(parent, x, y);
		_body = super.getBody();
		super.setType(type);
		super.setFree(false);
		this.detFill();
	}

	public void detFill() {
		ImagePattern fill = null;
		switch (super.getType()) {
		case PIPE_TL:
			fill = Constants.PIPE_TL;
			break;
		case PIPE_TR:
			fill = Constants.PIPE_TR;
			break;
		case PIPE_L:
			fill = Constants.PIPE_L;
			break;
		case PIPE_R:
			fill = Constants.PIPE_R;
			break;
		default:
			break;
		}
		_body.setFill(fill);
	}

}