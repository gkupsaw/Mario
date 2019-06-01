package Mario;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Star extends Item {

	public Star(Pane parent, double i, double j, Type type) {
		super(parent, i, j, type);
		super.getBody().setFill(Constants.STAR);
		super.setType(Type.STAR);
	}

	@Override
	public void afterRising() {
		super.afterRising();
		super.getMoveTL().play();
		super.getJumpTL().play();
	}

	@Override
	public void onHittingGround() {
		super.onHittingGround();
		super.getJumpTL().play();
	}

}