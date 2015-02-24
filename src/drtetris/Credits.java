
package drtetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Credits implements GameState {

	private final int id;
	
	private Image credits;
	private Music backgroundMusic;
	
	private int y = 0;
	private boolean clicked = false;
	
	public Credits(int id) {
		this.id = id;
	}
	
	@Override
	public int getID() {
		return id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		try {
			//Initializes the background music and the credits image
                        backgroundMusic = new Music(Config.MAINMENUBACKGROUNDMUSIC);
			credits = new Image(Config.CREDITSIMAGE);
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		try {
			//Makes the credits appear
                        credits.draw(0, y);
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		try {
                        //Allows the credits image to be any length
			backgroundMusic.setVolume(((Options) sbg.getState(DrTetris.OPTIONS)).getVolume());
			if (clicked) {
				sbg.enterState(DrTetris.MAIN_MENU);
			}
			if (y <= 600 - credits.getHeight()) {
				y = 600 - credits.getHeight();
			} else {
				y -= delta * Config.CREDITSSPEED;
			}
		} catch (Exception e) {
			sbg.addState(new ErrorReport(DrTetris.ERR_REPORT, e));
			sbg.enterState(DrTetris.ERR_REPORT);
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		try {
			backgroundMusic.loop(1F, ((Options) sbg.getState(DrTetris.OPTIONS)).getVolume());
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
		clicked = true;
	}

	@Override
	public void mousePressed(int i, int i1, int i2) {
	}

	@Override
	public void mouseReleased(int i, int i1, int i2) {
	}

	@Override
	public void mouseMoved(int i, int i1, int i2, int i3) {
	}

	@Override
	public void mouseDragged(int i, int i1, int i2, int i3) {
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
		clicked = true;
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
