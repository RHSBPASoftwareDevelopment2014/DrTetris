
package drtetris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Options implements GameState {

	private final int id;
	
	private int difficulty;
	private float volume;
	
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
		difficulty = Config.MEDIUM;
		volume = 1.0f;
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
		return volume;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		try {
			background = new Image(Config.OPTIONSBACKGROUND);
			backButton = new Button(Config.BACKBUTTON, 250, 500, 300, 50);
			plusButton = new Button(Config.PLUSBUTTON, 250, 395, 50, 50);
			minusButton = new Button(Config.MINUSBUTTON, 500, 395, 50, 50);
			easyButton = new Button(Config.EASYBUTTON, 250, 150, 300, 50);
			mediumButton = new Button(Config.MEDIUMBUTTON, 250, 201, 300, 50);
			extremeButton = new Button(Config.EXTREMEBUTTON, 250, 252, 300, 50);
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		try {
			sbg.getState(backgroundState).render(gc, sbg, g);
			background.draw();
			backButton.draw();
			plusButton.draw();
			minusButton.draw();
			easyButton.draw();
			mediumButton.draw();
			extremeButton.draw();
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		try {
			if (backButton.getClicked()) {
				sbg.enterState(backgroundState);
				backButton.setClicked(false);
			}
			
			if (easyButton.getClicked()) {
				difficulty = Config.EASY;
			}
			
			if (mediumButton.getClicked()) {
				difficulty = Config.MEDIUM;
			}
			
			if (extremeButton.getClicked()) {
				difficulty = Config.EXTREME;
			}
			
			switch(difficulty) {
				case Config.EASY:
					easyButton.setClicked(true);
					break;
				case Config.MEDIUM:
					mediumButton.setClicked(true);
					break;
				case Config.EXTREME:
					extremeButton.setClicked(true);
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
