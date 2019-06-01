package Mario;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This is the Constants class. Multiple useful values are stored here to allow
 * for easy editing of key components of the program.
 */

public class Constants {

	// bg is 14 blocks high, so 28 half blocks
	// block is 16x16 pixels
	// will have to go by half blocks <- no nvm frick that
	// 424x28 half blocks

	public static final int GAME_HEIGHT = 672; // height of the game space
	public static final int GAME_WIDTH = 912;

	public static final int BLOCK_LENGTH = 48;
	public static final int BG_WIDTH = 10176;
	public static final int INIT_V = -730; // 700
	public static final double DURATION = 0.016;
	public static final int GRAVITY = 1200;
	public static final int DISPLACEMENT = 1;
	public static final int BLOCKS_HIGH = 14;
	public static final int BLOCKS_WIDE = 19 + 1;// 209;
	public static final int TOT_BLOCKS_WIDE = 209;
	public static final int OFFSET = 6;
	public static final int COLS_WITH_OFFSET = 209 + OFFSET;

	public static final int TITLEBOX_HEIGHT = 20;
	public static final int TITLEBOX_WIDTH = GAME_WIDTH;

	public static final int STAGE_HEIGHT = GAME_HEIGHT;
	public static final int STAGE_WIDTH = GAME_WIDTH;

	public static final int MIN_X = 0;
	public static final double MAX_X = GAME_WIDTH / 2 - BLOCK_LENGTH;
	public static final double MAX_Y = GAME_HEIGHT;

	public static final double FADING_START_OPACITY = 0.2;
	public static final String TEXT_FONT = "Georgia";
	public static final String HIGH_SCORE = "/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Pacman/highscore.txt"; // high
																																		// score
																																		// data

	public static final ImagePattern BIG_HILL = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/bighill.png",
					BLOCK_LENGTH * 5, BLOCK_LENGTH * 2, false, false));
	public static final ImagePattern SMALL_HILL = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/smallhill.png",
					BLOCK_LENGTH * 3, BLOCK_LENGTH, false, false));
	public static final ImagePattern BIG_BUSH = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/bigbush.png",
					BLOCK_LENGTH * 5, BLOCK_LENGTH, false, false));
	public static final ImagePattern MED_BUSH = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/medbush.png",
					BLOCK_LENGTH * 4, BLOCK_LENGTH, false, false));
	public static final ImagePattern SMALL_BUSH = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/smallbush.png",
					BLOCK_LENGTH * 3, BLOCK_LENGTH, false, false));

	public static final ImagePattern FLOOR = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/floor.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern BRICK = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/brick.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern STAIR = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/stair.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern MYSTERY = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mystery.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern SKY = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/sky.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern BRICK_DEBRIS = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/brickdebris.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern MYSTERY2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mystery2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern MYSTERY3 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mystery3.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern[] MYSTERY_SET = new ImagePattern[] { MYSTERY, MYSTERY2, MYSTERY3, MYSTERY2 };
	public static final ImagePattern HIT = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mysteryhit.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern PIPE_TL = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/pipetopleft.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern PIPE_TR = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/pipetopright.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern PIPE_L = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/pipeleft.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern PIPE_R = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/piperight.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));

	public static final ImagePattern GOOMBA1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/goomba1.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern GOOMBA2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/goomba2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern GOOMBA_SQUISHED = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/squished.png",
					BLOCK_LENGTH, BLOCK_LENGTH / 2, false, false));
	public static final ImagePattern[] GOOMBA_COSTUMES = new ImagePattern[] { GOOMBA1, GOOMBA2, GOOMBA1 };

	public static final ImagePattern KOOPA1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/koopa1.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern KOOPA2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/koopa2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern KOOPA_SHELL = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/shell.png",
					BLOCK_LENGTH, BLOCK_LENGTH / 2, false, false));
	public static final ImagePattern KOOPA_EMERGING = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/koopaemerging.png",
					BLOCK_LENGTH, BLOCK_LENGTH / 2, false, false));
	public static final ImagePattern[] KOOPA_COSTUMES = new ImagePattern[] { KOOPA1, KOOPA2, KOOPA1 };

	public static final ImagePattern COIN1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/coin1.png",
					BLOCK_LENGTH / 2, BLOCK_LENGTH / 2, false, false));
	public static final ImagePattern COIN2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/coin2.png",
					BLOCK_LENGTH / 2, BLOCK_LENGTH / 2, false, false));
	public static final ImagePattern MUSHROOM = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mushroom.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FLOWER = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/flower.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern STAR = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/star.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));

	public static final ImagePattern MARIO1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariorun1.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern MARIO2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariorun3.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern MARIO3 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariorun4.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern MARIO4 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariorun2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern DEAD = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariodead.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern JUMP = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariojump.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern[] MARIO = new ImagePattern[] { MARIO1, MARIO2, MARIO3, MARIO2, MARIO4 };

	public static final ImagePattern BIG_MARIO1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariobig1.png",
					2 * BLOCK_LENGTH, 2 * BLOCK_LENGTH, false, false));
	public static final ImagePattern BIG_MARIO2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariobig3.png",
					2 * BLOCK_LENGTH, 2 * BLOCK_LENGTH, false, false));
	public static final ImagePattern BIG_MARIO3 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariobig4.png",
					2 * BLOCK_LENGTH, 2 * BLOCK_LENGTH, false, false));
	public static final ImagePattern BIG_MARIO4 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariobig2.png",
					2 * BLOCK_LENGTH, 2 * BLOCK_LENGTH, false, false));
	public static final ImagePattern BIG_JUMP = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/mariobigjump.png",
					2 * BLOCK_LENGTH, 2 * BLOCK_LENGTH, false, false));
	public static final ImagePattern[] BIG_MARIO = new ImagePattern[] { BIG_MARIO1, BIG_MARIO2, BIG_MARIO3, BIG_MARIO2,
			BIG_MARIO4 };

	public static final ImagePattern STAR_MARIO1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/starmario1.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern STAR_MARIO2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/starmario2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern STAR_MARIO3 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/starmario3.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern STAR_MARIO4 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/starmario4.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern STAR_JUMP = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/starmariojump.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern[] STAR_MARIO = new ImagePattern[] { STAR_MARIO1, STAR_MARIO2, STAR_MARIO3,
			STAR_MARIO2, STAR_MARIO4 };

	public static final ImagePattern FIRE_MARIO1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemario1.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_MARIO2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemario2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_MARIO3 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemario3.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_MARIO4 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemario4.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_JUMP = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemariojump.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern[] FIRE_MARIO = new ImagePattern[] { FIRE_MARIO1, FIRE_MARIO2, FIRE_MARIO3,
			FIRE_MARIO2, FIRE_MARIO4 };
	public static final ImagePattern FIRE_MARIO_SHOOT1 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemarioshoot1.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_MARIO_SHOOT2 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemarioshoot2.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_MARIO_SHOOT3 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemarioshoot3.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern FIRE_MARIO_SHOOT4 = new ImagePattern(
			new Image("file:/Users/griffinkupsaw/Documents/Y2S1/CS15/cs0150/workspace/cs0150/Mario/firemarioshoot4.png",
					BLOCK_LENGTH, BLOCK_LENGTH, false, false));
	public static final ImagePattern[] FIRE_MARIO_SHOOT = new ImagePattern[] { FIRE_MARIO_SHOOT1, FIRE_MARIO_SHOOT2,
			FIRE_MARIO_SHOOT3, FIRE_MARIO_SHOOT2, FIRE_MARIO_SHOOT4 };

}