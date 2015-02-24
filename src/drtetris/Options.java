package drtetris;

import java.awt.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Options implements GameState {

	private final int id;

	private int difficulty;
	private int volume;

	private UnicodeFont font;
	private Image background;
	private int backgroundState;
	private Button backButton;
	private Button plusButton;
	private Button minusButton;
	private Button easyButton;
	private Button mediumButton;
	private Button extremeButton;

	public Options(int id, int backgroundState) {
		this.id = id;
		this.backgroundState = backgroundState;
	}

	@Override
	public int getID() {
		return id;
	}

	public void setBackgroundState(int id) {
		backgroundState = id;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public float getVolume() {
		return ((float) volume) / 100f;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		try {
			//Initializes the buttons and backgrounds of the options menu
			difficulty = ((MainMenu) sbg.getState(DrTetris.MAIN_MENU)).getSaveHandler().getDifficulty();
			volume = ((MainMenu) sbg.getState(DrTetris.MAIN_MENU)).getSaveHandler().getVolume();
			background = new Image(Config.OPTIONSBACKGROUND);
			backButton = new Button(Config.BACKBUTTON, 250, 500, 300, 60);
			plusButton = new Button(Config.PLUSBUTTON, 500, 395, 50, 50);
			minusButton = new Button(Config.MINUSBUTTON, 250, 395, 50, 50);
			easyButton = new Button(Config.EASYBUTTON, 250, 150, 300, 60);
			mediumButton = new Button(Config.MEDIUMBUTTON, 250, 212, 300, 60);
			extremeButton = new Button(Config.EXTREMEBUTTON, 250, 274, 300, 60);
			font = new UnicodeFont(Config.FONT, 36, false, false);
			font.addAsciiGlyphs();
			font.getEffects().add(new ColorEffect(Color.BLACK));
			font.loadGlyphs();
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		try {
			//Draws all of the buttons and backgrounds of the options menu
			sbg.getState(backgroundState).render(gc, sbg, g);
			g.setFont(font);
			background.draw();
			backButton.draw();
			plusButton.draw();
			minusButton.draw();
			easyButton.draw();
			mediumButton.draw();
			extremeButton.draw();
			g.drawString(String.valueOf(volume), 360, 403);
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	private int volumeDelay = 250;

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		try {
			volumeDelay += delta;
			//Lets the difficulty be set from the buttons 
			if (backButton.getClicked()) {
				sbg.enterState(backgroundState);
				backButton.setClicked(false);
			}

			if (easyButton.getClicked()) {
				difficulty = Config.EASY;
				easyButton.setClicked(false);
			}

			if (mediumButton.getClicked()) {
				difficulty = Config.MEDIUM;
				mediumButton.setClicked(false);
			}

			if (extremeButton.getClicked()) {
				difficulty = Config.EXTREME;
				extremeButton.setClicked(false);
			}
			//Lets the volume be altered with a + and - button
			if (volumeDelay >= 250) {
				if (plusButton.getClicked()) {
					if (volume < 100) {
						volume += 5;
						volumeDelay = 0;
						plusButton.setClicked(false);
					}
				}

				if (minusButton.getClicked()) {
					if (volume > 0) {
						volume -= 5;
						volumeDelay = 0;
						minusButton.setClicked(false);
					}
				}
			}
			//Makes the selected difficulty be pressed down at all times to show which difficult is set
			switch (difficulty) {
				case (Config.EASY):
					easyButton.setState(Button.SELECTED);
					break;
				case (Config.MEDIUM):
					mediumButton.setState(Button.SELECTED);
					break;
				case (Config.EXTREME):
					extremeButton.setState(Button.SELECTED);
					break;
			}
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	@Override
	public void mouseWheelMoved(int i) {
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		backButton.mousePressed(button, x, y);
		plusButton.mousePressed(button, x, y);
		minusButton.mousePressed(button, x, y);
		easyButton.mousePressed(button, x, y);
		mediumButton.mousePressed(button, x, y);
		extremeButton.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		backButton.mouseReleased(button, x, y);
		plusButton.mouseReleased(button, x, y);
		minusButton.mouseReleased(button, x, y);
		easyButton.mouseReleased(button, x, y);
		mediumButton.mouseReleased(button, x, y);
		extremeButton.mouseReleased(button, x, y);
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		backButton.mouseMoved(oldX, oldY, newX, newY);
		plusButton.mouseMoved(oldX, oldY, newX, newY);
		minusButton.mouseMoved(oldX, oldY, newX, newY);
		easyButton.mouseMoved(oldX, oldY, newX, newY);
		mediumButton.mouseMoved(oldX, oldY, newX, newY);
		extremeButton.mouseMoved(oldX, oldY, newX, newY);
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		backButton.mouseDragged(oldX, oldY, newX, newY);
		plusButton.mouseDragged(oldX, oldY, newX, newY);
		minusButton.mouseDragged(oldX, oldY, newX, newY);
		easyButton.mouseDragged(oldX, oldY, newX, newY);
		mediumButton.mouseDragged(oldX, oldY, newX, newY);
		extremeButton.mouseDragged(oldX, oldY, newX, newY);
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
	public void keyPressed(int i, char c) {
	}

	@Override
	public void keyReleased(int i, char c) {
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
