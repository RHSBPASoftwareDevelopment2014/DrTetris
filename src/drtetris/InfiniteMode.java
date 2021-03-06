package drtetris;

import java.awt.Color;
import java.util.Random;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class InfiniteMode implements GameState {

	private final int id;

	private Random random;

	private UnicodeFont font;
	private Music music;

	private Image pausedOverlay;
	private Image gameoverOverlay;
	private Image levelNameBackground;

	private Button backButton;
	private Button pausedOptionsButton;
	private Button pausedExitButton;

	protected Level currentLevel;

	private Block nextBlock;
	private MovingBlock currentBlock;

	protected boolean challenge = false;
	private boolean gameover = false,
		paused = false;
	private double speed;
	private int stackDelay = 0,
		aDelay = 0,
		dDelay = 0;
	private int level = 0;
	private double difficulty;
	private boolean A = false,
		D = false;
	protected int levelCount = 0;
	protected int highestLevel;

	private Throwable exception;

	public InfiniteMode(int id) {
		this.id = id;
	}

	@Override
	public int getID() {
		return id;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void setLevel(int level) {
		try {
			this.level = level;
			currentLevel = new Level(String.valueOf(level), challenge);
			currentBlock = new MovingBlock(currentLevel.nextBlock(), TileMap.ROTATENONE, Config.DEFAULTX, Config.DEFAULTY);
			nextBlock = currentLevel.nextBlock();
			stackDelay = 0;
			aDelay = 0;
			dDelay = 0;
			paused = false;
			gameover = false;
		} catch (Exception e) {
			exception = e;
		}
	}

	public int getLevel() {
		return level;
	}

	protected int nextLevel() {
		levelCount++;
		highestLevel = Math.max(highestLevel, levelCount);
		return random.nextInt(Config.NUMBEROFLEVELS);
	}

	public int getHighestLevel() {
		return highestLevel;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		try {
			difficulty = (double) ((Options) sbg.getState(DrTetris.OPTIONS)).getDifficulty();
			music = new Music(Config.GAMEBACKGROUNDMUSIC);
			currentLevel = new Level(String.valueOf(level), challenge);
			pausedOverlay = new Image(Config.PAUSESCREEN);
			gameoverOverlay = new Image(Config.GAMEOVERSCREEN);
			levelNameBackground = new Image(Config.LEVELNAMEBACKGROUND);
			backButton = new Button(Config.BACKBUTTON, 250, 335, 300, 60);
			pausedOptionsButton = new Button(Config.INNEROPTIONSBUTTON, 250, 400, 300, 60);
			pausedExitButton = new Button(Config.INNEREXITBUTTON, 250, 465, 300, 60);
			currentBlock = new MovingBlock(currentLevel.nextBlock(), TileMap.ROTATENONE, Config.DEFAULTX, Config.DEFAULTY);
			nextBlock = currentLevel.nextBlock();
			speed = challenge ? (Config.BASESPEED) * ((difficulty / 4) + .8) : (Config.BASESPEED + Config.SPEEDINCREMENT * level) * (difficulty + .8);
			random = new Random();
			highestLevel = challenge ? highestLevel = ((MainMenu) sbg.getState(DrTetris.MAIN_MENU)).getSaveHandler().getChallengeLevel() : ((MainMenu) sbg.getState(DrTetris.MAIN_MENU)).getSaveHandler().getInfiniteLevel();
			font = new UnicodeFont(Config.FONT, 14, false, false);
			font.addAsciiGlyphs();
			font.getEffects().add(new ColorEffect(Color.WHITE));
			font.loadGlyphs();
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		try {
			g.setFont(font);
			currentLevel.getBackground().draw();
			if (currentLevel.getField().getState() == Field.NORMAL) {
				currentBlock.draw(Config.FIELDX, Config.FIELDY);
			}
			nextBlock.draw(Config.NEXTBLOCKX, Config.NEXTBLOCKY);
			currentLevel.getField().draw(Config.FIELDX, Config.FIELDY);
			levelNameBackground.draw(642, 273);
			g.drawString(challenge ? currentLevel.getName() : String.valueOf(levelCount + 1), 643, 281);
			if (!challenge) {
				g.drawString("Highest: " + highestLevel, 0, 0);
			}
			if (gameover) {
				gameoverOverlay.draw();
				backButton.draw();
			} else if (paused) {
				pausedOverlay.draw();
				backButton.draw();
				pausedOptionsButton.draw();
				pausedExitButton.draw();
			}
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		try {

			if (exception != null) {
				sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, exception));
				sbg.enterState(DrTetris.ERR_REPORT);
			}

			music.setVolume(((Options) sbg.getState(DrTetris.OPTIONS)).getVolume());

			if (speed > Config.SPEEDLIMIT) {
				speed = Config.SPEEDLIMIT;
			}

			if (paused) {
				if (backButton.getClicked()) {
					level = 0;
					backButton.setClicked(false);
					sbg.enterState(DrTetris.MAIN_MENU);
				}

				if (pausedExitButton.getClicked()) {
					((MainMenu) sbg.getState(DrTetris.MAIN_MENU)).save(sbg);
					gc.exit();
				}

				if (pausedOptionsButton.getClicked()) {
					((Options) sbg.getState(DrTetris.OPTIONS)).setBackgroundState(sbg.getCurrentStateID());
					sbg.enterState(DrTetris.OPTIONS);
					pausedOptionsButton.setClicked(false);
				}
			}
			if (gameover) {
				if (backButton.getClicked()) {
					level = 0;
					backButton.setClicked(false);
					sbg.enterState(DrTetris.MAIN_MENU);
				}
			}
			if (!paused && !gameover) {
				currentLevel.getField().update(delta);
			}

			switch (currentLevel.getField().getState()) {
				case Field.CONTINUE:
					if (challenge) {
						nextLevel();
						if (getLevel() >= Config.NUMBEROFLEVELS - 1) {
							sbg.enterState(DrTetris.WIN);
						} else {
							sbg.enterState(DrTetris.LEVEL_SELECTION);
						}
						gameover = false;
						paused = false;
					} else {
						level = nextLevel();
						currentLevel = new Level(String.valueOf(level), challenge);
						currentBlock = new MovingBlock(currentLevel.nextBlock(), TileMap.ROTATENONE, Config.DEFAULTX, Config.DEFAULTY);
						nextBlock = currentLevel.nextBlock();
						speed = (Config.BASESPEED) * (difficulty / 4 + 1);
					}
					break;
				case Field.END:
					gameover = true;
					break;
				case Field.ANIMATE:
					break;
				default:
					if (!paused && !gameover) {

						if (A) {
							aDelay += delta;
						} else {
							aDelay = 0;
						}

						if (D) {
							dDelay += delta;
						} else {
							dDelay = 0;
						}

						if (aDelay >= Config.XMOVEDELAY) {
							double speed = Config.BASEXSPEED + Config.SPEEDXINCREMENT * level;

							if (speed > Config.SPEEDXLIMIT) {
								speed = Config.SPEEDXLIMIT;
							}

							int xDelta = -(int) ((aDelay - Config.XMOVEDELAY) * speed);
							if (xDelta < 0) {
								for (int i = -1; i >= xDelta && currentLevel.getField().isRoom(currentBlock.getMap(), currentBlock.getX() + i, (int) currentBlock.getY(), Config.STACKTOLERANCE, false); i--) {
									currentBlock.modX(-1);
									currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
								}
								aDelay = Config.XMOVEDELAY;
							}
						}

						if (dDelay >= Config.XMOVEDELAY) {
							double speed = Config.BASEXSPEED + Config.SPEEDXINCREMENT * level;

							if (speed > Config.SPEEDXLIMIT) {
								speed = Config.SPEEDXLIMIT;
							}

							int xDelta = (int) ((dDelay - Config.XMOVEDELAY) * speed);
							if (xDelta > 0) {
								for (int i = 1; i <= xDelta && currentLevel.getField().isRoom(currentBlock.getMap(), currentBlock.getX() + i, (int) currentBlock.getY(), Config.STACKTOLERANCE, false); i++) {
									currentBlock.modX(1);
									currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
								}
								dDelay = Config.XMOVEDELAY;
							}
						}

						if (!currentLevel.getField().isRoom(currentBlock, Config.STACKTOLERANCE, true)) {
							if (stackDelay >= Config.BLOCKDELAY) {
								currentLevel.getField().addMap(currentBlock);
								currentBlock = new MovingBlock(nextBlock, TileMap.ROTATENONE, Config.DEFAULTX, Config.DEFAULTY);
								nextBlock = currentLevel.nextBlock();
								stackDelay = 0;
								aDelay = 0;
								dDelay = 0;
							} else {
								stackDelay += delta;
							}
						} else {
							currentBlock.modY(delta * speed);
							currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
							stackDelay = 0;
						}
					}
			}
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		try {
			music.loop(1F, ((Options) sbg.getState(DrTetris.OPTIONS)).getVolume());
			difficulty = (double) ((Options) sbg.getState(DrTetris.OPTIONS)).getDifficulty();
			speed = challenge ? (Config.BASESPEED) * ((difficulty / 4) + .8) : (Config.BASESPEED + Config.SPEEDINCREMENT * level) * (difficulty + .8);
			gameover = false;
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	@Override
	public void mouseWheelMoved(int i) {
	}

	@Override
	public void mouseClicked(int i, int i1, int i2, int i3) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (paused) {
			backButton.mousePressed(button, x, y);
			pausedOptionsButton.mousePressed(button, x, y);
			pausedExitButton.mousePressed(button, x, y);

		}
		if (gameover) {
			backButton.mousePressed(button, x, y);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (paused) {
			backButton.mouseReleased(button, x, y);
			pausedOptionsButton.mouseReleased(button, x, y);
			pausedExitButton.mouseReleased(button, x, y);
		}
		if (gameover) {
			backButton.mouseReleased(button, x, y);
		}
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		if (paused) {
			backButton.mouseMoved(oldX, oldY, newX, newY);
			pausedOptionsButton.mouseMoved(oldX, oldY, newX, newY);
			pausedExitButton.mouseMoved(oldX, oldY, newX, newY);
		}
		if (gameover) {
			backButton.mouseMoved(oldX, oldY, newX, newY);
		}
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		if (paused) {
			backButton.mouseDragged(oldX, oldY, newX, newY);
			pausedOptionsButton.mouseDragged(oldX, oldY, newX, newY);
			pausedExitButton.mouseDragged(oldX, oldY, newX, newY);
		}
		if (gameover) {
			backButton.mouseDragged(oldX, oldY, newX, newY);
		}
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public void keyPressed(int key, char c) {
		try {
			if (!paused && !gameover) {
				switch (key) {
					case Keyboard.KEY_Q:
						currentBlock.modRotation(Block.ROTATELEFT);
						if (currentLevel.getField().isRoom(currentBlock, Config.STACKTOLERANCE, false)) {
							currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
						} else {
							currentBlock.modRotation(Block.ROTATERIGHT);
						}
						break;

					case Keyboard.KEY_E:
						currentBlock.modRotation(Block.ROTATERIGHT);
						if (currentLevel.getField().isRoom(currentBlock, Config.STACKTOLERANCE, false)) {
							currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
						} else {
							currentBlock.modRotation(Block.ROTATELEFT);
						}
						break;

					case Keyboard.KEY_A:
						if (currentLevel.getField().isRoom(currentBlock.getMap(currentBlock.getRotation()), currentBlock.getX() - 1, currentBlock.getY(), Config.STACKTOLERANCE, false)) {
							currentBlock.modX(-1);
							currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
						}
						A = true;
						D = false;
						break;

					case Keyboard.KEY_D:
						if (currentLevel.getField().isRoom(currentBlock.getMap(currentBlock.getRotation()), currentBlock.getX() + 1, currentBlock.getY(), Config.STACKTOLERANCE, false)) {
							currentBlock.modX(1);
							currentLevel.getField().yLimit(currentBlock, Config.STACKTOLERANCE);
						}
						D = true;
						A = false;
						break;
					case Keyboard.KEY_S:
						speed = 2 * (challenge ? (Config.BASESPEED) * ((difficulty / 4) + .8) : (Config.BASESPEED + Config.SPEEDINCREMENT * level) * (difficulty + .8));
						break;
					case Keyboard.KEY_P:
						speed = challenge ? (Config.BASESPEED) * ((difficulty / 4) + .8) : (Config.BASESPEED + Config.SPEEDINCREMENT * level) * (difficulty + .8);
						paused = true;
						break;
				}
			} else {
				if (key == Keyboard.KEY_P) {
					paused = false;
				}
			}
		} catch (Exception e) {
			exception = e;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		try {
			if (!paused && !gameover) {
				switch (key) {
					case Keyboard.KEY_S:
						speed = challenge ? (Config.BASESPEED) * ((difficulty / 4) + .8) : (Config.BASESPEED + Config.SPEEDINCREMENT * level) * (difficulty + .8);
						break;

					case Keyboard.KEY_A:
						A = false;
						D = false;
						break;

					case Keyboard.KEY_D:
						A = false;
						D = false;
						break;
				}
			}
		} catch (Exception e) {
			exception = e;
		}
	}

	@Override
	public void controllerLeftPressed(int i) {
	}

	@Override
	public void controllerLeftReleased(int i) {
	}

	@Override
	public void controllerRightPressed(int i) {
	}

	@Override
	public void controllerRightReleased(int i) {
	}

	@Override
	public void controllerUpPressed(int i) {
	}

	@Override
	public void controllerUpReleased(int i) {
	}

	@Override
	public void controllerDownPressed(int i) {
	}

	@Override
	public void controllerDownReleased(int i) {
	}

	@Override
	public void controllerButtonPressed(int i, int i1) {
	}

	@Override
	public void controllerButtonReleased(int i, int i1) {
	}

}
