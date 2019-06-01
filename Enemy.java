package Mario;

import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public interface Enemy {

	public void rogueAction(Mario mario);

	public void pauseAll();

	public void playAll();

	public void setVelocity(double v);

	public void deathAnimation();

	public void delete();

	public void setDir(Direction dir);

	public boolean isDead();

	public boolean isRogue();

	public double getSpeedWeight();

	public Rectangle getBody();

	public Type getType();

	public Timeline getJumpTL();

	public Timeline getMoveTL();

	public Line getFoot();

	public Line getHead();

	public Line[] getCollisionLines();

	public void move(double xDisplacement, double yDisplacement);

	public void overTurn();

}

/*
 * package Mario;
 * 
 * import javafx.scene.layout.Pane; import javafx.scene.shape.Rectangle;
 * 
 * public class Enemy extends Character {
 * 
 * private Rectangle _body;
 * 
 * public Enemy(Pane parent, int i, int j) { super(parent,i,j); _body =
 * super.getBody(); parent.getChildren().add(_body); super.determineEnemy(true);
 * }
 * 
 * public void rogueAction(Direction dir) {} //defined, implemented in child
 * classes
 * 
 * 
 * }
 */