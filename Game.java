package Mario;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Game {

	private int _colsDeleted;
	private int _pulsateCount = 1;
	private boolean _paused;
	private Mario _mario;
	private Square[][] _squaresOnScreen;
	private Square[][] _squaresOffScreen;
	private Type[][] _map;
	private Type[][] _offScreenMap;
	private ArrayList<Enemy> _enemies;
	private ArrayList<Item> _items;
	private ArrayList<Offensives> _offensives;
	private ArrayList<Pulsating> _pulsating;
	private ArrayList<Character> _uninteractive;
	private Timeline _pulsate;
	private Pane _gamespace;
	private Rectangle _sky;
	private boolean _slowFall;

	public Game(Pane gamespace, Rectangle bg, Pane visualize) {
		_gamespace = gamespace;
		_sky = bg;
		_gamespace.addEventHandler(KeyEvent.KEY_PRESSED, new PressHandler());
		_gamespace.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (!_paused && !_mario.isDead()) {
					KeyCode keyPressed = event.getCode();
					switch (keyPressed) {
					case LEFT:
						_mario.getMoveTL().stop();
						_mario.getAnimate().stop();
						if (_mario.getJumpTL().getStatus() == Status.STOPPED) {
							_mario.startingSprite();
						}

//						_mario.getBody().setFill(Color.RED);
						break;
					case RIGHT:
						_mario.getMoveTL().stop();
						_mario.getAnimate().stop();
						if (_mario.getJumpTL().getStatus() == Status.STOPPED) {
							_mario.startingSprite();
						}

//						_mario.getBody().setFill(Color.RED);
						break;
					case SPACE:
						double minHoldY = (Constants.BLOCKS_HIGH - 5) * Constants.BLOCK_LENGTH;
						if (_mario.getVelocity() < 0) {
							if (_mario.getBody().getY() > minHoldY) {
								_mario.setVelocity(-1
										* Math.pow(2 * Constants.GRAVITY * (_mario.getBody().getY() - minHoldY), 0.5)); // kinematics
																														// equation
							} else {
								_mario.setVelocity(0);
							}
						}
						break;
					default:
						break;
					}
				}
			}
		});
		this.setupGame();
	}

	private void setupGame() {
		_mario = new Mario(_gamespace, 0, Constants.BLOCK_LENGTH * (Constants.BLOCKS_HIGH - 3));
		this.setupTLs();
		this.setupMap();
		_mario.getBody().toFront();
	}

	private void setupMap() { // make a new generation system where only blocks on screen are loaded
		Map temp = new Map();
		_enemies = new ArrayList<Enemy>(); // list of enemies on screen
		_items = new ArrayList<Item>(); // items on screen
		_offensives = new ArrayList<Offensives>(); // mario's attacks, e.g. fireballs
		_pulsating = new ArrayList<Pulsating>(); // things that pulsate on screen
		_uninteractive = new ArrayList<Character>(); // characters that the user doesn't interact with
		_squaresOnScreen = new Square[Constants.BLOCKS_HIGH][Constants.BLOCKS_WIDE];
		_squaresOffScreen = new Square[Constants.BLOCKS_HIGH][Constants.OFFSET];
		_map = temp.getMap();
		_offScreenMap = temp.getOffScreen();
		for (int i = 0; i < Constants.BLOCKS_HIGH; i++) { // generates the blocks initially on screen
			for (int j = 0; j < Constants.BLOCKS_WIDE; j++) {
				_squaresOnScreen[i][j] = this.detNewPiece(i, j);
			}
		}
		Square piece = null;
		for (int i = 0; i < Constants.BLOCKS_HIGH; i++) { // generates the blocks initially off screen
			for (int j = 0; j < Constants.OFFSET; j++) { // offset is how many columns are off screen
				switch (_offScreenMap[i][j]) {
				case FREE:
					piece = new Square(_gamespace, Constants.BLOCK_LENGTH * (j - Constants.OFFSET),
							Constants.BLOCK_LENGTH * i);
					break;
				case FLOOR:
					piece = new Floor(_gamespace, Constants.BLOCK_LENGTH * (j - Constants.OFFSET),
							Constants.BLOCK_LENGTH * i);
					break;
				default:
					break;
				}
				_squaresOffScreen[i][j] = piece;
			}
		}
		_sky.toBack();
	}

	private Square detNewPiece(int i, int j) {
		Square piece = null;
		Mystery mystery = null;
		double x = Constants.BLOCK_LENGTH * j;
		double y = Constants.BLOCK_LENGTH * i;
		switch (_map[i][j + _colsDeleted]) {
		case PIT:
		case FREE:
			piece = new Square(_gamespace, x, y);
			break;
		case BIGHILL:
			piece = new BackgroundNugget(_gamespace, x, y, Constants.BLOCK_LENGTH * 5, Constants.BLOCK_LENGTH * 2,
					Constants.BIG_HILL, Type.BIGHILL);
			break;
		case SMALLHILL:
			piece = new BackgroundNugget(_gamespace, x, y, Constants.BLOCK_LENGTH * 3, Constants.BLOCK_LENGTH,
					Constants.SMALL_HILL, Type.SMALLHILL);
			break;
		case BIGBUSH:
			piece = new BackgroundNugget(_gamespace, x, y, Constants.BLOCK_LENGTH * 5, Constants.BLOCK_LENGTH,
					Constants.BIG_BUSH, Type.BIGBUSH);
			break;
		case MEDBUSH:
			piece = new BackgroundNugget(_gamespace, x, y, Constants.BLOCK_LENGTH * 4, Constants.BLOCK_LENGTH,
					Constants.MED_BUSH, Type.MEDBUSH);
			break;
		case SMALLBUSH:
			piece = new BackgroundNugget(_gamespace, x, y, Constants.BLOCK_LENGTH * 3, Constants.BLOCK_LENGTH,
					Constants.SMALL_BUSH, Type.SMALLBUSH);
			break;
		case GOOMBA_START:
			piece = new Square(_gamespace, x, y);
			Goomba goomba = new Goomba(_gamespace, x, y);
			this.addNPCKeyFrame(goomba);
			goomba.getMoveTL().play();
			_enemies.add(goomba);
			break;
		case KOOPA_START:
			piece = new Square(_gamespace, x, y);
			Koopa koopa = new Koopa(_gamespace, x, y);
			this.addNPCKeyFrame(koopa);
			koopa.getMoveTL().play();
			_enemies.add(koopa);
			break;
		case FLOOR:
			piece = new Floor(_gamespace, x, y);
			break;
		case BRICK:
			piece = new Brick(_gamespace, x, y);
			break;
		case PIPE_TL:
			piece = new Pipe(_gamespace, x, y, Type.PIPE_TL);
			break;
		case PIPE_TR:
			piece = new Pipe(_gamespace, x, y, Type.PIPE_TR);
			break;
		case PIPE_L:
			piece = new Pipe(_gamespace, x, y, Type.PIPE_L);
			break;
		case PIPE_R:
			piece = new Pipe(_gamespace, x, y, Type.PIPE_R);
			break;
		case STAIR:
			piece = new Stair(_gamespace, x, y);
			break;
		case COIN_MYSTERY:
			piece = new Mystery(_gamespace, x, y, Type.COIN);
			mystery = (Mystery) piece;
			mystery.pulsate(_pulsateCount);
			_pulsating.add(mystery);
			break;
		case MUSHROOM_MYSTERY:
			piece = new Mystery(_gamespace, x, y, Type.MUSHROOM);
			mystery = (Mystery) piece;
			mystery.pulsate(_pulsateCount);
			_pulsating.add(mystery);
			break;
		case FLOWER_MYSTERY:
			piece = new Mystery(_gamespace, x, y, Type.FLOWER);
			mystery = (Mystery) piece;
			mystery.pulsate(_pulsateCount);
			_pulsating.add(mystery);
			break;
		case STAR_MYSTERY:
			piece = new Mystery(_gamespace, x, y, Type.STAR);
			mystery = (Mystery) piece;
			mystery.pulsate(_pulsateCount);
			_pulsating.add(mystery);
			break;
		default:
			break;
		}
		return piece;
	}

	private void addNPCKeyFrame(Character npc) {
		KeyFrame jump = new KeyFrame(Duration.seconds(Constants.DURATION), new NPCJump(npc));
		npc.getJumpTL().getKeyFrames().add(jump);

		KeyFrame move = new KeyFrame(Duration.seconds(Constants.DURATION), new NPCMove(npc));
		npc.getMoveTL().getKeyFrames().add(move);
	}

	private void setupTLs() {
		_mario.getJumpTL().getKeyFrames().add(new KeyFrame(Duration.seconds(Constants.DURATION), new MVM()));
		_mario.getMoveTL().getKeyFrames().add(new KeyFrame(Duration.seconds(Constants.DURATION / 4/* 5 */), new MHM()));
		_mario.getChangeForm().getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), "FormChange", new ChangeForm()));
		_mario.getBlink().getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), "Blink", new Blink()));

		_mario.getChangeForm().setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				_mario.setCostumes(_mario.getNewSet());
				_mario.setJumpSprite(_mario.getNewJump());
				if (_mario.getJumpTL().getStatus() == Status.PAUSED) {
					_mario.getBody().setFill(_mario.getJumpSprite());
				} else {
					_mario.getBody().setFill(_mario.getSet()[0]);
				}
				if (!_mario.isBig() || _mario.isFirey()) {
					_mario.setBig(true);
				} else {
					_mario.setBig(false);
				}
				if (!_mario.isBig()) {
					_mario.getBlink().play();
					_mario.setBlinking(true);
				}
				playAll();
//				_mario.getAnimate().stop();	//except animate ofc
				e.consume();
			}
		});
		_mario.getBlink().setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				_mario.setBlinking(false);
				if (_mario.getJumpTL().getStatus() == Status.STOPPED) {
					_mario.getBody().setFill(Constants.MARIO1);
				} else {
					_mario.getBody().setFill(Constants.JUMP);
				}
				playAll();
				e.consume();
			}
		});

		_pulsate = new Timeline();
		_pulsate.getKeyFrames().add(new KeyFrame(Duration.seconds(0.4), new Pulsate()));
		_pulsate.setCycleCount(Animation.INDEFINITE);
		_pulsate.play();
	}

	public void pauseAll() {
		_mario.pauseAll();
		for (Enemy enemy : _enemies) {
			enemy.pauseAll();
		}
		for (Item item : _items) {
			item.pauseAll();
		}
		for (Offensives attack : _offensives) {
			attack.pauseAll();
		}
		for (Character character : _uninteractive) {
			character.pauseAll();
		}
		_paused = true;
	}

	public void playAll() {
		_mario.playAll();
		for (Enemy enemy : _enemies) {
			enemy.playAll();
		}
		for (Item item : _items) {
			item.playAll();
		}
		for (Offensives attack : _offensives) {
			attack.playAll();
		}
		for (Character character : _uninteractive) {
			character.playAll();
		}
		_paused = false;
	}

	private Square nextBlock(Character character, double xDisplacement, double yDisplacement) {
		// only works with horizontal and vertical checks
		Line collisionLine = null;
		Line collisionLine2 = null;
		ArrayList<Line> lines = new ArrayList<Line>();
		if (yDisplacement == 0) { // moving horizontally
			if (character.getDir() == Direction.LEFT) {
				collisionLine = character.getCollisionLines()[0];
			} else if (character.getDir() == Direction.RIGHT) {
				collisionLine = character.getCollisionLines()[1];
			}
		} else if (xDisplacement == 0) { // moving vertically
			if (character.getVelocity() >= 0 || character.getJumpTL().getStatus() == Status.STOPPED) {
				collisionLine = character.getCollisionLines()[0];
				collisionLine2 = character.getCollisionLines()[1];
			} else if (character.getVelocity() < 0) {
				collisionLine = character.getCollisionLines()[2];
				collisionLine2 = character.getCollisionLines()[3];
			}
			lines.add(collisionLine2);
		}
		lines.add(collisionLine);

		for (Line line : lines) {
			if (line == null) {
				return null;
			}
			Line testLine = new Line(line.getStartX() + xDisplacement, line.getStartY() + yDisplacement,
					line.getEndX() + xDisplacement, line.getEndY() + yDisplacement);
			for (Square[] squares : _squaresOnScreen) {
				for (Square square : squares) {
					if (square.getBody().intersects(testLine.getBoundsInLocal()) && !square.isFree()) {
						return square;
					}
				}
			}
		}
		return null;
	}

	private class PressHandler implements EventHandler<KeyEvent> {

		public PressHandler() {
		}

		@Override
		public void handle(KeyEvent event) {
			if (!_paused && !_mario.isDead()) {
				KeyCode keyPressed = event.getCode();
				switch (keyPressed) {
				case LEFT:
					if (_mario.getMoveTL().getStatus() != Status.RUNNING) {
						_mario.setDir(Direction.LEFT);
						_mario.getBody().setRotate(180);
						_mario.getMoveTL().play();
						if (_mario.getAnimate().getStatus() != Status.RUNNING) {
							_mario.switchCostume();
							_mario.getAnimate().play();
						}
					}
					break;
				case RIGHT:
					if (_mario.getMoveTL().getStatus() != Status.RUNNING) {
						_mario.setDir(Direction.RIGHT);
						_mario.getBody().setRotate(0);
						_mario.getMoveTL().play();
						if (_mario.getAnimate().getStatus() != Status.RUNNING) {
							_mario.switchCostume();
							_mario.getAnimate().play();
						}
					}
					break;
				case SPACE:
					if (_mario.getJumpTL().getStatus() == Status.STOPPED/* RUNNING */) {
						_mario.getBody().setFill(_mario.getJumpSprite());
						_mario.getAnimate().pause();
						_mario.setVelocity(_mario.getInitV());
						_mario.getJumpTL().play();
					}
//					if (_mario.getVelocity() < 0 /*-600*/
//							&& _mario.getVelocity() > Constants.INIT_V*5/6) {
//						_mario.setVelocity(0);
//					}
					break;
				case DOWN:
					break;
				case U:
					_mario.setSpeedWeight(2);
					break;
				case D:
					_mario.setSpeedWeight(1);
					break;
				case K:
					_mario.transform();
					break;
				case X:
					System.out.println(_mario.getBody().getX());
					break;
				case Y:
					System.out.println(_mario.getBody().getY());
					break;
				case S:
					_mario.getJumpTL().getKeyFrames().clear();
					if (_slowFall) {
						_slowFall = false;
						_mario.getJumpTL().getKeyFrames()
								.add(new KeyFrame(Duration.seconds(Constants.DURATION), new MVM()));
					} else {
						_slowFall = true;
						_mario.getJumpTL().getKeyFrames()
								.add(new KeyFrame(Duration.seconds(Constants.DURATION * 5), new MVM()));
					}
					break;
				case H:
					if (_mario.getBody().getFill() == Color.RED) {
						_mario.getBody().setFill(Constants.MARIO1);
					} else {
						_mario.getBody().setFill(Color.RED);
						_mario.getAnimate().stop();
					}
					break;
				case G:
					Mushroom shroom = new Mushroom(_gamespace, _mario.getBody().getX(),
							_mario.getBody().getY() - 3 * Constants.BLOCK_LENGTH, Type.MUSHROOM);
					addNPCKeyFrame(shroom);
					shroom.getMoveTL().stop();
					_items.add(shroom);
					break;
				case L:
					Flower flwr = new Flower(_gamespace, _mario.getBody().getX(),
							_mario.getBody().getY() - Constants.BLOCK_LENGTH, Type.FLOWER);
					addNPCKeyFrame(flwr);
					_items.add(flwr);
					break;
				case B:
					System.out.println(_mario.isBig());
					break;
				case E:
					System.out.println(_enemies.size());
					break;
				case O:
					System.out.println(_offensives.size());
					break;
				case Q:
					Platform.exit();
					break;
				case F:
					if (_mario.isFirey()) {
						Fireball fireball = null;
						if (_mario.getDir() == Direction.RIGHT) {
							fireball = new Fireball(_gamespace, _mario.getBody().getX() + _mario.getBody().getWidth(),
									_mario.getBody().getY());
						} else {
							fireball = new Fireball(_gamespace, _mario.getBody().getX(), _mario.getBody().getY());
						}

						if (fireball != null) {
							fireball.setInitV(Constants.INIT_V / 2.5);
							fireball.setSpeedWeight(5);
							fireball.setDir(_mario.getDir());
							_offensives.add(fireball);
							addNPCKeyFrame(fireball);
							fireball.getMoveTL().play();
						}
						if (_mario.getCostNum() == 0) {
							_mario.getBody().setFill(Constants.FIRE_MARIO_SHOOT[0]);
						} else {
							_mario.getBody().setFill(Constants.FIRE_MARIO_SHOOT[_mario.getCostNum() - 1]);
						}
						_mario.getReturnToStandard().play();
					}
					break;
				default:
					break;
				}
			}

			event.consume();
		}
	}
	// make pause and stop all the same

	private class MVM implements EventHandler<ActionEvent> {

		private ArrayList<Enemy> _removeable;

		public MVM() {
			_removeable = new ArrayList<Enemy>();
		}

		@Override
		public void handle(ActionEvent e) {
			_mario.updateVelocity();
			double displacement = _mario.getVelocity() * Constants.DURATION;

			if (_mario.isDead()) {
				_mario.move(0, displacement);
			} else if (_mario.getVelocity() > 0) {
				this.goingDown();
			} else if (_mario.getVelocity() <= 0) {
				this.goingUp();
			}

			if (_mario.getBody().getY() > Constants.MAX_Y) {
				Platform.exit();
			}

			_enemies.removeAll(_removeable);
			_removeable.clear();

			e.consume();
		}

		@SuppressWarnings("unlikely-arg-type")
		private void detBlockAction(Square block) {
			if (!block.isImpacted()) {
				if (block.getType() == Type.BRICK) {
					if (_mario.isBig() && block.getBop().getStatus() != Status.RUNNING) {
						block.delete();
					} else {
						block.getBop().play();
					}
				} else if (block.getType() == Type.COIN || block.getType() == Type.MUSHROOM
						|| block.getType() == Type.FLOWER || block.getType() == Type.STAR) {
					this.newItem(block);
					block.delete();
					_pulsating.remove(block);
				}
				for (Enemy enemy : _enemies) { // kills enemies if block under them is hit
					if (block.getBody().intersects(enemy.getFoot().getBoundsInLocal())) {
						if (enemy.getType() == Type.KOOPA) {
							enemy.overTurn();
						} else {
							enemy.deathAnimation();
						}
					}
				}
			}
		}

		private void newItem(Square block) {
			// puts an item on the screen depending on what block was hit
			Item item = null;
			switch (block.getType()) {
			case COIN:
				_uninteractive.add(new Coin(_gamespace, block.getBody().getX(), block.getBody().getY(), Type.COIN));
				break;
			case MUSHROOM:
				item = new Mushroom(_gamespace, block.getBody().getX(), block.getBody().getY(), Type.MUSHROOM);
				addNPCKeyFrame(item);
				break;
			case FLOWER:
				item = new Flower(_gamespace, block.getBody().getX(), block.getBody().getY(), Type.FLOWER);
				addNPCKeyFrame(item);
				break;
			case STAR:
				item = new Star(_gamespace, block.getBody().getX(), block.getBody().getY(), Type.STAR);
				addNPCKeyFrame(item);
				break;
			default:
				break;
			}
			if (item != null) {
				_items.add(item);
			}
			_sky.toBack();
		}

		private void detEnemyAction(Enemy enemy) {
			// initiates an enemy's reponse to being jumped on
			switch (enemy.getType()) {
			case GOOMBA:
				enemy.delete();
				_enemies.remove(enemy);
				_uninteractive.add((Character) enemy);
				break;
			case KOOPA:
				if (enemy.getHead().intersects(_mario.getCollisionLines()[0].getBoundsInLocal())
						&& enemy.getHead().intersects(_mario.getCollisionLines()[1].getBoundsInLocal())) {
					enemy.setDir(Direction.NONE);
				} else if (enemy.getHead().intersects(_mario.getCollisionLines()[0].getBoundsInLocal())) {
					enemy.setDir(Direction.LEFT);
				} else if (enemy.getHead().intersects(_mario.getCollisionLines()[1].getBoundsInLocal())) {
					enemy.setDir(Direction.RIGHT);
				} else {
					enemy.setDir(Direction.NONE);
				}

				enemy.rogueAction(_mario);

//				enemy.getBody().setFill(Color.RED);
				// if mario touches the bottom half he dies
				break;
			default:
				break;
			}
		}

		private void goingUp() {
			double displacement = _mario.getVelocity() * Constants.DURATION;
			Square nextBlock = nextBlock(_mario, 0, displacement);
			if (nextBlock != null) {
				_mario.setVelocity(_mario.getVelocity() * -1);
				this.detBlockAction(nextBlock);
			}
//			for (Enemy enemy : _enemies)	{
//				 if ((_mario.getCollisionLines()[0].intersects(enemy.getBody().getBoundsInLocal())
//							||_mario.getCollisionLines()[1].intersects(enemy.getBody().getBoundsInLocal()))
//							&& enemy.isRogue()
//							&& enemy.getMoveTL().getStatus() != Status.RUNNING
//							&& _mario.getJumpTL().getStatus() != Status.RUNNING)	{
//						enemy.move(15*Constants.DISPLACEMENT*_mario.getSpeedWeight()*_mario.getDir().direction(), 0);
//						enemy.setDir(_mario.getDir());
//						enemy.rogueAction(_mario);
//					}
//			}
			_mario.move(0, displacement);
		}

		private void goingDown() {
			double displacement = _mario.getVelocity() * Constants.DURATION;
			Line foot = _mario.getFoot();
			Rectangle enemyHead = null;
			Square nextBlock = nextBlock(_mario, 0, displacement);
			if (nextBlock != null && !_mario.isDead()) { // when it lands
				_mario.onHittingGround();
				_mario.move(0, nextBlock.getBody().getY() - _mario.getBody().getY() - _mario.getBody().getHeight() - 1);
			} else {
				_mario.move(0, displacement);
			}

			for (Enemy enemy : _enemies) { // checks to see if mario is jumping on an enemy
				enemyHead = new Rectangle(enemy.getBody().getX(), enemy.getBody().getY(), enemy.getBody().getWidth(),
						enemy.getBody().getHeight() / 2);
				if (enemyHead.intersects(foot.getBoundsInLocal()) && !enemy.isDead()) {
					_mario.setVelocity(_mario.getInitV());
					this.detEnemyAction(enemy);
					break; // patch
				}
			}
		}

	}

	private class MHM implements EventHandler<ActionEvent> {

		public MHM() {
		}

		@Override
		public void handle(ActionEvent e) {

			this.determineAction();
			this.checkCollisions();

			e.consume();
		}

		private void determineAction() {
			double displacement = _mario.getDir().direction() * Constants.DISPLACEMENT * _mario.getSpeedWeight();
			if (_mario.isDead()) { // prioritizes falling to the bottom of the screen if dead
				_mario.move(displacement, 0);
			} else if (nextBlock(_mario, 0, 1) == null && _mario.getJumpTL().getStatus() != Status.RUNNING) { // acts as
																												// a
																												// gravity
																												// simulation
				_mario.fall();
			} else if (nextBlock(_mario, displacement, 0) == null) { // if mario is moving to a free block
				if (_mario.getBody().getX() >= Constants.MAX_X
						&& _colsDeleted < Constants.TOT_BLOCKS_WIDE - Constants.BLOCKS_WIDE) { // if mario is at
																								// scrolling distance
																								// and the end of the
																								// level is not reached
					this.adjustSquares();
					this.checkScroll();
					_sky.toBack();
				}
				_mario.move(displacement, 0);
			}
		}

		private void checkCollisions() {
			Rectangle deathZone = null;
			for (Enemy enemy : _enemies) {
				deathZone = new Rectangle(enemy.getCollisionLines()[0].getStartX(),
						enemy.getCollisionLines()[0].getStartY(), enemy.getBody().getWidth(),
						enemy.getBody().getHeight() / 2);
				if ((_mario.getCollisionLines()[0].intersects(deathZone.getBoundsInLocal())
						|| _mario.getCollisionLines()[1].intersects(deathZone.getBoundsInLocal()))
						&& !_mario.isBlinking() && !enemy.isDead() && _mario.getJumpTL().getStatus() != Status.RUNNING
						&& enemy.getMoveTL().getStatus() == Status.RUNNING) {
					pauseAll();
					_mario.die();
				} else if ((_mario.getCollisionLines()[0].intersects(deathZone.getBoundsInLocal())
						|| _mario.getCollisionLines()[1].intersects(deathZone.getBoundsInLocal())) && enemy.isRogue()
						&& enemy.getMoveTL().getStatus() != Status.RUNNING && _mario.getVelocity() < 0) { // weird bug
																											// when
																											// jumping
																											// on a
																											// turtle
																											// and
																											// moving
																											// where it
																											// just
																											// starts
																											// goin
					enemy.move(15 * Constants.DISPLACEMENT * _mario.getSpeedWeight() * _mario.getDir().direction(), 0);
					enemy.setDir(_mario.getDir());
					enemy.rogueAction(_mario);
				}
			}
			for (Line line : _mario.getCollisionLines()) {
				for (Item item : _items) {
					if (line.intersects(item.getBody().getBoundsInLocal())) {
						this.initEffect(item);
						_items.remove(item);
						break;
					}
				}
			}
		}

		private void initEffect(Item item) {
			switch (item.getType()) {
			case MUSHROOM:
				if (!_mario.isBig()) {
					pauseAll();
					_mario.growOrShrink();
				}
				break;
			case FLOWER:
				if (!_mario.isFirey()) {
					pauseAll();
					_mario.transform();
				}
				break;
			case STAR:
				_mario.invincible();
				break;
			default:
				break;
			}
			item.delete();
		}

		private void checkScroll() {
			if (_squaresOnScreen[0][0].getBody().getX() <= -1 * Constants.BLOCK_LENGTH) {
				this.shiftArrays();
				_mario.getBody().toFront();
				if (_colsDeleted != Constants.TOT_BLOCKS_WIDE + Constants.BLOCKS_WIDE) {
					_colsDeleted++;
				}
			}
		}

		private void shiftArrays() {
			for (int i = 0; i < Constants.BLOCKS_HIGH; i++) { // moves columns of array one space to the left
				for (int j = 1; j < Constants.OFFSET; j++) {
					_squaresOffScreen[i][j - 1] = _squaresOffScreen[i][j];
				}
			}
			for (int i = 0; i < Constants.BLOCKS_HIGH; i++) { // moves the leftmost column of onscreen to rightmost of
																// offscreen
				_squaresOffScreen[i][Constants.OFFSET - 1] = _squaresOnScreen[i][0];
			}
			for (int i = 0; i < Constants.BLOCKS_HIGH; i++) { // moves columns of array one space to the left
				for (int j = 1; j < Constants.BLOCKS_WIDE; j++) {
					_squaresOnScreen[i][j - 1] = _squaresOnScreen[i][j];
				}
			}
			for (int i = 0; i < Constants.BLOCKS_HIGH; i++) { // generate new squares, going down the column
				_squaresOnScreen[i][Constants.BLOCKS_WIDE - 1] = detNewPiece(i, Constants.BLOCKS_WIDE - 1);
			}
			for (Enemy enemy : _enemies) {
				enemy.getBody().toFront();
			}
			for (Offensives attack : _offensives) {
				attack.getBody().toFront();
			}
		}

		private void adjustSquares() {
			for (Square[] pieces : _squaresOffScreen) {
				for (Square piece : pieces) {
					piece.getBody().setX(piece.getBody().getX() - Constants.DISPLACEMENT * _mario.getSpeedWeight());
					if (piece.getBody().getX() < -1 * piece.getBody().getWidth()
							- Constants.OFFSET * Constants.BLOCK_LENGTH) {
						piece.delete();
					}
				}
			}
			for (Square[] squares : _squaresOnScreen) {
				for (Square square : squares) {
					square.getBody().setX(square.getBody().getX() - Constants.DISPLACEMENT * _mario.getSpeedWeight());
				}
			}
			for (Enemy enemy : _enemies) {
				enemy.move(-1 * Constants.DISPLACEMENT * _mario.getSpeedWeight(), 0);
				if (enemy.getBody().getX() < -1 * Constants.OFFSET * Constants.BLOCK_LENGTH) {
					_enemies.remove(enemy);
					enemy = null;
					break;
				}
			}
			for (Offensives attack : _offensives) {
				attack.move(-1 * Constants.DISPLACEMENT * _mario.getSpeedWeight(), 0);
				if (attack.getBody().getX() < -1 * Constants.OFFSET * Constants.BLOCK_LENGTH) {
					_offensives.remove(attack);
					attack = null;
					break;
				}
			}
			ArrayList<Character> entities = new ArrayList<Character>();
			ArrayList<Character> removeable = new ArrayList<Character>();
			entities.addAll(_items);
			entities.addAll(_uninteractive);
			for (Character entity : entities) {
				entity.move(-1 * Constants.DISPLACEMENT * _mario.getSpeedWeight(), 0);
				if (entity.getBody().getX() < -Constants.OFFSET * Constants.BLOCK_LENGTH) {
					removeable.add(entity);
				}
			}
			_items.removeAll(removeable);
			_uninteractive.removeAll(removeable);
		}
	}

	private class NPCJump implements EventHandler<ActionEvent> {

		private Character _jumping;

		public NPCJump(Character jumping) {
			_jumping = jumping;
		}

		@SuppressWarnings("unlikely-arg-type")
		@Override
		public void handle(ActionEvent e) {
			double displacement = _jumping.getVelocity() * Constants.DURATION;
			_jumping.updateVelocity();

			if (_jumping.isDead()) {
				_jumping.move(0, displacement);
			} else if (_jumping.getVelocity() > 0) {
				this.goingDown();
			} else if (_jumping.getVelocity() <= 0) {
				this.goingUp();
			}

			if (_jumping.getBody().getY() > Constants.MAX_Y) {
				_enemies.remove(_jumping);
				_items.remove(_jumping);
				_offensives.remove(_jumping);
				_uninteractive.remove(_jumping);
				_gamespace.getChildren().remove(_jumping.getBody());
				_jumping.pauseAll();
			}

//			if (_jumping.getBody().intersects(_mario.getBody().getBoundsInLocal())
//					&& _jumping.getType() == Type.KOOPA
//					&& _jumping.isRogue()
//					&& _jumping.getMoveTL().getStatus() != Status.RUNNING)	{	//handles edge case when a koopa is in its shell and falling
//				_jumping
//			}

			e.consume();
		}

		private void goingDown() {
			double displacement = _jumping.getVelocity() * Constants.DURATION;
			Square nextBlock = nextBlock(_jumping, 0, displacement);
			if (nextBlock != null && !_jumping.isDead()) { // when it lands
				_jumping.onHittingGround();
				_jumping.move(0,
						nextBlock.getBody().getY() - _jumping.getBody().getY() - _jumping.getBody().getHeight() - 1);
			} else {
				_jumping.move(0, displacement);
			}
		}

		private void goingUp() {
			double displacement = _jumping.getVelocity() * Constants.DURATION;
			if (nextBlock(_jumping, 0, displacement) != null) {
				_jumping.setVelocity(_jumping.getVelocity() * -1);
			}
			_jumping.move(0, displacement);
		}

	}

	private class NPCMove implements EventHandler<ActionEvent> {

		private Character _moving;

		public NPCMove(Character npc) {
			_moving = npc;
		}

		@Override
		public void handle(ActionEvent e) {

			this.checkCollisions();
			this.determineAction();
			this.checkForAttacks();

			e.consume();
		}

		private void initEffect() {
			switch (_moving.getType()) {
			case MUSHROOM:
				if (!_mario.isBig()) {
					pauseAll();
					_mario.growOrShrink();
				}
				break;
			case FLOWER:
				if (!_mario.isFirey()) {
					pauseAll();
					_mario.transform();
				}
				break;
			case STAR:
				_mario.invincible();
				break;
			default:
				break;
			}
			_moving.delete();
			_items.remove(_moving);
		}

		@SuppressWarnings("unlikely-arg-type")
		private boolean nextToNPC() {
			int index = 0;
			if (_moving.getDir() == Direction.LEFT) {
				index = 0;
			} else if (_moving.getDir() == Direction.RIGHT) {
				index = 1;
			}
			Line sideFacing = _moving.getCollisionLines()[index];
			for (Enemy enemy : _enemies) {
				if (enemy.getBody().intersects(sideFacing.getBoundsInLocal()) && !enemy.equals(_moving)) {
					if (_moving.isRogue() && !_moving.isDead() && !enemy.isDead()) {
						enemy.deathAnimation();
					}
					return true;
				}
			}
			for (Item item : _items) {
				if (item.getBody().intersects(sideFacing.getBoundsInLocal()) && !item.equals(_moving)) {
					return true;
				}
			}
			return false;
		}

		@SuppressWarnings("unlikely-arg-type")
		private void checkForAttacks() {
			for (Enemy enemy : _enemies) {
				if (enemy.getBody().intersects(_moving.getBody().getBoundsInLocal()) && _offensives.contains(_moving)
						&& !enemy.isDead()) {
					enemy.deathAnimation();
					_moving.delete();
					_offensives.remove(_moving);
				}
			}
		}

		private void checkCollisions() {
			Rectangle deathZone = new Rectangle(_moving.getCollisionLines()[0].getStartX(),
					_moving.getCollisionLines()[0].getStartY(), _moving.getBody().getWidth(),
					_moving.getBody().getHeight() / 2);
			if ((_mario.getCollisionLines()[0].intersects(deathZone.getBoundsInLocal())
					|| _mario.getCollisionLines()[1].intersects(deathZone.getBoundsInLocal())) && !_mario.isDead()
					&& !_mario.isBlinking() && !_moving.isDead() && _moving.isEnemy()
					&& _moving.getMoveTL().getStatus() == Status.RUNNING) {
				pauseAll();
				_mario.die();
			}
			for (Line line : _mario.getCollisionLines()) {
				if (_moving.getBody().intersects(line.getBoundsInLocal()) && _moving.isNPC() && !_moving.isEnemy()) {
					this.initEffect();
				}
			}
		}

		private void determineAction() {
			double displacement = _moving.getDir().direction() * Constants.DISPLACEMENT * _moving.getSpeedWeight();
			if (_moving.isDead()) {
				_moving.move(displacement, 0);
			} else if (nextBlock(_moving, 0, 1) == null && _moving.getJumpTL().getStatus() == Status.STOPPED) {
				_moving.fall();
			} else if (nextBlock(_moving, displacement, 0) == null && (!nextToNPC() || _moving.isRogue())) {
				_moving.move(displacement, 0);
			} else if (!_moving.isDead()) {
				_moving.flip();
			}
		}
	}

	private class Pulsate implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (!_pulsating.isEmpty()) {
				for (Pulsating pulsable : _pulsating) {
					pulsable.pulsate(_pulsateCount);
				}
				if (_pulsateCount == Constants.MYSTERY_SET.length - 1) {
					_pulsateCount = 0;
				} else {
					_pulsateCount++;
				}
			}
		}

	}

	private class ChangeForm implements EventHandler<ActionEvent> {

		// bugs:
		// going from fire&big to small

		private int _count = 1;

		@Override
		public void handle(ActionEvent e) {
			ImagePattern[] set = null;
			ImagePattern jump = null;
			if (_count % 2 == 1) { // look like new thing
				if ((_mario.isBig() && !_mario.isFirey()) || !_mario.isBig()) {
					_mario.changeHeight(true);
				}
				set = _mario.getNewSet();
				jump = _mario.getNewJump();
			} else { // look like old thing
				if ((_mario.isBig() && !_mario.isFirey()) || !_mario.isBig()) {
					_mario.changeHeight(false);
				}
				set = _mario.getSet();
				jump = _mario.getJumpSprite();
			}
			_mario.adjustCollisionLines();
			_mario.updateSprite(set, jump);

			_count++;

			e.consume();
		}

	}

	private class Blink implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			if (_gamespace.getChildren().contains(_mario.getBody())) {
				_gamespace.getChildren().remove(_mario.getBody());
			} else {
				_gamespace.getChildren().add(_mario.getBody());
			}

			e.consume();
		}

	}

}