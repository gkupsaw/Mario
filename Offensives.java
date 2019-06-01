package Mario;

import javafx.scene.shape.Rectangle;

public interface Offensives {

	public void pauseAll();

	public void playAll();

	public void move(double xDisplacement, double yDisplacement);

	public Rectangle getBody();

}