package Mario;

public enum Direction {

	UP, DOWN, RIGHT, LEFT, NONE;

	public int direction() {
		switch (this) {
		case LEFT:
			return -1;
		case RIGHT:
			return 1;
		case NONE:
			return 0;
		default:
			return 0;
		}
	}

	public Direction opposite() {
		switch (this) {
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		default:
			return null;
		}
	}

}